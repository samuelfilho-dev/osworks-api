package com.albino.tecnologia.osworks.model;

import com.albino.tecnologia.osworks.enums.TipoDeContrato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private Long qtdDePontosFuncao;
    private BigDecimal valor;
    private String descricao;
    private TipoDeContrato tipoDeContrato;
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<OS> os;
}
