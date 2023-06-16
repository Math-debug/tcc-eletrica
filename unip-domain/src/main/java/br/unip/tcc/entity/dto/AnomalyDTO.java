package br.unip.tcc.entity.dto;

import java.io.Serializable;
import java.time.Instant;

import br.unip.tcc.entity.AnomalyStatusEnum;
import br.unip.tcc.entity.UrgenceTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class AnomalyDTO implements Serializable{
	
	private static final long serialVersionUID = -4936570985134364206L;
	
	private Long anomalyid;
	private KeepAliveDTO keepAlive;
	private AnomalyTypeDTO anomalytype;
	private UrgenceTypeEnum urgeceid;
	String description;
	private AnomalyStatusEnum statusid;
	private Instant createdat;
	private Instant lasttreatment;
	private Instant normalizedat;
	private Instant closedat;
}
