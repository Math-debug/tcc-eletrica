package br.unip.tcc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.unip.tcc.service.SysUserGroupService;

@RestController
@RequestMapping(value = "/usergroup")
public class SysUserGroupController {
	@Autowired
	private SysUserGroupService sysUserGroupService;
	
	@GetMapping
	public ResponseEntity<List<SysUserGroupDTO>> findAll(){
		List<SysUserGroup> list = sysUserGroupService.findAll();
		List<SysUserGroupDTO> listDTO = new ArrayList<SysUserGroupDTO>();
		for(SysUserGroup item : list) {
			listDTO.add(UserGroupConverter.convertTo(item));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SysUserGroupDTO> findById(@PathVariable Long id) {
		SysUserGroupDTO dto = UserGroupConverter.convertTo(sysUserGroupService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<SysUserGroupDTO> insert (@RequestBody SysUserGroupDTO dto){
		SysUserGroupDTO obj = UserGroupConverter.convertTo(sysUserGroupService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
