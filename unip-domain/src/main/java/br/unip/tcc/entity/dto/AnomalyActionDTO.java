package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class AnomalyActionDTO implements Serializable{

	private static final long serialVersionUID = 4104139498671002690L;
	private Long actionid;
	private SysUserDTO userid;
	private String observation;
	private AnomalyDTO anomaly;
}
