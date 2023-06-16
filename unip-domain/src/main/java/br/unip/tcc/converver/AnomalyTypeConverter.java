package br.unip.tcc.converver;

import br.unip.tcc.entity.AnomalyType;
import br.unip.tcc.entity.dto.AnomalyTypeDTO;

public class AnomalyTypeConverter {

	public static AnomalyType convertTo (AnomalyTypeDTO dto) {
		AnomalyType entity = new AnomalyType();
		entity.setAnomalytypeid(dto.getAnomalytypeid());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	
	public static AnomalyTypeDTO convertTo(AnomalyType entity) {
		AnomalyTypeDTO dto = new AnomalyTypeDTO();
		dto.setAnomalytypeid(entity.getAnomalytypeid());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
