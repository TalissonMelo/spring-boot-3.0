package com.talissonmelo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "professor")
@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer numero;
	private String nome;
	private String individualidade;
	private String nomeHeroi;
	
	@ManyToOne
	@JoinColumn(name = "id_escola")
	private Escola escola;

	public Professor() {
	}

	public Professor(Long id, Integer numero, String nome, String individualidade, String nomeHeroi) {
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.individualidade = individualidade;
		this.nomeHeroi = nomeHeroi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

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
