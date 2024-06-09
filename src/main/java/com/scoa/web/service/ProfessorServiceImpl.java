package com.scoa.web.service;

import com.scoa.web.dto.ProfessorDto;
import com.scoa.web.models.Professor;
import com.scoa.web.repository.ProfessorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.scoa.web.mapper.ProfessorMapper.mapToProfessor;
import static com.scoa.web.mapper.ProfessorMapper.mapToProfessorDto;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private ProfessorRepositorio professorRepositorio;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepositorio professorRepositorio) {
        this.professorRepositorio = professorRepositorio;
    }

    @Override
    public List<ProfessorDto> findAllProfessores() {
        List<Professor> professores = professorRepositorio.findAll();
        return professores.stream().map((professor) -> mapToProfessorDto(professor)).collect(Collectors.toList());
    }

    @Override
    public Professor saveProfessor(ProfessorDto professorDto) {
        Professor professor = mapToProfessor(professorDto);
        return professorRepositorio.save(professor);
    }

    @Override
    public ProfessorDto findProfessorById(long professorId) {
        Professor professor = professorRepositorio.findById(professorId).get();
        return mapToProfessorDto(professor);
    }

    @Override
    public List<ProfessorDto> findProfessoresByParams(String nome, String cpf) {
        List<Professor> professores =  professorRepositorio.findByNomeAndCpf(nome,cpf);
        return professores.stream().map((professor) -> mapToProfessorDto(professor)).collect(Collectors.toList());
    }

    @Override
    public void updateProfessor(ProfessorDto professorDto) {
        Professor professor = mapToProfessor(professorDto);
        professorRepositorio.save(professor);
    }

    @Override
    public void delete(Long professorId) {
        professorRepositorio.deleteById(professorId);
    }

}
