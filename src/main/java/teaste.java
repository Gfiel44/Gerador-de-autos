import br.com.gabriel.auto.model.AutoDados;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
import br.com.gabriel.auto.reader.LeitorHelper;
import java.io.File;

public class teaste {
    public static void main(String[] args) {
        try (PDDocument document = PDDocument.load(new File("arquivos/relatorio.pdf"))){
            PDFTextStripper stripper= new PDFTextStripper();
            String texto = stripper.getText(document);
            System.out.println(texto);
        }catch (IOException e){
            System.err.println("Erro ao ler o arquivo PDF: " + e.getMessage());
        }
    }
}
