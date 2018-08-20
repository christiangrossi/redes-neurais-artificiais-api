package com.anhanguera.redeneuralmlp.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.anhanguera.neuralnetworks.MultilayerPerceptron;
import com.anhanguera.utils.CarregadorDeMatrizes;
import com.anhanguera.utils.Normalizador;

/**
 *
 * @author Christian Santos
 */
public class TesteClassificacaoFigurasGeometricas {

	/* Saídas esperadas */
	private static double[] valoresEsperados = { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
			1, 0, 1, 0 };
	
	 public static void circuloOuTriangulo(double resposta) {
	        if (resposta == 0) {
	            System.out.print(" -> É um círculo\n");
	        } else if (resposta == 1) {
	            System.out.print(" -> É um Triângulo\n");
	        }
	    }

	public static void main(String[] args) throws IOException, InterruptedException, Exception {

		System.out.println("--- Hello, i'm MutiLayer Perceptron ---\n");

		CarregadorDeMatrizes leitor = new CarregadorDeMatrizes();
		Normalizador normalizador = new Normalizador();

		String caminhoCirculos = new File("./conjunto/treinamento/circ/").getCanonicalPath();
		String caminhoTriangulos = new File("./conjunto/treinamento/trian").getCanonicalPath();

		List<double[]> circulos = normalizador.normalizarDados(leitor.LerDeDiretorios(caminhoCirculos, true));
		List<double[]> triangulos = normalizador.normalizarDados(leitor.LerDeDiretorios(caminhoTriangulos, true));

		int indiceC = 0;
		int indiceT = 0;
		int alternador = 0;

		List<double[]> conjuntoDeTreinamento = new ArrayList<>(170);

		/*
		 * ADICIONA DADOS ENTRELAÇADAMENTE NA LISTA CONJUNTO DE TREINAMENTO, ...CÍRCULOS
		 * EM ÍNDICES PARES E TRIANGULOS EM IMPARES
		 */
		for (int i = 0; i < 140; i++) {
			if (alternador == 0) {
				// System.out.println("adicionando circulo a lista...");
				conjuntoDeTreinamento.add(circulos.get(indiceC));
				indiceC++;
				alternador = 1;
			} else {
				// System.out.println("adicionando triângulo a lista...");
				conjuntoDeTreinamento.add(triangulos.get(indiceT));
				indiceT++;
				alternador = 0;
			}
		}

		MultilayerPerceptron redeneural = new MultilayerPerceptron(14, 1, 10000000);

		System.out.println("Inicializando Conexões sinápticas...");
		redeneural.inicializarConexoesSinapticas(10);// dez entradas para cada neruônio.

		redeneural.aprender(conjuntoDeTreinamento, valoresEsperados);

		String caminhoTestesViciadosCirculos = caminhoCirculos;
		String caminhoTesteTriangulos = caminhoTriangulos;

		System.out.println("\n\nCarregando dados viciados...\n\n");

		List<double[]> circulosDoTreinamento = leitor.LerDeDiretorios(caminhoTestesViciadosCirculos, true);
		List<double[]> triangulosDoTreinamento = leitor.LerDeDiretorios(caminhoTesteTriangulos, true);

		System.out.println("\n\nCarregando dados novos...\n\n");

		String caminhoCirculosClassificacao = new File("./conjunto/classificacao/circ/").getCanonicalPath();
		String caminhoTriangulosClassificacao = new File("./conjunto/classificacao/trian").getCanonicalPath();

		List<double[]> circulosNovos = leitor.LerDeDiretorios(caminhoCirculosClassificacao, true);
		List<double[]> triangulosNovos = leitor.LerDeDiretorios(caminhoTriangulosClassificacao, true);

		int acCirculosV = 0, acCirculos = 0, AcTriangulosV = 0, AcTriangulos = 0;

		System.out.println("*************************************************");
		System.out.println("Saida Classificação Círculos:");

		for (double[] item : circulosDoTreinamento) {
			double resposta = redeneural.classificar(normalizador.normalizarDados(item));
			circuloOuTriangulo(resposta);
			if (resposta == 1) {
				acCirculosV++;
			}
		}
		System.out.println("*************************************************");
		System.out.println("Saida Classificação Triângulos:");

		for (double[] item : triangulosDoTreinamento) {
			double resposta = redeneural.classificar(normalizador.normalizarDados(item));
			 circuloOuTriangulo(resposta);
			if (resposta == 0) {
				AcTriangulosV++;
			}
		}

		System.out.println("Épocas: " + redeneural.getEpocas() + " Erro:" + redeneural.getErro());
		System.out.println("\n*******************************************"
				+ "Dados não conhecidos pela Rede****************" + "************************\n");

		System.out.println("Círculos  não conhecidos...\n");
		for (double[] item : circulosNovos) {
			double resposta = redeneural.classificar(normalizador.normalizarDados(item));
			circuloOuTriangulo(resposta);
			if (resposta == 1) {
				acCirculos++;
			}
		}

		System.out.println("Triângulos não conhecidos...\n");

		for (double[] item : triangulosNovos) {
			double resposta = redeneural.classificar(normalizador.normalizarDados(item));
			 circuloOuTriangulo(resposta);
			if (resposta == 0) {
				AcTriangulos++;
			}
		}
		System.out.println("\n\n*********************RESULTADOS***************************\n");
		System.out.println(
				"Número de acertos de círculos conhecidos pela rede: " + acCirculosV + " de " + circulos.size());
		System.out.println(
				"Número de acertos de triângulos  conhecidos pela rede: " + AcTriangulosV + " de " + triangulos.size());
		System.out
				.println("Número de acertos de círculos não conhecidos pela rede: " + acCirculos + " de " + circulosNovos.size());
		System.out.println("Número de acertos de acerto triângulos não conhecidos pela rede: " + AcTriangulos + " de " + triangulosNovos.size());
		System.out.println("Épocas usadas para processamento: " + redeneural.getEpocas());
	}
}
