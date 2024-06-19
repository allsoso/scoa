package com.scoa.web.dto;

import lombok.Builder;
import lombok.Data;

public class AlunoDisciplinaInfoDto {
    private String nomeAluno;
    private String nomeDisciplina;
    private Long nota;
    private Long frequencia;

    // Constructor
    public AlunoDisciplinaInfoDto(String nomeAluno, String nomeDisciplina, Long nota, Long frequencia) {
        this.nomeAluno = nomeAluno;
        this.nomeDisciplina = nomeDisciplina;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    // Getters and Setters
    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public Long getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Long frequencia) {
        this.frequencia = frequencia;
    }
    @Override
    public String toString() {
        return "AlunoDisciplinaInfoDto{" +
                "nomeAluno='" + nomeAluno + '\'' +
                ", nomeDisciplina='" + nomeDisciplina + '\'' +
                ", nota=" + nota +
                ", frequencia=" + frequencia +
                '}';
    }
}

