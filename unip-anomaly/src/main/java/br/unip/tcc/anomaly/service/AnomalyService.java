package br.unip.tcc.anomaly.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.AnomalyConverter;
import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.AnomalyStatusEnum;
import br.unip.tcc.entity.UrgenceTypeEnum;
import br.unip.tcc.entity.dto.AnomalyDTO;
import br.unip.tcc.repository.AnomalyRepository;
import br.unip.tcc.websocket.WebSocketController;

@Service
public class AnomalyService {
	@Autowired
	AnomalyRepository anomalyRepository;
	
	@Autowired
	WebSocketController webSocket;
	
	public List<Anomaly> findAll(){
		return anomalyRepository.findAll();
	}
	public Anomaly findById(Long id) {
		return anomalyRepository.findById(id).get();
	}
	public Anomaly save (AnomalyDTO dto) {
		Anomaly entity = AnomalyConverter.convertTo(dto);
		if(dto.getUrgeceid().equals(UrgenceTypeEnum.LOW)) {
			webSocket.newAnomaly(entity);
		}else {
			webSocket.updateAnomaly(entity);
		}
		return anomalyRepository.save(entity);
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
