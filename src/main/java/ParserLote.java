import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserLote {

    public static void main(String[] args) {
        try (PDDocument document = PDDocument.load(new File("arquivos/relatorio.pdf"))){

            PDFTextStripper stripper= new PDFTextStripper();
            String texto = stripper.getText(document);
            String regex = "TOTAL DA VENDA\\n\\s*R\\$ (.*)";
            Pattern padrao = Pattern.compile(regex);
            Matcher matcher = padrao.matcher(texto);

            if (matcher.find()) {
                System.out.println(matcher.group(1).trim());
            }

        }catch (IOException e){
            System.err.println("Erro ao ler o arquivo PDF: " + e.getMessage());
        }

    }
}
