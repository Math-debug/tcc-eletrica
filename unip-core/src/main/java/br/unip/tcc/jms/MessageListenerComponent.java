package br.unip.tcc.jms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

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
    public void onReceiverQueue(String str) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            KeepAliveDTO dto = mapper.readValue(str, KeepAliveDTO.class);

            LOGGER.info("Keep Alive Recebido - Equipamento  : " + dto.getEquipment().getId());

            keepAliveService.save(dto);
            List<SyncBuffer> buffers = syncBufferRepository.findByData(str);
            if (buffers != null && !buffers.isEmpty()) {
                syncBufferRepository.delete(buffers.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Nao foi possivel ler a mensagem");
            SyncBuffer buffer = new SyncBuffer();
            List<SyncBuffer> buffers = syncBufferRepository.findByData(str);
            if (buffers == null || buffers.isEmpty()) {
                buffer.setData(str);
                buffer.setAttempt(1);
            } else {
                buffer = buffers.get(0);
                buffer.setAttempt(buffer.getAttempt() + 1);
            }
            syncBufferRepository.save(buffer);
        }
    }

}