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

import br.unip.tcc.anomaly.service.AnomalyConfigService;
import br.unip.tcc.converver.AnomalyConfigConverter;
import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.dto.AnomalyConfigDTO;

@RestController
@RequestMapping(value = "/anomaly/config")
public class AnomalyConfigController {
	@Autowired
	private AnomalyConfigService anomalyConfigService;
	
	@GetMapping
	public ResponseEntity<List<AnomalyConfigDTO>> findAll(){
		List<AnomalyConfig> list = anomalyConfigService.findAll();
		List<AnomalyConfigDTO> listDTO = new ArrayList<AnomalyConfigDTO>();
		for(AnomalyConfig anomalyType : list) {
			listDTO.add(AnomalyConfigConverter.convertTo(anomalyType));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/equipment/{id}")
	public ResponseEntity<AnomalyConfigDTO> findByEquipemntId(@PathVariable Long id) {
		AnomalyConfigDTO dto = AnomalyConfigConverter.convertTo(anomalyConfigService.findByEquipementId(id));
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnomalyConfigDTO> findById(@PathVariable Long id) {
		AnomalyConfigDTO dto = AnomalyConfigConverter.convertTo(anomalyConfigService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<AnomalyConfigDTO> insert (@RequestBody AnomalyConfigDTO dto){
		AnomalyConfigDTO obj = AnomalyConfigConverter.convertTo(anomalyConfigService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getAnomalyconfigid()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
