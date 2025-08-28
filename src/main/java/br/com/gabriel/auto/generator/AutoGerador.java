package br.com.gabriel.auto.generator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.IOException;

public class AutoGerador {
    public static void main(String[] args) throws IOException {
        String arquivoModelo = "novoDoc.docx";
        String arquivoNovo = "novoDocModifica.docx";
        String nome = "GABRIEL";
        String nomeArrematante = "lany";

        XWPFDocument document = new XWPFDocument(new FileInputStream(arquivoModelo));

        for (XWPFParagraph p : document.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                String texto = r.getText(0);

            }
        }
    }
}
