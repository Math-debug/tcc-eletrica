package br.unip.tcc.usecase.keepAlive;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.proto.converter.KeepAliveProtoConverter;
import br.unip.tcc.repository.SyncBufferRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ErrorToReceiveKeepAlive {

    private final SyncBufferRepository syncBufferRepository;

    public ErrorToReceiveKeepAlive(SyncBufferRepository syncBufferRepository) {
        this.syncBufferRepository = syncBufferRepository;
    }

    public void execute(KeepAliveDTO dto) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Optional<SyncBuffer> optional = syncBufferRepository.findById(dto.getBufferid());
        SyncBuffer buffer = null;
        if(!optional.isEmpty()) {
            buffer = optional.get();
        }
        if (buffer == null) {
            buffer = new SyncBuffer();
            try {
                buffer.setData(mapper.writeValueAsString(dto));
            } catch (JsonProcessingException e) {
                log.error("Nao foi possivel converter o objeto", e);
                return;
            }
            buffer.setAttempt(1);
        } else {
            buffer.setAttempt(buffer.getAttempt() + 1);
        }
        syncBufferRepository.save(buffer);
    }
}
