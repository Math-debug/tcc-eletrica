package security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.unip.tcc.entity.SysUser;
import br.unip.tcc.repository.SysUserRepository;

@Repository
public class UserAuthentication implements UserDetailsService{

	@Autowired
	SysUserRepository sysUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUserRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return user;
	}
	
}
