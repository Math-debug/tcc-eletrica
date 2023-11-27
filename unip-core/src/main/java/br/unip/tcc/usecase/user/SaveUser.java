package br.unip.tcc.usecase.user;

import br.unip.tcc.converver.SysUserConverter;
import br.unip.tcc.entity.SysUser;
import br.unip.tcc.entity.dto.SysUserDTO;
import br.unip.tcc.repository.SysUserRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveUser {

    private final SysUserRepository sysUserRepository;

    public SaveUser(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public SysUserDTO execute (SysUserDTO dto) {
        return SysUserConverter.convertTo(sysUserRepository.save(SysUserConverter.convertTo(dto)));
    }
}
