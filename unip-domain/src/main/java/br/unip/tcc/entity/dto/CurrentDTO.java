package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class CurrentDTO implements Serializable{
	
	private static final long serialVersionUID = 2704466153977437720L;
	Float a;
	Float b;
	Float c;
}
