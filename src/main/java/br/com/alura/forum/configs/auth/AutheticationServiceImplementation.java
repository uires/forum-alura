package br.com.alura.forum.configs.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repositorys.UsuarioRepository;

@Service
public class AutheticationServiceImplementation implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = this.usuarioRepository.findByEmail(username);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("E-mail e/o senha incorretos!");
	}

}
