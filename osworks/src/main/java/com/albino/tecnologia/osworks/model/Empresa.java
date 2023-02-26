package com.albino.tecnologia.osworks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String CNPJ;
    private TipoDeEmpresa tipoDeEmpresa;
    private String razaoSocial;
    private String inscricaoEstadual;
    private String numeroDeTelefone;
    private String email;
    private LocalDate dataDeNascimento;
}
