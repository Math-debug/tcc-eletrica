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
import javax.persistence.OneToOne;
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
@Table(name = "anom_anomaly")
public class Anomaly implements Serializable{

	private static final long serialVersionUID = 5197064276717531976L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long anomalyid;
	@OneToOne
	@JoinColumn(name = "keepaliveid")
	private KeepAlive keepAlive;
	@ManyToOne
	@JoinColumn(name = "anomalytypeid")
	private AnomalyType anomalytype;
	private UrgenceTypeEnum urgeceid;
	@Column(length = 1000)
	String description;
	private AnomalyStatusEnum statusid;
	private Instant createdat;
	private Instant lasttreatment;
	private Instant normalizedat;
	private Instant closedat;
}
