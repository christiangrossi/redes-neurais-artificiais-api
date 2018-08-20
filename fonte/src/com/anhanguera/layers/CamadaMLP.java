package com.anhanguera.layers;

import com.anhanguera.neurons.NeuronioPerceptron;

/**
 * Essa classe representa uma Camada de uma Rede Neural MLP
 *
 * @author Christian Santos
 */
public class CamadaMLP {

    private NeuronioPerceptron neuronios[];
    private double saidasNeuronios[];
    private int numNeuronios;
    private double numeroDeEntradas;

    public CamadaMLP() {

    }

    /**
     * @param numeroDeneuronios instancia um numero de neurônios para a camada.
     */
    public CamadaMLP(int numeroDeneuronios) {
        numNeuronios = numeroDeneuronios;
        neuronios = new NeuronioPerceptron[numeroDeneuronios];
        for (int i = 0; i < numeroDeneuronios; i++) {
            neuronios[i] = new NeuronioPerceptron();
        }
    }

    /**
     * @param i indice do neurônio.
     * @return um neurônio do vetor de neuronios da camada
     */
    public NeuronioPerceptron getNeuronio(int i) {
        return neuronios[i];
    }

    /**
     * Ajusta valor dos pesos dos neurônios da camada.
     * @param indiceNeuronio indice do peso a ser corrigido.
     * @param indicePeso Indice do peso a ser ajustado.
     * @param valor valor que será incrementado ao peso.
     */
    public void corrigigirPesosSinapticos(int indiceNeuronio, int indicePeso, double valor) {
        neuronios[indiceNeuronio].corrigirValorDoPeso(indicePeso, valor);
    }

    /**
     * @return O númerdo de Neurônios da Camada
     */
    public int numeroDeNeuronios() {
        return numNeuronios;
    }

    /**
     * Esse método inicializa as conexões sinápticas da rede
     *
     * @param numeroDeEntradas núemro de entradas, tamanho da coluna/matriz de
     * entrada, não considerar a entrada do bias, pois é considerada
     * automaticamente na inicialização dos pesos.
     */
    public void inicializarSinapses(int numeroDeEntradas) {
        this.numeroDeEntradas = numeroDeEntradas;
        for (NeuronioPerceptron neuronio : neuronios) {
            neuronio.inicializarSinapses(numeroDeEntradas);
        }
    }
    /**
     * Propaga os sinais dos neurônios da camada.
     * @param Entradas entradas para o processamento dos sinais pelos neuronios.
     * @return saída dos neurônios da camada para os dados de entrada.
     */
    public double[] progaparSinais(double[] Entradas) {
        saidasNeuronios = new double[neuronios.length];
        for (int i = 0; i < neuronios.length; i++) {
            //System.out.println("neuronio "+ i);
            saidasNeuronios[i] = neuronios[i].propagarSinal(Entradas);
        }
        return saidasNeuronios;
    }

    /**
     * altera valor do bias dos neurônios da camada.
     * Este método é um set para o bias. 
     * O bias é uma constante que deve ser definido pela função de ativação dos
     * neurônios. A função utilizada aqui é a função tangente hiperbólica, que 
     * trabalha com números entre 0 e 1, por isso o bias é definido como 1, para
     * outras funções o bias pode assumir outro valor, como por exemplo a função
     * logarítimica. Por isso é nomeada como uma constante, mas sem o modificador
     * final.
     */
    public void alterarBiasCamada(Double valorBias) {
        for (int i = 0; i < neuronios.length; i++) {
            neuronios[i].alterarBias(valorBias);
        }
    }

    /**
     * @return the saidasNeuronios
     */
    public double[] getSaidasNeuronios() {
        return saidasNeuronios;
    }

    /**
     * @return o número de entradas que foi passado definido para os neurônios
     * da camada
     */
    public double getNumeroDeEntradas() {
        return numeroDeEntradas;
    }

    /**
     * @return vetor de neurônios da camada
     */
    public NeuronioPerceptron[] getNeuronios() {
        return neuronios;
    }
}
