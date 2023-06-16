package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.AnomalyAction;

@Repository
public interface AnomalyActionRepository extends JpaRepository<AnomalyAction, Long>{

}
