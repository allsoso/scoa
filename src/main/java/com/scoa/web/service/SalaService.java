package com.scoa.web.service;

import com.scoa.web.dto.SalaDto;
import com.scoa.web.dto.SalaDto;
import com.scoa.web.models.Sala;

import java.util.List;

public interface SalaService {
    List<SalaDto> findAllSalas();
    List<SalaDto> findAllSalasByTurma(long turmaId);
    Sala saveSala(SalaDto salaDto);
    SalaDto findSalaById(long salaId);
    List<SalaDto> findSalasByParams(String nome, String codigo);
    void updateSala(SalaDto sala);
    void delete(Long salaId);
}
