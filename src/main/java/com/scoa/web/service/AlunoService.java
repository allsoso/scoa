package com.scoa.web.service;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;

import java.util.List;

public interface AlunoService {
    List<AlunoDto> findAllAlunos();
    Aluno saveAluno(Aluno aluno);
}
