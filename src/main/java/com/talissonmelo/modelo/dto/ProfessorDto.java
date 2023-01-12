package com.talissonmelo.modelo.dto;

import com.talissonmelo.modelo.Escola;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProfessorDto {

	@NotBlank(message = "Nome e obrigatório!.")
	private String nome;

	@NotBlank(message = "Individualidade e obrigatório!.")
	private String individualidade;

	@NotBlank(message = "Nome do Herói e obrigatório!.")
	private String nomeHeroi;

	@NotNull(message = "Escola e obrigatório!.")
	private Escola escola;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndividualidade() {
		return individualidade;
	}

	public void setIndividualidade(String individualidade) {
		this.individualidade = individualidade;
	}

	public String getNomeHeroi() {
		return nomeHeroi;
	}

	public void setNomeHeroi(String nomeHeroi) {
		this.nomeHeroi = nomeHeroi;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

}
