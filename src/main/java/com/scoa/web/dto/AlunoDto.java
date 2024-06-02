package com.scoa.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AlunoDto {
    private Long id;
    private String matricula;
    @NotEmpty(message = "Nome do aluno é obrigatório")
    private String nome;
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotEmpty(message = "Endereco do aluno é obrigatório")
    private String endereco;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_nascimento;
    private LocalDateTime criado_em;
}

@Constraint(validatedBy = CPFValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@interface CPF {
    String message() default "Invalid CPF";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


class CPFValidator implements ConstraintValidator<CPF, String> {
    @Override
    public void initialize(CPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) {
            return true; // Can handle null or empty CPF as valid if required
        }
        return isValidCPF(cpf);
    }

    private boolean isValidCPF(String cpf) {
        // Remove any non-digit characters
        cpf = cpf.replaceAll("\\D", "");

        // CPF must be 11 digits long
        if (cpf.length() != 11) {
            return false;
        }

        // Check for known invalid CPFs
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validate CPF digits
        int[] cpfArray = cpf.chars().map(c -> c - '0').toArray();
        for (int j = 9; j < 11; j++) {
            int sum = 0;
            for (int i = 0; i < j; i++) {
                sum += cpfArray[i] * ((j + 1) - i);
            }
            int digit = (sum * 10) % 11;
            if (digit == 10) {
                digit = 0;
            }
            if (digit != cpfArray[j]) {
                return false;
            }
        }
        return true;
    }
}