package br.unip.tcc.sync.usecase;

import br.unip.tcc.entity.SyncBuffer;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.entity.dto.KeepAliveEvent;
import br.unip.tcc.repository.SyncBufferRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncBufferUS {

    private final SyncBufferRepository syncBufferRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public SyncBufferUS(SyncBufferRepository syncBufferRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.syncBufferRepository = syncBufferRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void execute() {
        List<SyncBuffer> buffers = syncBufferRepository.findByAttemps();
        buffers.parallelStream().forEach(attemp -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                KeepAliveDTO dto = mapper.readValue(attemp.getData(), KeepAliveDTO.class);
                dto.setBufferid(attemp.getBufferid());
                applicationEventPublisher.publishEvent(new KeepAliveEvent(dto));
            } catch (Exception e) {
                attemp.setAttempt(attemp.getAttempt() + 1);
                syncBufferRepository.save(attemp);
            }
        });
    }
}
