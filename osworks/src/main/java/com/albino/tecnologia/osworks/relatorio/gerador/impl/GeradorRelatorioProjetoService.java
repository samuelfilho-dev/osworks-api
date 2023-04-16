package com.albino.tecnologia.osworks.relatorio.gerador.impl;

import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;
import com.albino.tecnologia.osworks.relatorio.gerador.Rodape;
import com.albino.tecnologia.osworks.repository.ProjetoRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Log4j2
public class GeradorRelatorioProjetoService implements GeradorPDF {

    private final ProjetoRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void gerarRelatorio(Long id, HttpServletResponse response) {

        try {

            Document document = new Document(PageSize.A4);
            Projeto projeto = repository.findById(id).get();

            log.info("Criando um Novo Relatorio de Contrato com Id: '{}'", id);

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new Rodape());
            document.open();

            gerarCabecalho(document);
            gerarCorpo(document,projeto);

            document.close();

        } catch (IOException e) {
            log.error("Erro a tentar Processar o relatorio de Projeto");
            log.error(e.getLocalizedMessage());
        }

    }

    @Override
    public void exportar(HttpServletResponse response, Long id) {
        gerarRelatorio(id, response);
    }

    private void gerarCabecalho(Document document) {

        Paragraph titulo = new Paragraph();
        titulo.setAlignment(Element.ALIGN_CENTER);

        try {
            Image logo = Image.getInstance("icon/logo.png");
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleToFit(100, 100);
            titulo.add(logo);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }

        Paragraph cabecalho = new Paragraph("RELATÓRIO DE PROJETO ",
                new Font(Font.TIMES_ROMAN, 18, Font.BOLD));
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        document.add(cabecalho);

        document.add(new Paragraph(" "));

        var separator = new LineSeparator();
        separator.setLineColor(Color.DARK_GRAY);
        document.add(separator);

        document.add(new Paragraph(" "));
    }

    private void gerarCorpo(Document document, Projeto projeto) {

        Font boldFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);

        PdfPTable tabela = new PdfPTable(2);
        tabela.setWidthPercentage(100);

        // Título da tabela
        PdfPCell tituloTabela = new PdfPCell(new Phrase("Informações do Projeto",
                new Font(Font.HELVETICA, 16, Font.BOLD)));
        tituloTabela.setColspan(2);
        tituloTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        tituloTabela.setBackgroundColor(new Color(191, 191, 191));
        tabela.addCell(tituloTabela);

        // Cria células para cada atributo do projeto e adiciona à tabela
        PdfPCell celula = new PdfPCell(new Phrase("Nome do Projeto:", boldFont));
        celula.setBackgroundColor(new Color(240, 240, 240));
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(projeto.getNome(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Descrição:", boldFont));
        celula.setBackgroundColor(new Color(240, 240, 240));
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(projeto.getDescricao(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Data de Início:", boldFont));
        celula.setBackgroundColor(new Color(240, 240, 240));
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(projeto.getDataDeInicio().format(formatter), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Data de Término:", boldFont));
        celula.setBackgroundColor(new Color(240, 240, 240));
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(projeto.getDataDeTermino().format(formatter), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Status:", boldFont));
        celula.setBackgroundColor(new Color(240, 240, 240));
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(capitizarStatus(projeto.getStatus()), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Gerente de Projeto:", boldFont));
        celula.setBackgroundColor(new Color(240, 240, 240));
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(projeto.getGerenteDeProjeto().getNome(), normalFont));
        tabela.addCell(celula);

        document.add(tabela);

        document.add(new Paragraph(" "));

        PdfPTable tableOS = new PdfPTable(2);
        tableOS.setWidthPercentage(100);
        tableOS.setSpacingBefore(10);
        tableOS.setSpacingAfter(10);

        // Adicionando título da tabela
        PdfPCell cellTituloOS = new PdfPCell(new Phrase("OS Do Projeto", new Font
                (Font.HELVETICA, 16, Font.BOLD)));
        cellTituloOS.setColspan(2);
        cellTituloOS.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTituloOS.setBackgroundColor(new Color(191, 191, 191));
        tableOS.addCell(cellTituloOS);

        // Adicionando informações da OS à tabela
        PdfPCell cellCodigo = new PdfPCell(new Phrase("Código da OS:", boldFont));
        cellCodigo.setBackgroundColor(new Color(240,240,240));
        PdfPCell cellValorCodigo = new PdfPCell(new Phrase(
                projeto.getOs().getCodigoDaOS(), normalFont));


        PdfPCell cellDescricao = new PdfPCell(new Phrase("Descrição:", boldFont));
        cellDescricao.setBackgroundColor(new Color(240,240,240));
        PdfPCell cellValorDescricao = new PdfPCell(new Phrase(
                projeto.getOs().getDescricao(), normalFont));


        PdfPCell cellQtdHoras = new PdfPCell(new Phrase("Quantidade de horas:", boldFont));
        cellQtdHoras.setBackgroundColor(new Color(240,240,240));
        PdfPCell cellValorQtdHoras = new PdfPCell(new Phrase(
                projeto.getOs().getQtdDeHoras().toString(), normalFont));


        PdfPCell cellDataAbertura = new PdfPCell(new Phrase("Data de abertura:", boldFont));
        cellDataAbertura.setBackgroundColor(new Color(240,240,240));
        PdfPCell cellValorDataAbertura = new PdfPCell(new Phrase(
                projeto.getOs().getDataDeAbertura().format(formatter), normalFont));


        String status = projeto.getOs().getStatus();
        PdfPCell cellStatus = new PdfPCell(new Phrase("Status:", boldFont));
        cellStatus.setBackgroundColor(new Color(240,240,240));
        PdfPCell cellValorStatus = new PdfPCell(new Phrase(status.substring(0,1).toUpperCase() +
                status.substring(1).toLowerCase(),
                normalFont));

        tableOS.addCell(cellCodigo);
        tableOS.addCell(cellValorCodigo);
        tableOS.addCell(cellDescricao);
        tableOS.addCell(cellValorDescricao);
        tableOS.addCell(cellQtdHoras);
        tableOS.addCell(cellValorQtdHoras);
        tableOS.addCell(cellDataAbertura);
        tableOS.addCell(cellValorDataAbertura);
        tableOS.addCell(cellStatus);
        tableOS.addCell(cellValorStatus);

        document.add(tableOS);
        document.add(new Paragraph(" "));

    }

    private String capitizarStatus(String status){

        String[] palavras = status.split(" ");
        palavras[0] = palavras[0].substring(0, 1).toUpperCase() + palavras[0].substring(1);
        palavras[1] = palavras[1].substring(0, 1).toUpperCase() + palavras[1].substring(1);
        String statusCapitalizado = String.join(" ", palavras);

        return statusCapitalizado;
    }
}
