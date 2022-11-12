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

import br.unip.tcc.converver.EquipmentTypeConverter;
import br.unip.tcc.entity.EquipmentType;
import br.unip.tcc.entity.dto.EquipmentTypeDTO;
import br.unip.tcc.service.EquipmentTypeService;

@RestController
@RequestMapping(value = "/equipmenttype")
public class EquipmentTypeController {
	@Autowired
	private EquipmentTypeService equipmentTypeService;
	
	@GetMapping
	public ResponseEntity<List<EquipmentTypeDTO>> findAll(){
		List<EquipmentType> list = equipmentTypeService.findAll();
		List<EquipmentTypeDTO> listDTO = new ArrayList<EquipmentTypeDTO>();
		for(EquipmentType equipment : list) {
			listDTO.add(EquipmentTypeConverter.convertTo(equipment));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentTypeDTO> findById(@PathVariable Long id) {
		EquipmentTypeDTO dto = EquipmentTypeConverter.convertTo(equipmentTypeService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<EquipmentTypeDTO> insert (@RequestBody EquipmentTypeDTO dto){
		EquipmentTypeDTO obj = EquipmentTypeConverter.convertTo(equipmentTypeService.save(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
