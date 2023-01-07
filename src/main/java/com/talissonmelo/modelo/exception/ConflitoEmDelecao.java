package com.talissonmelo.modelo.exception;

public class ConflitoEmDelecao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConflitoEmDelecao(String mensagem) {
		super(mensagem);
	}
}
