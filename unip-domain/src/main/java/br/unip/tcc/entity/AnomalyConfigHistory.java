package br.unip.tcc.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@Table(name = "anom_anomalyconfighistory")
public class AnomalyConfigHistory extends AnomalyConfigAbstract implements Serializable {

	private static final long serialVersionUID = 7825822735111198219L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long anomalyconfighistoryid;
	@CreationTimestamp
	@Column(name="createdat")
	private Instant createdat;
	private Long equipmentid;
}
