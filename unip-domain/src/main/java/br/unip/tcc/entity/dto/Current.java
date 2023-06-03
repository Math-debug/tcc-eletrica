package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class Current implements Serializable{
	
	private static final long serialVersionUID = 2704466153977437720L;
	Double current1;
	Double current2;
	Double current3;
}
