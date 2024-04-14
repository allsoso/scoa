package com.scoa.web.service;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.repository.AlunoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService{
    private AlunoRepositorio alunoRepositorio;

    @Autowired
    public AlunoServiceImpl(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    @Override
    public List<AlunoDto> findAllAlunos() {
        List<Aluno> alunos = alunoRepositorio.findAll();
        return alunos.stream().map((aluno) -> mapToAlunoDto(aluno)).collect(Collectors.toList());
    }

    @Override
    public Aluno saveAluno(Aluno aluno) {
        return alunoRepositorio.save(aluno);
    }

    private AlunoDto mapToAlunoDto(Aluno aluno){
        AlunoDto alunoDto = AlunoDto.builder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .data_nascimento(aluno.getData_nascimento())
                .endereco(aluno.getEndereco())
                .criado_em(aluno.getCriado_em())
                .build();
        return alunoDto;
    }
}
