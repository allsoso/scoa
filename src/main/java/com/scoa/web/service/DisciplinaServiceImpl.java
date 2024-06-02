package com.scoa.web.service;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.models.Disciplina;
import com.scoa.web.repository.DisciplinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {
    private DisciplinaRepositorio disciplinaRepositorio;

    @Autowired
    public DisciplinaServiceImpl(DisciplinaRepositorio disciplinaRepositorio) {
        this.disciplinaRepositorio = disciplinaRepositorio;
    }

    @Override
    public List<DisciplinaDto> findAllDisciplinas() {
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        return disciplinas.stream().map((disciplina) -> mapToDisciplinaDto(disciplina)).collect(Collectors.toList());
    }

    @Override
    public Disciplina saveDisciplina(DisciplinaDto disciplinaDto) {
        Disciplina disciplina = mapToDisciplina(disciplinaDto);
        return disciplinaRepositorio.save(disciplina);
    }

    @Override
    public DisciplinaDto findDisciplinaById(long disciplinaId) {
        Disciplina disciplina = disciplinaRepositorio.findById(disciplinaId).get();
        return mapToDisciplinaDto(disciplina);
    }

    @Override
    public List<DisciplinaDto> findDisciplinasByParams(String nome, String codigo) {
        List<Disciplina> disciplinas =  disciplinaRepositorio.findByNomeAndCodigo(nome,codigo);
        return disciplinas.stream().map((disciplina) -> mapToDisciplinaDto(disciplina)).collect(Collectors.toList());
    }

    @Override
    public void updateDisciplina(DisciplinaDto disciplinaDto) {
        Disciplina disciplina = mapToDisciplina(disciplinaDto);
        disciplinaRepositorio.save(disciplina);
    }

    @Override
    public void delete(Long disciplinaId) {
        disciplinaRepositorio.deleteById(disciplinaId);
    }

    private Disciplina mapToDisciplina(DisciplinaDto disciplina) {
        Disciplina disciplinaDto = Disciplina.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .codigo(disciplina.getCodigo())
                .horario(disciplina.getHorario())
                .build();
        return disciplinaDto;
    }

    private DisciplinaDto mapToDisciplinaDto(Disciplina disciplina){
        DisciplinaDto disciplinaDto = DisciplinaDto.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .codigo(disciplina.getCodigo())
                .horario(disciplina.getHorario())
                .build();
        return disciplinaDto;
    }
}
