package br.com.gabriel.auto.reader;

import br.com.gabriel.auto.model.AutoDados;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.io.FileReader;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class LeitorHelper {
    private static String encontrarInformacao(String texto, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()){
            return matcher.group(1).trim();
        }
        return null;
    }

    public static void extrairDadosArrematante(AutoDados dados, String textoCompleto){
        String login = encontrarInformacao(textoCompleto, "LOGIN (.*)");
        dados.setLoginArrematante(login);

        String nome = encontrarInformacao(textoCompleto, "NOME (.*)");
        dados.setNomeArrematante(nome);

        String cpf = encontrarInformacao(textoCompleto, "CPF / CNPJ(.*) RG");
        dados.setCpfArrematante(cpf);

        String rg = encontrarInformacao(textoCompleto, "RG / IE(.*)");
        dados.setRgArrematante(rg);

        String endereco = encontrarInformacao(textoCompleto, "ENDEREÇO(.*)");
        dados.setEnderecoArrematante(endereco);

        String cidade = encontrarInformacao(textoCompleto, "CIDADE(.*)");
        dados.setCidadeArrematante(cidade);

        String cep = encontrarInformacao(textoCompleto, "CEP (.*)");
        dados.setCepArrematante(cep);

        String email = encontrarInformacao(textoCompleto, "E-MAIL (.*)");
        dados.setEmailArrematante(email);

        String telefone = encontrarInformacao(textoCompleto,  "TELEFONE (.*) -");
        dados.setTelefoneArrematante(telefone);

        String celular = encontrarInformacao(textoCompleto, "CELULAR(.*)");
        dados.setCelularArrematante(celular);

        String ip = encontrarInformacao(textoCompleto, "IP (.*)");
        dados.setIpArrematante(ip);
    }

    public static void extrairDadosLeilao(AutoDados dados, String textoCompleto){

        Pattern padraoLote = Pattern.compile("(LOTE|TOTAL DE LOTES)[\\s\\S]*?(\\d{1,5})\\b", Pattern.DOTALL);
        Matcher matcherLote = padraoLote.matcher(textoCompleto);
        if (matcherLote.find()) {
            dados.setLote(matcherLote.group(2).trim());
        }

        Pattern padraoValor = Pattern.compile("TOTAL DA VENDA.*?R\\$\\s(.*)TOTAL DE COMISSÃO", Pattern.DOTALL);
        Matcher matcherValor = padraoValor.matcher(textoCompleto);
        if (matcherValor.find()) {
            dados.setValor(matcherValor.group(1).trim());
        }

        Pattern padraoDescricao = Pattern.compile("LOTE.*?\\d{1,5}\\s(.*?)\\s+PROCESSO:", Pattern.DOTALL);
        Matcher matcherDescricao = padraoDescricao.matcher(textoCompleto);
        if (matcherDescricao.find()) {
            dados.setDescricao(matcherDescricao.group(1).trim());
        }

        String dataPrimeiroLeilao = encontrarInformacao(textoCompleto, "1º Data do Leilão:(.*)");
        String[] partes = dataPrimeiroLeilao.split(" ");
        String[] dataPrimeiroDividida = partes[0].split("/");
        dados.setDiaPrimeiroLeilao(dataPrimeiroDividida[0]);
        dados.setMesPrimeiroLeilao(dataPrimeiroDividida[1]);
        dados.setAnoPrimeiroLeilao(dataPrimeiroDividida[2]);
        dados.setHoraLeilao(partes[1]);

        String dataSegundoLeilao = encontrarInformacao(textoCompleto, "2º Data do Leilão:(.*)");
        String[] partesSegundo = dataSegundoLeilao.split(" ");
        String[] dataSegundoDividida = partesSegundo[0].split("/");
        dados.setDiaSegundoLeilao(dataSegundoDividida[0]);
        dados.setMesSegundoLeilao(dataSegundoDividida[1]);
        dados.setAnoSegundoLeilao(dataSegundoDividida[2]);
    }

    public static void extrairDadosProcesso(AutoDados dados, String textoCompleto){
        // Regex para o numero do processo
        Pattern padraoProcesso = Pattern.compile("PROCESSO:\\s*([\\s\\S]*?)\\s*EXECUTADO:");
        Matcher matcherProcesso = padraoProcesso.matcher(textoCompleto);
        if (matcherProcesso.find()) {
            dados.setNumeroProcesso(matcherProcesso.group(1).trim());
        }

        // Regex para o nome do executado
        Pattern padraoExecutado = Pattern.compile("EXECUTADO:\\s*([\\s\\S]*?)\\s*EXEQUENTE:");
        Matcher matcherExecutado = padraoExecutado.matcher(textoCompleto);
        if (matcherExecutado.find()) {
            dados.setNomeExecutado(matcherExecutado.group(1).trim());
        }

        // Regex para o nome do exequente
        Pattern padraoExequente = Pattern.compile("EXEQUENTE:\\s*([\\s\\S]*?)R\\$");
        Matcher matcherExequente = padraoExequente.matcher(textoCompleto);
        if (matcherExequente.find()) {
            dados.setNomeExequente(matcherExequente.group(1).trim());
        }

        String juizoDeDireito = encontrarInformacao(textoCompleto, "Comitente: (.*) da Comarca");
        dados.setJuizoDeDireito(juizoDeDireito);

        String cidadeJuizo = encontrarInformacao(textoCompleto, "da Comarca de (.*)");
        dados.setCidadeJuizo(cidadeJuizo);
    }


    public static void extrairDadosCsv(AutoDados dados){
        String nomeComitente = dados.getJuizoDeDireito() + " de " + dados.getCidadeJuizo();
        String caminhoArquivoCSV = "arquivos/arquivo.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Date data = new Date();

        try (ICsvMapReader mapReader = new CsvMapReader(new FileReader(caminhoArquivoCSV), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE)){
            String[] cabecalho = mapReader.getHeader(true);

            Map<String, String> linha;
            while ((linha = mapReader.read(cabecalho)) != null){
                String nome = linha.get("Nome");

                if (nomeComitente.equals(nome)) {
                    String ufJuizo = linha.get("UF");
                    dados.setUfJuizo(ufJuizo);

                    String cepJuizo = linha.get("Cep");
                    dados.setCepJuizo(cepJuizo);

                    String enderecoJuizo = linha.get("Endereço");
                    dados.setEnderecoJuizo(enderecoJuizo);

                    String numeroEnderecoJuizo = linha.get("Número do Endereço");
                    dados.setNumeroEnderecoJuizo(numeroEnderecoJuizo);

                    String bairroJuizo = linha.get("Bairro");
                    dados.setBairroJuizo(bairroJuizo);

                    String complementoJuizo = linha.get("Complemento");
                    dados.setComplementoJuizo(complementoJuizo);

                    for(int i = 1; i < 10; i++){
                        if(linha.get(String.format("Substituto %d Nome", i)) != null){
                            LocalDate dataInicio = LocalDate.parse(linha.get(String.format("Substituto %d Início", i)), formatter);
                            LocalDate dataFim = LocalDate.parse(linha.get(String.format("Substituto %d Fim", i)), formatter);

                            if(dataInicio.isBefore(LocalDate.now()) && dataFim.isAfter(LocalDate.now())){
                                dados.setNomeJuiz(linha.get(String.format("Substituto %d Nome", i)));
                            }else {
                                dados.setNomeJuiz(linha.get("Titular"));
                            }

                        }else{
                            dados.setNomeJuiz(linha.get("Titular"));
                            dados.setNomeJuiz(linha.get(String.format("Substituto %d Nome", i)));
                        }
                    }
                    return;
                }
            }
        }catch (IOException e){
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
