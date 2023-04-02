package com.albino.tecnologia.osworks.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private LocalDate dataDeInicio;

    private LocalDate dataDeTermino;

    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    private OS os;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario gerenteDeProjeto;

}
