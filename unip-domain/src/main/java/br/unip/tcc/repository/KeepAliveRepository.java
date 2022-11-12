package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.tcc.entity.KeepAlive;

public interface KeepAliveRepository extends JpaRepository <KeepAlive,Long>{

}
