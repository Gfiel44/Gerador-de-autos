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

        String cpf = encontrarInformacao(textoCompleto, "CPF / CNPJ (.*?)");
        dados.setCpfArrematante(cpf);

        String rg = encontrarInformacao(textoCompleto, "RG / IE (.*?)");
        dados.setRgArrematante(rg);

        String endereco = encontrarInformacao(textoCompleto, "ENDERECO (.*)");
        dados.setEnderecoArrematante(endereco);

        String cidade = encontrarInformacao(textoCompleto, "CIDADE (.*)");
        dados.setEnderecoArrematante(cidade);

        String cep = encontrarInformacao(textoCompleto, "CEP (.*)");
        dados.setCepArrematante(cep);

        String email = encontrarInformacao(textoCompleto, "EMAIL (.*)");
        dados.setEmailArrematante(email);

        String telefone = encontrarInformacao(textoCompleto,  "TELEFONE (.*?) - CELULAR");
        dados.setTelefoneArrematante(telefone);

        String celular = encontrarInformacao(textoCompleto, "CELULAR (.*)");
        dados.setCelularArrematante(celular);

        String ip = encontrarInformacao(textoCompleto, "IP (.*)");
        dados.setIpArrematante(ip);
    }

    public static void extrairDadosLote(AutoDados dados, String textoCompleto){
        String lote = encontrarInformacao(textoCompleto, "LOTE (.*?(\\d{3}))");
        dados.setLote(lote);

        String descricao = encontrarInformacao(textoCompleto, "LOTE.*?(\\d{3})\\s(.*?)\\s+PROCESSO:");
        dados.setDescricao(descricao);

        String valor = encontrarInformacao(textoCompleto, "TOTAL DA VENDA\\n\\s*R\\$ (.*)");
        dados.setValor(valor);
    }

    public static void extrairDadosProcesso(AutoDados dados, String textoCompleto){
        String numeroProcesso = encontrarInformacao(textoCompleto, "PROCESSO: (\\\\d+)");
        dados.setNumeroProcesso(numeroProcesso);

        String nomeExecutado = encontrarInformacao(textoCompleto, "EXECUTADO: (.*?)\\n.*?EXEQUENTE:");
        dados.setNomeExecutado(nomeExecutado);

        String nomeExequente = encontrarInformacao(textoCompleto, "EXEQUENTE: (.*?)");
        dados.setNomeExequente(nomeExequente);

        String juizoDeDireito = encontrarInformacao(textoCompleto, "Comitente: (.*)");
        dados.setJuizoDeDireito(juizoDeDireito);
    }
}
