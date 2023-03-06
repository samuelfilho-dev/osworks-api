package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.service.impl.OSServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/os")
@RequiredArgsConstructor
public class OSController {

    private final OSServiceImpl osService;

    @GetMapping("/{id}")
    public OS encontrarPeloId(@PathVariable Long id){
        return osService.encontrarPeloId(id);
    }

    @GetMapping
    public List<OS> listarTodasOS(){
        return osService.listarTodasOS();
    }

    @PostMapping
    public OS criarOS(@RequestBody OSDTO osdto){
        return osService.criarOS(osdto);
    }

    @PutMapping("/{id}")
    public OS atualizarOS(@PathVariable Long id, @RequestBody OSDTO osdto){
        return osService.atualizarOS(id,osdto);
    }

    @DeleteMapping("/{id}")
    public void deletarOS(@PathVariable Long id){
        osService.deletarOS(id);
    }

}
