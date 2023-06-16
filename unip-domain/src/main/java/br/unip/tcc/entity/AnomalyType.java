package br.unip.tcc.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "anom_anomalytype")
public class AnomalyType implements Serializable{
	
	private static final long serialVersionUID = -7279204075889148675L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long anomalytypeid;
	private String description;
	@CreationTimestamp
	private Instant createdat;
}
