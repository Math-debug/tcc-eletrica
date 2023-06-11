package br.unip.tcc.proto.converter;

import com.google.protobuf.InvalidProtocolBufferException;

import br.unip.tcc.entity.dto.Current;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.entity.dto.Voltage;
import br.unip.tcc.proto.KeepAliveProto;
import br.unip.tcc.proto.KeepAliveProto.KeepAlive;

public class KeepAliveProtoConverter {
	
	public static KeepAlive convertTO(KeepAliveDTO dto) {
		KeepAliveProto.Equipment.Builder builderEquipment = KeepAliveProto.Equipment.newBuilder();
		builderEquipment.setId(dto.getEquipment().getId().intValue());
		KeepAliveProto.Current.Builder builderCurrent = KeepAliveProto.Current.newBuilder();
		builderCurrent.setA(dto.getCurrent().getA());
		builderCurrent.setB(dto.getCurrent().getB());
		builderCurrent.setC(dto.getCurrent().getC());
		KeepAliveProto.Voltage.Builder builderVoltage = KeepAliveProto.Voltage.newBuilder();
		builderVoltage.setAb(dto.getVoltage().getAb());
		builderVoltage.setBc(dto.getVoltage().getBc());
		builderVoltage.setCa(dto.getVoltage().getCa());
		KeepAliveProto.KeepAlive.Builder builderKeepAlive = KeepAliveProto.KeepAlive.newBuilder();
		builderKeepAlive.setEquipment(builderEquipment);
		builderKeepAlive.setCurrent(builderCurrent);
		builderKeepAlive.setVoltage(builderVoltage);
		builderKeepAlive.setType(dto.getType());
		return builderKeepAlive.build();
	}
	
	public static KeepAliveDTO convertTO (byte[] bytes) throws InvalidProtocolBufferException {
		KeepAlive proto = KeepAliveProto.KeepAlive.parseFrom(bytes);
		KeepAliveDTO dto = new KeepAliveDTO();
		EquipmentDTO equipment = new EquipmentDTO();
		equipment.setId(new Long(proto.getEquipment().getId()));
		dto.setEquipment(equipment);
		Current current = new Current();
		Voltage voltage = new Voltage();
		current.setA(proto.getCurrent().getA());
		current.setB(proto.getCurrent().getB());
		current.setC(proto.getCurrent().getC());
		voltage.setAb(proto.getVoltage().getAb());
		voltage.setBc(proto.getVoltage().getBc());
		voltage.setCa(proto.getVoltage().getCa());
		dto.setCurrent(current);
		dto.setVoltage(voltage);
		dto.setType(proto.getType());
		return dto;
	}
}
