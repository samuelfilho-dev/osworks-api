package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.service.impl.ContratoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contrato")
@RequiredArgsConstructor
@Log4j2
public class ContratoController {
    private final ContratoServiceImpl contratoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_FINANCEIRO','ROLE_DIRETOR','ROLE_GP')")
    public ResponseEntity<Contrato> encontrarPeloIdContrato(@PathVariable Long id){

        log.info("Retornando um Contrato");

        Contrato contrato = contratoService.encontrarPeloIdContrato(id);

        return ResponseEntity.ok(contrato);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_FINANCEIRO','ROLE_DIRETOR','ROLE_GP')")
    public ResponseEntity<List<Contrato>> listarTodosContratos(){

        log.info("Retornando um Todos Contratos");

        List<Contrato> contratoList = contratoService.listarTodosContratos();

        return ResponseEntity.ok(contratoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Contrato> criarContrato(@Valid @RequestBody ContratoDTO contratoDTO){

        log.info("Criando Um Contrato");

        Contrato contrato = contratoService.criarContrato(contratoDTO);

        return new ResponseEntity<>(contrato, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Contrato> atualizarContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO){

        log.info("Atualizando Um Contrato");

        Contrato contratoAtualizado = contratoService.atualizarContrato(id, contratoDTO);

        return ResponseEntity.ok(contratoAtualizado);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id){

        log.info("Invativando Um Contrato");

        contratoService.deletarContrato(id);

        return ResponseEntity.noContent().build();
    }
}

