package com.albino.tecnologia.osworks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String valor;
    private String descricao;
    private String produtoOuServico;
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<OS> os;
}
