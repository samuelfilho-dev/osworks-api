package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ResponsavelDTO;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.service.impl.ResponsavelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/responsavel")
@RequiredArgsConstructor
public class ResponsavelController {
    private final ResponsavelServiceImpl responsavelService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Responsavel> encontrarPeloIdResponsavel(@PathVariable Long id) {

        Responsavel responsavel = responsavelService.encontrarPeloIdResponsavel(id);

        return ResponseEntity.ok(responsavel);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<List<Responsavel>> listarTodosResponsaveis() {

        List<Responsavel> responsavelList = responsavelService.listarTodosResponsaveis();

        return ResponseEntity.ok(responsavelList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Responsavel> criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO) {

        Responsavel responsavelCriado = responsavelService.criarResponsavel(responsavelDTO);

        return new ResponseEntity<>(responsavelCriado, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable Long id, @RequestBody ResponsavelDTO responsavelDTO) {

        Responsavel responsavelAtualizado = responsavelService.atualizarResponsavel(id, responsavelDTO);

        return ResponseEntity.ok(responsavelAtualizado);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Void> deletarResponsavel(@PathVariable Long id) {

        responsavelService.deletarResponsavel(id);

        return ResponseEntity.noContent().build();
    }
}
