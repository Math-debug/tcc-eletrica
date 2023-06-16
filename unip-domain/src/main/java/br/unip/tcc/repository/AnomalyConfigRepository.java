package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.AnomalyConfig;

@Repository
public interface AnomalyConfigRepository extends JpaRepository<AnomalyConfig, Long>{

}
