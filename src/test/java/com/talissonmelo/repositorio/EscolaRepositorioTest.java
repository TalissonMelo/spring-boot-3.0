package com.talissonmelo.repositorio;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.talissonmelo.modelo.Escola;

@ActiveProfiles("test")
@SpringBootTest
public class EscolaRepositorioTest {

	@Autowired
	EscolaRepositorio escolaRepositorio;

	@Test
	public void deveBuscarUmaEscolaPorId() {
		// Cénario
		Escola escola = this.escolaRepositorio.save(this.criarEscola());

		// Ação ou execução
		Optional<Escola> optional = this.escolaRepositorio.buscarPorId(escola.getId());

		// Verificação
		Assertions.assertTrue(optional.isPresent());
		Assertions.assertEquals(optional.get().getId(), 1);
		Assertions.assertEquals(optional.get().getNome(), escola.getNome());
	}
	
	@Test
	public void deveRetornarVazioBuscarUmaEscolaPorId() {
		// Cénario
		this.escolaRepositorio.save(this.criarEscola());

		// Ação ou execução
		Optional<Escola> optional = this.escolaRepositorio.buscarPorId((long) 12);

		// Verificação
		Assertions.assertFalse(optional.isPresent());
	}

	public Escola criarEscola() {
		return new Escola("Colégio U.A.");
	}
}
