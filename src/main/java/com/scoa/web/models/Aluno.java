package com.scoa.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate data_nascimento;
    @ManyToOne
    @JoinColumn(name="id_turma",nullable = true)
    private Turma turma;
    @CreationTimestamp
    private LocalDateTime criado_em;
    @UpdateTimestamp
    private LocalDateTime atualizado_em;

    @OneToMany(mappedBy = "aluno")
    private Set<InfoAluno> infoAlunos;
}