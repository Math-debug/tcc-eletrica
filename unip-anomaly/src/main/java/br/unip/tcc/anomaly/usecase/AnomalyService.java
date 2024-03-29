package br.unip.tcc.anomaly.usecase;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import br.unip.tcc.converver.AnomalyConverter;
import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.AnomalyStatusEnum;
import br.unip.tcc.entity.UrgenceTypeEnum;
import br.unip.tcc.entity.dto.AnomalyDTO;
import br.unip.tcc.repository.AnomalyRepository;
import br.unip.tcc.outbound.websocket.WebSocketController;

@Service
public class AnomalyService {
	private final AnomalyRepository anomalyRepository;
	private final WebSocketController webSocket;

	public AnomalyService(AnomalyRepository anomalyRepository, WebSocketController webSocket) {
		this.anomalyRepository = anomalyRepository;
		this.webSocket = webSocket;
	}

	public List<Anomaly> findAll(){
		return anomalyRepository.findAll();
	}
	public Anomaly findById(Long id) {
		return anomalyRepository.findById(id).get();
	}
	public Anomaly save (AnomalyDTO dto) {
		Anomaly entity = anomalyRepository.save(AnomalyConverter.convertTo(dto));
		if(dto.getUrgeceid().equals(UrgenceTypeEnum.LOW)) {
			webSocket.newAnomaly(entity);
		}else {
			webSocket.updateAnomaly(entity);
		}
		return entity;
	}
	public Anomaly normalized (Anomaly entity) {
		entity.setStatusid(AnomalyStatusEnum.NORMALIZED);
		entity.setNormalizedat(Instant.now());
		webSocket.updateAnomaly(entity);
		return anomalyRepository.save(entity);
	}
	public Anomaly closed (Anomaly entity) {
		entity.setStatusid(AnomalyStatusEnum.CLOSED);
		entity.setClosedat(Instant.now());
		webSocket.updateAnomaly(entity);
		return anomalyRepository.save(entity);
	}
	public Anomaly findByValidStatusAndEquipmentEquipmentid(Long id) {
		return anomalyRepository.findByValidStatusAndEquipmentEquipmentid(id);
	}
}
