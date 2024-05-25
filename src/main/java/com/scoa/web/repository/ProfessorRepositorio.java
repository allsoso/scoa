package com.scoa.web.repository;

import com.scoa.web.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepositorio extends JpaRepository<Professor, Long> {
    Optional<Professor> findByNome(String cpf);
    List<Professor> findByNomeAndCpf(String nome, String cpf);
}
