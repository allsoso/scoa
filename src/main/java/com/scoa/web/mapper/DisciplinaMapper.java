package com.scoa.web.mapper;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.models.Disciplina;

public class DisciplinaMapper {
    public static Disciplina mapToDisciplina(DisciplinaDto disciplina) {
        Disciplina disciplinaDto = Disciplina.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .codigo(disciplina.getCodigo())
                .horario(disciplina.getHorario())
                .professor(disciplina.getProfessor())
                .turmas(disciplina.getTurmas())
                .build();
        return disciplinaDto;
    }

    public static DisciplinaDto mapToDisciplinaDto(Disciplina disciplina){
        DisciplinaDto disciplinaDto = DisciplinaDto.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .codigo(disciplina.getCodigo())
                .horario(disciplina.getHorario())
                .professor(disciplina.getProfessor())
                .turmas(disciplina.getTurmas())
                .build();
        return disciplinaDto;
    }
}
