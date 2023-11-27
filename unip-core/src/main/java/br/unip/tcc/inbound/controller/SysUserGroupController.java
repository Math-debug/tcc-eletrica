package br.unip.tcc.inbound.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import br.unip.tcc.usecase.userGroup.FindUserGroup;
import br.unip.tcc.usecase.userGroup.SaveUserGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unip.tcc.converver.UserGroupConverter;
import br.unip.tcc.entity.SysUserGroup;
import br.unip.tcc.entity.dto.SysUserGroupDTO;

@RestController
@RequestMapping(value = "/usergroup")
public class SysUserGroupController {
	private final FindUserGroup findUserGroup;
	private final SaveUserGroup saveUserGroup;

	public SysUserGroupController(FindUserGroup findUserGroup, SaveUserGroup saveUserGroup) {
		this.findUserGroup = findUserGroup;
		this.saveUserGroup = saveUserGroup;
	}

	@GetMapping
	public ResponseEntity<List<SysUserGroupDTO>> findAll(){
		List<SysUserGroupDTO> listDTO = findUserGroup.execute();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SysUserGroupDTO> findById(@PathVariable Long id) {
		SysUserGroupDTO dto = findUserGroup.execute(id);
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<SysUserGroupDTO> insert (@RequestBody SysUserGroupDTO dto){
		SysUserGroupDTO obj = saveUserGroup.execute(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
