package com.albino.tecnologia.osworks.relatorio.gerador.impl;

import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;

import javax.servlet.http.HttpServletResponse;

public class GeradorRelatorioProjetoService implements GeradorPDF {

    @Override
    public void gerarRelatorio(Long id, HttpServletResponse response) {

    }

    @Override
    public void exportar(HttpServletResponse response, Long id) {
        gerarRelatorio(id,response);
    }
}
