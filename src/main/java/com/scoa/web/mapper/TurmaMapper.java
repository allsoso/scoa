package com.scoa.web.mapper;

import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Turma;

public class TurmaMapper {
    public static Turma mapToTurma(TurmaDto turma) {
        Turma turmaDto = Turma.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .codigo(turma.getCodigo())
                .quantidade_alunos(turma.getQuantidade_alunos())
                .criado_em(turma.getCriado_em())
                .disciplinas(turma.getDisciplinas())
                .build();
        return turmaDto;
    }
    public static TurmaDto mapToTurmaDto(Turma turma){
        TurmaDto turmaDto = TurmaDto.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .codigo(turma.getCodigo())
                .quantidade_alunos(turma.getQuantidade_alunos())
                .criado_em(turma.getCriado_em())
                .disciplinas(turma.getDisciplinas())
                .build();
        return turmaDto;
    }
}
