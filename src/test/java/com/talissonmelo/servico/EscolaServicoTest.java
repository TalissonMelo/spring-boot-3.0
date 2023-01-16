package com.talissonmelo.servico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.talissonmelo.modelo.Escola;
import com.talissonmelo.modelo.dto.EscolaDto;
import com.talissonmelo.modelo.dto.EscolaResposta;
import com.talissonmelo.modelo.exception.EntidadeNaoEncontrada;
import com.talissonmelo.repositorio.EscolaRepositorio;

@ActiveProfiles("test")
@SpringBootTest
public class EscolaServicoTest {

	@Autowired
	EscolaServico escolaServico;

	@Autowired
	EscolaRepositorio escolaRepositorio;

	@Test
	public void validarListarPorId() {
		// Cénario
		escolaRepositorio.deleteAll();
		EscolaResposta escolaResposta = this.escolaServico.salvar(this.criarEscolaDto());

		// Ação ou execução
		Escola escola = this.escolaServico.listarPorId(escolaResposta.getId());

		// Verificação
		Assertions.assertTrue(escola.getId() != null);
		Assertions.assertEquals(escola.getNome(), escola.getNome());
	}

	@Test
	public void DeveLancarErroAoListarPorId() {
		// Cénario
		EscolaResposta escolaResposta = this.escolaServico.salvar(this.criarEscolaDto());

		// Ação ou execução
		Assertions.assertThrows(EntidadeNaoEncontrada.class,
				() -> this.escolaServico.listarPorId(escolaResposta.getId() + 90));

		// Verificação
	}

	private EscolaDto criarEscolaDto() {
		EscolaDto escolaDto = new EscolaDto();
		escolaDto.setNome("My Hero Academy");
		return escolaDto;
	}
}
