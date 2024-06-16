package com.scoa.web.repository;

import com.scoa.web.models.Disciplina;
import com.scoa.web.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaRepositorio extends JpaRepository<Sala,Long> {
    Optional<Sala> findByCodigo(String codigo);
    List<Sala> findByNomeAndCodigo(String nome, String codigo);
    Optional<Sala> findByTurma_Id(Long id);
}
