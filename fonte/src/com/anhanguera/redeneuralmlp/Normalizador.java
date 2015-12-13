package com.anhanguera.redeneuralmlp;

import java.util.ArrayList;

/**
 * Essa classe faz a normalização dos dados de entrada para a rede, convertendo
 * valores para valores de um intervalo entre 0 e 1. Esse processo é necessário
 * pois a função tangente hiperbólica, definida como a função de ativação dos
 * neurônios trabalha com um intervalo de números entre 0 e 1.
 *
 * @author christian santos
 */
public class Normalizador {

    private ArrayList<double[]> conjuntoDeDadosNormalizado;

    public Normalizador() {
    }

    /**
     * Normaliza os dados de cada item de uma lista. É obtido o maior valor de
     * um item (nesse caso, um vetor double) e os dados do mesmo são
     * normalizados através desse valor, dividindo cada valor do vetor pelo seu
     * maior valor, fazendo com que os números fiquem entre 0 e 1.
     *
     * @param listaDeDados lista de dados a ser normalizada.
     * @return uma lista de vetores double normalizada para valores entre 0 e 1.
     */
    public ArrayList<double[]> normalizarDados(ArrayList<double[]> listaDeDados) {
        conjuntoDeDadosNormalizado = new ArrayList<>();
        double maiorValor = 0;
        for (double[] dados : listaDeDados) {
            double[] dadosNormalizados = new double[listaDeDados.get(0).length];
            maiorValor = retornaMaiorValor(dados);
            for (int i = 0; i < dados.length; i++) {
                dadosNormalizados[i] = dados[i] / maiorValor;
            }
            conjuntoDeDadosNormalizado.add(dadosNormalizados);
        }
        return conjuntoDeDadosNormalizado;
    }

    /**
     * Normaliza dados de um vetor e retorna um vetor normalizado.
     *
     * @param dadosDeEntrada vetor a ser normalizado.
     * @return um vetor com dados normalizados com valores entre 0 e 1
     */
    public double[] normalizarDados(double[] dadosDeEntrada) {
        double maiorValor = retornaMaiorValor(dadosDeEntrada);
        double[] dadosNormalizados = new double[dadosDeEntrada.length];
        for (int i = 0; i < dadosDeEntrada.length; i++) {
            dadosNormalizados[i] = dadosDeEntrada[i] / maiorValor;
        }
        return dadosNormalizados;
    }
    
    /**
     * Retorna o maior valor do vetor double passado como parâmetro.
     * @param dados vetor de dados do qual se deseja obter o maior valor.
     * @return o maior valor de um vetor de double.
     */
    public double retornaMaiorValor(double[] dados) {
        double maior = 0;
        for (double dado : dados) {
            if (maior < dado) {
                maior = dado;
            }
        }
        return maior;
    }

    /**
     * Imprime os valores da lista passada por parâmetro.
     * @param listaDeDados lista de dados que terá os valores imprimidos 
     */
    public void imprimirDados(ArrayList<double[]> listaDeDados) {
        System.out.println("**************************************************");
        for (int i = 0; i < listaDeDados.size(); i++) {
            System.out.println();
            for (int j = 0; j < listaDeDados.get(0).length; j++) {
                System.out.println("item: " + i + " pos[" + j + "]: " + listaDeDados.get(i)[j]);
            }
        }
    }
    
    /**
     * Imprime os valores de uma lista depoisd e ter sido normalizada. 
     */
    public void imprimirDados() {
        imprimirDados(conjuntoDeDadosNormalizado);
    }
}
