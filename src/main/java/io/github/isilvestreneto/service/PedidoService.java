package io.github.isilvestreneto.service;

import io.github.isilvestreneto.model.Pedido;
import io.github.isilvestreneto.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);

}
