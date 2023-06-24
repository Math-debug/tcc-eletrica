package br.unip.tcc.anomaly.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.AnomalyConverter;
import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.AnomalyStatusEnum;
import br.unip.tcc.entity.dto.AnomalyDTO;
import br.unip.tcc.repository.AnomalyRepository;

@Service
public class AnomalyService {
	@Autowired
	AnomalyRepository anomalyRepository;
	
	public List<Anomaly> findAll(){
		return anomalyRepository.findAll();
	}
	public Anomaly findById(Long id) {
		return anomalyRepository.findById(id).get();
	}
	public Anomaly save (AnomalyDTO dto) {
		return anomalyRepository.save(AnomalyConverter.convertTo(dto));
	}
	public Anomaly normalized (Anomaly entity) {
		entity.setStatusid(AnomalyStatusEnum.NORMALIZED);
		entity.setNormalizedat(Instant.now());
		return anomalyRepository.save(entity);
	}
	public Anomaly closed (Anomaly entity) {
		entity.setStatusid(AnomalyStatusEnum.CLOSED);
		entity.setClosedat(Instant.now());
		return anomalyRepository.save(entity);
	}
	public Anomaly findByValidStatusAndEquipmentEquipmentid(Long id) {
		return anomalyRepository.findByValidStatusAndEquipmentEquipmentid(id);
	}
}
