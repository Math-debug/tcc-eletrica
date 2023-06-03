package br.unip.tcc.entity.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class KeepAliveDTO implements Serializable{
	
	public static final Integer MONOFASICO = 1;
	public static final Integer BIFASICO = 2;
	public static final Integer TRIFASICO = 3;
	
	private static final long serialVersionUID = -2989840500215423036L;
	
	private Long id;
	private Double voltageEf;
	private Double currentEf;
	private Voltage voltage;
	private Current current;
	private Instant createdAt;
	private EquipmentDTO equipment;
	private Long bufferid;
	private Integer type;
}
