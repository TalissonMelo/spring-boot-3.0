package com.talissonmelo.modelo.dto;

import jakarta.validation.constraints.NotBlank;

public class EscolaDto {

	@NotBlank(message = "Nome da escola e obrigatório!.")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
