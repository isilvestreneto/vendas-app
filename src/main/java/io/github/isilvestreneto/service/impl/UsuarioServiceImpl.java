package io.github.isilvestreneto.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.isilvestreneto.exception.SenhaInvalidaException;
import io.github.isilvestreneto.model.Usuario;
import io.github.isilvestreneto.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails usuarioBD = loadUserByUsername(usuario.getUsername());
		boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), usuarioBD.getPassword());
		
		if(senhasBatem) {
			return usuarioBD;
		}
		
		throw new SenhaInvalidaException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

		String[] roles = usuario.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

		return User.builder().username(usuario.getUsername()).password(usuario.getSenha()).roles(roles).build();
	}

}
