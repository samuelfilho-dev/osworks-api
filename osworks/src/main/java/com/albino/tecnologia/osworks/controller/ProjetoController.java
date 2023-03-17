package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.service.impl.ProjetoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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
    public ResponseEntity<List<Projeto>> listarTodosProjetos() {

        List<Projeto> projetoList = projetoService.listarTodosProjetos();

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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {

        projetoService.deletarProjeto(id);

        return ResponseEntity.noContent().build();
    }
}
