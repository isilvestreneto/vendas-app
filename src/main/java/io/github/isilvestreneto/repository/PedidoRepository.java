package io.github.isilvestreneto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.isilvestreneto.model.Cliente;
import io.github.isilvestreneto.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente cliente);

}
