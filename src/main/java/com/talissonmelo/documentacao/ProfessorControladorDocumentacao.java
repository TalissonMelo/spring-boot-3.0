package com.talissonmelo.documentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.talissonmelo.modelo.Professor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professor", description = "Gerenciamento de professores!.")
public interface ProfessorControladorDocumentacao {

	@Operation(summary = "Listar os professores")
	ResponseEntity<Page<Professor>> listar(Pageable pageable, String nome, Integer idade, String nomeHeroi, Long idEscola);

}
