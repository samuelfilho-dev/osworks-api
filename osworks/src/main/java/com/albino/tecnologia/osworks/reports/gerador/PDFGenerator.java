package com.albino.tecnologia.osworks.reports.gerador;

import javax.servlet.http.HttpServletResponse;

public interface PDFGenerator {
    void generateReport(Long id, HttpServletResponse response);

    void export(HttpServletResponse response, Long id);
}
