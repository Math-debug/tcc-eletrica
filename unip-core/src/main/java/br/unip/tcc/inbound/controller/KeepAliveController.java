package br.unip.tcc.inbound.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import br.unip.tcc.usecase.keepAlive.FindKeepAlive;
import br.unip.tcc.usecase.keepAlive.SaveKeepAlive;
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

@RestController
@RequestMapping(value = "/keepalive")
public class KeepAliveController {
	private final SaveKeepAlive saveKeepAlive;
	private final FindKeepAlive findKeepAlive;

	public KeepAliveController(SaveKeepAlive saveKeepAlive, FindKeepAlive findKeepAlive) {
		this.saveKeepAlive = saveKeepAlive;
		this.findKeepAlive = findKeepAlive;
	}

	@GetMapping
	public ResponseEntity<List<KeepAliveDTO>> findAll(){
		List<KeepAliveDTO> listDTO = findKeepAlive.execute();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KeepAliveDTO> findById(@PathVariable Long id) {
		KeepAliveDTO dto = findKeepAlive.execute(id);
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<KeepAliveDTO> insert (@RequestBody KeepAliveDTO dto){
		KeepAliveDTO obj = KeepAliveConverter.convertTo(saveKeepAlive.execute(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
