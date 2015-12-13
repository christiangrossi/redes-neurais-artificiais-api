package com.anhanguera.redeneuralmlp;

import java.util.ArrayList;

/**
 * Essa Classe representa uma Rede Neural MultilayerPerceptron (Perceptron de
 * múltiplas camadas).
 *
 * @author Christian Santos
 */
public class RedeNeuralMultilayerPerceptron {

    private Camada camadaIntermediaria;
    private Camada camadaDeSaida;
    private int numeroMaximoDeEpoca = 100000;
    private int epocas = 0;
    private double taxaDeAprendizado = 0.03;
    private double porcentagemDeErroAceitavel = 0.05;//5% de erro. 95% de precisão.
    private double BIAS = 1.0;
    private double[] matrizDeEntradas;
    private double[] entradaCamadaDeSaida;
    private double[][] conjuntoTreinamentoMatriz;
    private double[] matrizDeTreinamento;
    private double erro = 1.0;
    private double gradiente;
    private double saidaRede;
    private BackPropopagation backpropagation;
    private double respostaRede;
    private ArrayList<double[]> conjuntoTreinamentoLista;

    /**
     * Esse construtor quando instanciado, usa o valor default para o número
     * máximo de épocas
     *
     * @param n1 número de neurônios a serem instanciados para a camada
     * Intermediária.
     * @param n2 número de neurônios a serem instanciados para a camada de
     * Saída.
     */
    public RedeNeuralMultilayerPerceptron(int n1, int n2) {// numero de neuronios em cada camada
        camadaIntermediaria = new Camada(n1);
        camadaDeSaida = new Camada(n2);
        backpropagation = new BackPropopagation();
    }

    /**
     * Esse construtor quando instanciado, especifica um número máximo de
     * épocas, se não especificado, seguirá o valor default de 100000 épocas.
     *
     * @param n1 número de neurônios a serem instanciados para a camada
     * Intermediária.
     * @param n2 número de neurônios a serem instanciados para a camada de
     * Saída.
     * @param maxEpocas número máximo de épocas desejado.
     */
    public RedeNeuralMultilayerPerceptron(int n1, int n2, int maxEpocas) {// numero de neuronios em cada camada
        camadaIntermediaria = new Camada(n1);
        camadaDeSaida = new Camada(n2);
        backpropagation = new BackPropopagation();
        numeroMaximoDeEpoca = maxEpocas;
    }
    
    /**
     * Inicializa pesos sinápticos da rede.
     *
     * @param numeroDeEntradas Numero de entradas para os neurônios da rede,
     * deve ser o tamanho do vetor de entradas.
     */
    public void inicializarConexoesSinapticasDaRede(int numeroDeEntradas) {
        camadaIntermediaria.inicializarSinapses(numeroDeEntradas);
        camadaDeSaida.inicializarSinapses(camadaIntermediaria.numeroDeNeuronios());
    }

    /**
     * Esse método executa o processo de treinamento da rede neural a partir de
     * uma matriz de treinamento. Recomendável para processamentos de alto custo 
     * onde se visa mais performance do que facilidade de implementação.
     *
     * @param conjuntoTreinamento matriz de entradas que será utilizada para o
     * treinamento, é passada coluna a coluna para os neurônios da rede.
     * @param valoresEsperados valores esperados para cada entrada/coluna do
     * conjunto de treinamento.
     */
    public void treinar(double[][] conjuntoTreinamento, double[] valoresEsperados) {
        System.out.println("Processo de treinamento iniciado...");
        epocas = 0;
        erro = 1.0;
        this.conjuntoTreinamentoMatriz = conjuntoTreinamento;
        matrizDeTreinamento = new double[this.conjuntoTreinamentoMatriz.length];

        while ((Math.abs(erro) > porcentagemDeErroAceitavel) && (epocas < numeroMaximoDeEpoca)) {

            for (int i = 0; i < this.conjuntoTreinamentoMatriz[0].length; i++) {
                for (int j = 0; j < this.conjuntoTreinamentoMatriz.length; j++) {
                    matrizDeTreinamento[j] = this.conjuntoTreinamentoMatriz[j][i];
                }
                entradaCamadaDeSaida = camadaIntermediaria.progaparSinais(matrizDeTreinamento);
                double[] valorSaida = camadaDeSaida.progaparSinais(entradaCamadaDeSaida);
                saidaRede = valorSaida[0];
                erro = calcularErro(valoresEsperados, saidaRede, i);
                gradiente = calcularGradienteDeRetropopagacao(saidaRede, erro);
                backpropagation.retroPropagarErroPelaRede(this);
            }
            //System.out.println("ep:"+epocas+" er:"+erro);
            epocas++;
        }
    }

