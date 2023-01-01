package com.talissonmelo.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/escolas")
public class EscolaControlador {

	@GetMapping
	public String iniciar() {
		return "Iniciando...";
	}
}
