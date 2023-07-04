package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.ReportList;

@Repository
public interface ReportListRepository extends JpaRepository<ReportList, Long>{
	
}
