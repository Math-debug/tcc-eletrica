package br.unip.tcc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class SysUserGroupDTO  implements Serializable{

	private static final long serialVersionUID = 8864475478843057738L;

	private Long id;
	private String groupName;
}
