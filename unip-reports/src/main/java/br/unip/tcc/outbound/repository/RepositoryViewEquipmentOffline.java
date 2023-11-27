package br.unip.tcc.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.model.dto.view.ViewEquipmentOffline;

@Repository
public interface RepositoryViewEquipmentOffline extends JpaRepository<ViewEquipmentOffline, Long>{

}
