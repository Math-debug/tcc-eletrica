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

	public static Double calculateCurrent(Current current, String type) {

		if (type.equals(KeepAliveDTO.TRIFASICO)) {
			if (current.getA() != null && current.getA() > 0) {
				return current.getA();
			} else if (current.getB() != null && current.getB() > 0) {
				return current.getB();
			} else {
				return current.getC();
			}
		} else if(type.equals(KeepAliveDTO.BIFASICO)) {
			if (current.getA() != null && current.getA() > 0) {
				return current.getA();
			} else {
				return current.getB();
			}
		} else {
			return current.getA();
		}
	}

	public static Double calculateVoltage(Voltage voltage, String type) {
		if (type.equals(KeepAliveDTO.TRIFASICO)) {
			if (voltage.getAb() != null && voltage.getAb() > 0) {
				return voltage.getAb();
			} else if (voltage.getBc() != null && voltage.getBc() > 0) {
				return voltage.getBc();
			} else {
				return voltage.getCa();
			}
		} else {
			return voltage.getAb();
		}
	}
}
