package br.unip.tcc.inbound.controller;

import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.usecase.equipment.FindEquipment;
import br.unip.tcc.usecase.equipment.SaveEquipment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {
	private final FindEquipment findEquipment;
	private final SaveEquipment saveEquipment;

	public EquipmentController(FindEquipment findEquipment, SaveEquipment saveEquipment) {
		this.findEquipment = findEquipment;
		this.saveEquipment = saveEquipment;
	}

	@GetMapping
	public ResponseEntity<List<EquipmentDTO>> findAll(){
		List<EquipmentDTO> list = findEquipment.execute();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentDTO> findById(@PathVariable Long id) {
		EquipmentDTO dto = findEquipment.execute(id);
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<EquipmentDTO> insert (@RequestBody EquipmentDTO dto){
		EquipmentDTO obj = saveEquipment.execute(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
