package com.scoa.web.service;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.models.Disciplina;
import com.scoa.web.repository.DisciplinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static com.scoa.web.mapper.DisciplinaMapper.mapToDisciplina;
import static com.scoa.web.mapper.DisciplinaMapper.mapToDisciplinaDto;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {
    private DisciplinaRepositorio disciplinaRepositorio;

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<DisciplinaDto> findAllDisciplinasByTurma(long turmaId) {
        List<Disciplina> disciplinas = disciplinaRepositorio.findByTurmas_Id(turmaId);
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

    @Transactional
    public void clearProfessorFromDisciplinas(Long professorId) {
        entityManager.createQuery("UPDATE Disciplina d SET d.professor = null WHERE d.professor.id = :professorId")
                .setParameter("professorId", professorId)
                .executeUpdate();
    }

    @Override
    public void delete(Long disciplinaId) {
        disciplinaRepositorio.deleteById(disciplinaId);
    }

}
