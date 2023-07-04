package br.unip.tcc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "report_list")
public class ReportList implements Serializable{
	
	private static final long serialVersionUID = -154497117952603729L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportid;
	private String title;
	private String query;
	private String parametro;
}
