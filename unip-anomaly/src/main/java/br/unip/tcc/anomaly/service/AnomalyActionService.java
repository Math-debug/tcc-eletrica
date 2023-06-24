package br.unip.tcc.anomaly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.AnomalyActionConverter;
import br.unip.tcc.entity.AnomalyAction;
import br.unip.tcc.entity.dto.AnomalyActionDTO;
import br.unip.tcc.repository.AnomalyActionRepository;

@Service
public class AnomalyActionService {
	@Autowired
	AnomalyActionRepository anomalyActionRepository;
	
	public List<AnomalyAction> findAll(){
		return anomalyActionRepository.findAll();
	}
	public AnomalyAction findById(Long id) {
		return anomalyActionRepository.findById(id).get();
	}
	public AnomalyAction save (AnomalyActionDTO dto) {
		return anomalyActionRepository.save(AnomalyActionConverter.convertTo(dto));
	}
}
