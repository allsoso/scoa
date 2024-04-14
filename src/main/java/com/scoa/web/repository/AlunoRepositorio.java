package com.scoa.web.repository;

import com.scoa.web.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNome(String cpf);
}
