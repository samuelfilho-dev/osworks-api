package com.albino.tecnologia.osworks.controller.dto;

import com.albino.tecnologia.osworks.enums.TipoDeContrato;
import com.albino.tecnologia.osworks.infra.jackson.deserializer.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AditivoDTO {

    @Positive(message = "Prencha o Campo Pontos De Função Com Número Positivo")
    private Long pontosFuncao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataTermino;

    @Positive(message = "Prencha o Valor Com Número Positivo")
    @JsonSerialize
    private BigDecimal valorUnitario;

    @NotNull(message = "Prencha o Tipo De Contrato Corretamente")
    private List<TipoDeContrato> tipoDeContrato;

    @NotNull(message = "Prencha o Descrição Corretamente")
    private List<String> descricoes;
}
