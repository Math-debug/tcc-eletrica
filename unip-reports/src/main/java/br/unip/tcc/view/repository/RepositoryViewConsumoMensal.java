package br.unip.tcc.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.util.MonthsOfYear;
import br.unip.tcc.view.ViewConsumoMensal;

@Repository
public interface RepositoryViewConsumoMensal extends JpaRepository<ViewConsumoMensal, MonthsOfYear>{

}
