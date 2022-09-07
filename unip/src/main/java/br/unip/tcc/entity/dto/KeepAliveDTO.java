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

	private static final long serialVersionUID = -2989840500215423036L;
	
	private Long id;
	private Double voltage;
	private Double current;
	private Double fp;
	private Double potenciaativa;
	private Double potenciareativa;
	private Double potenciaaparente;
	private Instant createdAt;
	private EquipmentDTO equipment;
}
