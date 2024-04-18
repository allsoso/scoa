package com.scoa.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private Float quantidade_cadeiras;
    @OneToOne
    @JoinColumn(name="codigo_turma",nullable = true)
    private Turma turma;
    @CreationTimestamp
    private LocalDateTime criado_em;
    @UpdateTimestamp
    private LocalDateTime atualizado_em;
}
