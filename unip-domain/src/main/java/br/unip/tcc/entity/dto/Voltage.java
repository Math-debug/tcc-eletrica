package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class Voltage implements Serializable{
	
	private static final long serialVersionUID = 8668014860428191385L;
	
	Double ab;
	Double bc;
	Double ca;
}
