package com.talissonmelo.documentacao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.talissonmelo.modelo.Escola;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Escolas", description = "Gerenciamento de escolas!.")
public interface EscolaControladorDocumentacao {

	@Operation(summary = "Lista as escolas")
	ResponseEntity<List<Escola>> listar();

	@Operation(summary = "Busca uma escola por Id")
	ResponseEntity<Escola> listarPorId(Long id);

	@Operation(summary = "Cadastra uma escola", description = "Cadastro de uma escola e obrigatório o preenchimento do nome!.")
	ResponseEntity<Escola> persistir(Escola escolaDto);

	@Operation(summary = "Atualiza uma escola por Id")
	ResponseEntity<Void> deletarPorId(Long id);

	@Operation(summary = "Deleta uma cidade por Id")
	ResponseEntity<Escola> atualizarEscola(Long id, Escola escolaDto);
}
