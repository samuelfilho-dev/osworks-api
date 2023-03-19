package com.albino.tecnologia.osworks.controller.dto;


import com.albino.tecnologia.osworks.enums.TipoDeContrato;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    @Positive(message = "Prencha o Valor Com Número Positivo")
    @JsonSerialize
    private BigDecimal valor;

    @Positive(message = "Prencha o Campo Pontos De Função Com Número Positivo")
    private Long qtdDePontosFuncao;

    @NotBlank(message = "Prencha o Descrição Corretamente")
    @Size(min = 10, max = 255, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String descricao;


    private TipoDeContrato tipoDeContrato;

    @Positive(message = "O id Da Empresa Deverá Ser Positivo")
    private Long idDaEmpresa;

    @Positive(message = "O id Do Responsavel Deverá Ser Positivo")
    private Long idDoResponsavel;

    @Positive
    private Long idDoUsuario;
}
