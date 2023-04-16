package com.albino.tecnologia.osworks.relatorio.gerador.impl;

import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;
import com.albino.tecnologia.osworks.relatorio.gerador.Rodape;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
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
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Log4j2
public class GeradorRelatorioContratoService implements GeradorPDF {

    private final ContratoRepository contratoRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void gerarRelatorio(Long id, HttpServletResponse response) {

        try {
            Document document = new Document(PageSize.A4);
            Contrato contrato = contratoRepository.findById(id).get();


            log.info("Criando um Novo Relatorio do Contrato com Id: '{}' " + id);

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new Rodape());
            document.open();

            gerarCabecalho(document);
            gerarCorpo(document,contrato);

            document.close();

        } catch (Exception ex) {
            log.error("Erro a tentar Processar o relatorio de Contrato");
            log.error(ex.getLocalizedMessage());
        }

    }

    private void gerarOperacaoValorConsumido(Document document, Contrato contrato) throws DocumentException {

        Font normalBoldFont = new Font(Font.HELVETICA,10,Font.NORMAL);

        BigDecimal pontosDeFuncao = BigDecimal.valueOf(contrato.getQtdDePontosFuncao());
        BigDecimal valorTotal = contrato.getValorTotalDoContrato();
        BigDecimal valorUnitario = contrato.getValorUnitario();
        BigDecimal valorConsumido = valorTotal.subtract(pontosDeFuncao.multiply(valorUnitario));

        BigDecimal valorDisponivel = valorTotal.subtract(valorConsumido);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);


        float[] columnWidths = {2f, 2f, 2f};
        table.setWidths(columnWidths);


        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        PdfPCell cell = new PdfPCell();


        cell.setBackgroundColor(new Color(191, 191, 191));


        cell.setPhrase(new Phrase("Valor Total do Contrato", normalBoldFont));
        table.addCell(cell);


        cell.setPhrase(new Phrase("Valor Consumido", normalBoldFont));
        table.addCell(cell);


        cell.setPhrase(new Phrase("Valor Disponível", normalBoldFont));
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(valorTotal.subtract(valorConsumido))));
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(valorConsumido)));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(valorDisponivel)));
        table.addCell(cell);

        document.add(table);

    }

    private Long contarOSemAndamento(Contrato contrato) {

        List<OS> listaDeOS = contrato.getOs();

        return listaDeOS.stream()
                .filter(os -> os.getStatus().equals("em andamento"))
                .count();
    }

    private Long contarOSFinazlidada(Contrato contrato) {

        List<OS> listaDeOS = contrato.getOs();

        return listaDeOS.stream()
                .filter(os -> os.getStatus().equals("finalizada"))
                .count();
    }

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

        document.add(new Paragraph(" "));
        titulo.add(new Chunk("RELATÓRIO DETALHADO DO CONTRATO", new Font(Font.HELVETICA, 24)));
        document.add(titulo);
        document.add(new Paragraph(" "));

        var separator = new LineSeparator();
        separator.setLineColor(Color.DARK_GRAY);
        document.add(separator);
    }


    private void gerarCorpo(Document document, Contrato contrato) {

        var fontNormal = new Font(Font.HELVETICA,12,Font.BOLD);

        document.add(new Paragraph(" "));

        document.add(new Paragraph("Informações gerais do contrato:",
                new Font(Font.HELVETICA, 14, Font.BOLD)));

        document.add(new Paragraph(" "));

        Paragraph contratoInfo = new Paragraph();
        contratoInfo.setAlignment(Element.ALIGN_JUSTIFIED);
        contratoInfo.add(new Chunk("Código do contrato: ", fontNormal));
        contratoInfo.add(new Chunk(contrato.getCodigoDoContrato(),fontNormal));
        contratoInfo.add(new Chunk("\nValor total do contrato: ", fontNormal));
        contratoInfo.add(new Chunk(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(contrato.getValorTotalDoContrato()),fontNormal));
        contratoInfo.add(new Chunk("\nValor ainda disponível: ",fontNormal));
        contratoInfo.add(new Chunk(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(contrato.getValorTotalDoContrato()),fontNormal));
        contratoInfo.add(new Chunk("\nData de início do contrato: ", fontNormal));
        contratoInfo.add(new Chunk(contrato.getDataInicio().format(formatter),fontNormal));
        contratoInfo.add(new Chunk("\nData de término do contrato: ", fontNormal));
        contratoInfo.add(new Chunk(contrato.getDataTermino().format(formatter),fontNormal));
        contratoInfo.add(new Chunk("\nQuantidade de pontos de função já consumidos: ", fontNormal));
        contratoInfo.add(new Chunk(contrato.getQtdDePontosFuncao().toString(),fontNormal));
        contratoInfo.add(new Chunk("\nQuantidade de pontos de função ainda disponíveis: ", fontNormal));
        contratoInfo.add(new Chunk(contrato.getQtdTotalDePontosFuncao().toString(),fontNormal));
        contratoInfo.add(new Chunk("\nNúmero de Ordens de Serviço (OS) já criadas: ", fontNormal));
        contratoInfo.add(new Chunk(String.valueOf(contrato.getOs().size()),fontNormal));
        contratoInfo.add(new Chunk("\nNúmero de OS em andamento: ", fontNormal));
        contratoInfo.add(new Chunk(String.valueOf(contarOSemAndamento(contrato)),fontNormal));
        contratoInfo.add(new Chunk("\nNúmero de OS já finalizadas: ", fontNormal));
        contratoInfo.add(new Chunk(String.valueOf(contarOSFinazlidada(contrato)),fontNormal));
        contratoInfo.add(new Chunk("\nResponsável pela gestão da empresa: ", fontNormal));
        contratoInfo.add(new Chunk(contrato.getEmpresa().getResponsavel().getNome(),fontNormal));

        document.add(contratoInfo);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Tabela Do Valor Consumido:",
                new Font(Font.HELVETICA, 14, Font.BOLD)));

        document.add(new Paragraph(" "));

        gerarOperacaoValorConsumido(document,contrato);
    }

}
