package com.scoa.web.service;

import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Turma;
import com.scoa.web.repository.TurmaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private Turma mapToTurma(TurmaDto turma) {
        Turma turmaDto = Turma.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .codigo(turma.getCodigo())
                .quantidade_alunos(turma.getQuantidade_alunos())
                .criado_em(turma.getCriado_em())
                .build();
        return turmaDto;
    }

    private TurmaDto mapToTurmaDto(Turma turma){
        TurmaDto turmaDto = TurmaDto.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .codigo(turma.getCodigo())
                .quantidade_alunos(turma.getQuantidade_alunos())
                .criado_em(turma.getCriado_em())
                .build();
        return turmaDto;
    }
}
