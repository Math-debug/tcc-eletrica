package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.*;

@Getter 
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTypeDTO implements Serializable{
	
	private static final long serialVersionUID = -8342057755105631069L;
	
	private Long id;
	private String name;
	private String description;
}
