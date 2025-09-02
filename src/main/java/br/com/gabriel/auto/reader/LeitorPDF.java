package br.com.gabriel.auto.reader;

import br.com.gabriel.auto.model.AutoDados;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class LeitorPDF {

    private static AutoDados extrairDados() {
        AutoDados dados = new AutoDados();
        try (PDDocument document = PDDocument.load(new File("arquivos/relatorio.pdf"))){
            PDFTextStripper stripper= new PDFTextStripper();
            String texto = stripper.getText(document);

            LeitorHelper.extrairDadosArrematante(dados, texto);
            LeitorHelper.extrairDadosLeilao(dados, texto);
            LeitorHelper.extrairDadosProcesso(dados, texto);
        }catch (IOException e){
            System.err.println("Erro ao ler o arquivo PDF: " + e.getMessage());
            return null;
        }
        return dados;
    }

    public static void main(String[] args) {
        AutoDados dados = LeitorPDF.extrairDados();

        // Verifica se a extração foi bem-sucedida.
        if (dados != null) {
            // Imprime os dados usando o método toString() da sua classe AutoDados.
            System.out.println(dados.toString());
        } else {
            System.out.println("Erro na extração dos dados. Verifique a classe LeitorPDF.");
        }
    }


}
