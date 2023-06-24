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
@Table(name = "anom_action")
public class AnomalyAction implements Serializable {
	private static final long serialVersionUID = -2375516340939743515L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long actionid;
	@ManyToOne
	@JoinColumn(name = "userid")
	SysUser user;
	@CreationTimestamp
	private Instant createdat;
	@Column(length = 1000)
	private String observation;
	@ManyToOne
	@JoinColumn(name = "anomalyid")
	private Anomaly anomaly;
}
