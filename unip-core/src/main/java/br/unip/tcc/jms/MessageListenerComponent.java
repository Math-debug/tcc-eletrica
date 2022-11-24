package br.unip.tcc.jms;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.repository.SyncBufferRepository;
import br.unip.tcc.service.KeepAliveService;

@Component
public class MessageListenerComponent {

    @Autowired
    KeepAliveService keepAliveService;

    @Autowired
    private SyncBufferRepository syncBufferRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerComponent.class);

    @SuppressWarnings("null")
    @JmsListener(destination = "keepAlive")
    public void onReceiverQueue(String json) throws JsonMappingException, JsonProcessingException {
        try {
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            KeepAliveDTO dto = mapper.readValue(json,KeepAliveDTO.class);

            LOGGER.info("Keep Alive Recebido - Equipamento  : " + dto.getEquipment().getId());

            keepAliveService.save(dto);
            if(dto.getBufferid() != null) {
            SyncBuffer buffer = syncBufferRepository.findById(dto.getBufferid()).get();
                syncBufferRepository.delete(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Nao foi possivel salvar a mensagem");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            KeepAliveDTO dto = mapper.readValue(json,KeepAliveDTO.class);
            Optional<SyncBuffer> optional = syncBufferRepository.findById(dto.getBufferid());
            SyncBuffer buffer = null;
            if(optional != null) {
                buffer = optional.get();
            }
            if (buffer == null) {
                buffer = new SyncBuffer();
                buffer.setData(json);
                buffer.setAttempt(1);
            } else {
                buffer.setAttempt(buffer.getAttempt() + 1);
            }
            syncBufferRepository.save(buffer);
        }
    }

}