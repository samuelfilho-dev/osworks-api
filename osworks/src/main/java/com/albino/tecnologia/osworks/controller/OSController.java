package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.service.impl.OSServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @PreAuthorize("hasAnyRole('ROLE_GP','ROLE_DIRETOR')")
    public ResponseEntity<OS> encontrarPeloId(@PathVariable Long id){
        OS os = osService.encontrarPeloId(id);

        return ResponseEntity.ok(os);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GP','ROLE_DIRETOR')")
    public ResponseEntity<Page<OS>> listarTodasOS(Pageable pageable){

        Page<OS> osList = osService.listarTodasOS(pageable);

        return ResponseEntity.ok(osList);
    }

    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GP','ROLE_DIRETOR')")
    public ResponseEntity<Contrato> mostrarContratoDaOS(@PathVariable Long id){

        Contrato contrato = osService.mostrarContratoDaOS(id);

        return ResponseEntity.ok(contrato);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<OS> criarOS(@PathVariable Long id,@Valid @RequestBody OSDTO osdto){

        OS osCriada = osService.criarOS(id,osdto);

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
    public ResponseEntity<Void> inativarOS(@PathVariable Long id){

        osService.inativarOS(id);

        return ResponseEntity.noContent().build();
    }

}
