package com.talissonmelo.servico;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.talissonmelo.controlador.EscolaControlador;
import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.dto.EscolaDto;
import com.talissonmelo.modelo.dto.EscolaResposta;
import com.talissonmelo.modelo.exception.ConflitoEmDelecao;
import com.talissonmelo.modelo.exception.EntidadeNaoEncontrada;
import com.talissonmelo.repositorio.EscolaRepositorio;

import jakarta.validation.Valid;

@Service
public class EscolaServico {

	@Autowired
	private EscolaRepositorio repositorio;

	public EscolaResposta salvar(EscolaDto escolaDto) {
		Escola escola = new Escola(escolaDto.getNome());
		return this.retornaEscolaResposta(repositorio.save(escola));
	}

	public List<EscolaResposta> listar() {
		return this.retornarEscolaResposta(repositorio.listarEscolas());
	}

	public List<EscolaResposta> listar(Escola escola) {
		Example<Escola> example = Example.of(escola,ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return this.retornarEscolaResposta(repositorio.findAll(example));
	}

	public Escola listarPorId(Long id) {
		return  repositorio.buscarPorId(id).orElseThrow(() -> new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id));
	}

	public void deletar(Long id) {
		try {
			repositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id);
		} catch (DataIntegrityViolationException e) {
			throw new ConflitoEmDelecao("Escola não pode ser Deletada, possui persistencia em outra tabela.");
		}
	}

	public EscolaResposta atualizar(Long id, @Valid EscolaDto escolaDto) {
		Escola escola = repositorio.buscarPorId(id).orElseThrow(() -> new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id));
		BeanUtils.copyProperties(escolaDto, escola, "id");
		return this.retornaEscolaResposta(repositorio.save(escola));
	}

	public List<EscolaResposta> retornarEscolaResposta(List<Escola> escolas) {
		return escolas.stream().map(escola -> EscolaResposta.criar(escola)).collect(Collectors.toList());
	}
	
	public EscolaResposta retornaEscolaResposta(Escola escola) {
		return new EscolaResposta(escola.getId(), escola.getNome());
	}
	
	public void addLink(List<EscolaResposta> escolaRespostas) {
		escolaRespostas.forEach(resposta -> {
			resposta.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EscolaControlador.class).listarPorId(resposta.getId())).withSelfRel());
			resposta.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EscolaControlador.class).listar()).withRel("escolas"));
		});
	}
}
