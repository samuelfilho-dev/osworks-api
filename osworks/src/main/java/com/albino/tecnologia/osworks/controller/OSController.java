package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.service.impl.OSServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/os")
@RequiredArgsConstructor
public class OSController {

    private final OSServiceImpl osService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<OS> encontrarPeloId(@PathVariable Long id){
        OS os = osService.encontrarPeloId(id);

        return ResponseEntity.ok(os);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<List<OS>> listarTodasOS(){

        List<OS> osList = osService.listarTodasOS();

        return ResponseEntity.ok(osList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<OS> criarOS(@Valid @RequestBody OSDTO osdto){

        OS osCriada = osService.criarOS(osdto);

        return new ResponseEntity<>(osCriada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<OS> atualizarOS(@PathVariable Long id, @RequestBody OSDTO osdto){

        OS osAtualizada = osService.atualizarOS(id, osdto);

        return ResponseEntity.ok(osAtualizada);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Void> deletarOS(@PathVariable Long id){

        osService.deletarOS(id);

        return ResponseEntity.noContent().build();
    }

}
