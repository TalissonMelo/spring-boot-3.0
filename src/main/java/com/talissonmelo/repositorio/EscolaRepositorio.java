package com.talissonmelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.modelo.Escola;

public interface EscolaRepositorio extends JpaRepository<Escola, Long> {

}
