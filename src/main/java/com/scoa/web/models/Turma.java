package com.scoa.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private Float quantidade_alunos;
    @ManyToMany
    @JoinTable(name="alocacao_turma_disciplina",
            joinColumns = @JoinColumn(name="codigo_turma"),
            inverseJoinColumns = @JoinColumn(name = "codigo_disciplina"))
    private Set<Disciplina> disciplinas;

    @CreationTimestamp
    private LocalDateTime criado_em;
    @UpdateTimestamp
    private LocalDateTime atualizado_em;
}
