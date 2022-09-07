package br.unip.tcc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="tcc_sysusergroup")
public class SysUserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sysusergroupid;
	@Column(name="groupname")
	private String groupName;
}
