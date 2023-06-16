package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.AnomalyConfigHistory;

@Repository
public interface AnomalyConfigHistoryRepository extends JpaRepository<AnomalyConfigHistory, Long>{

}
