package com.albino.tecnologia.osworks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String codigoDaOS;
    private String descricao;
    private LocalTime qtdDeHoras;
    private Integer qtdPontosDeFuncao;
    private LocalDate dataDeAbertura;

}
