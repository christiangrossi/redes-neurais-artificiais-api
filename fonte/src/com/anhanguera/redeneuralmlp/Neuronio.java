package com.anhanguera.redeneuralmlp;

/**
 * Essa classe representa um neurônio artificial Perceptron.
 *
 * @author Christian Santos
 */
public class Neuronio {

    private double pesos[];// w0(peso do bias),W1, W2, W3, Wn...
    private double matrizDeEntradas[];// X0, X1, X2, Xn...
    private double u; // variável onde será acumulada a integração de todas as entradas, multiplicadas pelos pesos
    private double BIAS = 1.0; // Bias do neurônio

    public Neuronio() {
    }

    /**
     * Instancia e inicializa uma matriz/vetor de pesos sinápticos do neuronio
     * com valores randomicos entre 1 e 0.
     *
     * @param numeroDeEntradas numero de entradas que o neuronio irá receber. O
     * número de pesos será o tamanho da matriz de entradas+1 pois será
     * acrescentado peso do bias. O bias ocupará o índice zero(0) da matriz.
     */
    public void inicializarSinapses(int numeroDeEntradas) {
        //Random valoresRandomicos = new Random();
        pesos = new double[numeroDeEntradas + 1];
        for (int i = 0; i < numeroDeEntradas + 1; i++) {
            pesos[i] = Math.random();
        }
    }

    /**
     * Esse método corrige o valor do peso do neurônio incrementando a um novo
     * valor.
     *
     * @param indice indice do peso do neurônio que terá seu valor incrementado.
     * Lembrar que o índice 0 da matriz de pesos é o peso do bias.
     * @param novoValor valor que será incrementado ao valor atual do peso.
     */
    public void corrigirValorDoPeso(int indice, double novoValor) {
        //System.out.println(">>p:"+pesos[indice]+"  nv:"+novoValor);
        pesos[indice] += novoValor;
        //System.out.println("--p:"+pesos[indice]);
    }

    /**
     * Esse método faz a integração entre os sinais de entrada e os pesos
     * sinápticos.
     *
     * @param matrizDeEntradas matriz/vetor de entradas
     */
    private double funcaoSomadora(double[] matrizDeEntradas) {
        /*FUNÇÃO SOMADORA USADA PARA CLASSIFICAÇÃO*/
        this.matrizDeEntradas = matrizDeEntradas;
        double u = 0.0;
        u += (pesos[0] * BIAS);
        for (int i = 0; i < this.matrizDeEntradas.length; i++) {
            u += pesos[(i + 1)] * this.matrizDeEntradas[i];
        }
        return u;

    }

    /**
     * Função de ativação do neurônio, no caso, uma tangente hiperbólica.
     *
     * @param u Sinal de integração dado pela função de soma.
     */
    private double funcaoDeAtivacao(double u) {

        return 1.0 / (1.0 + Math.exp(-u));
    }

    /**
     * Esse método propaga o sinal do neurônio.
     *
     * @param matrizDeEntradas matrizDeEntradas matriz/vetor de entradas
     * @return Um sinal de Saída derivado das funções de soma e de ativação
     */
    double propagarSinal(double[] entradas) {
        /*PROPAGAÇÃO DE SINAL NO TREINAMENTO DOS NEURONIOS DA REDE*/
        double funcaoSomadora = funcaoSomadora(entradas);
        //funcaoSomadora*=0.001;
        return funcaoDeAtivacao(funcaoSomadora );
    }

    /**
     * Obtém o valor do bias do Neurônio
     *
     * @return O valor do bias do neurônio
     */
    public double getBias() {
        return BIAS;
    }

    /**
     * Altera o valor do bias do neurônio
     * O bias é uma constante que deve ser definido pela função de ativação dos
     * neurônios. A função utilizada aqui é a função tangente hiperbólica, que 
     * trabalha com números entre 0 e 1, por isso o bias é definido como 1, para
     * outras funções o bias pode assumir outro valor, como por exemplo a função
     * logarítimica. Por isso é nomeada como uma constante, mas sem o modificador
     * final.
     *
     * @param BIAS O BIAS para o set
     */
    public void alterarBias(double BIAS) {
        this.BIAS = BIAS;
    }

    /**
     * Le um valor da matriz de pesos.
     *
     * @param i O índice do peso a ser lido na matriz de pesos.
     * @return um valor da matriz de pesos do neurônio.
     */
    public double getPeso(int i) {
        return pesos[i];
    }

    /**
     * @return O vetor/matriz de pesos do neurônio
     */
    public double[] getPesos() {
        return pesos;
    }
    
    /**
     * adiciona um vetor de pesos para o neurônio. 
     */
    public void setPesos(double[] novosPesos){
        pesos = novosPesos;
    }

    /**
     * @return a matriz de entradas atual do neurônio
     */
    public double[] getMatrizDeEntradas() {
        return matrizDeEntradas;
    }

    /**
     * Seta uma nova matriz de entradas para o neurônio
     *
     * @param matrizDeEntradas a matrizDeEntradas para o set
     */
    public void setMatrizDeEntradas(double[] matrizDeEntradas) {
        this.matrizDeEntradas = matrizDeEntradas;
    }

}
