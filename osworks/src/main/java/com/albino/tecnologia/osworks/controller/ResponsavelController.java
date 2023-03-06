package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.ResponsavelDTO;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.service.impl.ResponsavelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Responsavel")
@RequiredArgsConstructor
public class ResponsavelController {
    private final ResponsavelServiceImpl responsavelService;
    @GetMapping("/{id}")
    public Responsavel encontrarPeloIdResponsavel(@PathVariable Long id){
        return responsavelService.encontrarPeloIdResponsavel(id);
    }

    @GetMapping
    public List<Responsavel> listarTodosResponsaveis(){
        return responsavelService.listarTodosResponsaveis();
    }

    @PostMapping
    public Responsavel criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO){
        return responsavelService.criarResponsavel(responsavelDTO);
    }

    @PutMapping("/{id}")
    public Responsavel atualizarResponsavel(@PathVariable Long id, @RequestBody ResponsavelDTO responsavelDTO){
        return responsavelService.atualizarResponsavel(id,responsavelDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarResponsavel(@PathVariable Long id){ responsavelService.deletarResponsavel(id);}
}
