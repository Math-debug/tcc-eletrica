package br.unip.tcc.usecase.userGroup;

import br.unip.tcc.converver.UserGroupConverter;
import br.unip.tcc.entity.dto.SysUserGroupDTO;
import br.unip.tcc.repository.SysUserGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveUserGroup {

    private final SysUserGroupRepository sysUserGroupRepository;

    public SaveUserGroup(SysUserGroupRepository sysUserGroupRepository) {
        this.sysUserGroupRepository = sysUserGroupRepository;
    }

    public SysUserGroupDTO execute(SysUserGroupDTO dto) {
        return UserGroupConverter.convertTo(sysUserGroupRepository.save(UserGroupConverter.convertTo(dto)));
    }
}
