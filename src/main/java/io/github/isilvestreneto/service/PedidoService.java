package io.github.isilvestreneto.service;

import io.github.isilvestreneto.model.Pedido;
import io.github.isilvestreneto.model.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);

}
