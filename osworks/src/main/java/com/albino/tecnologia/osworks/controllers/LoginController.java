package com.albino.tecnologia.osworks.controllers;

import com.albino.tecnologia.osworks.controllers.dto.LoginDTO;
import com.albino.tecnologia.osworks.models.UserModel;
import com.albino.tecnologia.osworks.repositories.UserModelRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public LoginResult login(@Valid @RequestBody LoginDTO loginDTO) {

        UserModel userModel = userModelRepository.findByUsername(loginDTO.getUsername()).get();

        userModel.setLastAccess(LocalDateTime.now());
        String role = userModel.getRoles().get(0).getRoleName().toString();
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), userModel.getPassword());

        log.info("The User '{}' login in {} ", userModel.getName(), LocalDateTime.now());

        return new LoginResult(role, matches);
    }

    @Getter
    @AllArgsConstructor
    private final class LoginResult {

        private final String role;
        private final boolean matches;

    }

}
