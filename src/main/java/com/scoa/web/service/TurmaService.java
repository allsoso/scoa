package com.scoa.web.service;

import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Turma;

import java.util.List;

public interface TurmaService {
    List<TurmaDto> findAllTurmas();
    Turma saveTurma(TurmaDto turmaDto);
    TurmaDto findTurmaById(long turmaId);
    List<TurmaDto> findTurmasByParams(String nome, String codigo);
    void updateTurma(TurmaDto turma);
    void delete(Long turmaId);
}
