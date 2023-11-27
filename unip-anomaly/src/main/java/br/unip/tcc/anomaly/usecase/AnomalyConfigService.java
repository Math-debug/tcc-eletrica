package br.unip.tcc.anomaly.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.unip.tcc.converver.AnomalyConfigConverter;
import br.unip.tcc.converver.EquipmentConverter;
import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.dto.AnomalyConfigDTO;
import br.unip.tcc.repository.AnomalyConfigHistoryRepository;
import br.unip.tcc.repository.AnomalyConfigRepository;
import br.unip.tcc.repository.EquipmentRepository;

@Service
public class AnomalyConfigService {
	private final AnomalyConfigRepository anomalyConfigRepository;
	private final EquipmentRepository equipmentRepository;
	private final AnomalyConfigHistoryRepository anomalyConfigHistoryRepository;

	public AnomalyConfigService(AnomalyConfigRepository anomalyConfigRepository, EquipmentRepository equipmentRepository, AnomalyConfigHistoryRepository anomalyConfigHistoryRepository) {
		this.anomalyConfigRepository = anomalyConfigRepository;
		this.equipmentRepository = equipmentRepository;
		this.anomalyConfigHistoryRepository = anomalyConfigHistoryRepository;
	}

	public List<AnomalyConfig> findAll(){
		return anomalyConfigRepository.findAll();
	}
	public AnomalyConfig findById(Long id) {
		return anomalyConfigRepository.findById(id).get();
	}
	public AnomalyConfig findByEquipementId(Long id) {
		return anomalyConfigRepository.findByEquipmentEquipmentid(id);
	}
	public AnomalyConfig save (AnomalyConfigDTO dto) {
		Equipment now = equipmentRepository.findById(dto.getEquipment().getId()).get();
		Equipment equipment = now;
		equipment.setVerify(dto.getEquipment().getVerify() != null ? dto.getEquipment().getVerify() : false);
		dto.setEquipment(EquipmentConverter.convertTo(now));
		AnomalyConfig config =  anomalyConfigRepository.save(AnomalyConfigConverter.convertTo(dto));
		equipment.setAnomalyconfig(config);
		config.setEquipment(equipment);
		anomalyConfigHistoryRepository.save(AnomalyConfigConverter.convertToHistory(config));
		equipmentRepository.save(equipment);
		return config;
	}
}
