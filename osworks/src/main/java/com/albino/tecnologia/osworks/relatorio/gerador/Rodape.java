package com.albino.tecnologia.osworks.relatorio.gerador;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class Rodape extends PdfPageEventHelper {

    private final Font fonteRodape = new Font(Font.HELVETICA, 12, Font.NORMAL);

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        // adiciona o texto com o número total de páginas
        int numPaginas = writer.getPageNumber();

        Phrase rodape = new Phrase(String.format("Página %d até %d", 1, numPaginas), fonteRodape);

        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, rodape,
                document.right(), document.bottomMargin() - 10, 0);

    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte canvas = writer.getDirectContent();

        canvas.moveTo(document.left(), document.bottomMargin());
        canvas.lineTo(document.right(), document.bottomMargin());
        canvas.stroke();

        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase(" " ,fonteRodape),
                document.right(), document.bottomMargin() - 10, 0);


        String direitosAutorais = "Albino Tecnologia © 2023, Todos os Direitos Reservados";

        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase(direitosAutorais, fonteRodape),
                (document.left() + document.right()) / 2, document.bottomMargin() - 10, 0);

    }
}
