package br.unip.tcc.converver;

import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.AnomalyConfigHistory;
import br.unip.tcc.entity.dto.AnomalyConfigDTO;

public class AnomalyConfigConverter {
	public static AnomalyConfig convertTo (AnomalyConfigDTO dto) {
		AnomalyConfig entity = new AnomalyConfig();
		entity.setAnomalyconfigid(dto.getAnomalyconfigid() != null ? dto.getAnomalyconfigid() : null);
		entity.setCreatedat(dto.getCreatedat());
		if(entity.getAnomalyconfigid() != null) {
			entity.setEquipment(EquipmentConverter.convertTo(dto.getEquipment()));
		}
		entity.setImax(dto.getImax());
		entity.setImin(dto.getImin());
		entity.setType(dto.getType());
		entity.setVmax(dto.getVmax());
		entity.setVmin(dto.getVmin());
		return entity;
	}
	
	public static AnomalyConfigHistory convertToHistory (AnomalyConfig entity) {
		AnomalyConfigHistory history = new AnomalyConfigHistory();
		history.setEquipmentid(entity.getEquipment().getEquipmentid());
		history.setImax(entity.getImax());
		history.setImin(entity.getImin());
		history.setType(entity.getType());
		history.setVmax(entity.getVmax());
		history.setVmin(entity.getVmin());
		return history;
	}
	
	public static AnomalyConfigDTO convertTo (AnomalyConfig entity) {
		AnomalyConfigDTO dto = new AnomalyConfigDTO();
		dto.setAnomalyconfigid(entity.getAnomalyconfigid());
		dto.setCreatedat(entity.getCreatedat());
		dto.setEquipment(EquipmentConverter.convertTo(entity.getEquipment()));
		dto.setImax(entity.getImax());
		dto.setImin(entity.getImin());
		dto.setType(entity.getType());
		dto.setVmax(entity.getVmax());
		dto.setVmin(entity.getVmin());
		return dto;
	}
}
