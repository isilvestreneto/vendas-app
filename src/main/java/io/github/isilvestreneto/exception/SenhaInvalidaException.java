package io.github.isilvestreneto.exception;

public class SenhaInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 6418451716800583892L;

	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}
