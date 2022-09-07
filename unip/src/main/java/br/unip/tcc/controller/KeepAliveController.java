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

import br.unip.tcc.converver.KeepAliveConverter;
import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.service.KeepAliveService;

@RestController
@RequestMapping(value = "/keepalive")
public class KeepAliveController {
	@Autowired
	private KeepAliveService keepAliveService;
	
	@GetMapping
	public ResponseEntity<List<KeepAliveDTO>> findAll(){
		List<KeepAlive> list = keepAliveService.findAll();
		List<KeepAliveDTO> listDTO = new ArrayList<KeepAliveDTO>();
		for(KeepAlive item : list) {
			listDTO.add(KeepAliveConverter.convertTo(item));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KeepAliveDTO> findById(@PathVariable Long id) {
		KeepAliveDTO dto = KeepAliveConverter.convertTo(keepAliveService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<KeepAliveDTO> insert (@RequestBody KeepAliveDTO dto){
		KeepAliveDTO obj = KeepAliveConverter.convertTo(keepAliveService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
