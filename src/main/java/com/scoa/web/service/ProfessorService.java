package com.scoa.web.service;

import com.scoa.web.dto.ProfessorDto;
import com.scoa.web.dto.ProfessorDto;
import com.scoa.web.models.Professor;

import java.util.List;

public interface ProfessorService {

    List<ProfessorDto> findAllProfessores();
    Professor saveProfessor(ProfessorDto professorDto);
    ProfessorDto findProfessorById(long professorId);
    List<ProfessorDto> findProfessoresByParams(String nome, String cpf);

    void updateProfessor(ProfessorDto professor);

    void delete(Long professorId);
}
