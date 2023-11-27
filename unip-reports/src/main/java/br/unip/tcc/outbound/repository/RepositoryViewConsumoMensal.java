package br.unip.tcc.outbound.repository;

import br.unip.tcc.model.dto.view.ViewConsumoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.util.MonthsOfYear;

@Repository
public interface RepositoryViewConsumoMensal extends JpaRepository<ViewConsumoMensal, MonthsOfYear>{

}
