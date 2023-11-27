package br.unip.tcc.sync.outbound.jms;

import br.unip.tcc.entity.dto.KeepAliveEvent;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;
import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class KeepAliveListenner {

    private final JmsTemplate jmsTemplate;

    public KeepAliveListenner(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @EventListener
    public void on(KeepAliveEvent event) {
        jmsTemplate.convertAndSend("keepAlive", KeepAliveProtoConverter.convertTO(event.getKeepAliveDTO()).toByteArray());
    }
}
