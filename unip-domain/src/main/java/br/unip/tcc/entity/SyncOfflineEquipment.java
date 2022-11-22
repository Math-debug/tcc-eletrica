package br.unip.tcc.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="sync_offlineequip")
public class SyncOfflineEquipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offlineequipid;
	@ManyToOne
	@JoinColumn(name = "equipmentid")
	Equipment equipment;
	@CreationTimestamp
	@Column(name="createdat")
	private Instant createdAt;
	@Column(name = "active")
	private Boolean active;
	@UpdateTimestamp
	@Column(name="updateat")
	private Instant updateAt;
}
