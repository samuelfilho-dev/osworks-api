package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.service.impl.ContratoServiceImpl;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contrato")
@RequiredArgsConstructor
@Log4j2
public class ContratoController {
    private final ContratoServiceImpl contratoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_FINANCEIRO','ROLE_DIRETOR','ROLE_GPP')")
    public ResponseEntity<Contrato> encontrarPeloIdContrato(@PathVariable Long id){

        log.info("Retornando um Contrato");

        Contrato contrato = contratoService.encontrarPeloIdContrato(id);

        return ResponseEntity.ok(contrato);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_FINANCEIRO','ROLE_DIRETOR','ROLE_GPP')")
    public ResponseEntity<Page<Contrato>> listarTodosContratos(Pageable pageable){

        log.info("Retornando um Todos Contratos");

        Page<Contrato> contratoList = contratoService.listarTodosContratos(pageable);

        return ResponseEntity.ok(contratoList);
    }

    @GetMapping("/os/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<List<OS>> listarOSDoContrato(@PathVariable Long id){

        log.info("Retornando um Lista de OS do Contrato ");

        List<OS> osDoContrato = contratoService.listarOSDoContrato(id);

        return ResponseEntity.ok(osDoContrato);

    }

    @GetMapping("/vencimento")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<List<Contrato>> listarContratoPorDataDeVencimento(
            @Param("dataDeVencimento") LocalDate dataDeVencimento){

        log.info("Retornando um Lista de OS do Contrato ");

        List<Contrato> contratoPorDataDeVencimento =
                contratoService.listarContratoPorDataDeVencimento(dataDeVencimento);

        return ResponseEntity.ok(contratoPorDataDeVencimento);

    }

    @GetMapping("/vencimento/nd/{numerosDeDias}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<List<Contrato>> listarContratosPorPrazoDeVencimento(
            @PathVariable Integer numerosDeDias){

        log.info("Retornando um Lista de OS do Contrato ");

        List<Contrato> contratoPorDataDeVencimento =
                contratoService.listarContratosPorPrazoDeVencimento(numerosDeDias);

        return ResponseEntity.ok(contratoPorDataDeVencimento);

    }

    @GetMapping("/vencimento/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Integer> verDiaDeVencimento(
            @PathVariable Long id){

        log.info("Retornando um Lista de OS do Contrato ");

        Integer diasParaVecimento = contratoService.verDiaDeVencimento(id);

        return ResponseEntity.ok(diasParaVecimento);

    }

    @GetMapping("/gp/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<List<Contrato>> listarContratoPorGerenteDeProjeto(@PathVariable Long id){

        log.info("Retornando uma Lista do Contrato De Com Gerente de Projeto Com ID: '{}' ",id);

        List<Contrato> contratoPorGerenteDeProjeto = contratoService.listarContratoPorGerenteDeProjeto(id);

        return ResponseEntity.ok(contratoPorGerenteDeProjeto);

    }

    @GetMapping("/gp/relatorio/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Contrato> listarRelatorioDeContrato(@PathVariable Long id){

        log.info("Retornando uma Lista do Contrato De Com Gerente de Projeto Com ID: '{}' ",id);



        return null;

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

    @PutMapping("/distribuir/{id}")
    @PreAuthorize("hasRole('ROLE_GPP')")
    public ResponseEntity<Contrato> distribuirContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO){

        log.info("Distribuindo Um Contrato");

        Contrato contratoAtualizado = contratoService.distribuirContrato(id, contratoDTO);

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

