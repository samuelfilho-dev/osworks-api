package com.albino.tecnologia.osworks.model;

import com.albino.tecnologia.osworks.enums.TipoDeEmpresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String cnpj;
    private TipoDeEmpresa tipoDeEmpresa;
    private String razaoSocial;
    private String inscricaoEstadual;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Responsavel responsavel;
    private String numeroDeTelefone;
    private String email;
    private LocalDate dataDeNascimento;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Endereco endereco;
    private String status;
}
