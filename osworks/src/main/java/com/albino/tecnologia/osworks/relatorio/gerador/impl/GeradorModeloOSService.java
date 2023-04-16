package com.albino.tecnologia.osworks.relatorio.gerador.impl;

import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;
import com.albino.tecnologia.osworks.relatorio.gerador.Rodape;
import com.albino.tecnologia.osworks.repository.OSRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
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
public class GeradorModeloOSService implements GeradorPDF {

    private final OSRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void gerarRelatorio(Long id, HttpServletResponse response) {

        Document document = new Document(PageSize.A4);
        OS os = repository.findById(id).get();

        log.info("Gerando a OS '{}' ",os.getCodigoDaOS());

        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new Rodape());
            document.open();

            gerarCabecalho(document,os);
            gerarCorpo(document,os);

            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void exportar(HttpServletResponse response, Long id) {
        gerarRelatorio(id,response);
    }

    private void gerarCabecalho(Document document, OS os){


        try {
            Image logo = Image.getInstance("icon/logo.png");
            logo.scaleToFit(150, 150);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
        } catch (IOException e) {
            log.error("Erro a Iniciar a Logo");
            log.error(e.getLocalizedMessage());
        }


        Paragraph title = new Paragraph("Ordem de Serviço", new Font(Font.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph osInfo = new Paragraph();
        osInfo.add(Chunk.NEWLINE);
        osInfo.setAlignment(Element.ALIGN_CENTER);
        osInfo.setSpacingBefore(20);
        document.add(osInfo);


        LineSeparator separator = new LineSeparator();
        separator.setLineColor(Color.black);
        separator.setLineWidth(0.5f);
        document.add(separator);
    }

    private void gerarCorpo(Document document, OS os){

        Font boldFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);

        Color whiteColor = new Color(225, 225, 225);

        PdfPTable tabela = new PdfPTable(2);
        tabela.setWidthPercentage(100);
        tabela.getDefaultCell().setBorder(0);

        tabela.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);

        tabela.getDefaultCell().setBackgroundColor(Color.darkGray);


        tabela.getDefaultCell().setPadding(10);

        PdfPCell celula = new PdfPCell(new Phrase("Número da OS:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getCodigoDaOS(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Empresa:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getEmpresa().getRazaoSocial(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Descrição:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getDescricao(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Data de Abertura:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getDataDeAbertura().format(formatter), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Qtd. De Horas:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getQtdDeHoras().toString(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Qtd. De Pontos De Função (PF):", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getQtdPontosDeFuncao().toString(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Status:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);

        String status = os.getStatus();
        celula = new PdfPCell(new Phrase(status.substring(0,1).toUpperCase() +
                status.substring(1).toLowerCase(), normalFont));
        tabela.addCell(celula);

        celula = new PdfPCell(new Phrase("Responsável:", boldFont));
        celula.setBackgroundColor(whiteColor);
        tabela.addCell(celula);
        celula = new PdfPCell(new Phrase(os.getResponsavel().getNome(), normalFont));
        tabela.addCell(celula);

        document.add(tabela);

    }

}
