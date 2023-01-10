package com.talissonmelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.modelo.Professor;

public interface ProfessorRepositorio extends JpaRepository<Professor, Long> {

}
