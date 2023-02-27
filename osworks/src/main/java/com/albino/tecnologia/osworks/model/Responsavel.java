package com.albino.tecnologia.osworks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String CPF;
    private String nome;
    private String Rg;
    private String numTelefone;
    private String email;
    private String endereco;
    private String departamento;
    private String cargo;
}
