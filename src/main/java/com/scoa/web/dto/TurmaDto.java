package com.scoa.web.dto;

import com.scoa.web.models.Disciplina;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class TurmaDto {
    private Long id;
    private String codigo;
    private String nome;
    private Float quantidade_alunos;
    private LocalDateTime criado_em;
    private LocalDateTime atualizado_em;

    private Set<Disciplina> disciplinas;
}
