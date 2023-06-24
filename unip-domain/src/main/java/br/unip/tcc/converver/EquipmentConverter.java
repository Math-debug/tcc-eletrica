package br.unip.tcc.converver;

import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.SyncEquipmentConfig;
import br.unip.tcc.entity.dto.EquipmentDTO;

public class EquipmentConverter {
	
	public static Equipment convertTo (EquipmentDTO dto) {
		Equipment entity = new Equipment();
		entity.setEquipmentid(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setEquipmentType(dto.getEquipmentType() == null? null : EquipmentTypeConverter.convertTo(dto.getEquipmentType()));
		entity.setName(dto.getName());
		entity.setNominalCurrent(dto.getNominalCurrent());
		entity.setVoltage(dto.getVoltage());
		entity.setActive(dto.getActive());
		entity.setVerify(dto.getVerify());
		return entity;
	}
	
	public static Equipment convertToSaveConfigs (EquipmentDTO dto, AnomalyConfig config) {
		Equipment entity = new Equipment();
		entity.setEquipmentid(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setEquipmentType(dto.getEquipmentType() == null? null : EquipmentTypeConverter.convertTo(dto.getEquipmentType()));
		entity.setName(dto.getName());
		entity.setNominalCurrent(dto.getNominalCurrent());
		entity.setVoltage(dto.getVoltage());
		entity.setActive(dto.getActive());
		entity.setVerify(dto.getVerify());
		entity.setAnomalyconfig(config);
		return entity;
	}
	
	public static EquipmentDTO convertTo (Equipment entity) {
		EquipmentDTO dto = new EquipmentDTO();
		dto.setId(entity.getEquipmentid());
		dto.setDescription(entity.getDescription());
		dto.setEquipmentType(EquipmentTypeConverter.convertTo(entity.getEquipmentType()));
		dto.setName(entity.getName());
		dto.setNominalCurrent(entity.getNominalCurrent());
		dto.setVoltage(entity.getVoltage());
		dto.setActive(entity.getActive() == null ? false : entity.getActive());
		dto.setVerify(entity.getVerify());
		return dto;
	}
	
	public static EquipmentDTO convertTo (Equipment entity, SyncEquipmentConfig config) {
		EquipmentDTO dto = new EquipmentDTO();
		dto.setId(entity.getEquipmentid());
		dto.setDescription(entity.getDescription());
		dto.setEquipmentType(EquipmentTypeConverter.convertTo(entity.getEquipmentType()));
		dto.setName(entity.getName());
		dto.setNominalCurrent(entity.getNominalCurrent());
		dto.setVoltage(entity.getVoltage());
		dto.setActive(entity.getActive());
		dto.setSyncronized(config.getActive());
		dto.setIp(config.getIp());
		dto.setVerify(entity.getVerify());
		return dto;
	}

}
