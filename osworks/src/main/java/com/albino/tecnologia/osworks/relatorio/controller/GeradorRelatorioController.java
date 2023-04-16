package com.albino.tecnologia.osworks.relatorio.controller;

import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;
import com.albino.tecnologia.osworks.relatorio.gerador.impl.GeradorRelatorioContratoService;
import com.albino.tecnologia.osworks.relatorio.gerador.impl.GeradorRelatorioOSService;
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
@RequestMapping("/api/v1/relatorio")
@RequiredArgsConstructor
@Log4j2
public class GeradorRelatorioController {

    private final GeradorRelatorioContratoService contratoService;
    private final GeradorRelatorioOSService osService;

    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasRole('ROLE_DIRETOR')")
    public void gerarRelatorioDeContrato(HttpServletResponse response , @PathVariable Long id){

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio_" + dataAtual + ".pdf";

        response.setHeader(headerKey,headerValue);

        contratoService.exportar(response,id);
    }

    @GetMapping("/os/{id}")
    @PreAuthorize("hasRole('ROLE_DIRETOR')")
    public void gerarRelatorioDeOS(HttpServletResponse response , @PathVariable Long id){

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio_os_" + dataAtual + ".pdf";

        response.setHeader(headerKey,headerValue);

        osService.exportar(response,id);
    }

}
