package com.scoa.web.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class TurmaDto {
    private Long codigo;
    private String nome;
    private Float quantidade_alunos;
    private LocalDateTime criado_em;
    private LocalDateTime atualizado_em;
}
