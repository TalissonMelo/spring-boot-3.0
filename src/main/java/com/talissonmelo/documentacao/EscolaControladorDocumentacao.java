package com.talissonmelo.documentacao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.talissonmelo.modelo.dto.EscolaDto;
import com.talissonmelo.modelo.dto.EscolaResposta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Escolas", description = "Gerenciamento de escolas!.")
public interface EscolaControladorDocumentacao {

	@Operation(summary = "Lista as escolas")
	ResponseEntity<List<EscolaResposta>> listar();

	@Operation(summary = "Busca uma escola por Id")
	ResponseEntity<EscolaResposta> listarPorId(
			@Parameter(description = "ID de uma escola", example = "1", required = true) Long id);

	@Operation(summary = "Cadastra uma escola", description = "Cadastro de uma escola e obrigatório o preenchimento do nome!.")
	ResponseEntity<EscolaResposta> persistir(
			@RequestBody(description = "Representação de uma escola", required = true) EscolaDto escolaDto);

	@Operation(summary = "Deleta uma escola por Id")
	ResponseEntity<Void> deletarPorId(
			@Parameter(description = "ID de uma escola", example = "1", required = true) Long id);

	@Operation(summary = "Atualiza uma escola por Id")
	ResponseEntity<EscolaResposta> atualizarEscola(
			@Parameter(description = "ID de uma escola", example = "1", required = true) Long id, 
			@RequestBody(description = "Representação de uma escola para atualização de dados.", required = true) EscolaDto escolaDto);

	@Operation(summary = "Lista as escolas por nome")
	ResponseEntity<List<EscolaResposta>> findAll(
			@Parameter(description = "Descrição do nome da escola parcial ou completo.", example = "Pro") String nome);
}
