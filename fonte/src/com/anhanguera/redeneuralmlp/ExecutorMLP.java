package com.anhanguera.redeneuralmlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Christian Santos
 */
public class ExecutorMLP {

    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException, Exception {

        System.out.println("--- Hello, i'm MutiLayer Perceptron ---\n");

        CarregadorDeMatrizes leitor = new CarregadorDeMatrizes();
        Normalizador normalizador = new Normalizador();

        String caminhoCirculos = ""
                + "C:\\Users\\Christian Santos\\Desktop\\Conjunto\\treinamento\\circ";
        String caminhoTriangulos = ""
                + "C:\\Users\\Christian Santos\\Desktop\\Conjunto\\treinamento\\trian";

        ArrayList<double[]> circulos = normalizador.normalizarDados(leitor.LerDeDiretorios(caminhoCirculos));
        ArrayList<double[]> triangulos = normalizador.normalizarDados(leitor.LerDeDiretorios(caminhoTriangulos));
        int indiceC = 0;
        int indiceT = 0;
        int alternador = 0;

        ArrayList<double[]> conjuntoDeTreinamento = new ArrayList<>(170);

        /*ADICIONA DADOS ENTRELAÇADAMENTE NA LISTA CONJUNTO DE TREINAMENTO, 
         ...CÍRCULOS EM ÍNDICES PARES E TRIANGULOS EM IMPARES*/
        for (int i = 0; i < 140; i++) {
            if (alternador == 0) {
                //System.out.println("adicionando circulo a listaººº ");
                conjuntoDeTreinamento.add(circulos.get(indiceC));
                indiceC++;
                alternador = 1;
            } else {
                //System.out.println("adicionando triângulo a lista***");
                conjuntoDeTreinamento.add(triangulos.get(indiceT));
                indiceT++;
                alternador = 0;
            }
        }

        RedeNeuralMultilayerPerceptron redeneural = new RedeNeuralMultilayerPerceptron(14, 1, 10000000);

        System.out.println("Inicializando Conexões sinápticas...");
        redeneural.inicializarConexoesSinapticasDaRede(10);// dez entradas para cada neruônio.
        GerenciadorDeConhecimento conhecimento = new GerenciadorDeConhecimento();

        /*Saídas esperadas*/
        double[] valoresEsperados = {
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0
        };

        redeneural.treinar(conjuntoDeTreinamento, valoresEsperados);

        String caminhoTestesViciadosCirculos = caminhoCirculos;
        String caminhoTesteTriangulos = caminhoTriangulos;

        System.out.println("\n\nCarregando dados viciados...\n\n");

        ArrayList<double[]> circulosDoTreinamento = leitor.LerDeDiretorios(caminhoTestesViciadosCirculos);
        ArrayList<double[]> triangulosDoTreinamento = leitor.LerDeDiretorios(caminhoTesteTriangulos);

        System.out.println("\n\nCarregando dados novos...\n\n");

        ArrayList<double[]> circulosNovos
                = leitor.LerDeDiretorios("C:\\Users\\Christian Santos\\Desktop\\Conjunto\\classificação\\circ");
        ArrayList<double[]> triangulosNovos
                = leitor.LerDeDiretorios("C:\\Users\\Christian Santos\\Desktop\\Conjunto\\classificação\\trian");

        int acCirculosV = 0, acCirculos = 0, AcTriangulosV = 0, AcTriangulos = 0;

        System.out.println("*************************************************");
        System.out.println("Saida Classificação Círculos:");

        for (double[] item : circulosDoTreinamento) {
            double resposta = redeneural.classificarDados(normalizador.normalizarDados(item));
            if (resposta == 1) {
                acCirculosV++;
            }
        }
        System.out.println("*************************************************");
        System.out.println("Saida Classificação Triângulos:");

        for (double[] item : triangulosDoTreinamento) {
            double resposta = redeneural.classificarDados(normalizador.normalizarDados(item));
            if (resposta == 0) {
                AcTriangulosV++;
            }
        }

        System.out.println("Épocas: " + redeneural.getEpocas() + " Erro:" + redeneural.getErro());
        System.out.println("\n*******************************************"
                + "Dados não conhecidos pela Rede****************"
                + "************************\n");

        System.out.println("Círculos  não conhecidos...\n");
        for (double[] item : circulosNovos) {
            double resposta = redeneural.classificarDados(normalizador.normalizarDados(item));
             if (resposta == 1) {
                acCirculos++;
            }
        }

        System.out.println("Triângulos não conhecidos...\n");

        for (double[] item : triangulosNovos) {
            double resposta = redeneural.classificarDados(normalizador.normalizarDados(item));
            if (resposta == 0) {
                AcTriangulos++;
            }
        }
        System.out.println("\n\n*********************RESULTADOS***************************");
        System.out.println("acerto de Círculos conhecidos pela rede: "+acCirculosV +"acertos de "+circulos.size());
        System.out.println("acerto de Triângulos  conhecidos pela rede: "+AcTriangulosV+"acertos de "+triangulos.size());
        System.out.println("acerto de Círculos não conhecidos pela rede: "+acCirculos+"acertos de "+circulosNovos.size());
        System.out.println("acerto de Triângulos não conhecidos pela rede: "+acCirculos+"acertos de "+triangulosNovos.size());
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        /*
         GerenciadorDeConhecimento conhecimento = new GerenciadorDeConhecimento();
         CONHECIMENTO SALVO...
         */
        /*
        System.out.println("Digite um nome para o arquivo que guardará os"
                + "dados sinápticos da rede:");

        String nomeDoArquivo = s.next();
        conhecimento.guardarConhecimento(redeneural, "C:\\Users\\Christian Santos\\Desktop\\Backup\\", nomeDoArquivo);
        */
    }
}
