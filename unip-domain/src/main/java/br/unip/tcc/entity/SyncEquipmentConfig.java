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
@Table(name="sync_equipmentconfig")
public class SyncEquipmentConfig implements Serializable{
	
	private static final long serialVersionUID = -355122670882531902L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmentconfigid;
	@Column(name="active")
	private Boolean active;
	@ManyToOne
	@JoinColumn(name = "equipmentid")
	private Equipment equipment;
	@Column(name="ip")
	private String ip;
}
