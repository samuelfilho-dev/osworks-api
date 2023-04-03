package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.AditivoDTO;
import com.albino.tecnologia.osworks.model.Aditivo;
import com.albino.tecnologia.osworks.service.impl.AditivoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/aditivo")
@RequiredArgsConstructor
@Log4j2
public class AditivoController {

    private final AditivoServiceImpl aditivoService;


    @PostMapping("/data/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Aditivo> adicionarAditivoDeData(@PathVariable Long id,
                                                           @Valid @RequestBody AditivoDTO aditivoDTO){

        log.info("Adição de Aditivo De Data");

        Aditivo aditivoDeData = aditivoService.adicionarAditivoDeData(id, aditivoDTO);

        return new ResponseEntity<>(aditivoDeData, HttpStatus.CREATED);
    }

    @PostMapping("/valor/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Aditivo> adicionarAditivoDenovoValorUnitario
            (@PathVariable Long id, @Valid @RequestBody AditivoDTO aditivoDTO){

        log.info("Adição de Aditivo De Valor Unitario");

        Aditivo aditivoDenovoValorUnitario = aditivoService.adicionarAditivoDenovoValorUnitario(id, aditivoDTO);

        return new ResponseEntity<>(aditivoDenovoValorUnitario, HttpStatus.CREATED);
    }

    @PostMapping("/descricao/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Aditivo> adicionarAditivoDeDescricoes
            (@PathVariable Long id, @Valid @RequestBody AditivoDTO aditivoDTO){

        log.info("Adição de Aditivo De Descrições");

        Aditivo aditivoDeDescricoes = aditivoService.adicionarAditivoDeDescricoes(id, aditivoDTO);

        return new ResponseEntity<>(aditivoDeDescricoes, HttpStatus.CREATED);
    }

    @PostMapping("/tipo/{id}")
    @PreAuthorize("hasRole('ROLE_GP')")
    public ResponseEntity<Aditivo> adicionarAditivoDetiposDeContratos
            (@PathVariable Long id, @Valid @RequestBody AditivoDTO aditivoDTO){

        log.info("Adição de Aditivo De Tipo De Contrato");

        Aditivo aditivoDetiposDeContratos = aditivoService.adicionarAditivoDetiposDeContratos(id, aditivoDTO);

        return new ResponseEntity<>(aditivoDetiposDeContratos, HttpStatus.CREATED);
    }

}
