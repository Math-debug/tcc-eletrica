package br.unip.tcc.converver;

import br.unip.tcc.entity.AnomalyAction;
import br.unip.tcc.entity.dto.AnomalyActionDTO;

public class AnomalyActionConverter {
	public static AnomalyAction convertTo( AnomalyActionDTO dto ) {
		AnomalyAction entity = new AnomalyAction();
		entity.setActionid(dto.getActionid());
		entity.setAnomaly(AnomalyConverter.convertTo(dto.getAnomaly()));
		entity.setObservation(dto.getObservation());
		entity.setUser(SysUserConverter.convertTo(dto.getUser()));
		return entity;
	}
	public static AnomalyActionDTO convertTo( AnomalyAction entity ) {
		AnomalyActionDTO dto = new AnomalyActionDTO();
		dto.setActionid(entity.getActionid());
		dto.setAnomaly(AnomalyConverter.convertTo(entity.getAnomaly()));
		dto.setObservation(entity.getObservation());
		dto.setUser(SysUserConverter.convertTo(entity.getUser()));
		return dto;
	}
}
