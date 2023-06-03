package br.unip.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.tcc.entity.SyncEquipmentConfig;

public interface SyncEquipmentConfigRepository extends JpaRepository<SyncEquipmentConfig,Long>{

	List<SyncEquipmentConfig> findByEquipmentEquipmentid(Long id);
}
