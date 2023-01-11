package com.talissonmelo.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.documentacao.ProfessorControladorDocumentacao;
import com.talissonmelo.modelo.Professor;
import com.talissonmelo.servico.ProfessorServico;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorControlador implements ProfessorControladorDocumentacao {

	private static final Logger log = LoggerFactory.getLogger(ProfessorControlador.class);

	@Autowired
	private ProfessorServico servico;

	@GetMapping
	public ResponseEntity<List<Professor>> listar(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "numero", required = false) Integer numero,
			@RequestParam(value = "nomeHeroi", required = false) String nomeHeroi,
			@RequestParam(value = "idEscola", required = false) Long idEscola) {
		log.info("Listando professores");
		return ResponseEntity.ok().body(servico.listar(nome, numero, nomeHeroi, idEscola));
	}
}
