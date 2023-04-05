package io.github.isilvestreneto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.isilvestreneto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByUsername(String login);

}
