package br.unip.tcc.outbound.jms;

import br.unip.tcc.entity.dto.KeepAliveEvent;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;
import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class AnomalyPublisher {

    private final JmsTemplate jmsTemplate;

    public AnomalyPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @EventListener
    public void onEvent (KeepAliveEvent event){
        jmsTemplate.convertAndSend("anomalyAnalyse", KeepAliveProtoConverter.convertTO(event.getKeepAliveDTO()).toByteArray());
    }
}
