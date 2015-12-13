package com.anhanguera.redeneuralmlp;

/**
 * Essa classe implementa o algorítimo Backpropagation (Algororítimo de
 * Retropropagação de erro), que corrige os pesos sinápticos da rede.
 *
 * @author Christian Santos
 */
public class BackPropopagation {

    private double TAXA_DE_APRENDIZADO;
    private double BIAS;
    private double gradiente;

    public BackPropopagation() {
    }

    /**
     * Esse método ajusta os pesos sinápticos da rede.
     *
     * @param rede A rede Neural cujos pesos sinápticos serão ajustados.
     */
    public void retroPropagarErroPelaRede(RedeNeuralMultilayerPerceptron rede) {
        gradiente = rede.getGradiente();
        BIAS = rede.getBIAS();
        TAXA_DE_APRENDIZADO = rede.getTaxaDeAprendizado();
        retropropagarErroPelaCamadaDeSaida(rede.getEntradaCamadaDeSaida(), rede.getCamadaDeSaida());
        retropropagarErroPelaCamadaIntermediaria(rede.getMatrizDeTreinamento(), rede.getEntradaCamadaDeSaida(), gradiente, rede.getCamadaDeSaida(), rede.getCamadaIntermediaria());
    }

    /**
     * Esse método ajusta os pesos sinápticos da Camada Intermediária
     *
     * @param conjuntoTreinamento matriz de treinamento, será uma coluna do
     * conjunto de treinamento carregado.
     * @param entradaCamadaDeSaida matriz de entradas da camada de saída.
     * @param gradiente gradiente do erro da saída da rede.
     * @param camadaDeSaida Camada de saída da rede.
     * @param camadaIntermediaria Camada intermediária da rede.
     */
    private void retropropagarErroPelaCamadaIntermediaria(double[] conjuntoTreinamento, double[] entradaCamadaDeSaida, double gradiente, Camada camadaDeSaida, Camada camadaIntermediaria) {

        for (int j = 0; j < entradaCamadaDeSaida.length; j++) {

            double derivadaFuncaoTransferencia = entradaCamadaDeSaida[j] * (1.0 - entradaCamadaDeSaida[j]);
            double sigma = derivadaFuncaoTransferencia * (camadaDeSaida.getNeuronio(0).getPeso(j + 1) * gradiente);

            for (int k = 0; k < camadaIntermediaria.getNumeroDeEntradas(); k++) {
                //indice k+1 pois o índice 0 é o peso do bias
                camadaIntermediaria.corrigigirPesosSinapticos(j, k + 1, TAXA_DE_APRENDIZADO * sigma * conjuntoTreinamento[k]);
            }
            camadaIntermediaria.corrigigirPesosSinapticos(j, 0, TAXA_DE_APRENDIZADO * sigma * BIAS);
        }
    }

    /**
     * Esse método ajusta os pesos da camada de saída.
     *
     * @param entradasCamadaDeSaida matriz de entradas da camada de saída.
     * @param camadaDeSaida Camada de saída da rede.
     */
    private void retropropagarErroPelaCamadaDeSaida(double[] entradasCamadaDeSaida, Camada camadaDeSaida) {
        for (int i = 0; i < camadaDeSaida.getNeuronio(0).getPesos().length - 1; i++) {
            camadaDeSaida.getNeuronio(0).corrigirValorDoPeso((i + 1), (TAXA_DE_APRENDIZADO * entradasCamadaDeSaida[i] * gradiente));// += RedeNeuralMLP.TAXA_APRENDIZADO * entradaSegundaCamada[j] * gradiente;
            //peso(i+1) pois ira de 1 até length-1(tamanho-1) do vetor de pesos do neurônio
            camadaDeSaida.corrigigirPesosSinapticos(0, i + 1, TAXA_DE_APRENDIZADO * entradasCamadaDeSaida[i] * gradiente);
        }
        //ajusta peso do bias, neurônio 0 pois essa camada só tem um neurônio, indice 0, indice do peso do bias.
        camadaDeSaida.corrigigirPesosSinapticos(0, 0, TAXA_DE_APRENDIZADO * BIAS * gradiente);
    }

}
