package br.unip.tcc.entity;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@MappedSuperclass
public abstract class AnomalyConfigAbstract {
	private Float vmax;
	private Float vmin;
	private Float imax;
	private Float imin;
	private NetWorkTypeEnum type;
}
