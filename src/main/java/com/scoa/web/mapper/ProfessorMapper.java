package com.scoa.web.mapper;

import com.scoa.web.dto.ProfessorDto;
import com.scoa.web.models.Professor;

public class ProfessorMapper {
    public static Professor mapToProfessor(ProfessorDto professor) {
        Professor professorDto = Professor.builder()
                .id(professor.getId())
                .cpf(professor.getCpf())
                .nome(professor.getNome())
                .matricula(professor.getMatricula())
                .data_nascimento(professor.getData_nascimento())
                .endereco(professor.getEndereco())
                .criado_em(professor.getCriado_em())
                .build();
        return professorDto;
    }

    public static ProfessorDto mapToProfessorDto(Professor professor){
        ProfessorDto professorDto = ProfessorDto.builder()
                .id(professor.getId())
                .cpf(professor.getCpf())
                .nome(professor.getNome())
                .matricula(professor.getMatricula())
                .data_nascimento(professor.getData_nascimento())
                .endereco(professor.getEndereco())
                .criado_em(professor.getCriado_em())
                .build();
        return professorDto;
    }

}
