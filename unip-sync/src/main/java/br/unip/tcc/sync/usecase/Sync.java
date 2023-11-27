package br.unip.tcc.sync.usecase;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.SyncEquipmentConfig;
import br.unip.tcc.entity.SyncOfflineEquipment;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.entity.dto.KeepAliveEvent;
import br.unip.tcc.repository.SyncBufferRepository;
import br.unip.tcc.repository.SyncEquipmentConfigRepository;
import br.unip.tcc.repository.SyncOfflineEquipmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Sync {
	private final SyncEquipmentConfigRepository syncEquipmentConfigRepository;

	private final ApplicationEventPublisher applicationEventPublisher;

	private final SyncBufferRepository syncBufferRepository;

	private final SyncOfflineEquipmentRepository syncOfflineEquipmentRepository;

	private final SyncBufferUS syncBuffer;

	public Sync(SyncEquipmentConfigRepository syncEquipmentConfigRepository, ApplicationEventPublisher applicationEventPublisher, SyncBufferRepository syncBufferRepository, SyncOfflineEquipmentRepository syncOfflineEquipmentRepository, SyncBufferUS syncBuffer) {
		this.syncEquipmentConfigRepository = syncEquipmentConfigRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.syncBufferRepository = syncBufferRepository;
		this.syncOfflineEquipmentRepository = syncOfflineEquipmentRepository;
		this.syncBuffer = syncBuffer;
	}

	@Scheduled(fixedDelayString = "${sync.time}")
	public void execute() {
		List<SyncEquipmentConfig> config = syncEquipmentConfigRepository.findAll();
		config.parallelStream().forEach(equipment -> {
			if (equipment.getActive()) {
				try {
					OkHttpClient client = new OkHttpClient();
					client.setConnectTimeout(10, TimeUnit.SECONDS); // connect timeout
					client.setReadTimeout(5000, TimeUnit.MILLISECONDS); // socket timeout

					Request request = new Request.Builder().url("http://" + equipment.getIp()).get()
							.addHeader("cache-control", "no-cache").addHeader("Accept", "application/json; text/plain; q=0.5; charset=utf-8")
							.build();
					Response response = null;
					try {
						response = client.newCall(request).execute();
					} catch (NoRouteToHostException e) {
						log.error("dispositivo não localizado em: {}",equipment.getIp());
						SyncOfflineEquipment syncOfflineEquipment = syncOfflineEquipmentRepository
								.findByEquipmentEquipmentidAndActive(equipment.getEquipment().getEquipmentid(), true);
						if (syncOfflineEquipment == null) {
							SyncOfflineEquipment offline = new SyncOfflineEquipment();
							offline.setActive(true);
							offline.setEquipment(equipment.getEquipment());
							syncOfflineEquipmentRepository.save(offline);
						}
					} catch (SocketTimeoutException e2) {
						return;
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
					log.info(json);
					try {
						ObjectMapper mapper = new ObjectMapper();
						mapper.registerModule(new JavaTimeModule());
						KeepAliveDTO dto = mapper.readValue(json, KeepAliveDTO.class);
						applicationEventPublisher.publishEvent(new KeepAliveEvent(dto));
					} catch (Exception e) {
						SyncBuffer buffer = new SyncBuffer();
						buffer.setData(json);
						buffer.setAttempt(1);
						syncBufferRepository.save(buffer);
						log.error(e.getMessage(),e);
					}
				} catch (Exception e) {
					// Ignorar TimeOut
				}
			}
		});
		syncBuffer.execute();
	}
}
