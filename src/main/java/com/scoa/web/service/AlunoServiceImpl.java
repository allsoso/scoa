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
    public Aluno saveAluno(AlunoDto alunoDto) {
        Aluno aluno = mapToAluno(alunoDto);
        return alunoRepositorio.save(aluno);
    }

    @Override
    public AlunoDto findAlunoById(long alunoId) {
        Aluno aluno = alunoRepositorio.findById(alunoId).get();
        return mapToAlunoDto(aluno);
    }

    @Override
    public List<AlunoDto> findAlunosByParams(String nome, String cpf) {
        List<Aluno> alunos =  alunoRepositorio.findByNomeAndCpf(nome,cpf);
        return alunos.stream().map((aluno) -> mapToAlunoDto(aluno)).collect(Collectors.toList());
    }

    @Override
    public void updateAluno(AlunoDto alunoDto) {
        Aluno aluno = mapToAluno(alunoDto);
        alunoRepositorio.save(aluno);
    }

    @Override
    public void delete(Long alunoId) {
        alunoRepositorio.deleteById(alunoId);
    }

    private Aluno mapToAluno(AlunoDto aluno) {
        Aluno alunoDto = Aluno.builder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .data_nascimento(aluno.getData_nascimento())
                .endereco(aluno.getEndereco())
                .criado_em(aluno.getCriado_em())
                .turma(aluno.getTurma())
                .build();
        return alunoDto;
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
                .turma(aluno.getTurma())
                .build();
        return alunoDto;
    }
}
