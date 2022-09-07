package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class EquipmentDTO implements Serializable{

	private static final long serialVersionUID = -6581767378846452980L;
	
	private Long id;
	private EquipmentTypeDTO equipmentType;
	private Double voltage;
	private Double nominalCurrent;
	private String name;
	private String description;
}
