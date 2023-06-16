package br.unip.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unip.tcc.entity.SyncBuffer;

public interface SyncBufferRepository extends JpaRepository<SyncBuffer, Long>{
	@Query("select b from SyncBuffer b where b.attempt < 10")
	List<SyncBuffer> findByAttemps();
	List<SyncBuffer> findByData(String data);
}
