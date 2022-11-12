package br.unip.tcc.converver;

import br.unip.tcc.entity.EquipmentType;
import br.unip.tcc.entity.dto.EquipmentTypeDTO;

public class EquipmentTypeConverter {
	public static EquipmentType convertTo (EquipmentTypeDTO dto) {
		EquipmentType entity = new EquipmentType();
		entity.setEquipmenttypeid(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setName(dto.getName());
		return entity;
	}
	
	public static EquipmentTypeDTO convertTo (EquipmentType entity) {
		EquipmentTypeDTO dto = new EquipmentTypeDTO();
		dto.setId(entity.getEquipmenttypeid());
		dto.setDescription(entity.getDescription());
		dto.setName(entity.getName());
		return dto;
	}
}
