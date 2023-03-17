package com.albino.tecnologia.osworks.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    @NotBlank(message = "Prencha o Campo CEP Corretamente")
    @Size(min = 2, max = 10, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String cep;

    @NotBlank(message = "Prencha o Campo Logradouro Corretamente")
    @Size(min = 2, max = 50, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String logradouro;

    @NotBlank(message = "Prencha o Campo Número Corretamente")
    @Size(min = 2, max = 10, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String numero;


    @Size(min = 2, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String complemento;

    @NotBlank(message = "Prencha o Campo Bairro Corretamente")
    @Size(min = 2, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String bairro;

    @NotBlank(message = "Prencha o Campo Cidade Corretamente")
    @Size(min = 2, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String cidade;

    @NotBlank(message = "Prencha o Campo UF Corretamente")
    @Size(min = 1, max = 2, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String uf;
}
