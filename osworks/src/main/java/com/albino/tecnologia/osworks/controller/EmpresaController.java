package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.service.impl.EmpresaServiceImpl;
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
    public ResponseEntity<Page<Empresa>> listarTodasEmpresas(
            @RequestParam(value = "status",required = false) String status, Pageable pageable){

        Page<Empresa> empresaList = empresaService.listarTodasEmpresas(status, pageable);

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
    public Empresa atualizarEmpresa(@PathVariable Long id,@Valid @RequestBody EmpresaDTO empresaDTO){
        return empresaService.atualizarEmpresa(id,empresaDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Void> inativarEmpresa(@PathVariable Long id){

        empresaService.inativarEmpresa(id);

        return ResponseEntity.noContent().build();
    }
}
