package br.unip.tcc.sync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;

@RestController
@RequestMapping(value = "/sync")
public class SyncController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@PostMapping
	public ResponseEntity<?> teste (@RequestBody KeepAliveDTO dto){
		jmsTemplate.convertAndSend("keepAlive", KeepAliveProtoConverter.convertTO(dto).toByteArray());
		return ResponseEntity.ok().body(null);
	} 
}