    /**
     * Esse método executa o processo de treinamento da rede neural a partir de
     * uma lista de vetores com os dados de treinamento. Cada elemento dessa
     * lista deve ser um vetor de double, todos com o mesmo tamanho.
     *
     * @param conjuntoTreinamento matriz de entradas que será utilizada para o
     * treinamento, é passada coluna a coluna para os neurônios da rede.
     * @param valoresEsperados valores esperados para cada entrada/coluna do
     * conjunto de treinamento.
     */
    public void treinar(ArrayList<double[]> conjuntoTreinamento, double[] valoresEsperados) {
        epocas = 0;
        erro = 1.0;
        int aux = 1000;// variável para printar a cada n épocas o número de épocas e o erro, para reduzir custo de processamento.
        this.conjuntoTreinamentoLista = conjuntoTreinamento;
        matrizDeTreinamento = new double[this.conjuntoTreinamentoLista.get(0).length];
        System.out.println("Processo de treinamento iniciado...");
        System.out.println("Procesessando...");
        while ((Math.abs(erro) > porcentagemDeErroAceitavel) && (epocas < numeroMaximoDeEpoca)) {
            for (int i = 0; i < this.conjuntoTreinamentoLista.size(); i++) {

                matrizDeTreinamento = conjuntoTreinamentoLista.get(i);
                entradaCamadaDeSaida = camadaIntermediaria.progaparSinais(matrizDeTreinamento);
                double[] valorSaida = camadaDeSaida.progaparSinais(entradaCamadaDeSaida);
                saidaRede = valorSaida[0];
                erro = calcularErro(valoresEsperados, saidaRede, i);
                gradiente = calcularGradienteDeRetropopagacao(saidaRede, erro);
                backpropagation.retroPropagarErroPelaRede(this);
            }
            if (epocas == aux) {
                System.out.println("Processando.... época:" + epocas + " erro:" + erro);
                aux += 1000;
            }
            epocas++;
        }
    }

    /**
     * Esse método executa o processo de classificação de um dado passado para a
     * rede, que para responder corretamente, tem que estar treinada. Esse
     * método só carrega um arquivo, ou seja, um vetor/matriz por vez.
     *
     * @param entrada dados de entrada a serem classificados.
     */
    public double classificarDados(double[] entrada) {
        if (epocas > numeroMaximoDeEpoca) {
            System.out.println("A rede não conseguiu atingir um ponto de convergencia, verifique os parametros e a estrutura da rede.");
            return 3; // rede não convergiu
        } else {
            double[] saidasPrimeiraCamada = camadaIntermediaria.progaparSinais(entrada);
            double[] y = camadaDeSaida.progaparSinais(saidasPrimeiraCamada);
            respostaRede = Math.round(y[0]);
            System.out.print("Saida para os dados de entrada:" + Math.round(y[0]) + " ");
            circuloOuTriangulo(respostaRede);
            return respostaRede;
        }
        
    }
    public double classificarDados(double[] entrada, double esperado) {
        if (epocas > numeroMaximoDeEpoca) {
            System.out.println("A rede não conseguiu atingir um ponto de convergencia, verifique os parametros e a estrutura da rede.");
            return 3; // rede não convergiu
        } else {
            double[] saidasPrimeiraCamada = camadaIntermediaria.progaparSinais(entrada);
            double[] y = camadaDeSaida.progaparSinais(saidasPrimeiraCamada);
            respostaRede = Math.round(y[0]);
            if(respostaRede==esperado){
            System.out.println("Saida para os dados de entrada:" + Math.round(y[0]) + " (acertou)");
            }else{
            System.out.println("Saida para os dados de entrada:" + Math.round(y[0]) + " (errou)");
            }
            
            return respostaRede;
        }
        
    }

    public void circuloOuTriangulo(double resposta) {
        if (resposta == 0) {
            System.out.print(" -> É um círculo\n");
        } else if (resposta == 1) {
            System.out.print(" -> É um Triângulo\n");
        }
    }

