package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.LoginDTO;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public Boolean login(@Valid @RequestBody LoginDTO loginDTO){

        Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername()).get();
        usuario.setUltimoAcesso(LocalDateTime.now());
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword());

        return matches;
    }
}
