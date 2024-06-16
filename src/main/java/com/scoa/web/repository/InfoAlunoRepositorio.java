package com.scoa.web.repository;

import com.scoa.web.models.Aluno;
import com.scoa.web.models.Disciplina;
import com.scoa.web.models.InfoAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InfoAlunoRepositorio extends JpaRepository<InfoAluno, Long> {

    List<InfoAluno> findByDisciplina_Id(Long id);
    Optional<InfoAluno> findByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);

}
