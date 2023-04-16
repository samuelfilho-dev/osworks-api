package com.albino.tecnologia.osworks.relatorio.gerador;

import javax.servlet.http.HttpServletResponse;

public interface GeradorPDF {
    public void gerarRelatorio(Long id, HttpServletResponse response);

    public void exportar(HttpServletResponse response, Long id);
}
