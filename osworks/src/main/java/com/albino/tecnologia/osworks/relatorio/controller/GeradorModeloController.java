package com.albino.tecnologia.osworks.relatorio.controller;

import com.albino.tecnologia.osworks.relatorio.gerador.impl.GeradorModeloContratoService;
import com.albino.tecnologia.osworks.relatorio.gerador.impl.GeradorModeloOSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/modelo")
@RequiredArgsConstructor
@Log4j2
public class GeradorModeloController {

    private final GeradorModeloOSService modeloOSService;
    private final GeradorModeloContratoService modeloContratoService;

    @GetMapping("/os/{id}")
    @PreAuthorize("hasAnyRole('ROLE_DIRETOR','ROLE_GP')")
    public void gerarModeloDeOS(HttpServletResponse response , @PathVariable Long id){

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=os_" + dataAtual + ".pdf";

        response.setHeader(headerKey,headerValue);

        modeloOSService.exportar(response,id);
    }

    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasAnyRole('ROLE_DIRETOR','ROLE_GP')")
    public void gerarModeloDeContrato(HttpServletResponse response , @PathVariable Long id){

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contrato_" + dataAtual + ".pdf";

        response.setHeader(headerKey,headerValue);

        modeloContratoService.exportar(response,id);
    }
}
