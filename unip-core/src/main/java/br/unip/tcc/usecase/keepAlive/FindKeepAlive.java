package br.unip.tcc.usecase.keepAlive;

import br.unip.tcc.converver.KeepAliveConverter;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.repository.KeepAliveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FindKeepAlive {

    private final KeepAliveRepository keepAliveRepository;

    public FindKeepAlive(KeepAliveRepository keepAliveRepository) {
        this.keepAliveRepository = keepAliveRepository;
    }

    public KeepAliveDTO execute(Long id) {
        return KeepAliveConverter.convertTo(Objects.requireNonNull(keepAliveRepository.findById(id).orElse(null)));
    }

    public List<KeepAliveDTO> execute() {
        return keepAliveRepository.findAll().stream().map(KeepAliveConverter::convertTo).collect(Collectors.toList());
    }
}
