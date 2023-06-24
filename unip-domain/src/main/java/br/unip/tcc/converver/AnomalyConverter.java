package br.unip.tcc.converver;

import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.dto.AnomalyDTO;

public class AnomalyConverter {
	public static Anomaly convertTo (AnomalyDTO dto) {
		Anomaly entity = new Anomaly();
		entity.setAnomalyid(dto.getAnomalyid());
		entity.setAnomalytype(AnomalyTypeConverter.convertTo(dto.getAnomalytype()));
		entity.setClosedat(dto.getClosedat());
		entity.setCreatedat(dto.getCreatedat());
		entity.setDescription(dto.getDescription());
		entity.setKeepAlive(KeepAliveConverter.converToAnomaly(dto.getKeepAlive()));
		entity.setLasttreatment(dto.getLasttreatment());
		entity.setNormalizedat(dto.getNormalizedat());
		entity.setStatusid(dto.getStatusid());
		entity.setUrgeceid(dto.getUrgeceid());
		
		return entity;
	}
	
	public static AnomalyDTO convertTo (Anomaly entity) {
		AnomalyDTO dto = new AnomalyDTO();
		dto.setAnomalyid(entity.getAnomalyid());
		dto.setAnomalytype(AnomalyTypeConverter.convertTo(entity.getAnomalytype()));
		dto.setClosedat(entity.getClosedat());
		dto.setCreatedat(entity.getCreatedat());
		dto.setDescription(entity.getDescription());
		dto.setKeepAlive(KeepAliveConverter.convertTo(entity.getKeepAlive()));
		dto.setLasttreatment(entity.getLasttreatment());
		dto.setNormalizedat(entity.getNormalizedat());
		dto.setStatusid(entity.getStatusid());
		dto.setUrgeceid(entity.getUrgeceid());
		
		return dto;
	}
}
