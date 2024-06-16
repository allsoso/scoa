package com.scoa.web.service;

import com.scoa.web.dto.SalaDto;
import com.scoa.web.models.Sala;
import com.scoa.web.repository.SalaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static com.scoa.web.mapper.SalaMapper.mapToSala;
import static com.scoa.web.mapper.SalaMapper.mapToSalaDto;

@Service
public class SalaServiceImpl implements SalaService {
    private SalaRepositorio salaRepositorio;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SalaServiceImpl(SalaRepositorio salaRepositorio) {
        this.salaRepositorio = salaRepositorio;
    }

    @Override
    public List<SalaDto> findAllSalas() {
        List<Sala> salas = salaRepositorio.findAll();
        return salas.stream().map((sala) -> mapToSalaDto(sala)).collect(Collectors.toList());
    }

    @Override
    public List<SalaDto> findAllSalasByTurma(long turmaId) {
        Optional<Sala> salas = salaRepositorio.findByTurma_Id(turmaId);
        return salas.stream().map((sala) -> mapToSalaDto(sala)).collect(Collectors.toList());
    }
    @Override
    public Sala saveSala(SalaDto salaDto) {
        Sala sala = mapToSala(salaDto);
        return salaRepositorio.save(sala);
    }

    @Override
    public SalaDto findSalaById(long salaId) {
        Sala sala = salaRepositorio.findById(salaId).get();
        return mapToSalaDto(sala);
    }

    @Override
    public List<SalaDto> findSalasByParams(String nome, String codigo) {
        List<Sala> salas =  salaRepositorio.findByNomeAndCodigo(nome,codigo);
        return salas.stream().map((sala) -> mapToSalaDto(sala)).collect(Collectors.toList());
    }

    @Override
    public void updateSala(SalaDto salaDto) {
        Sala sala = mapToSala(salaDto);
        salaRepositorio.save(sala);
    }

    @Override
    public void delete(Long salaId) {
        salaRepositorio.deleteById(salaId);
    }

}
