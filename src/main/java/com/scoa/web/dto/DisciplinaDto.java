package com.scoa.web.dto;

import com.scoa.web.models.Disciplina;
import com.scoa.web.models.Professor;
import com.scoa.web.models.Turma;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
public class DisciplinaDto {
    private Long id;
    private String codigo;
    private String nome;
    private LocalTime horario;
    private LocalDateTime criado_em;
    private LocalDateTime atualizado_em;

    private Set<Turma> turmas;
    private Professor professor;
}