package com.scoa.web.service;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.models.Disciplina;

import java.util.List;

public interface DisciplinaService {
    List<DisciplinaDto> findAllDisciplinas();
    List<DisciplinaDto> findAllDisciplinasByTurma(long turmaId);
    Disciplina saveDisciplina(DisciplinaDto disciplinaDto);
    DisciplinaDto findDisciplinaById(long disciplinaId);
    List<DisciplinaDto> findDisciplinasByParams(String nome, String codigo);
    void updateDisciplina(DisciplinaDto disciplina);
    void delete(Long disciplinaId);
    void clearProfessorFromDisciplinas(Long professorId);
}
