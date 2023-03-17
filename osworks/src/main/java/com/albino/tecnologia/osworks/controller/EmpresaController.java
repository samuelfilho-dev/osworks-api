package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.service.impl.EmpresaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaServiceImpl empresaService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Empresa> encontrarPeloId(@PathVariable Long id){

        Empresa empresa = empresaService.encontrarPeloId(id);

        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<List<Empresa>> listarTodasEmpresas(@RequestParam(value = "status",required = false) String status){

        List<Empresa> empresaList = empresaService.listarTodasEmpresas(status);

        return ResponseEntity.ok(empresaList);

    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Empresa> criarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO){

        Empresa empresaCriada = empresaService.criarEmpresa(empresaDTO);

        return new ResponseEntity<>(empresaCriada, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public Empresa atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO){
        return empresaService.atualizarEmpresa(id,empresaDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id){

        empresaService.deletarEmpresa(id);

        return ResponseEntity.noContent().build();
    }
}
