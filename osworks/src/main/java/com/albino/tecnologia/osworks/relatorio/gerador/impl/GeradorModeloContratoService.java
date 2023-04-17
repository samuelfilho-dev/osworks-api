package com.albino.tecnologia.osworks.relatorio.gerador.impl;

import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.relatorio.gerador.GeradorPDF;
import com.albino.tecnologia.osworks.relatorio.gerador.Rodape;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class GeradorModeloContratoService implements GeradorPDF {

    private final ContratoRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void gerarRelatorio(Long id, HttpServletResponse response) {

        Document document = new Document(PageSize.A4);
        Contrato contrato = repository.findById(id).get();

        log.info("Criando um Novo Modelo do Contrato: '{}' ",contrato.getCodigoDoContrato());

        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, response.getOutputStream());
        } catch (IOException e) {
            log.error("Erro a Gerar um Modelo De Contrato");
            log.error(e.getLocalizedMessage());
        }

        Objects.requireNonNull(writer).setPageEvent(new Rodape());
        document.open();

        gerarCabecalho(document, contrato);
        gerarCorpo(document,contrato);

        document.close();
    }

    @Override
    public void exportar(HttpServletResponse response, Long id) {
        gerarRelatorio(id,response);
    }

    private void gerarCabecalho(Document document, Contrato contrato){

        Font boldFont = new Font(Font.HELVETICA, 14, Font.BOLD);

        Paragraph titulo = new Paragraph("CONTRATO DE PRESTAÇÃO DE SERVIÇOS", boldFont);
        titulo.setAlignment(Element.ALIGN_LEFT);
        document.add(titulo);


        try {
            Image logo = Image.getInstance("icon/logo.png");
            logo.scaleToFit(100, 100);
            logo.setAlignment(Element.ALIGN_RIGHT);
            document.add(logo);
        } catch (IOException e) {
            log.error("Erro no Processamento Da Logo Do Contrato");
            log.error(e.getLocalizedMessage());
        }


        var separator = new LineSeparator();
        separator.setLineColor(Color.BLACK);
        document.add(separator);

    }

    private void gerarCorpo(Document document, Contrato contrato){

        NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        Font tituloFont = new Font(Font.TIMES_ROMAN,16);
        Font subtituloFont = new Font(Font.HELVETICA,14);
        Font normalFont = new Font(Font.HELVETICA, 12);

        Paragraph titulo = new Paragraph("CLÁUSULAS DO CONTRATO", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(10);
        document.add(titulo);

        document.add(new Paragraph(" "));

        // Cláusula 1: Contratante
        String infoContrante = String.format("O contratante, %s, inscrito no CNPJ " +
                "%s, com sede na cidade de %s, no endereço %s,%s - %s, %s-%s,%s" +
                ", Tendo o Responsável pelo pagamento dos valores estipulados neste contrato, %s, com CPF: %s e RG: %s," +
                        "domiciliado na %s,%s - %s, %s-%s,%s"
        ,contrato.getEmpresa().getRazaoSocial(),
                contrato.getEmpresa().getCnpj(),
                contrato.getEmpresa().getEndereco().getCidade(),
                contrato.getEmpresa().getEndereco().getLogradouro(),
                contrato.getEmpresa().getEndereco().getNumero(),
                contrato.getEmpresa().getEndereco().getBairro(),
                contrato.getEmpresa().getEndereco().getCidade(),
                contrato.getEmpresa().getEndereco().getUF(),
                contrato.getEmpresa().getEndereco().getCEP(),
                contrato.getEmpresa().getResponsavel().getNome(),
                contrato.getEmpresa().getResponsavel().getCpf(),
                contrato.getEmpresa().getResponsavel().getRg(),
                contrato.getEmpresa().getResponsavel().getEndereco().getLogradouro(),
                contrato.getEmpresa().getResponsavel().getEndereco().getNumero(),
                contrato.getEmpresa().getResponsavel().getEndereco().getBairro(),
                contrato.getEmpresa().getResponsavel().getEndereco().getCidade(),
                contrato.getEmpresa().getResponsavel().getEndereco().getUF(),
                contrato.getEmpresa().getResponsavel().getEndereco().getCEP()
        );

        Paragraph clasula1titulo = new Paragraph("CLÁUSULA 1 - CONTRATANTE",subtituloFont);
        clasula1titulo.setSpacingAfter(5);
        document.add(clasula1titulo);

        Paragraph clasula1Texto = new Paragraph(infoContrante, normalFont);
        clasula1Texto.setIndentationLeft(30);
        clasula1Texto.setSpacingAfter(15);
        document.add(clasula1Texto);



        // Cláusula 2: Contratado
        Paragraph clasula2titulo = new Paragraph("CLÁUSULA 2 - CONTRATADO", subtituloFont);
        clasula2titulo.setSpacingAfter(5);
        document.add(clasula2titulo);

        Paragraph clasula2texto = new Paragraph("O contratado, Albino Tecnologia S.A, " +
                "inscrito no CNPJ 11.621.967/0001-07, com sede na cidade de Belém," +
                " no endereço R. Manoel Barata, 900 - Cruzeiro (Icoraci), Belém - PA, 66810100, " +
                "compromete-se a executar os serviços descritos neste contrato " +
                "com eficiência e qualidade, dentro do prazo estipulado. ", normalFont);
        clasula2texto.setIndentationLeft(30);
        clasula2texto.setSpacingAfter(15);
        document.add(clasula2texto);


        // Cláusula 3: Objeto
        Paragraph clausula1Titulo = new Paragraph("CLÁUSULA 3 - OBJETO", subtituloFont);
        clausula1Titulo.setSpacingAfter(5);
        document.add(clausula1Titulo);

        Paragraph clausula1Texto = new Paragraph("O presente contrato tem como objeto a prestação de " +
                "serviços descritos abaixo:", normalFont);
        clausula1Texto.setIndentationLeft(30);
        clausula1Texto.setSpacingAfter(15);
        document.add(clausula1Texto);

        List listaServicos = new List(List.UNORDERED);
        listaServicos.add(new ListItem(contrato.getDescricoes().get(0), normalFont));
        listaServicos.setIndentationLeft(60);
        document.add(listaServicos);

        document.add(new Paragraph(" "));

        // Cláusula 4: Prazo
        Paragraph clausula2Titulo = new Paragraph("CLÁUSULA 4 - PRAZO", subtituloFont);
        clausula2Titulo.setSpacingAfter(5);
        document.add(clausula2Titulo);

        long difDeDias = ChronoUnit.DAYS.between(contrato.getDataInicio(),contrato.getDataTermino());

        String clasula2 = String.format("O prazo para a realização dos serviços será de %d dias corridos, " +
                "contados a partir da data de %s",difDeDias,contrato.getDataInicio().format(formatter));

        Paragraph clausula2Texto = new Paragraph(clasula2, normalFont);
        clausula2Texto.setIndentationLeft(30);
        clausula2Texto.setSpacingAfter(15);
        document.add(clausula2Texto);

        document.add(new Paragraph(" "));

        // Cláusula 5: Preço

        Paragraph clausula3Titulo = new Paragraph("CLÁUSULA 5 - PREÇO", subtituloFont);
        clausula3Titulo.setSpacingAfter(5);
        document.add(clausula3Titulo);

        String clausula3 = String.format("O valor total dos serviços descritos na cláusula 1 " +
                "será de %s",
                formatoMonetario.format(contrato.getValorTotalDoContrato()));

        Paragraph clausula3Texto = new Paragraph(clausula3, normalFont);
        clausula3Texto.setIndentationLeft(30);
        clausula3Texto.setSpacingAfter(15);
        document.add(clausula3Texto);

        document.add(new Paragraph(" "));

        // Cláusula 6: Foro
        Paragraph clausula5Titulo = new Paragraph("CLÁUSULA 6 - FORO", subtituloFont);
        clausula5Titulo.setSpacingAfter(5);
        document.add(clausula5Titulo);

        Paragraph clausula5Texto = new Paragraph("Fica eleito o foro da comarca de Belém/PA, para dirimir " +
                "quaisquer controvérsias oriundas do presente contrato, com renúncia expressa a qualquer outro, por mais " +
                "privilegiado que seja.");

        clausula5Texto.setIndentationLeft(30);
        clausula5Texto.setSpacingAfter(15);
        document.add(clausula5Texto);

    }

}
