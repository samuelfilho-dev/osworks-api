package com.albino.tecnologia.osworks.reports.controller;

import com.albino.tecnologia.osworks.reports.gerador.impl.ContractReportServiceGenerator;
import com.albino.tecnologia.osworks.reports.gerador.impl.ServiceOrderReportServiceGenerator;
import com.albino.tecnologia.osworks.reports.gerador.impl.ProjectReportServiceGenerator;
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

    private final ContractReportServiceGenerator contratoService;
    private final ServiceOrderReportServiceGenerator osService;
    private final ProjectReportServiceGenerator projetoService;


    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasRole('ROLE_DIRETOR')")
    public void gerarRelatorioDeContrato(HttpServletResponse response , @PathVariable Long id){

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio_contr_" + dataAtual + ".pdf";

        response.setHeader(headerKey,headerValue);

        contratoService.export(response,id);
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

        osService.export(response,id);
    }

    @GetMapping("/projeto/{id}")
    @PreAuthorize("hasRole('ROLE_DIRETOR')")
    public void gerarRelatorioDeProjeto(HttpServletResponse response , @PathVariable Long id){

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio_proj_" + dataAtual + ".pdf";

        response.setHeader(headerKey,headerValue);

        projetoService.export(response,id);
    }

}
