package com.albino.tecnologia.osworks.controller.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numero;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String valor;
    private String descricao;
    private String produtoOuServico;
}
