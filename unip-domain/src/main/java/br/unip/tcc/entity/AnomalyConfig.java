package br.unip.tcc.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@Table(name = "anom_anomalyconfig")
public class AnomalyConfig extends AnomalyConfigAbstract implements Serializable{

	private static final long serialVersionUID = -2334340892522752878L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long anomalyconfigid;
	@CreationTimestamp
	@Column(name="createdat")
	private Instant createdat;
	@OneToOne(cascade = CascadeType.ALL,  mappedBy = "anomalyconfig")
	private Equipment equipment;
}
