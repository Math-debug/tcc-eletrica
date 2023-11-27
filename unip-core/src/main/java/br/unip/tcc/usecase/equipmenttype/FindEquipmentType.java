package br.unip.tcc.usecase.equipmenttype;

import br.unip.tcc.converver.EquipmentTypeConverter;
import br.unip.tcc.entity.dto.EquipmentTypeDTO;
import br.unip.tcc.repository.EquipmentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindEquipmentType {
    private final EquipmentTypeRepository equipmentTypeRepository;

    public FindEquipmentType(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    public List<EquipmentTypeDTO> execute(){
        return equipmentTypeRepository.findAll().stream()
                .map(EquipmentTypeConverter::convertTo)
                .collect(Collectors.toList());
    }

    public EquipmentTypeDTO execute(Long id) {
        return equipmentTypeRepository.findById(id)
                .map(EquipmentTypeConverter::convertTo)
                .orElse(null);
    }
}
