package com.scoa.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AlunoDto {
    private Long id;
    private String matricula;
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate data_nascimento;
    private LocalDateTime criado_em;
}
