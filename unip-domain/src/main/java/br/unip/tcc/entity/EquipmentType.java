package br.unip.tcc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="tcc_equipmenttype")
public class EquipmentType implements Serializable {

	private static final long serialVersionUID = -268126207559888258L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmenttypeid;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
}
