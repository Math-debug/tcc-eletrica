package br.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.SysUserConverter;
import br.unip.tcc.entity.SysUser;
import br.unip.tcc.entity.dto.SysUserDTO;
import br.unip.tcc.repository.SysUserRepository;

@Service
public class SysUserService {
	@Autowired
	SysUserRepository sysUserRepository;
	
	public List<SysUser> findAll(){
		return sysUserRepository.findAll();
	}
	public SysUser findById(Long id) {
		return sysUserRepository.findById(id).get();
	}
	public SysUser save (SysUserDTO dto) {
		return sysUserRepository.save(SysUserConverter.convertTo(dto));
	}
	public SysUserDTO findByUserName(String name) {
		SysUser user =  sysUserRepository.findByUserName(name);
		return SysUserConverter.convertTo(user);
	}
}
