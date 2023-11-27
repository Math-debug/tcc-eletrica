package br.unip.tcc.usecase.keepAlive;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.repository.SyncBufferRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReceiveKeepAlive {

    private final SyncBufferRepository syncBufferRepository;
    private final SaveKeepAlive saveKeepAlive;
    private final ErrorToReceiveKeepAlive errorToReceiveKeepAlive;


    public ReceiveKeepAlive(SyncBufferRepository syncBufferRepository, SaveKeepAlive saveKeepAlive, ErrorToReceiveKeepAlive errorToReceiveKeepAlive) {
        this.syncBufferRepository = syncBufferRepository;
        this.saveKeepAlive = saveKeepAlive;
        this.errorToReceiveKeepAlive = errorToReceiveKeepAlive;
    }

    public void execute(KeepAliveDTO dto) {
        try {
            saveKeepAlive.execute(dto);
            if(dto.getBufferid() != null) {
                Optional<SyncBuffer> optional = syncBufferRepository.findById(dto.getBufferid());
                if(optional.isPresent()) {
                    SyncBuffer buffer = optional.get();
                    syncBufferRepository.delete(buffer);
                }
            }
        } catch (Exception e) {
            log.error("Nao foi possivel salvar a mensagem", e);
            errorToReceiveKeepAlive.execute(dto);
        }
    }
}
