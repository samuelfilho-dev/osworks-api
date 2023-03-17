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
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OSDTO {

    @NotBlank(message = "Prencha o Campo Descrição Corretamente")
    @Size(min = 5, max = 100, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String descricao;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime qtdDeHoras;

    @Positive(message = "OS Pontos De Função Precisam ser Positivos")
    private Integer qtdPontosDeFuncao;

    @Positive
    private Long idDoResponsavel;

    @Positive
    private Long idDaEmpresa;

    @Positive
    private Long idDoContrato;

}
