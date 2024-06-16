package com.scoa.web.mapper;

import com.scoa.web.dto.SalaDto;
import com.scoa.web.models.Sala;

public class SalaMapper {

    public static Sala mapToSala(SalaDto sala) {
        Sala salaDto = Sala.builder()
                .id(sala.getId())
                .nome(sala.getNome())
                .codigo(sala.getCodigo())
                .quantidade_cadeiras(sala.getQuantidade_cadeiras())
                .turma(sala.getTurma())
                .build();
        return salaDto;
    }

    public static SalaDto mapToSalaDto(Sala sala){
        SalaDto salaDto = SalaDto.builder()
                .id(sala.getId())
                .nome(sala.getNome())
                .codigo(sala.getCodigo())
                .quantidade_cadeiras(sala.getQuantidade_cadeiras())
                .turma(sala.getTurma())
                .build();
        return salaDto;
    }
}
