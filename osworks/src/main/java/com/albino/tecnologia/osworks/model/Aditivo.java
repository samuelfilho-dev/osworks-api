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
public class Aditivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pontosFuncao;

    private LocalDate dataTermino;

    private BigDecimal valorUnitario;

    @ElementCollection
    @Column
    private List<TipoDeContrato> tipoDeContrato;

    @ElementCollection
    @Column(name = "descricao",columnDefinition = "TEXT")
    private List<String> descricoes;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

}
