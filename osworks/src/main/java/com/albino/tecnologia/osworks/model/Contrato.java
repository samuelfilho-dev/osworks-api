package com.albino.tecnologia.osworks.model;

import com.albino.tecnologia.osworks.enums.TipoDeContrato;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

    private String codigoDoContrato;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private LocalDate dataInicio;

    private LocalDate dataTermino;

    private Long qtdDePontosFuncao;

    private Long qtdTotalDePontosFuncao;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotalDoContrato;

    @ElementCollection
    @Column(name = "descricao",columnDefinition = "TEXT")
    private List<String> descricoes = new ArrayList<>();

    private String status;

    @ElementCollection
    @Column(name = "t_contrato",columnDefinition = "TEXT")
    private List<TipoDeContrato> tipoDeContrato;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contrato_id")
    @JsonIgnoreProperties("contrato")
    private List<OS> os = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gp_id")
    private Usuario gerenteDeProjeto;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("contrato")
    private List<Aditivo> aditivos = new ArrayList<>();

}
