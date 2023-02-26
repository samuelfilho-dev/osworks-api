package com.albino.tecnologia.osworks.controller.dto;

import com.albino.tecnologia.osworks.model.TipoDeEmpresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private String CNPJ;
    private String razaoSocial;
    private TipoDeEmpresa tipoDeEmpresa;
    private String inscricaoEstadual;
    private String numeroDeTelefone;
    private String email;
    private LocalDate dataDeNascimento;
}
