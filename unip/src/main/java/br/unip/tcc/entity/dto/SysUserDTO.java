package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class SysUserDTO implements Serializable{

	private static final long serialVersionUID = -589144038726823835L;
	
	private Long id;
	private String userName;
	private String password;
	private SysUserGroupDTO sysUserGroup;
}
