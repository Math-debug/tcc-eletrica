package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.Anomaly;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long>{
	//Spring usa o valor ordinal para salvar no banco...
	@Query("select a from Anomaly a where statusid <> 2 and a.keepAlive.equipment.equipmentid = :id")
	public Anomaly findByValidStatusAndEquipmentEquipmentid(@Param("id") Long id);
}
