package io.github.isilvestreneto.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.isilvestreneto.exception.RegraNegocioException;
import io.github.isilvestreneto.model.Cliente;
import io.github.isilvestreneto.model.ItemPedido;
import io.github.isilvestreneto.model.ItemPedidoDTO;
import io.github.isilvestreneto.model.Pedido;
import io.github.isilvestreneto.model.PedidoDTO;
import io.github.isilvestreneto.model.Produto;
import io.github.isilvestreneto.repository.ClienteRepository;
import io.github.isilvestreneto.repository.ItemPedidoRepository;
import io.github.isilvestreneto.repository.PedidoRepository;
import io.github.isilvestreneto.repository.ProdutoRepository;
import io.github.isilvestreneto.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
		}

		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();

			Produto produto = produtoRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());
	}

}
