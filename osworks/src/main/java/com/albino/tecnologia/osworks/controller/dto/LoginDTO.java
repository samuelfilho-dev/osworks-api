package com.albino.tecnologia.osworks.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Prencha o Campo Username Corretamente")
    @Size(min = 3, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String username;

    @NotBlank(message = "Prencha o Campo Senha Corretamente")
    @Size(min = 1, max = 8, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String password;

}
