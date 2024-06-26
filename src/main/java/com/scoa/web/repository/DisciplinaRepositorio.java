package com.scoa.web.repository;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.models.Disciplina;
import com.scoa.web.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepositorio extends JpaRepository<Disciplina,Long> {
    Optional<Disciplina> findByCodigo(String codigo);
    List<Disciplina> findByNomeAndCodigo(String nome, String codigo);
    List<Disciplina> findByTurmas_Id(Long id);
}
