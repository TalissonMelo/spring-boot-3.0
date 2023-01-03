package com.talissonmelo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.modelo.Escola;
import com.talissonmelo.repositorio.EscolaRepositorio;

@RestController
@RequestMapping(value = "/escolas")
public class EscolaControlador {

	@Autowired
	private EscolaRepositorio repositorio;

	@GetMapping
	public List<Escola> listar() {
		return repositorio.findAll();
	}
}
