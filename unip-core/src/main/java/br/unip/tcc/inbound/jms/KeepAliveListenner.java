package br.unip.tcc.inbound.jms;

import br.unip.tcc.usecase.keepAlive.ReceiveKeepAlive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;

@Component
@Slf4j
public class KeepAliveListenner {

    private final ReceiveKeepAlive receiveKeepAlive;

    public KeepAliveListenner(ReceiveKeepAlive receiveKeepAlive) {
        this.receiveKeepAlive = receiveKeepAlive;
    }


    @SuppressWarnings("null")
    @JmsListener(destination = "keepAlive", concurrency = "1")
    public void onReceiverQueue(byte[] message) throws InvalidProtocolBufferException {
            KeepAliveDTO dto = KeepAliveProtoConverter.convertTO(message);
            log.info("Keep Alive Recebido - Equipamento  : " + dto.getEquipment().getId());
            receiveKeepAlive.execute(dto);
    }

}