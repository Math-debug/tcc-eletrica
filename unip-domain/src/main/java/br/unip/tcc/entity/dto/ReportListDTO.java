package br.unip.tcc.entity.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReportListDTO {
	private Long reportid;
	private String title;
	private String query;
	private String parametro;
}
