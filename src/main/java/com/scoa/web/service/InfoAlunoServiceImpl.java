package com.scoa.web.service;

import com.scoa.web.models.Aluno;
import com.scoa.web.models.Disciplina;
import com.scoa.web.models.InfoAluno;
import com.scoa.web.repository.InfoAlunoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InfoAlunoServiceImpl implements InfoAlunoService {
    private InfoAlunoRepositorio infoAlunoRepositorio;
    @Autowired
    public InfoAlunoServiceImpl(InfoAlunoRepositorio infoAlunoRepositorio){this.infoAlunoRepositorio = infoAlunoRepositorio;}
    @Override
    public InfoAluno insertNotaForAluno(InfoAluno infoAluno){
       return infoAlunoRepositorio.saveAndFlush(infoAluno);
    }
    @Override
    public List<InfoAluno> findAllNotaByDisciplina(Long disciplinaId){
        List<InfoAluno> infoAlunos = infoAlunoRepositorio.findByDisciplina_Id(disciplinaId);
        return infoAlunos;
    }
    @Override
    public Optional<InfoAluno> findByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina){
        return infoAlunoRepositorio.findByAlunoAndDisciplina(aluno,disciplina);
    }
}
