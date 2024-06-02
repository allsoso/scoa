package com.scoa.web.repository;

import com.scoa.web.models.Aluno;
import com.scoa.web.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurmaRepositorio extends JpaRepository<Turma,Long> {
    Optional<Turma> findByNome(String codigo);
    List<Turma> findByNomeAndCodigo(String nome, String codigo);
}
