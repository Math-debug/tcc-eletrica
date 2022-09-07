package br.unip.tcc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="tcc_equipment")
public class Equipment implements Serializable {
	
	private static final long serialVersionUID = -3218983817179028735L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmentid;
	@ManyToOne
	@JoinColumn(name = "equipmenttypeid")
	private EquipmentType equipmentType;
	@Column(name="voltage")
	private Double voltage;
	@Column(name="nominalcurrent")
	private Double nominalCurrent;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
}
