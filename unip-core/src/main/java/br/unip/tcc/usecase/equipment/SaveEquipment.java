package br.unip.tcc.usecase.equipment;

import br.unip.tcc.converver.EquipmentConverter;
import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.SyncEquipmentConfig;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.repository.AnomalyConfigRepository;
import br.unip.tcc.repository.EquipmentRepository;
import br.unip.tcc.repository.SyncEquipmentConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveEquipment {

    private final EquipmentRepository equipmentRepository;
    private final AnomalyConfigRepository anomalyConfigRepository;
    private final SyncEquipmentConfigRepository syncEquipmentConfigRepository;


    public SaveEquipment(EquipmentRepository equipmentRepository, AnomalyConfigRepository anomalyConfigRepository, SyncEquipmentConfigRepository syncEquipmentConfigRepository) {
        this.equipmentRepository = equipmentRepository;
        this.anomalyConfigRepository = anomalyConfigRepository;
        this.syncEquipmentConfigRepository = syncEquipmentConfigRepository;
    }

    public EquipmentDTO execute (EquipmentDTO dto) {
        EquipmentDTO obj = EquipmentConverter.convertTo(save(dto));
        List<SyncEquipmentConfig> configs = syncEquipmentConfigRepository.findByEquipmentEquipmentid(obj.getId());
        SyncEquipmentConfig config;
        if(configs == null || configs.isEmpty()) {
            config = new SyncEquipmentConfig();
            config.setActive(dto.getSyncronized() != null && dto.getSyncronized());
        }else {
            config = configs.get(0);
            config.setActive(dto.getSyncronized());
        }
        config.setEquipment(EquipmentConverter.convertTo(obj));
        config.setIp(dto.getIp());
        syncEquipmentConfigRepository.save(config);
        return obj;
    }

    private Equipment save(EquipmentDTO dto){
        if (dto.getId() == null) {
            dto.setActive(false);
            return equipmentRepository.save(EquipmentConverter.convertTo(dto));
        }else {
            AnomalyConfig config =  anomalyConfigRepository.findByEquipmentEquipmentid(dto.getId());
            if (config != null) {
                return equipmentRepository.save(EquipmentConverter.convertToSaveConfigs(dto,config));
            }else {
                return equipmentRepository.save(EquipmentConverter.convertTo(dto));
            }
        }
    }
}
