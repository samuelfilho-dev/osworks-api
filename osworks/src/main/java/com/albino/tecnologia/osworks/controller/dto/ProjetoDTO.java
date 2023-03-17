package com.albino.tecnologia.osworks.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {

    @NotBlank(message = "Prencha o Campo Nome Do Projeto Corretamente")
    @Size(min = 5, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String nome;

    @NotBlank(message = "Prencha o Campo Descrição Do Projeto Corretamente")
    @Size(min = 5, max = 100, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeTermino;

    @NotBlank(message = "Prencha o Campo Status Do Projeto Corretamente")
    @Size(min = 3, max = 15, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String status;

    @Positive
    private Long idDaOs;

    @Positive
    private Long idDoUsuario;
}
