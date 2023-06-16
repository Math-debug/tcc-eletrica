package br.unip.tcc.converver;

import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.dto.AnomalyConfigDTO;

public class AnomalyConfigConverter {
	public static AnomalyConfig converTo (AnomalyConfigDTO dto) {
		AnomalyConfig entity = new AnomalyConfig();
		entity.setAnomalyconfigid(dto.getAnomalyconfigid());
		entity.setCreatedat(dto.getCreatedat());
		entity.setEquipmentid(EquipmentConverter.convertTo(dto.getEquipmentid()));
		entity.setImax(dto.getImax());
		entity.setImin(dto.getImin());
		entity.setType(dto.getType());
		entity.setVmax(dto.getVmax());
		entity.setVmin(dto.getVmin());
		return entity;
	}
	
	public static AnomalyConfigDTO converTo (AnomalyConfig entity) {
		AnomalyConfigDTO dto = new AnomalyConfigDTO();
		dto.setAnomalyconfigid(entity.getAnomalyconfigid());
		dto.setCreatedat(entity.getCreatedat());
		dto.setEquipmentid(EquipmentConverter.convertTo(entity.getEquipmentid()));
		dto.setImax(entity.getImax());
		dto.setImin(entity.getImin());
		dto.setType(entity.getType());
		dto.setVmax(entity.getVmax());
		dto.setVmin(entity.getVmin());
		return dto;
	}
}
