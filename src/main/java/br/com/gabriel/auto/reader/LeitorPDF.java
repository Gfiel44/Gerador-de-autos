package br.com.gabriel.auto.reader;

import br.com.gabriel.auto.model.AutoDados;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.regex.*;

import java.io.File;

public class LeitorPDF {
    AutoDados autoDados = new AutoDados();
    LeitorHelper leitorHelper = new LeitorHelper();

    private static String extrairTexto() {
        try (PDDocument document = PDDocument.load(new File("arquivos/relatorio.pdf"))){
            PDFTextStripper stripper= new PDFTextStripper();
            String texto = stripper.getText(document);
            return texto;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {}


}
