package br.unip.tcc.usecase.user;

import br.unip.tcc.converver.SysUserConverter;
import br.unip.tcc.entity.dto.SysUserDTO;
import br.unip.tcc.repository.SysUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FindUser {
    private final SysUserRepository sysUserRepository;

    public FindUser(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public SysUserDTO execute(Long id){
        return SysUserConverter.convertTo(Objects.requireNonNull(sysUserRepository.findById(id).orElse(null)));
    }

    public List<SysUserDTO> execute(){
        return sysUserRepository.findAll().stream()
                .map(SysUserConverter::convertTo)
                .collect(Collectors.toList());
    }

    public SysUserDTO execute(String name){
        return SysUserConverter.convertTo(sysUserRepository.findByUserName(name));
    }
}
