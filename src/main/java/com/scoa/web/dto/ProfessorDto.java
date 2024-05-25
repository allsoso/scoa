package com.scoa.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ProfessorDto {
    private Long id;
    private String matricula;
    @NotEmpty(message = "Nome do professor é obrigatório")
    private String nome;
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotEmpty(message = "Endereco do professor é obrigatório")
    private String endereco;
    private LocalDate data_nascimento;
    private LocalDateTime criado_em;
}
