package com.albino.tecnologia.osworks.controllers.dto;

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

    @NotBlank(message = "Fill the Username Field Correctly")
    @Size(min = 3, max = 25, message = "'${validatedValue}' needs to be between {min} characters")
    private String username;

    @NotBlank(message = "Fill the Password Field Correctly")
    @Size(min = 1, max = 8, message = "'${validatedValue}' needs to be between {min} characters")
    private String password;

}
