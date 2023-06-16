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

import com.google.common.base.Optional;

import br.unip.tcc.converver.EquipmentConverter;
import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.SyncEquipmentConfig;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.repository.SyncEquipmentConfigRepository;
import br.unip.tcc.service.EquipmentService;

@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private SyncEquipmentConfigRepository syncEquipmentConfigRepository;
	
	@GetMapping
	public ResponseEntity<List<EquipmentDTO>> findAll(){
		List<Equipment> list = equipmentService.findAll();
		List<EquipmentDTO> listDTO = new ArrayList<EquipmentDTO>();
		for(Equipment equipment : list) {
			listDTO.add(EquipmentConverter.convertTo(equipment, syncEquipmentConfigRepository.findByEquipmentEquipmentid(equipment.getEquipmentid()).get(0)));
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EquipmentDTO> findById(@PathVariable Long id) {
		EquipmentDTO dto = EquipmentConverter.convertTo(equipmentService.findById(id), syncEquipmentConfigRepository.findByEquipmentEquipmentid(id).get(0));
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping
	public ResponseEntity<EquipmentDTO> insert (@RequestBody EquipmentDTO dto){
		EquipmentDTO obj = EquipmentConverter.convertTo(equipmentService.save(dto));
		List<SyncEquipmentConfig> configs = syncEquipmentConfigRepository.findByEquipmentEquipmentid(obj.getId());
		if(configs == null || configs.isEmpty()) {
		    SyncEquipmentConfig config = new SyncEquipmentConfig();
		    config.setActive(dto.getSyncronized() == null ? false : dto.getSyncronized());
		    config.setEquipment(EquipmentConverter.convertTo(obj));
		    config.setIp(dto.getIp());
		    syncEquipmentConfigRepository.save(config);
		}else {
		    SyncEquipmentConfig config = configs.get(0);
		    config.setActive(dto.getSyncronized());
            config.setEquipment(EquipmentConverter.convertTo(obj));
            config.setIp(dto.getIp());
            syncEquipmentConfigRepository.save(config);
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
