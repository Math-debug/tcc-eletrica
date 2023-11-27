package br.unip.tcc.usecase.equipment;

import br.unip.tcc.converver.EquipmentConverter;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.repository.EquipmentRepository;
import br.unip.tcc.repository.SyncEquipmentConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindEquipment {

    private final EquipmentRepository equipmentRepository;
    private final SyncEquipmentConfigRepository syncEquipmentConfigRepository;

    public FindEquipment(EquipmentRepository equipmentRepository, SyncEquipmentConfigRepository syncEquipmentConfigRepository) {
        this.equipmentRepository = equipmentRepository;
        this.syncEquipmentConfigRepository = syncEquipmentConfigRepository;
    }

    public List<EquipmentDTO> execute(){
        return equipmentRepository.findAll().stream()
                .map(equipment -> EquipmentConverter.convertTo(equipment, syncEquipmentConfigRepository.findByEquipmentEquipmentid(equipment.getEquipmentid()).get(0)))
                .collect(Collectors.toList());
    }

    public EquipmentDTO execute(Long id) {
        return equipmentRepository.findById(id)
                .map(equipment -> EquipmentConverter.convertTo(equipment, syncEquipmentConfigRepository.findByEquipmentEquipmentid(equipment.getEquipmentid()).get(0)))
                .orElse(null);
    }
}
