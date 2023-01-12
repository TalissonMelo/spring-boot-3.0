package com.talissonmelo.servico;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.dto.EscolaDto;
import com.talissonmelo.modelo.exception.ConflitoEmDelecao;
import com.talissonmelo.modelo.exception.EntidadeNaoEncontrada;
import com.talissonmelo.repositorio.EscolaRepositorio;

import jakarta.validation.Valid;

@Service
public class EscolaServico {

	@Autowired
	private EscolaRepositorio repositorio;

	public Escola salvar(EscolaDto escolaDto) {
		Escola escola = new Escola(escolaDto.getNome());
		return repositorio.save(escola);
	}

	public List<Escola> listar() {
		return repositorio.listarEscolas();
	}

	public List<Escola> listar(Escola escola) {
		Example<Escola> example = Example.of(escola, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return repositorio.findAll(example);
	}

	public Escola listarPorId(Long id) {
		return repositorio.buscarPorId(id)
				.orElseThrow(() -> new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id));
	}

	public void deletar(Long id) {
		try {
			repositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id);
		} catch (DataIntegrityViolationException e) {
			throw new ConflitoEmDelecao("Pessoa não pode ser Deletada, possui persistencia em outra tabela.");
		}
	}

	public Escola atualizar(Long id, @Valid EscolaDto escolaDto) {
		Escola escola = this.listarPorId(id);
		BeanUtils.copyProperties(escolaDto, escola, "id");
		return repositorio.save(escola);
	}
}
