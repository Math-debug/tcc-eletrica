package br.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.EquipmentTypeConverter;
import br.unip.tcc.entity.EquipmentType;
import br.unip.tcc.entity.dto.EquipmentTypeDTO;
import br.unip.tcc.repository.EquipmentTypeRepository;

@Service
public class EquipmentTypeService {
	@Autowired
	EquipmentTypeRepository equipmentTypeRepository;
	
	public List<EquipmentType> findAll(){
		return equipmentTypeRepository.findAll();
	}
	public EquipmentType findById(Long id) {
		return equipmentTypeRepository.findById(id).get();
	}
	public EquipmentType save (EquipmentTypeDTO dto) {
		return equipmentTypeRepository.save(EquipmentTypeConverter.convertTo(dto));
	}
}
