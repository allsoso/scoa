package com.scoa.web.service;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.repository.AlunoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.scoa.web.mapper.AlunoMapper.mapToAluno;
import static com.scoa.web.mapper.AlunoMapper.mapToAlunoDto;

@Service
public class AlunoServiceImpl implements AlunoService{
    private AlunoRepositorio alunoRepositorio;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AlunoServiceImpl(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    @Override
    public List<AlunoDto> findAllAlunos() {
        List<Aluno> alunos = alunoRepositorio.findAll();
        return alunos.stream().map((aluno) -> mapToAlunoDto(aluno)).collect(Collectors.toList());
    }

    @Override
    public Aluno saveAluno(AlunoDto alunoDto) {
        Aluno aluno = mapToAluno(alunoDto);
        return alunoRepositorio.save(aluno);
    }

    @Override
    public AlunoDto findAlunoById(long alunoId) {
        Aluno aluno = alunoRepositorio.findById(alunoId).get();
        return mapToAlunoDto(aluno);
    }

    @Override
    public List<AlunoDto> findAlunosByParams(String nome, String cpf) {
        List<Aluno> alunos =  alunoRepositorio.findByNomeAndCpf(nome,cpf);
        return alunos.stream().map((aluno) -> mapToAlunoDto(aluno)).collect(Collectors.toList());
    }

    @Override
    public void updateAluno(AlunoDto alunoDto) {
        Aluno aluno = mapToAluno(alunoDto);
        alunoRepositorio.save(aluno);
    }

    @Override
    @Transactional
    public void deleteAllNotaByAlunoId(Long alunoId) {
        entityManager.createQuery("DELETE FROM InfoAluno n WHERE n.aluno.id = :alunoId")
                .setParameter("alunoId", alunoId)
                .executeUpdate();
    }

    @Override
    public void delete(Long alunoId) {
        alunoRepositorio.deleteById(alunoId);
    }

}
