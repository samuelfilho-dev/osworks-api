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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private BigDecimal valor;
    private String descricao;
    private TipoDeContrato tipoDeContrato;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contrato_id")
    @JsonIgnoreProperties("contrato")
    private List<OS> os = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
