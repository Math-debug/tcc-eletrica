package br.unip.tcc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unip.tcc.converver.SysUserConverter;
import br.unip.tcc.entity.SysUser;
import br.unip.tcc.entity.dto.SysUserDTO;
import br.unip.tcc.service.SysUserService;

@RestController
@RequestMapping(value = "/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@GetMapping
	public ResponseEntity<List<SysUserDTO>> findAll(){
		List<SysUser> list = sysUserService.findAll();
		List<SysUserDTO> listDTO = new ArrayList<SysUserDTO>();
		for(SysUser item : list) {
			listDTO.add(SysUserConverter.convertTo(item));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SysUserDTO> findById(@PathVariable Long id) {
		SysUserDTO dto = SysUserConverter.convertTo(sysUserService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<SysUserDTO> insert (@RequestBody SysUserDTO dto){
		SysUserDTO obj = SysUserConverter.convertTo(sysUserService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@GetMapping("/get")
	public ResponseEntity<SysUserDTO> getUser() {
		Authentication obj = SecurityContextHolder.getContext().getAuthentication();
		SysUserDTO user = sysUserService.findByUserName(obj.getName());
		return ResponseEntity.ok().body(user);
	}
}
