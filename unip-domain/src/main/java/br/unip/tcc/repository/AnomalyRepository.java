package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.Anomaly;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long>{

}
