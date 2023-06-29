package br.unip.tcc.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.unip.tcc.util.MonthsOfYear;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "v_consumo_mensal") 
public class ViewConsumoMensal {
	Double consumo;
	@Id
	MonthsOfYear mes;
}
