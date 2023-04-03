package com.albino.tecnologia.osworks.relatorio.gerador;

import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Log4j2
public class GeradorRelatorioContratoService {

    private final ContratoRepository contratoRepository;

    public void gerarRelatorioContrato(Long id, HttpServletResponse response) {

        Contrato contrato = contratoRepository.findById(id).get();

        try {

            Document document = new Document();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            PdfWriter.getInstance(document,response.getOutputStream());
            document.open();

            log.info("Criando um Novo Relatorio do Contrato com Id: " + id);

            // Adicão De Cabecalho

            Paragraph paragrafoTitulo = new Paragraph();
            paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
            paragrafoTitulo.add(new Chunk("RELATÓRIO DETALHADO DO CONTRATO", new Font(Font.HELVETICA, 24)));
            document.add(paragrafoTitulo);
            document.add(new Paragraph(" "));

            // Adição dos dados do contrato ao documento PDF (Corpo)

            document.add(new Paragraph("Código do contrato: " + contrato.getCodigoDoContrato()));
            document.add(new Paragraph("Valor total do contrato: " +
                    NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                            .format(contrato.getValorTotalDoContrato())));

            gerarOperacaoValorConsumido(document, contrato);

            document.add(new Paragraph("Valor ainda disponível: " +
                    NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                            .format(contrato.getValorTotalDoContrato())));

            document.add(new Paragraph("Data de início do contrato: " + contrato.getDataInicio().format(formatter)));
            document.add(new Paragraph("Data de término do contrato: " + contrato.getDataInicio().format(formatter)));
            document.add(new Paragraph("Quantidade de pontos de função já consumidos: " + contrato.getQtdDePontosFuncao().toString()));
            document.add(new Paragraph("Quantidade de pontos de função ainda disponíveis: " + contrato.getQtdTotalDePontosFuncao().toString()));
            document.add(new Paragraph("Número de Ordens de Serviço (OS) já criadas: " + contrato.getOs().size()));
            document.add(new Paragraph("Número de OS em andamento: " + contarOSemAndamento(contrato)));
            document.add(new Paragraph("Número de OS já finalizadas: " + contarOSFinazlidada(contrato)));
            document.add(new Paragraph("Responsável pela gestão do contrato: " + contrato.getEmpresa().getResponsavel().getNome()));

            // Adição Do Rodapé

            Paragraph paragrafoSessao = new Paragraph("----------------------------------------------");
            paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
            document.add(paragrafoSessao);
            document.add(new Paragraph(" "));

            Paragraph paragraphRodape = new Paragraph();
            paragraphRodape.setAlignment(Element.ALIGN_CENTER);
            paragraphRodape.add(new Chunk("Albino Teclogia 2023, Todos Direitos Resevados" ,
                    new Font(Font.TIMES_ROMAN,14)));
            document.add(paragraphRodape);

            log.info("Criado Novo Relatorio do Contrato com Id: " + id);

            // Fechamento do documento PDF
            document.close();

        } catch (Exception ex) {
            log.error("Erro a tentar Processar o relatorio de Contrato");
            log.error(ex.getLocalizedMessage());
        }

    }

    private void gerarOperacaoValorConsumido(Document document, Contrato contrato) throws DocumentException {

        Font normalBoldFont = new Font(Font.BOLD);
        Font normalFont = new Font(Font.NORMAL);
        Font subTituloFont = new Font(Font.TIMES_ROMAN);

        Paragraph paragraph = new Paragraph("Operação de Valor Consumido", subTituloFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        BigDecimal pontosDeFuncao = BigDecimal.valueOf(contrato.getQtdDePontosFuncao());
        BigDecimal valorTotal = contrato.getValorTotalDoContrato();
        BigDecimal valorUnitario = contrato.getValorUnitario();
        BigDecimal valorConsumido = valorTotal.subtract(pontosDeFuncao.multiply(valorUnitario));

        BigDecimal valorDisponivel = valorTotal.subtract(valorConsumido);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Valor Total do Contrato", normalBoldFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Consumido", normalBoldFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Disponível", normalBoldFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(valorTotal)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(valorConsumido)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(  NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
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

    private BigDecimal gerarValorDisponivel(Contrato contrato){

        BigDecimal pontosDeFuncao = BigDecimal.valueOf(contrato.getQtdDePontosFuncao());
        BigDecimal valorTotal = contrato.getValorTotalDoContrato();
        BigDecimal valorUnitario = contrato.getValorUnitario();
        BigDecimal valorConsumido = valorTotal.subtract(pontosDeFuncao.multiply(valorUnitario));


        return valorTotal.subtract(valorConsumido);
    }

    public void exportar(HttpServletResponse response, Long id){
        gerarRelatorioContrato(id,response);
    }
}
