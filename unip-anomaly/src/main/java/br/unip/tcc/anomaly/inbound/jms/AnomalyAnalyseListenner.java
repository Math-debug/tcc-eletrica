package br.unip.tcc.anomaly.inbound.jms;

import br.unip.tcc.anomaly.usecase.DefineAnalyser;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnomalyAnalyseListenner {
	
    private final DefineAnalyser analyseService;
	

    public AnomalyAnalyseListenner(DefineAnalyser analyseService) {
        this.analyseService = analyseService;
    }

    @JmsListener(destination = "anomalyAnalyse", concurrency = "1")
    public void onReceiverQueue(byte[] message) throws JsonMappingException, JsonProcessingException, InvalidProtocolBufferException {
        try {
            KeepAliveDTO dto = KeepAliveProtoConverter.convertTO(message);

            log.info("Analisando leitura - Equipamento  : " + dto.getEquipment().getId());
            
            analyseService.execute(dto);

        } catch (Exception e) {
        	log.error("Erro na analise da anomalia", e);
        }
    }

}