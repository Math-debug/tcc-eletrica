package br.unip.tcc.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.tcc.view.ViewEquipmentOffline;

@Repository
public interface RepositoryViewEquipmentOffline extends JpaRepository<ViewEquipmentOffline, Long>{

}
