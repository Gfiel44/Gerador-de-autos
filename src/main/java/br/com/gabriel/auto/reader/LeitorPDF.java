package br.com.gabriel.auto.reader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.regex.*;

import java.io.File;

public class LeitorPDF {
    public static void main(String[] args) throws IOException {
        PDDocument document = PDDocument.load(new File("relatorio.pdf"));
        PDFTextStripper stripper = new PDFTextStripper();
        String texto = stripper.getText(document);

        String palavraChave = "nome ";
        int index = texto.indexOf(palavraChave);

        if (index != -1) {
            String resto = texto.substring(index + palavraChave.length());
            String nome = resto.split("\\r?\\n")[0];
            System.out.println(nome);
        }

        System.out.println("fim");
    }
}
