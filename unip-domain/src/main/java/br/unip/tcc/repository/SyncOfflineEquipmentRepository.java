package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.SyncOfflineEquipment;

public interface SyncOfflineEquipmentRepository extends JpaRepository<SyncOfflineEquipment, Long>{
	
	SyncOfflineEquipment findByEquipmentEquipmentidAndActive(Long id, Boolean active);
}
