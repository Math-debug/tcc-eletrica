package br.unip.tcc.anomaly.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.AnomalyActionConverter;
import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.AnomalyAction;
import br.unip.tcc.entity.AnomalyStatusEnum;
import br.unip.tcc.entity.dto.AnomalyActionDTO;
import br.unip.tcc.repository.AnomalyActionRepository;
import br.unip.tcc.repository.AnomalyRepository;
import br.unip.tcc.websocket.WebSocketController;

@Service
public class AnomalyActionService {
	@Autowired
	AnomalyActionRepository anomalyActionRepository;
	
	@Autowired
    AnomalyRepository anomalyRepository;
	
	@Autowired
	WebSocketController webSocket;
	
	public List<AnomalyAction> findAll(){
		return anomalyActionRepository.findAll();
	}
	public AnomalyAction findById(Long id) {
		return anomalyActionRepository.findById(id).get();
	}
	public List<AnomalyAction> findByAnomalyId(Long id) {
        return anomalyActionRepository.findByAnomalyAnomalyid(id);
    }
	public AnomalyAction save (AnomalyActionDTO dto) {
		Anomaly anomaly = anomalyRepository.findById(dto.getAnomaly().getAnomalyid()).get();
		AnomalyAction action = anomalyActionRepository.save(AnomalyActionConverter.convertTo(dto));
		anomaly.setLasttreatment(Instant.now());
		if(!anomaly.getStatusid().equals(AnomalyStatusEnum.CLOSED)) {
			anomaly.setStatusid(AnomalyStatusEnum.TRATED);
		}
		anomalyRepository.save(anomaly);
		webSocket.updateAnomaly(anomaly);
		return action;
	}
}
