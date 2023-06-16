package br.unip.tcc.converver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.unip.tcc.entity.SysUser;
import br.unip.tcc.entity.dto.SysUserDTO;

public class SysUserConverter {
	
	public static SysUser convertTo (SysUserDTO dto) {
		SysUser entity = new SysUser();
		entity.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		entity.setSysuserid(dto.getId());
		entity.setUserName(dto.getUserName());
		entity.setSysUserGroup(UserGroupConverter.convertTo(dto.getSysUserGroup()));
		return entity;
	}
	
	public static SysUserDTO convertTo (SysUser entity) {
		SysUserDTO dto = new SysUserDTO();
		dto.setPassword(entity.getPassword());
		dto.setId(entity.getSysuserid());
		dto.setUserName(entity.getUsername());
		dto.setSysUserGroup(UserGroupConverter.convertTo(entity.getSysUserGroup()));
		return dto;
	}

}
