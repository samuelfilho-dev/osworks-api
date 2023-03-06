package com.albino.tecnologia.osworks.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelDTO {
    private String CPF;
    private String Nome;
    private String Rg;
    private String NumTelefone;
    private String Email;
    private String Endereco;
    private String Departamento;
    private String Cargo;

}
