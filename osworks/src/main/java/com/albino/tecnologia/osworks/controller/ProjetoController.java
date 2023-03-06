package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.service.impl.ProjetoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projeto")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoServiceImpl projetoService;

    @GetMapping("/{id}")
    public Projeto encontrarPeloId(@PathVariable Long id) {
        return projetoService.encontrarPeloId(id);
    }

    @GetMapping
    public List<Projeto> listarTodosProjetos() {
        return projetoService.listarTodosProjetos();
    }

    @PostMapping
    public Projeto criarProjeto(@RequestBody ProjetoDTO projetoDTO) {
        return projetoService.criarProjeto(projetoDTO);
    }

    @PutMapping("/{id}")
    public Projeto atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
        return projetoService.atualizarProjeto(id, projetoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
    }
}
