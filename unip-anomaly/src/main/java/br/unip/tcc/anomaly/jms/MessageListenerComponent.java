package br.unip.tcc.anomaly.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.protobuf.InvalidProtocolBufferException;

import br.unip.tcc.anomaly.service.AnalyseService;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;

@Component
public class MessageListenerComponent {
	
	@Autowired
	AnalyseService analyseService;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerComponent.class);
    
    @JmsListener(destination = "anomalyAnalyse", concurrency = "1")
    public void onReceiverQueue(byte[] message) throws JsonMappingException, JsonProcessingException, InvalidProtocolBufferException {
        try {
            KeepAliveDTO dto = KeepAliveProtoConverter.convertTO(message);

            LOGGER.info("Analisando leitura - Equipamento  : " + dto.getEquipment().getId());
            
            analyseService.loadAnalyzerByNetWork(dto);

        } catch (Exception e) {
        	LOGGER.error("Erro na analise da anomalia", e);
        }
    }

}