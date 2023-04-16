package com.albino.tecnologia.osworks.relatorio.gerador.impl;

import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;
import com.albino.tecnologia.osworks.relatorio.gerador.Rodape;
import com.albino.tecnologia.osworks.repository.OSRepository;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
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
public class GeradorRelatorioOSService implements GeradorPDF {

    private final OSRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void gerarRelatorio(Long id, HttpServletResponse response) {

        try {

            Document document = new Document(PageSize.A4);
            OS os = repository.findById(id).get();

            log.info("Criando um Novo Relatorio da OS com Id: '{}'", id);

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new Rodape());
            document.open();

            gerarCabecalho(document, os);
            gerarCorpo(document, os);

            document.close();

        } catch (IOException e) {
            log.error("Erro a tentar Processar o relatorio da OS");
            log.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void exportar(HttpServletResponse response, Long id) {
        gerarRelatorio(id, response);
    }

    private void gerarCabecalho(Document document, OS os) {

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

        Paragraph cabecalho = new Paragraph("RELATÓRIO DA ORDEM DE SERVIÇO " + os.getCodigoDaOS(),
                new Font(Font.TIMES_ROMAN, 18, Font.BOLD));
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        document.add(cabecalho);

        document.add(new Paragraph(" "));

        var separator = new LineSeparator();
        separator.setLineColor(Color.DARK_GRAY);
        document.add(separator);

        document.add(new Paragraph(" "));
    }

    private void gerarCorpo(Document document, OS os) {

        Font boldFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);

        PdfPTable tableOS = new PdfPTable(2);
        tableOS.setWidthPercentage(100);
        tableOS.setSpacingBefore(10);
        tableOS.setSpacingAfter(10);

        // Adicionando título da tabela
        PdfPCell cellTituloOS = new PdfPCell(new Phrase("Informações Da OS:", new Font
                (Font.HELVETICA, 16, Font.BOLD)));
        cellTituloOS.setColspan(2);
        cellTituloOS.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTituloOS.setBackgroundColor(new Color(191, 191, 191));
        tableOS.addCell(cellTituloOS);

        // Adicionando informações da OS à tabela
        PdfPCell cellCodigo = new PdfPCell(new Phrase("Código da OS:", boldFont));
        PdfPCell cellValorCodigo = new PdfPCell(new Phrase(os.getCodigoDaOS(), normalFont));


        PdfPCell cellDescricao = new PdfPCell(new Phrase("Descrição:", boldFont));
        PdfPCell cellValorDescricao = new PdfPCell(new Phrase(os.getDescricao(), normalFont));


        PdfPCell cellQtdHoras = new PdfPCell(new Phrase("Quantidade de horas:", boldFont));
        PdfPCell cellValorQtdHoras = new PdfPCell(new Phrase(os.getQtdDeHoras().toString(), normalFont));


        PdfPCell cellDataAbertura = new PdfPCell(new Phrase("Data de abertura:", boldFont));
        PdfPCell cellValorDataAbertura = new PdfPCell(new Phrase(os.getDataDeAbertura().format(formatter), normalFont));


        String status = os.getStatus();
        PdfPCell cellStatus = new PdfPCell(new Phrase("Status:", boldFont));
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

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // Título da tabela
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);

        PdfPCell tituloEmpresa = new PdfPCell(new Phrase("Informações Da Empresa ", new Font
                (Font.HELVETICA, 16, Font.BOLD)));
        tituloEmpresa.setColspan(2);
        tituloEmpresa.setHorizontalAlignment(Element.ALIGN_CENTER);
        tituloEmpresa.setBackgroundColor(new Color(191, 191, 191));
        tituloEmpresa.setBorderColor(new Color(0, 0, 0));
        tituloEmpresa.setBorderWidth(1f);
        table.addCell(tituloEmpresa);


        // adiciona um espaçamento entre as células
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // adiciona as células à tabela
        table.addCell(new PdfPCell(new Phrase("Razão Social Da Empresa", boldFont)));
        table.addCell(new PdfPCell(new Phrase(os.getEmpresa().getRazaoSocial(), normalFont)));

        table.addCell(new PdfPCell(new Phrase("Endereço Da Empresa", boldFont)));
        PdfPCell enderecoCell = new PdfPCell();
        enderecoCell.addElement(new Phrase("CEP:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getCEP(), normalFont));
        enderecoCell.addElement(new Phrase("Logradouro:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getLogradouro(), normalFont));
        enderecoCell.addElement(new Phrase("Número:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getNumero(), normalFont));
        enderecoCell.addElement(new Phrase("Complemento:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getComplemento(), normalFont));
        enderecoCell.addElement(new Phrase("Bairro:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getBairro(), normalFont));
        enderecoCell.addElement(new Phrase("Cidade:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getCidade(), normalFont));
        enderecoCell.addElement(new Phrase("UF:", boldFont));
        enderecoCell.addElement(new Phrase(os.getEmpresa().getEndereco().getUF(), normalFont));
        table.addCell(enderecoCell);

        table.addCell(new PdfPCell(new Phrase("Responsável", boldFont)));
        PdfPCell responsavelCell = new PdfPCell();
        responsavelCell.addElement(new Phrase("Nome:", boldFont));
        responsavelCell.addElement(new Phrase(os.getEmpresa().getResponsavel().getNome(), normalFont));
        responsavelCell.addElement(new Phrase("Cargo:", boldFont));
        responsavelCell.addElement(new Phrase(os.getEmpresa().getResponsavel().getCargo(), normalFont));
        responsavelCell.addElement(new Phrase("Departamento:", boldFont));
        responsavelCell.addElement(new Phrase(os.getEmpresa().getResponsavel().getDepartamento(), normalFont));
        table.addCell(responsavelCell);

        // adiciona a tabela ao documento
        document.add(table);

        document.add(new Paragraph(" "));

        PdfPCell cell = new PdfPCell();
        cell.setBorderColor(new Color(0, 0, 0));
        cell.setBorderWidth(1f);

        float[] columnWidths = {2, 3};
        table = new PdfPTable(columnWidths);

        table.setWidthPercentage(100);

        PdfPCell cellTituloContrato = new PdfPCell(new Phrase("Informação Do Contrato:", new Font
                (Font.HELVETICA, 16, Font.BOLD)));
        cellTituloContrato.setColspan(2);
        cellTituloContrato.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTituloContrato.setBackgroundColor(new Color(191, 191, 191));
        table.addCell(cellTituloContrato);

        cell = new PdfPCell(new Paragraph("Código do Contrato da OS:", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(os.getContrato().getCodigoDoContrato(), normalFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Data de início do contrato:", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(os.getContrato().getDataInicio().format(formatter), normalFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Data de término do contrato:", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(os.getContrato().getDataTermino().format(formatter), normalFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        document.add(table);
    }
}
