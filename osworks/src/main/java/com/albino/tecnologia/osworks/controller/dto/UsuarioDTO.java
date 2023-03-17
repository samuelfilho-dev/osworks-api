package com.albino.tecnologia.osworks.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotBlank(message = "Prencha o Campo Username Corretamente")
    @Size(min = 3, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String username;

    @NotBlank(message = "Prencha o Campo Senha Corretamente")
    @Size(min = 1, max = 8, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String password;

    @NotBlank(message = "Prencha o Campo E-mail Corretamente")
    @Size(min = 5, max = 25, message = "'${validatedValue}' precisa estar entre {min} caracteres")
    private String email;

    @NotNull(message = "Prencha o Campo De Permissões Corretamente")
    private List<Long> roleIds;

}
