package br.unip.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.tcc.entity.SysUser;

public interface SysUserRepository extends JpaRepository <SysUser,Long>{
	SysUser findByUserName(String username);
}
