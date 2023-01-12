package com.talissonmelo.controlador;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.talissonmelo.documentacao.EscolaControladorDocumentacao;
import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.dto.EscolaDto;
import com.talissonmelo.servico.EscolaServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/escolas")
public class EscolaControlador implements EscolaControladorDocumentacao {

	private static final Logger log = LoggerFactory.getLogger(EscolaControlador.class);

	@Autowired
	private EscolaServico servico;

	@GetMapping(value = "/nomes")
	public ResponseEntity<List<Escola>> findAll(@RequestParam(value = "nome", required = false) String nome) {
		log.info("Listando escolas nomes");
		Escola escola = new Escola();
		escola.setNome(nome);
		List<Escola> escolas = servico.listar(escola);
		return ResponseEntity.ok().body(escolas);
	}

	@GetMapping
	public ResponseEntity<List<Escola>> listar() {
		log.info("Listando escolas");
		List<Escola> escolas = servico.listar();
		return ResponseEntity.ok().body(escolas);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Escola> listarPorId(@PathVariable Long id) {
		log.info("Listando escola por Id: {}", id);
		Escola escola = servico.listarPorId(id);
		return ResponseEntity.ok().body(escola);
	}

	@PostMapping
	public ResponseEntity<Escola> persistir(@Valid @RequestBody EscolaDto escolaDto) {
		log.info("Cadastrando escola.");
		Escola escola = servico.salvar(escolaDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(escola.getId()).toUri();
		return ResponseEntity.created(uri).body(escola);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
		log.warn("Deletando escola por Id: {}.", id);
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Escola> atualizarEscola(@PathVariable Long id, @Valid @RequestBody EscolaDto escolaDto) {
		log.warn("Atualizando escola por Id: {}.", id);
		Escola escola = servico.atualizar(id, escolaDto);
		return ResponseEntity.ok().body(escola);
	}
}
