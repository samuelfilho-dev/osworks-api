package com.albino.tecnologia.osworks.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.br.CPF;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelDTO {

    @NotBlank(message = "Prencha o Campo CPF Corretamente")
    @CPF(message = "'${validatedValue}' é invalido")
    private String cpf;

    @NotBlank(message = "Prencha o Campo Nome Corretamente")
    @Size(min = 2, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String nome;

    @NotBlank(message = "Prencha o Campo RG Corretamente")
    @Size(min = 2, max = 15, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String rg;

    @NotBlank(message = "Prencha o Campo Telefone Corretamente")
    @Size(min = 2, max = 13, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String numDeTelefone;

    @NotBlank(message = "Prencha o Campo E-mail Corretamente")
    @Size(min = 2, max = 20, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String email;

    @NotBlank(message = "Prencha o Campo Departamento Corretamente")
    @Size(min = 2, max = 15, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String departamento;

    @NotBlank(message = "Prencha o Campo Cargo Corretamente")
    @Size(min = 2, max = 15, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String cargo;

}
