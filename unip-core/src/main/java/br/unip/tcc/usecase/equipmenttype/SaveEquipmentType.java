package br.unip.tcc.usecase.equipmenttype;

import br.unip.tcc.converver.EquipmentTypeConverter;
import br.unip.tcc.entity.EquipmentType;
import br.unip.tcc.entity.dto.EquipmentTypeDTO;
import br.unip.tcc.repository.EquipmentTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveEquipmentType {

    private final EquipmentTypeRepository equipmentTypeRepository;

    public SaveEquipmentType(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    public EquipmentTypeDTO execute(EquipmentTypeDTO dto) {
        EquipmentType equipmentType = equipmentTypeRepository.save(EquipmentTypeConverter.convertTo(dto));
        return EquipmentTypeConverter.convertTo(equipmentType);
    }
}
