package br.com.gabriel.auto.reader;

import br.com.gabriel.auto.model.AutoDados;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        dados.setDataPrimeiroLeilao(dataPrimeiroLeilao);

        String dataSegundoLeilao = encontrarInformacao(textoCompleto, "2º Data do Leilão:(.*)");
        dados.setDataSegundoLeilao(dataSegundoLeilao);
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

        String juizoDeDireito = encontrarInformacao(textoCompleto, "Comitente: (.*)");
        dados.setJuizoDeDireito(juizoDeDireito);
    }
}
