package br.unip.tcc.entity;

import java.io.Serializable;
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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="sync_buffer")
public class SyncBuffer implements Serializable{
	
	private static final long serialVersionUID = -8511083758300064601L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bufferid;
	@ManyToOne
	@JoinColumn(name = "equipmentid")
	private Equipment equipment;
	@CreationTimestamp
	@Column(name="createdat")
	private Instant createdAt;
	@Column(name="data")
	private String data;
	@Column(name="attempt")
	private Integer attempt;
}
