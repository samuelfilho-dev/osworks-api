package com.albino.tecnologia.osworks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @OneToOne
    private Responsavel responsavel;
    @ManyToOne
    private Empresa empresa;
    @OneToOne
    private Projeto projeto;
    @ManyToOne
    private Contrato contrato;
}
