package com.talissonmelo.modelo.exception;

public class EntidadeNaoEncontrada extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontrada(String modelo, Long id) {
		super(modelo + " de ID: " + id + ", não encontrado.");
	}
}
