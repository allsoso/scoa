package com.scoa.web.dto;

import com.scoa.web.models.Turma;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class SalaDto {
    private Long id;
    private String codigo;
    private String nome;
    private Float quantidade_cadeiras;
    private Turma turma;
    private LocalDateTime criado_em;
    private LocalDateTime atualizado_em;
}
