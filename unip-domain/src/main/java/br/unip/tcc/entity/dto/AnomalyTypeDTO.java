package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class AnomalyTypeDTO implements Serializable{
	private static final long serialVersionUID = 6553672356139266855L;
	private Long anomalytypeid;
	private String description;
}