    /**
     * Esse método calcula o erro da rede em relação a saída esperada.
     *
     * @param valoresEsperados matriz/vetor de valores esperados.
     * @param valorSaida valor de saída da rede.
     * @param i índice do valor esperado.
     */
    private double calcularErro(double[] valoresEsperados, double valorSaida, int i) {
        //System.out.println("indice:"+ i +" Saida: "+valorSaida+" Esperado:"+valoresEsperados[i]+" resultado: "+(valoresEsperados[i]-valorSaida));
        return valoresEsperados[i] - valorSaida;
    }

    /**
     * Esse método calcula o gradiente de retropropagação da rede.
     *
     * @param valorSaida valor de saída da rede.
     * @param erro erro da rede em relação a saída esperada.
     */
    private double calcularGradienteDeRetropopagacao(double valorSaida, double erro) {
        return valorSaida * (1 - valorSaida) * erro;
    }

    /**
     * Propaga sinais pela rede.
     *
     */
    public void propagarSinaisPelaRede(double[] entradas) {
        camadaDeSaida.progaparSinais(camadaIntermediaria.progaparSinais(entradas));
    }

    /**
     * @return the camadaIntermediaria
     */
    public Camada getCamadaIntermediaria() {
        return camadaIntermediaria;
    }

    /**
     * @return the camadaDeSaida
     */
    public Camada getCamadaDeSaida() {
        return camadaDeSaida;
    }

    /**
     * @return the TAXA_DE_APRENDIZADO
     */
    public double getTaxaDeAprendizado() {
        return taxaDeAprendizado;
    }

    public void setTaxaDeAprendizado(double taxa) {
        taxaDeAprendizado = taxa;
    }

    /**
     * @return the matrizDeEntradas
     */
    public double[] getMatrizDeEntradas() {
        return matrizDeEntradas;
    }

    /**
     * @return numero de épocas usadas para o treinamento.
     */
    public int getEpocas() {
        return epocas;
    }

    /**
     * @return o valor do BIAS da rede
     */
    public double getBIAS() {
        return BIAS;
    }

    /**
     * Altera bias da Rede. O bias é uma constante que deve ser definido pela
     * função de ativação dos neurônios. A função utilizada aqui é a função
     * tangente hiperbólica, que trabalha com números entre 0 e 1, por isso o
     * bias é definido como 1, para outras funções o bias pode assumir outro
     * valor, como por exemplo a função logarítimica. Por isso é nomeada como
     * uma constante, mas sem o modificador final
     */
    public void alterarBiasDaRede(double bias) {
        BIAS = bias;
        camadaIntermediaria.alterarBiasCamada(BIAS);
        camadaDeSaida.alterarBiasCamada(BIAS);
    }

    /**
     * @return the entradaCamadaDeSaida
     */
    public double[] getEntradaCamadaDeSaida() {
        return entradaCamadaDeSaida;
    }

    /**
     * @return the saidaRede
     */
    public double getSaidaRede() {
        return saidaRede;
    }

    /**
     * @return the conjuntoTreinamentoMatriz
     */
    public double[][] getConjuntoTreinamento() {
        return conjuntoTreinamentoMatriz;
    }

    /**
     * @param conjuntoTreinamento the conjuntoTreinamentoMatriz to set
     */
    public void setConjuntoTreinamento(double[][] conjuntoTreinamento) {
        this.conjuntoTreinamentoMatriz = conjuntoTreinamento;
    }

    /**
     * @return the matrizDeTreinamento
     */
    public double[] getMatrizDeTreinamento() {
        return matrizDeTreinamento;
    }

    /**
     * @param matrizDeTreinamento the matrizDeTreinamento to set
     */
    public void setMatrizDeTreinamento(double[] matrizDeTreinamento) {
        this.matrizDeTreinamento = matrizDeTreinamento;
    }

    /**
     * @return the erro
     */
    public double getErro() {
        return erro;
    }

    /**
     * @return o gradiente de retropropagação de erro. o valor retornado será
     * diferente para cada loop de treinamento.
     */
    public double getGradiente() {
        return gradiente;
    }

    public void setPorcentagemDeErroAceitavel(double taxaDeErroAceitavel) {
        porcentagemDeErroAceitavel = taxaDeErroAceitavel;
    }

    public double getPorcentagemDeErroAceitavel() {
        return porcentagemDeErroAceitavel;
    }
}
