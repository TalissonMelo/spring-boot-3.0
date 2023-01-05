package com.talissonmelo.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.exception.EntidadeNaoEncontrada;
import com.talissonmelo.repositorio.EscolaRepositorio;

@Service
public class EscolaServico {

	@Autowired
	private EscolaRepositorio repositorio;

	public Escola salvar(Escola escola) {
		return repositorio.save(escola);
	}

	public List<Escola> listar() {
		return repositorio.listarEscolas();
	}
	
	public Escola listarPorId(Long id) {
		return repositorio.buscarPorId(id).orElseThrow(() -> new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id));
	}
}
