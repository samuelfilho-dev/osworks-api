package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.service.impl.ContratoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contrato")
@RequiredArgsConstructor
public class ContratoController {
    private final ContratoServiceImpl contratoService;

    @GetMapping("/{id}")
    public Contrato encontrarPeloIdContrato(@PathVariable Long id){
        return contratoService.encontrarPeloIdContrato(id);
    }

    @GetMapping
    public List<Contrato> listarTodosContratos(){
        return contratoService.listarTodosContratos();
    }

    @PostMapping
    public Contrato criarContrato(@RequestBody ContratoDTO contratoDTO){
        return contratoService.criarContrato(contratoDTO);
    }

    @PutMapping("/{id}")
    public Contrato atualizarContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO){
        return contratoService.atualizarContrato(id,contratoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarContrato(@PathVariable Long id){
        contratoService.deletarContrato(id);
    }
}

