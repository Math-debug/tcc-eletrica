package br.unip.tcc.anomaly.controller;

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

import br.unip.tcc.anomaly.service.AnomalyService;
import br.unip.tcc.converver.AnomalyConverter;
import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.dto.AnomalyDTO;

@RestController
@RequestMapping(value = "/anomaly")
public class AnomalyController {
	@Autowired
	private AnomalyService anomalyActionService;
	
	@GetMapping
	public ResponseEntity<List<AnomalyDTO>> findAll(){
		List<Anomaly> list = anomalyActionService.findAll();
		List<AnomalyDTO> listDTO = new ArrayList<AnomalyDTO>();
		for(Anomaly anomalyType : list) {
			listDTO.add(AnomalyConverter.convertTo(anomalyType));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnomalyDTO> findById(@PathVariable Long id) {
		AnomalyDTO dto = AnomalyConverter.convertTo(anomalyActionService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<AnomalyDTO> insert (@RequestBody AnomalyDTO dto){
		AnomalyDTO obj = AnomalyConverter.convertTo(anomalyActionService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getAnomalyid()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
