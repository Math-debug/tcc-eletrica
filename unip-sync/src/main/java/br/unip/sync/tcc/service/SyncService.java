package br.unip.sync.tcc.service;

import java.net.NoRouteToHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.SyncEquipmentConfig;
import br.unip.tcc.entity.SyncOfflineEquipment;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;
import br.unip.tcc.repository.SyncBufferRepository;
import br.unip.tcc.repository.SyncEquipmentConfigRepository;
import br.unip.tcc.repository.SyncOfflineEquipmentRepository;

@Service
public class SyncService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);

	@Autowired
	SyncEquipmentConfigRepository syncEquipmentConfigRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private SyncBufferRepository syncBufferRepository;

	@Autowired
	private SyncOfflineEquipmentRepository syncOfflineEquipmentRepository;

	@Scheduled(fixedDelayString = "${sync.time}")
	public void sync() {
		List<SyncEquipmentConfig> config = syncEquipmentConfigRepository.findAll();
		List<SyncBuffer> buffers = syncBufferRepository.findByAttemps();
		config.parallelStream().forEach(equipment -> {
			if (equipment.getActive()) {
				try {
					OkHttpClient client = new OkHttpClient();
					client.setConnectTimeout(10, TimeUnit.SECONDS); // connect timeout
					client.setReadTimeout(10, TimeUnit.SECONDS); // socket timeout

					Request request = new Request.Builder().url("http://" + equipment.getIp()).get()
							.addHeader("cache-control", "no-cache").addHeader("Accept", "application/json; q=0.5")
							.build();
					Response response = null;
					try {
						response = client.newCall(request).execute();
					} catch (NoRouteToHostException e) {
						e.printStackTrace();
						SyncOfflineEquipment syncOfflineEquipment = syncOfflineEquipmentRepository
								.findByEquipmentEquipmentidAndActive(equipment.getEquipment().getEquipmentid(), true);
						if (syncOfflineEquipment == null) {
							SyncOfflineEquipment offline = new SyncOfflineEquipment();
							offline.setActive(true);
							offline.setEquipment(equipment.getEquipment());
							syncOfflineEquipmentRepository.save(offline);
						}
					}
					if (response.code() != 200) {
						SyncOfflineEquipment syncOfflineEquipment = syncOfflineEquipmentRepository
								.findByEquipmentEquipmentidAndActive(equipment.getEquipment().getEquipmentid(), true);
						if (syncOfflineEquipment == null) {
							SyncOfflineEquipment offline = new SyncOfflineEquipment();
							offline.setActive(true);
							offline.setEquipment(equipment.getEquipment());
							syncOfflineEquipmentRepository.save(offline);
						}
					} else {
						SyncOfflineEquipment syncOfflineEquipment = syncOfflineEquipmentRepository
								.findByEquipmentEquipmentidAndActive(equipment.getEquipment().getEquipmentid(), true);
						if (syncOfflineEquipment != null) {
							syncOfflineEquipment.setActive(false);
							syncOfflineEquipmentRepository.save(syncOfflineEquipment);
						}
					}
					String json = response.body().string();
					LOGGER.info(json);
					try {
						ObjectMapper mapper = new ObjectMapper();
						mapper.registerModule(new JavaTimeModule());
						KeepAliveDTO dto = mapper.readValue(json, KeepAliveDTO.class);
						jmsTemplate.convertAndSend("keepAlive", KeepAliveProtoConverter.convertTO(dto).toByteArray());
					} catch (Exception e) {
						SyncBuffer buffer = new SyncBuffer();
						buffer.setData(json);
						buffer.setAttempt(1);
						syncBufferRepository.save(buffer);
						e.printStackTrace();
					}
				} catch (Exception e) {
					// Ignorar TimeOut
				}
			}
		});
		buffers.parallelStream().forEach(attemp -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				KeepAliveDTO dto = mapper.readValue(attemp.getData(), KeepAliveDTO.class);
				dto.setBufferid(attemp.getBufferid());
				ObjectMapper mapper2 = new ObjectMapper();
				mapper2.registerModule(new JavaTimeModule());
				String json = mapper2.writeValueAsString(dto);
				jmsTemplate.convertAndSend("keepAlive", json);
			} catch (Exception e) {
				attemp.setAttempt(attemp.getAttempt() + 1);
				syncBufferRepository.save(attemp);
			}
		});
	}
}
