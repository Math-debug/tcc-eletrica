package br.unip.tcc.inbound.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import br.unip.tcc.usecase.user.FindUser;
import br.unip.tcc.usecase.user.SaveUser;
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

@RestController
@RequestMapping(value = "/user")
public class SysUserController {
	private final FindUser findUser;
	private final SaveUser saveUser;

	public SysUserController(FindUser findUser, SaveUser saveUser) {
		this.findUser = findUser;
		this.saveUser = saveUser;
	}

	@GetMapping
	public ResponseEntity<List<SysUserDTO>> findAll(){
		List<SysUserDTO> list = findUser.execute();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SysUserDTO> findById(@PathVariable Long id) {
		SysUserDTO dto = findUser.execute(id);
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<SysUserDTO> insert (@RequestBody SysUserDTO dto){
		SysUserDTO obj = saveUser.execute(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@GetMapping("/get")
	public ResponseEntity<SysUserDTO> getUser() {
		Authentication obj = SecurityContextHolder.getContext().getAuthentication();
		SysUserDTO user = findUser.execute(obj.getName());
		return ResponseEntity.ok().body(user);
	}
}
