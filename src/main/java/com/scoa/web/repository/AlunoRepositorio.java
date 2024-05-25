package com.scoa.web.repository;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNome(String cpf);
    List<Aluno> findByNomeAndCpf(String nome, String cpf);

}
