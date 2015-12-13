package com.anhanguera.redeneuralmlp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Essa classe salva e carrega os valores dos pesos sinápticos de uma rede.
 * Os pesos sinápticos dos neurônios representam o conhecimento de uma rede
 * neural. Foi implementada para que houvesse uma forma de guardar o resultado
 * obtido de um treinamento. A extensão do arquivo onde os dados são salvos
 * foi definida como .dmlp (acrônimo de Dados Multilayer Perceptron).
 * 
 * @author Christian Santos
 */
public class GerenciadorDeConhecimento {


    public GerenciadorDeConhecimento() {
    }

    /**
     * Salva dados das conexões sinápticas da rede passada como parâmetro.
     * 
     * @param rede rede da qual serão salvos os valores de suas conexões sinápticas
     * @param caminho caminho da pasta onde o arquivo será gravado
     * @param nomeArquivo nome do arquivo que será gravado
     */
    public void guardarConhecimento(RedeNeuralMultilayerPerceptron rede, String caminho, String nomeArquivo) throws IOException {
        Neuronio[] neuroniosCamadaSaida = rede.getCamadaDeSaida().getNeuronios();
        Neuronio[] neuroniosCamadaIntermediaria = rede.getCamadaIntermediaria().getNeuronios();
        String descricao = ""
                + "Atenção, a alteração, inserção ou exclusão de dados nesse"
                + " arquivo pode acarretar erros.\n"
                + "**Conhecimento Camada de Saída**\n";
        String pesosCamadaSaida = "";
        for (Neuronio item : neuroniosCamadaSaida) {
            double[] pesos = item.getPesos();
            pesosCamadaSaida += ">";
            for (double peso : pesos) {
                pesosCamadaSaida += peso + " ";
            }
            pesosCamadaSaida = pesosCamadaSaida.trim();
            pesosCamadaSaida = pesosCamadaSaida.replaceAll(" ", "|");
        }
        pesosCamadaSaida += "\n";

        String pesosCamadaIntermediaria = "";
        String decricao2 = "--Conhecimento Camada Intermediaria--\n";
        for (Neuronio item : neuroniosCamadaIntermediaria) {
            double[] pesos = item.getPesos();
            pesosCamadaIntermediaria += ">";
            for (double peso : pesos) {
                pesosCamadaIntermediaria += peso + " ";
            }
            pesosCamadaIntermediaria = pesosCamadaIntermediaria.trim();
            pesosCamadaIntermediaria = pesosCamadaIntermediaria.replaceAll(" ", "|");
            pesosCamadaIntermediaria += "\n";
        }
        String dadosGravar = descricao + pesosCamadaSaida + decricao2 + pesosCamadaIntermediaria;
        gravarArquivo(dadosGravar, caminho, nomeArquivo);
    }

   

    /**
     * Carrega conhecimento (dados de conexões sinápticas)para a rede passada 
     * por parâmetro. A rede deve ter o mesmo número de neurônios, tamanho de 
     * entrada que os da rede para qual os dados foram salvos no arquivo.
     * 
     * @param rede rede para qual serão carregados os dados de conexões sinápticas.
     * @param caminhoDaPasta  caminho da pasta do arquivo que será carregado
     * @param nomeDoArquivo  nome do arquivo que será carregado.
     */
    
    public void carregarConhecimento(RedeNeuralMultilayerPerceptron rede, String caminhoDaPasta, String nomeDoArquivo) throws FileNotFoundException, IOException {
        String caminho = caminhoDaPasta + "/" + nomeDoArquivo + ".dmlp";
        FileReader arq = new FileReader(caminho);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        String dados = linha;
        while (linha != null) {
            dados += linha;
            linha = lerArq.readLine();
        }
        System.out.println("Dados de " + caminho + " lidos com sucesso...");
        arq.close();
        String[] split = dados.split("--");
        String[] pesosString = split[0].split(">");

        String[] pesosConverter = pesosString[1].split("\\|");
        double[] pesosCamadaSaida = new double[pesosConverter.length];
        for (int i = 0; i < pesosConverter.length; i++) {
            pesosCamadaSaida[i] = Double.parseDouble(pesosConverter[i]);
        }
        ArrayList<double[]> pesosCamadaIntermediaria = new ArrayList<>();
        String[] pesosIntermedString = split[2].split(">");
        for (int i = 1; i < pesosIntermedString.length; i++) {// o índice 0 é sujeira
            String[] pesosString2 = pesosIntermedString[i].split("\\|");
            double[] values = new double[pesosString2.length];
            for (int j = 0; j < pesosString2.length; j++) {
                values[j] = Double.parseDouble(pesosString2[j]);
            }
            pesosCamadaIntermediaria.add(values);
        }
        rede.getCamadaDeSaida().getNeuronio(0).setPesos(pesosCamadaSaida);
        for (int i = 0; i < rede.getCamadaIntermediaria().getNeuronios().length; i++) {
            rede.getCamadaIntermediaria()
                    .getNeuronio(i)
                    .setPesos(pesosCamadaIntermediaria.get(i));
        }
    }

    /**
     * Gera um arquivo .dmlp
     * 
     * @param dados dados que serão salvos no arquivo.
     * @param CaminhoPasta caminho da pasta onde o arquivo será gravado
     * @param nomeArquivo nome do arquivo que será gravado
     */
    private void gravarArquivo(String dados, String CaminhoPasta, String nomeArquivo) throws IOException {
        nomeArquivo = nomeArquivo.replace(nomeArquivo, nomeArquivo + ".dmlp");
        FileWriter arq = new FileWriter(CaminhoPasta + "\\" + nomeArquivo);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.print(dados);
        System.out.println("\n"
                + "Valores de conexões sinápitcas guardados...\n."
                + "Arquivo salvo com sucesso em: " + CaminhoPasta + "\nNome do arquivo: " + nomeArquivo);
        arq.close();

    }
}
