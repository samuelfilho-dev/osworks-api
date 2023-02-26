package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.service.impl.EmpresaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaServiceImpl empresaService;

    @GetMapping("/{id}")
    public Empresa encontrarPeloId(@PathVariable Long id){
        return empresaService.encontrarPeloId(id);
    }

    @GetMapping
    public List<Empresa> listarTodasEmpresas(){
        return empresaService.listarTodasEmpresas();
    }

    @PostMapping
    public Empresa criarEmpresa(@RequestBody EmpresaDTO empresaDTO){
        return empresaService.criarEmpresa(empresaDTO);
    }

    @PutMapping("/{id}")
    public Empresa atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO){
        return empresaService.atualizarEmpresa(id,empresaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarEmpresa(@PathVariable Long id){
        empresaService.deletarEmpresa(id);
    }
}
