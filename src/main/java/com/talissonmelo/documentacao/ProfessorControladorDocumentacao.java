package com.talissonmelo.documentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.talissonmelo.modelo.Professor;
import com.talissonmelo.modelo.dto.ProfessorDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professor", description = "Gerenciamento de professores!.")
public interface ProfessorControladorDocumentacao {

	@Operation(summary = "Listar os professores")
	ResponseEntity<Page<Professor>> listar(Pageable pageable, String nome, Integer idade, String nomeHeroi, Long idEscola);
	
	@Operation(summary = "Cadastra um professor")
	ResponseEntity<Professor> salvar(
			@RequestBody(description = "Representação de um professor", required = true) ProfessorDto professorDto);
	
	@Operation(summary = "Busca um professor por Id")
	ResponseEntity<Professor> listarPorId(
			@Parameter(description = "ID de um professor", example = "1", required = true) Long id);
	
	@Operation(summary = "Deletar um professor por Id")
	ResponseEntity<Void> deletarPorId(
			@Parameter(description = "ID do professor", example = "1", required = true) Long id);
	
	@Operation(summary = "Atualiza um professor por Id")
	ResponseEntity<Professor> atualizar(
			@Parameter(description = "ID de um professor", example = "1", required = true) Long id, 
			@RequestBody(description = "Representação de um professor.", required = true) ProfessorDto professorDto);

}
