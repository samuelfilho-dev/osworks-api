package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.UsuarioDTO;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> encontrarPeloId(@PathVariable Long id){

        Usuario usuario = usuarioService.encontrarPeloId(id);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Usuario>> listarTodosUsuarios(Pageable pageable){

        Page<Usuario> usuarios = usuarioService.listarTodosUsuarios(pageable);

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuarioCriado = usuarioService.criarUsuario(usuarioDTO);

        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/senha/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> trocarSenha(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioService.trocarSenha(id, usuarioDTO);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/senha/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> trocarSenhaPorUsername(@PathVariable String username,
                                                          @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioService.trocarSenhaPorUsername(username, usuarioDTO);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/senha/email/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> trocarSenhaPorEmail(@PathVariable String email,
                                                       @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioService.trocarSenhaPorEmail(email, usuarioDTO);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> inativarUsuario(@PathVariable Long id){

        usuarioService.inativarUsuario(id);

        return ResponseEntity.noContent().build();
    }

}
