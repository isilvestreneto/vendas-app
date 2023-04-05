package io.github.isilvestreneto.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -6511984802664254366L;

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
