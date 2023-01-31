package com.talissonmelo.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.Professor;
import com.talissonmelo.modelo.dto.ProfessorDto;
import com.talissonmelo.modelo.exception.ConflitoEmDelecao;
import com.talissonmelo.modelo.exception.EntidadeNaoEncontrada;
import com.talissonmelo.repositorio.ProfessorRepositorio;

import jakarta.validation.Valid;

@Service
public class ProfessorServico {

	@Autowired
	private ProfessorRepositorio repositorio;
	
	@Autowired
	private EscolaServico escolaServico;

	public Page<Professor> listar(Pageable pageable, String nome, Integer numero, String nomeHeroi, Long idEscola) {
		Professor professor = new Professor();
		professor.setNome(nome);
		professor.setNumero(numero);
		professor.setNomeHeroi(nomeHeroi);
		if (idEscola != null) {
			Escola escola = escolaServico.listarPorId(idEscola);
			professor.setEscola(escola);
		}
		Example<Professor> example = Example.of(professor,ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return repositorio.findAll(example, pageable);
	}
	
	public Professor salvar(ProfessorDto professorDto) {
		Professor professor = new Professor();
		Integer numero = repositorio.numeroMaximoProfessor(professorDto.getEscola().getId());
		BeanUtils.copyProperties(professorDto, professor);
		professor.setNumero(numero + 1);
		return repositorio.save(professor);
	}
	
	public void deletar(Long id) {
		try {
			repositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(Escola.class.getSimpleName().toString(), id);
		} catch (DataIntegrityViolationException e) {
			throw new ConflitoEmDelecao("Professor não pode ser Deletada, possui persistencia em outra tabela.");
		}
	}
	
	public Professor listarPorId(Long idProfessor) {
		return this.repositorio.findById(idProfessor).orElseThrow(() -> new EntidadeNaoEncontrada(Professor.class.getSimpleName(), idProfessor));
	}

	public Professor atualizar(Long id, @Valid ProfessorDto professorDto) {
		Professor professor = this.listarPorId(id);
		BeanUtils.copyProperties(professorDto, professor, "id");
		return this.repositorio.save(professor);
	}

}
