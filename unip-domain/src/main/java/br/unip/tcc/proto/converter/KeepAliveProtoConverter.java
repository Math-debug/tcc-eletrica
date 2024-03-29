package br.unip.tcc.proto.converter;

import com.google.protobuf.InvalidProtocolBufferException;

import br.unip.tcc.entity.dto.CurrentDTO;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.entity.dto.VoltageDTO;
import br.unip.tcc.proto.KeepAliveProto;
import br.unip.tcc.proto.KeepAliveProto.KeepAlive;

import java.util.Optional;

public class KeepAliveProtoConverter {
	
	public static KeepAlive convertTO(KeepAliveDTO dto) {
		KeepAliveProto.Equipment.Builder builderEquipment = KeepAliveProto.Equipment.newBuilder();
		builderEquipment.setId(dto.getEquipment().getId().intValue());
		KeepAliveProto.Current.Builder builderCurrent = KeepAliveProto.Current.newBuilder();
		builderCurrent.setA(dto.getCurrent().getA());
		builderCurrent.setB(dto.getCurrent().getB() != null ? dto.getCurrent().getB():dto.getCurrent().getA());
		builderCurrent.setC(dto.getCurrent().getC() != null ? dto.getCurrent().getC():dto.getCurrent().getA());
		KeepAliveProto.Voltage.Builder builderVoltage = KeepAliveProto.Voltage.newBuilder();
		builderVoltage.setAb(dto.getVoltage().getAb());
		builderVoltage.setBc(dto.getVoltage().getBc()!= null ? dto.getVoltage().getBc(): dto.getVoltage().getAb());
		builderVoltage.setCa(dto.getVoltage().getCa()!= null ? dto.getVoltage().getCa(): dto.getVoltage().getAb());
		KeepAliveProto.KeepAlive.Builder builderKeepAlive = KeepAliveProto.KeepAlive.newBuilder();
		builderKeepAlive.setEquipment(builderEquipment);
		builderKeepAlive.setCurrent(builderCurrent);
		builderKeepAlive.setVoltage(builderVoltage);
		builderKeepAlive.setType(dto.getType());
		if(dto.getId() != null) {
			builderKeepAlive.setId(dto.getId().intValue());
		}
		return builderKeepAlive.build();
	}
	
	public static KeepAliveDTO convertTO (byte[] bytes) throws InvalidProtocolBufferException {
		KeepAlive proto = KeepAliveProto.KeepAlive.parseFrom(bytes);
		KeepAliveDTO dto = new KeepAliveDTO();
		EquipmentDTO equipment = new EquipmentDTO();
		equipment.setId(new Long(proto.getEquipment().getId()));
		dto.setEquipment(equipment);
		CurrentDTO current = new CurrentDTO();
		VoltageDTO voltage = new VoltageDTO();
		current.setA(proto.getCurrent().getA());
		current.setB(proto.getCurrent().getB());
		current.setC(proto.getCurrent().getC());
		voltage.setAb(proto.getVoltage().getAb());
		voltage.setBc(proto.getVoltage().getBc());
		voltage.setCa(proto.getVoltage().getCa());
		dto.setCurrent(current);
		dto.setVoltage(voltage);
		dto.setType(proto.getType());
		if(proto.getId() > 0) {
			dto.setId(new Long(proto.getId()));
		}
		return dto;
	}
}
