package br.unip.tcc.converver;

import br.unip.tcc.entity.SysUserGroup;
import br.unip.tcc.entity.dto.SysUserGroupDTO;

public class UserGroupConverter {
	public static SysUserGroup convertTo(SysUserGroupDTO dto) {
		SysUserGroup entity = new SysUserGroup();
		entity.setGroupName(dto.getGroupName());
		entity.setSysusergroupid(dto.getId());
		return entity;
	}

	public static SysUserGroupDTO convertTo(SysUserGroup entity) {
		SysUserGroupDTO dto = new SysUserGroupDTO();
		dto.setGroupName(entity.getGroupName());
		dto.setId(entity.getSysusergroupid());
		return dto;
	}
}
