package br.unip.tcc.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.EquipmentConverter;
import br.unip.tcc.converver.KeepAliveConverter;
import br.unip.tcc.entity.Equipment;
import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.dto.EquipmentDTO;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;
import br.unip.tcc.repository.KeepAliveRepository;
import br.unip.tcc.repository.SyncBufferRepository;

@Service
public class KeepAliveService {
	@Autowired
	KeepAliveRepository keepAliveRepository;
	
	@Autowired
	SyncBufferRepository syncBufferRepository;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	public List<KeepAlive> findAll(){
		return keepAliveRepository.findAll();
	}
	public KeepAlive findById(Long id) {
		return keepAliveRepository.findById(id).get();
	}
	public KeepAlive save (KeepAliveDTO dto) {
	    if(dto.getBufferid() != null) {
	        Optional<SyncBuffer> optional = syncBufferRepository.findById(dto.getBufferid());
            if(!optional.isEmpty()) {
            SyncBuffer buffer = optional.get();
            dto.setCreatedAt(buffer.getCreatedAt());
            }else {
                return null;
            }
	    } else {
	        dto.setCreatedAt(Instant.now());
	    }
	    Equipment equipment = equipmentService.findById(dto.getEquipment().getId());
	    dto.setEquipment(EquipmentConverter.convertTo(equipment));
		KeepAlive entity =  KeepAliveConverter.convertTo(dto);
		KeepAlive keepAlive = keepAliveRepository.save(entity);
		dto.setId(keepAlive.getKeepaliveid());
		if(!equipment.getActive()) {
		    equipment.setActive(true);
		    equipmentService.save(EquipmentConverter.convertTo(equipment));
		}
		
		if(equipment.getVerify() != null || equipment.getVerify()) {
		    jmsTemplate.convertAndSend("anomalyAnalyse", KeepAliveProtoConverter.convertTO(dto).toByteArray());
		}
		
		return keepAlive;
	}
}
