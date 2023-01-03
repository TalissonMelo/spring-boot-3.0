package com.talissonmelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.talissonmelo.modelo.Escola;

public interface EscolaRepositorio extends JpaRepository<Escola, Long> {

	@Query(value = "select * from escola", nativeQuery = true)
	List<Escola> listarEscolas();
}
