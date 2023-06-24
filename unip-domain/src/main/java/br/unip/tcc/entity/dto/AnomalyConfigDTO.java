package br.unip.tcc.entity.dto;

import java.io.Serializable;
import java.time.Instant;

import br.unip.tcc.entity.NetWorkTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class AnomalyConfigDTO implements Serializable{

	private static final long serialVersionUID = -8948060506689958813L;
	
	private Long anomalyconfigid;
	private Instant createdat;
	private EquipmentDTO equipment;
	private Float vmax;
	private Float vmin;
	private Float imax;
	private Float imin;
	private NetWorkTypeEnum type;
}
