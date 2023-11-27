package br.unip.tcc.inbound.controller;

import br.unip.tcc.entity.dto.EquipmentTypeDTO;
import br.unip.tcc.usecase.equipmenttype.FindEquipmentType;
import br.unip.tcc.usecase.equipmenttype.SaveEquipmentType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/equipmenttype")
public class EquipmentTypeController {
	private final FindEquipmentType findEquipmentType;
	private final SaveEquipmentType saveEquipmentType;

	public EquipmentTypeController(FindEquipmentType findEquipmentType, SaveEquipmentType saveEquipmentType) {
		this.findEquipmentType = findEquipmentType;
		this.saveEquipmentType = saveEquipmentType;
	}

	@GetMapping
	public ResponseEntity<List<EquipmentTypeDTO>> findAll(){
		List<EquipmentTypeDTO> listDTO = findEquipmentType.execute();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentTypeDTO> findById(@PathVariable Long id) {
		EquipmentTypeDTO dto = findEquipmentType.execute(id);
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<EquipmentTypeDTO> insert (@RequestBody EquipmentTypeDTO dto){
		EquipmentTypeDTO obj = saveEquipmentType.execute(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
