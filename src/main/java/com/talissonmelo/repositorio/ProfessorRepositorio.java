package com.talissonmelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.talissonmelo.modelo.Professor;

public interface ProfessorRepositorio extends JpaRepository<Professor, Long> {

	@Query(nativeQuery = true, value = "select 	coalesce(max(pro.numero), 0) from professor pro where pro.id_escola = :idEscola")
	Integer numeroMaximoProfessor(Long idEscola);
}
