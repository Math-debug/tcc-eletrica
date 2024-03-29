package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.*;

@Getter 
@Setter 
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO implements Serializable{

	private static final long serialVersionUID = -6581767378846452980L;
	
	private Long id;
	private EquipmentTypeDTO equipmentType;
	private Double voltage;
	private Double nominalCurrent;
	private String name;
	private String description;
	private Boolean active;
	private Boolean syncronized;
	private String ip;
	private Boolean verify;
}
