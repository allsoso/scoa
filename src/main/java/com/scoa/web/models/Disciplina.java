package com.scoa.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private LocalTime horario;
    @ManyToOne
    @JoinColumn(name="id",nullable = true)
    private Professor professor;

    @ManyToMany
    @JoinTable(name="alocacao_turma_disciplina",
            joinColumns = @JoinColumn(name="codigo_disciplina"),
            inverseJoinColumns = @JoinColumn(name = "codigo_turma"))
    private Set<Turma> turmas;
}
