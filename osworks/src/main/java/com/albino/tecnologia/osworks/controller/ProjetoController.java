package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioPorIdDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioPorUsernameDTO;
import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.service.impl.ProjetoServiceImpl;
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
@RequestMapping("/api/v1/projeto")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoServiceImpl projetoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Projeto> encontrarPeloId(@PathVariable Long id) {

        Projeto projeto = projetoService.encontrarPeloId(id);

        return ResponseEntity.ok(projeto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Page<Projeto>> listarTodosProjetos(Pageable pageable) {

        Page<Projeto> projetoList = projetoService.listarTodosProjetos(pageable);

        return ResponseEntity.ok(projetoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Projeto> criarProjeto(@Valid @RequestBody ProjetoDTO projetoDTO) {

        Projeto projetoCriado = projetoService.criarProjeto(projetoDTO);

        return new ResponseEntity<>(projetoCriado, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {

        Projeto projetoAtualizado = projetoService.atualizarProjeto(id, projetoDTO);

        return ResponseEntity.ok(projetoAtualizado);

    }

    @PutMapping("distribuir/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Projeto> distribuirProjeto(@PathVariable Long id,
                                                     @RequestBody @Valid UsuarioPorIdDTO usuarioDTO) {

        Projeto projetoDistribuido = projetoService.distribuirProjeto(id, usuarioDTO);

        return ResponseEntity.ok(projetoDistribuido);

    }

    @PutMapping("distribuir/username/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Projeto> distribuirProjetoPorUsername
            (@PathVariable Long id, @Valid @RequestBody UsuarioPorUsernameDTO usernameDTO) {

        Projeto projetoDistribuido = projetoService.distribuirProjetoPorUsername(id, usernameDTO);

        return ResponseEntity.ok(projetoDistribuido);

    }

    @PutMapping("encerrar/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Projeto> encerrarProjeto(@PathVariable Long id) {

        Projeto projetoEncerrado = projetoService.encerrarProjeto(id);

        return ResponseEntity.ok(projetoEncerrado);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Void> inativarProjeto(@PathVariable Long id) {

        projetoService.inativarProjeto(id);

        return ResponseEntity.noContent().build();
    }
}
