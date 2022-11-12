package br.unip.tcc.converver;

import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.dto.KeepAliveDTO;

public class KeepAliveConverter {
	public static KeepAlive convertTo(KeepAliveDTO dto) {
		KeepAlive entity = new KeepAlive();
		entity.setKeepaliveid(dto.getId());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setCurrent(dto.getCurrent());
		entity.setFp(dto.getFp());
		entity.setPotenciaaparente(dto.getPotenciaaparente());
		entity.setPotenciaativa(dto.getPotenciaativa());
		entity.setPotenciareativa(dto.getPotenciareativa());
		entity.setEquipment(EquipmentConverter.convertTo(dto.getEquipment()));
		entity.setVoltage(dto.getVoltage());
		return entity;
	}
	
	public static KeepAliveDTO convertTo(KeepAlive entity) {
		KeepAliveDTO dto = new KeepAliveDTO();
		dto.setId(entity.getKeepaliveid());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setCurrent(entity.getCurrent());
		dto.setFp(entity.getFp());
		dto.setPotenciaaparente(entity.getPotenciaaparente());
		dto.setPotenciaativa(entity.getPotenciaativa());
		dto.setPotenciareativa(entity.getPotenciareativa());
		dto.setEquipment(EquipmentConverter.convertTo(entity.getEquipment()));
		return dto;
	}
}
