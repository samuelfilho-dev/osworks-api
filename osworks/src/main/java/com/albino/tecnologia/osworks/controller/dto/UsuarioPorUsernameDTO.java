package com.albino.tecnologia.osworks.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPorUsernameDTO {

    @NotBlank(message = "Prencha o Campo Status Do Projeto Corretamente")
    @Size(min = 3, max = 15, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String usernameDoUsuario;

}
