package br.unip.tcc.usecase.userGroup;

import br.unip.tcc.converver.UserGroupConverter;
import br.unip.tcc.entity.dto.SysUserGroupDTO;
import br.unip.tcc.repository.SysUserGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindUserGroup {

    private final SysUserGroupRepository sysUserGroupRepository;

    public FindUserGroup(SysUserGroupRepository sysUserGroupRepository) {
        this.sysUserGroupRepository = sysUserGroupRepository;
    }

    public SysUserGroupDTO execute(Long id) {
        return sysUserGroupRepository.findById(id)
                .map(UserGroupConverter::convertTo)
                .orElse(null);
    }

    public List<SysUserGroupDTO> execute(){
        return sysUserGroupRepository.findAll().stream()
                .map(UserGroupConverter::convertTo)
                .collect(Collectors.toList());
    }

}
