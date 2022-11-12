package br.unip.tcc.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.service.KeepAliveService;

@Component
public class MessageListenerComponent {
	
	@Autowired KeepAliveService keepAliveService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerComponent.class);

    @JmsListener(destination = "keepAlive")
    public void onReceiverQueue(String str) {
    	try {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        KeepAliveDTO dto = mapper.readValue(str,KeepAliveDTO.class);
        
        LOGGER.info("Keep Alive Recebido - Equipamento  : " + dto.getEquipment().getId());
        
        keepAliveService.save(dto);
        
    	}catch (Exception e) {
    		e.printStackTrace();
			LOGGER.error("Nao foi possivel ler a mensagem");
		}
    }

}