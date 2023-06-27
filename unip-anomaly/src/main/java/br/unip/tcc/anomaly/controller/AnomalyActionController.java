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

import br.unip.tcc.anomaly.service.AnomalyActionService;
import br.unip.tcc.converver.AnomalyActionConverter;
import br.unip.tcc.converver.EquipmentTypeConverter;
import br.unip.tcc.entity.AnomalyAction;
import br.unip.tcc.entity.dto.AnomalyActionDTO;
import br.unip.tcc.entity.dto.EquipmentTypeDTO;

@RestController
@RequestMapping(value = "/anomaly/action")
public class AnomalyActionController {
	@Autowired
	private AnomalyActionService anomalyActionService;
	
	@GetMapping
	public ResponseEntity<List<AnomalyActionDTO>> findAll(){
		List<AnomalyAction> list = anomalyActionService.findAll();
		List<AnomalyActionDTO> listDTO = new ArrayList<AnomalyActionDTO>();
		for(AnomalyAction anomalyType : list) {
			listDTO.add(AnomalyActionConverter.convertTo(anomalyType));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<AnomalyActionDTO>> findById(@PathVariable Long id) {
		List<AnomalyAction> actions = anomalyActionService.findByAnomalyId(id);
		List<AnomalyActionDTO> dtoList = new ArrayList<>();
		if(!actions.isEmpty()) {
			for(AnomalyAction action : actions) {
				dtoList.add(AnomalyActionConverter.convertTo(action));
			}
		}
		return ResponseEntity.ok().body(dtoList);
	}
	@PostMapping
	public ResponseEntity<AnomalyActionDTO> insert (@RequestBody AnomalyActionDTO dto){
		AnomalyActionDTO obj = AnomalyActionConverter.convertTo(anomalyActionService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getActionid()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
