package io.github.isilvestreneto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.isilvestreneto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	<S extends Cliente> List<S> saveAll(Iterable<S> entities);
	
}
