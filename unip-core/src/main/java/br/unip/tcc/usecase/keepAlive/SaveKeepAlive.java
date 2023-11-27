package br.unip.tcc.usecase.keepAlive;

import br.unip.tcc.converver.KeepAliveConverter;
import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.entity.dto.KeepAliveEvent;
import br.unip.tcc.repository.KeepAliveRepository;
import br.unip.tcc.repository.SyncBufferRepository;
import br.unip.tcc.usecase.equipment.FindEquipment;
import br.unip.tcc.usecase.equipment.SaveEquipment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class SaveKeepAlive {

    private final KeepAliveRepository keepAliveRepository;
    private final FindEquipment findEquipment;
    private final SyncBufferRepository syncBufferRepository;
    private final SaveEquipment saveEquipment;
    private final ApplicationEventPublisher applicationEvent;

    public SaveKeepAlive(KeepAliveRepository keepAliveRepository, FindEquipment findEquipment, SyncBufferRepository syncBufferRepository, SaveEquipment saveEquipment, ApplicationEventPublisher applicationEvent) {
        this.keepAliveRepository = keepAliveRepository;
        this.findEquipment = findEquipment;
        this.syncBufferRepository = syncBufferRepository;
        this.saveEquipment = saveEquipment;
        this.applicationEvent = applicationEvent;
    }

    public KeepAlive execute(KeepAliveDTO dto) {
        if (dto.getBufferid() != null) {
            Optional<SyncBuffer> optional = syncBufferRepository.findById(dto.getBufferid());
            if (optional.isPresent()) {
                SyncBuffer buffer = optional.get();
                dto.setCreatedAt(buffer.getCreatedAt());
            } else {
                return null;
            }
        } else {
            dto.setCreatedAt(Instant.now());
        }
        EquipmentDTO equipment = findEquipment.execute(dto.getEquipment().getId());
        dto.setEquipment(equipment);
        KeepAlive entity = KeepAliveConverter.convertTo(dto);
        KeepAlive keepAlive = keepAliveRepository.save(entity);
        dto.setId(keepAlive.getKeepaliveid());
        if (!equipment.getActive()) {
            equipment.setActive(true);
            saveEquipment.execute(equipment);
        }

        if (equipment.getVerify() != null || equipment.getVerify()) {
            applicationEvent.publishEvent(new KeepAliveEvent(dto));
        }

        return keepAlive;
    }
}