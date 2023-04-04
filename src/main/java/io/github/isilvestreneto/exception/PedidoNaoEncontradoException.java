package io.github.isilvestreneto.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
