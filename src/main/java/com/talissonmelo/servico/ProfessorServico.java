package com.talissonmelo.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.Professor;
import com.talissonmelo.modelo.dto.EscolaResposta;
import com.talissonmelo.modelo.dto.ProfessorDto;
import com.talissonmelo.repositorio.ProfessorRepositorio;

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
			EscolaResposta escolaResposta = escolaServico.listarPorId(idEscola);
			Escola escola = new Escola(escolaResposta.getId(), escolaResposta.getNome());
			professor.setEscola(escola);
		}
		Example<Professor> example = Example.of(professor,ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return repositorio.findAll(example, pageable);
	}
	
	public Professor salvar(ProfessorDto professorDto) {
		Professor professor = new Professor();
		Integer numero = repositorio.numeroMaximoProfessor(professorDto.getEscola().getId());
		BeanUtils.copyProperties(professorDto, professor);
		professor.setNumero(numero);
		return repositorio.save(professor);
	}

}
