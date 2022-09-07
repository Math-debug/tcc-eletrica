package br.unip.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.tcc.entity.SysUser;

public interface SysUserRepository extends JpaRepository <SysUser,Long>{
	List<SysUser> findByUserName(String username);
}
