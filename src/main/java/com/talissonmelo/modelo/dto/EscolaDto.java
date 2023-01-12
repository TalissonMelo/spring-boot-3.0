package com.talissonmelo.modelo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class EscolaDto {

	@NotBlank(message = "Nome da escola e obrigatório!.")
	@Schema(example = "Escola Estadual Pro Hero")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
