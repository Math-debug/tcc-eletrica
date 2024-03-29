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
@Table(name="tcc_keepalive")
public class KeepAlive implements Serializable {

	private static final long serialVersionUID = 1192110678771263936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long keepaliveid;
	@ManyToOne
	@JoinColumn(name = "equipmentid")
	private Equipment equipment;
	@Column(name="voltage")
	private Float voltage;
	@Column(name="current")
	private Float current;
	@Column(name="createdat")
	private Instant createdAt;
}
