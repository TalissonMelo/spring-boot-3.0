package com.talissonmelo.documentacao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.talissonmelo.modelo.Professor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professor", description = "Gerenciamento de professores!.")
public interface ProfessorControladorDocumentacao {

	@Operation(summary = "Listar os professores")
	ResponseEntity<List<Professor>> listar(String nome, Integer idade, String nomeHeroi, Long idEscola);

}
