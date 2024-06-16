package com.scoa.web.service;

import com.scoa.web.models.Aluno;
import com.scoa.web.models.Disciplina;
import com.scoa.web.models.InfoAluno;

import java.util.List;
import java.util.Optional;

public interface InfoAlunoService {
    InfoAluno insertNotaForAluno(InfoAluno infoAluno);

    List<InfoAluno> findAllNotaByDisciplina(Long id);
    Optional<InfoAluno> findByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);
}
