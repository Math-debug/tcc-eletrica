package br.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.EquipmentConverter;
import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.repository.EquipmentRepository;

@Service
public class EquipmentService {
	@Autowired
	EquipmentRepository equipmentRepository;
	
	public List<Equipment> findAll(){
		return equipmentRepository.findAll();
	}
	public Equipment findById(Long id) {
		return equipmentRepository.findById(id).get();
	}
	public Equipment save (EquipmentDTO dto) {
		return equipmentRepository.save(EquipmentConverter.convertTo(dto));
	}
}
