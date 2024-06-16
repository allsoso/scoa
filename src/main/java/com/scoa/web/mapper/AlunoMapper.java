package com.scoa.web.mapper;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;

public class AlunoMapper {
    public static Aluno mapToAluno(AlunoDto aluno) {
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

    public static AlunoDto mapToAlunoDto(Aluno aluno){
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
