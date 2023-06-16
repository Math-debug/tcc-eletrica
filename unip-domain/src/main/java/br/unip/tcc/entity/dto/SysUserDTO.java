package br.unip.tcc.entity.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private SysUserGroupDTO sysUserGroup;
}
