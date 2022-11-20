package br.unip.sync.tcc.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.SyncEquipmentConfig;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.repository.SyncBufferRepository;
import br.unip.tcc.repository.SyncEquipmentConfigRepository;

@Service
public class SyncService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);

	@Autowired
	SyncEquipmentConfigRepository syncEquipmentConfigRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private SyncBufferRepository syncBufferRepository;

	public void sync() {

		final long time = 5000; // a cada X ms
		Timer timer = new Timer();
		TimerTask tarefa = new TimerTask() {
			public void run() {
				List<SyncEquipmentConfig> config = syncEquipmentConfigRepository.findAll();
				List<SyncBuffer> buffers = syncBufferRepository.findByAttemps();
				
				for (SyncEquipmentConfig equipment : config) {
					if (equipment.getActive()) {
						try {
							OkHttpClient client = new OkHttpClient();
							client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
							client.setReadTimeout(15, TimeUnit.SECONDS); // socket timeout

							Request request = new Request.Builder().url("http://" + equipment.getIp()).get()
									.addHeader("cache-control", "no-cache")
									.addHeader("Accept", "application/json; q=0.5").build();

							Response response = client.newCall(request).execute();
							String json = response.body().string();
							LOGGER.info(json);
							try {
								ObjectMapper mapper = new ObjectMapper();
								mapper.registerModule(new JavaTimeModule());
								mapper.readValue(json, KeepAliveDTO.class);
								jmsTemplate.convertAndSend("keepAlive", json);
							} catch (Exception e) {
								SyncBuffer buffer = new SyncBuffer();
								buffer.setEquipment(equipment.getEquipment());
								buffer.setData(json);
								buffer.setAttempt(1);
								syncBufferRepository.save(buffer);
								e.printStackTrace();
							}
						} catch (Exception e) {
							
						}
					}
				}
				for(SyncBuffer attemp : buffers) {
					try {
					ObjectMapper mapper = new ObjectMapper();
					mapper.registerModule(new JavaTimeModule());
					mapper.readValue(attemp.getData(), KeepAliveDTO.class);
					jmsTemplate.convertAndSend("keepAlive", attemp.getData());
					}catch (Exception e) {
						attemp.setAttempt(attemp.getAttempt() +1);
						syncBufferRepository.save(attemp);
					}
				}
			}
		};
		timer.scheduleAtFixedRate(tarefa, time, time);
	}
}
