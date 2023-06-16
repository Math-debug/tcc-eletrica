package br.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.UserGroupConverter;
import br.unip.tcc.entity.SysUserGroup;
import br.unip.tcc.entity.dto.SysUserGroupDTO;
import br.unip.tcc.repository.SysUserGroupRepository;

@Service
public class SysUserGroupService {
	@Autowired
	SysUserGroupRepository sysUserGroupRepository;
	
	public List<SysUserGroup> findAll(){
		return sysUserGroupRepository.findAll();
	}
	public SysUserGroup findById(Long id) {
		return sysUserGroupRepository.findById(id).get();
	}
	public SysUserGroup save (SysUserGroupDTO dto) {
		return sysUserGroupRepository.save(UserGroupConverter.convertTo(dto));
	}
}
