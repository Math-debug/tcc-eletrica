package br.unip.tcc.entity.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.*;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeepAliveDTO implements Serializable{
	
	public static final String MONOFASICO = "M";
	public static final String BIFASICO = "B";
	public static final String TRIFASICO = "T";
	
	private static final long serialVersionUID = -2989840500215423036L;
	
	private Long id;
	private Float voltageEf;
	private Float currentEf;
	private VoltageDTO voltage;
	private CurrentDTO current;
	private Instant createdAt;
	private EquipmentDTO equipment;
	private Long bufferid;
	private String type;
}
