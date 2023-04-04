package io.github.isilvestreneto.service;

import java.util.Optional;

import io.github.isilvestreneto.model.Pedido;
import io.github.isilvestreneto.model.enums.StatusPedido;
import io.github.isilvestreneto.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);

	void atualizaStatus(Integer id, StatusPedido statusPedido);

}
