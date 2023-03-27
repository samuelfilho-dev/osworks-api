package com.albino.tecnologia.osworks.controller.dto;

import com.albino.tecnologia.osworks.enums.TipoDeEmpresa;
import com.albino.tecnologia.osworks.infra.jackson.deserializer.LocalDateDeserializer;
import com.albino.tecnologia.osworks.model.Endereco;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

    @CNPJ
    @NotBlank(message = "Prencha o Campo CNPJ Corretamente")
    @Size(min = 2, max = 50, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String cnpj;

    @NotBlank(message = "Prencha o Campo Razão Social Corretamente")
    @Size(min = 5, max = 50, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String razaoSocial;


    private TipoDeEmpresa tipoDeEmpresa;

    @NotBlank(message = "Prencha o Campo Inscrição Estadual Corretamente")
    @Size(min = 5, max = 50, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String inscricaoEstadual;

    @NotBlank(message = "Prencha o Campo Número De Telefone Corretamente")
    @Size(min = 5, max = 50, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String numeroDeTelefone;

    @Size(min = 5, max = 50, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    @Email(message = "Prencha o Campo Email Corretamente")
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataDeNascimento;

    @NotNull(message = "Algum Campo Do Responsavel Está Nulo")
    private Responsavel responsavel;

    @NotNull(message = "Algum Campo Do Endereco Está Nulo")
    private Endereco endereco;
}
