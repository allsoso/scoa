package com.scoa.web.service;

import com.scoa.web.dto.TurmaDto;
import com.scoa.web.mapper.TurmaMapper;
import com.scoa.web.models.Turma;
import com.scoa.web.repository.TurmaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.scoa.web.mapper.SalaMapper.mapToSalaDto;
import static com.scoa.web.mapper.TurmaMapper.mapToTurma;
import static com.scoa.web.mapper.TurmaMapper.mapToTurmaDto;

@Service
public class TurmaServiceImpl implements TurmaService {
    private TurmaRepositorio turmaRepositorio;

    @Autowired
    public TurmaServiceImpl(TurmaRepositorio turmaRepositorio) {
        this.turmaRepositorio = turmaRepositorio;
    }

    @Override
    public List<TurmaDto> findAllTurmas() {
        List<Turma> turmas = turmaRepositorio.findAll();
        return turmas.stream().map((turma) -> mapToTurmaDto(turma)).collect(Collectors.toList());
    }

    @Override
    public Turma saveTurma(TurmaDto turmaDto) {
        Turma turma = mapToTurma(turmaDto);
        return turmaRepositorio.save(turma);
    }

    @Override
    public TurmaDto findTurmaById(long turmaId) {
        Turma turma = turmaRepositorio.findById(turmaId).get();
        return mapToTurmaDto(turma);
    }
    @Override
    public TurmaDto findTurmaByNome(String nome) {
        Optional<Turma> turmaOptional = turmaRepositorio.findByNome(nome);
        return turmaOptional.map(TurmaMapper::mapToTurmaDto).orElse(null);
    }

    @Override
    public List<TurmaDto> findTurmasByParams(String nome, String codigo) {
        List<Turma> turmas =  turmaRepositorio.findByNomeAndCodigo(nome,codigo);
        return turmas.stream().map((turma) -> mapToTurmaDto(turma)).collect(Collectors.toList());
    }

    @Override
    public void updateTurma(TurmaDto turmaDto) {
        Turma turma = mapToTurma(turmaDto);
        turmaRepositorio.save(turma);
    }

    @Override
    public void delete(Long turmaId) {
        turmaRepositorio.deleteById(turmaId);
    }


}
