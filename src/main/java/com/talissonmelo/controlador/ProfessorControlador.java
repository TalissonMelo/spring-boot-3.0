package com.talissonmelo.controlador;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talissonmelo.documentacao.ProfessorControladorDocumentacao;
import com.talissonmelo.modelo.Professor;
import com.talissonmelo.modelo.dto.ProfessorDto;
import com.talissonmelo.servico.ProfessorServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorControlador implements ProfessorControladorDocumentacao {

	private static final Logger log = LoggerFactory.getLogger(ProfessorControlador.class);

	@Autowired
	private ProfessorServico servico;

	@GetMapping
	public ResponseEntity<Page<Professor>> listar(@PageableDefault(size = 2) Pageable pageable,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "numero", required = false) Integer numero,
			@RequestParam(value = "nomeHeroi", required = false) String nomeHeroi,
			@RequestParam(value = "idEscola", required = false) Long idEscola) {
		log.info("Listando professores");
		return ResponseEntity.ok().body(servico.listar(pageable, nome, numero, nomeHeroi, idEscola));
	}

	@PostMapping
	public ResponseEntity<Professor> salvar(@Valid @RequestBody ProfessorDto professorDto) {
		log.info("Cadastrando professor.");
		Professor professor = servico.salvar(professorDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professor.getId())
				.toUri();
		return ResponseEntity.created(uri).body(professor);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Professor> listarPorId(@PathVariable Long id) {
		log.info("Listar professor por id.");
		return ResponseEntity.ok().body(this.servico.listarPorId(id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
		log.info("Deletar professor.");
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Professor> atualizar(@PathVariable Long id, @Valid @RequestBody ProfessorDto professorDto) {
		log.info("Atualizar professor.");
		Professor professor = servico.atualizar(id, professorDto);
		return ResponseEntity.ok().body(professor);
	}
}
