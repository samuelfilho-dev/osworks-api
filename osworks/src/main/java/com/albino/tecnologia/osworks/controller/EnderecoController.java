package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.EnderecoDTO;
import com.albino.tecnologia.osworks.model.Endereco;
import com.albino.tecnologia.osworks.service.impl.EnderecoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoServiceImpl enderecoService;

    @GetMapping("/{id}")
    public Endereco encontrarpeloEndereco(@PathVariable Long id){return enderecoService.encontrarPeloEndereco(id); }

    @GetMapping
    public List<Endereco> listarTodosEnderecos(){return enderecoService.listarTodosEnderecos(); }

    @PostMapping
    public Endereco criarEndereco (@RequestBody EnderecoDTO enderecoDTO){return enderecoService.criarEndreco(enderecoDTO); }

    @PutMapping("/{id}")
    public Endereco atualizarEndereco (@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO){
        return enderecoService.atualizarEndereco(id, enderecoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarEndereco (@PathVariable Long id) { enderecoService.deletarEndereco(id);}
}
