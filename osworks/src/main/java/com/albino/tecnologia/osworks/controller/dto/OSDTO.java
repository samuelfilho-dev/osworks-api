package com.albino.tecnologia.osworks.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OSDTO {

    private String descricao;
    private LocalTime qtdDeHoras;
    private Integer qtdPontosDeFuncao;
    private LocalDate dataDeAbertura;

}
