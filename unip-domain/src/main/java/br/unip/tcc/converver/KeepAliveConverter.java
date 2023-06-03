package br.unip.tcc.converver;

import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.dto.Current;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.entity.dto.Voltage;

public class KeepAliveConverter {
	public static KeepAlive convertTo(KeepAliveDTO dto) {
		KeepAlive entity = new KeepAlive();
		entity.setKeepaliveid(dto.getId());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setCurrent(calculateCurrent(dto.getCurrent(), dto.getType()));
		entity.setEquipment(EquipmentConverter.convertTo(dto.getEquipment()));
		entity.setVoltage(calculateVoltage(dto.getVoltage(), dto.getType()));
		return entity;
	}

	public static KeepAliveDTO convertTo(KeepAlive entity) {
		KeepAliveDTO dto = new KeepAliveDTO();
		dto.setId(entity.getKeepaliveid());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setCurrentEf(entity.getCurrent());
		dto.setVoltageEf(entity.getVoltage());
		dto.setEquipment(EquipmentConverter.convertTo(entity.getEquipment()));
		return dto;
	}

	public static Double calculateCurrent(Current current, Integer type) {

		if (type.equals(KeepAliveDTO.TRIFASICO)) {
			if (current.getCurrent1() != null && current.getCurrent1() > 0) {
				return current.getCurrent1();
			} else if (current.getCurrent2() != null && current.getCurrent2() > 0) {
				return current.getCurrent2();
			} else {
				return current.getCurrent3();
			}
		} else if(type.equals(KeepAliveDTO.BIFASICO)) {
			if (current.getCurrent1() != null && current.getCurrent1() > 0) {
				return current.getCurrent1();
			} else {
				return current.getCurrent2();
			}
		} else {
			return current.getCurrent1();
		}
	}

	public static Double calculateVoltage(Voltage voltage, Integer type) {
		if (type.equals(KeepAliveDTO.TRIFASICO)) {
			if (voltage.getFase1fase2() != null && voltage.getFase1fase2() > 0) {
				return voltage.getFase1fase2();
			} else if (voltage.getFase2fase3() != null && voltage.getFase2fase3() > 0) {
				return voltage.getFase2fase3();
			} else {
				return voltage.getFase3fase1();
			}
		} else {
			return voltage.getFase1fase2();
		}
	}
}
