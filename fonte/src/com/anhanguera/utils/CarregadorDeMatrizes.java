package com.anhanguera.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Essa carrega arquivos e monta matrizes de entrada e treinamento,de teste e de
 * valores esperados para saídas da rede.
 *
 * @author Christian Santos
 *
 */
public class CarregadorDeMatrizes {

	private int qtdImg; // quantidade de imagens que serão utilizadas para treinamento e classificação
	private String[] caminhos; // vetor de caminhos de imagem
	private double[] valoresEsperados; // vetor de valores esperados para cada vetor de treinamento
	private int linhas;
	private int colunas;
	private double[] dados;
	private ArrayList<double[]> listaDeTreinamento;

	public CarregadorDeMatrizes() {

	}

	/**
	 * Carrega todos os arquivos de um diretório com base de um caminho passado pela
	 * entrada do usuário. O diretório não pode ter subdiretórios.
	 * 
	 * @return uma lista de dados que pode ser usada tanto para treinamento quanto
	 *         para classificação ou definição de saídas esperadas.
	 */
	public ArrayList<double[]> LerDeDiretorios() {
		try {
			ArrayList<double[]> listaDeTreinamento = new ArrayList<>();

			System.out.print("Digite o caminho da pasta de diretório das imagens\n->");
			Scanner s = new Scanner(System.in);
			String caminho = s.nextLine();

			File raiz = new File(caminho);
			String[] list = raiz.list();
			File[] arquivos = raiz.listFiles();
			FileReader leitorArquivo;
			BufferedReader leitorBuffer = null;
			for (File arquivo : arquivos) {
				System.out.println(arquivo.getAbsolutePath() + " carregado...");
				leitorArquivo = new FileReader(arquivo);
				leitorBuffer = new BufferedReader(leitorArquivo);
				String linha = leitorBuffer.readLine();
				String stringSplit = linha;
				/*
				 * while (linha != null) { stringSplit += linha; linha =
				 * leitorBuffer.readLine(); }
				 */
				String[] numerosString = stringSplit.split(" ");
				double[] vetorDouble = new double[numerosString.length];

				for (int i = 0; i < numerosString.length; i++) {
					vetorDouble[i] = Double.parseDouble(numerosString[i]);
				}
				listaDeTreinamento.add(vetorDouble);
			}
			leitorBuffer.close();
			arquivos.clone();
			/*
			 * for (String list1 : list) { System.out.println(">" + list1); }
			 */

			return listaDeTreinamento;
		} catch (IOException | NumberFormatException e) {
			System.out.println("Ocorreu um erro, repita o processo");
			return LerDeDiretorios();

		}
	}

	/**
	 * Carrega todos os arquivos de um diretório com base de um caminho passado pela
	 * entrada do usuário. O diretório não pode ter subdiretórios.
	 * 
	 * @return uma lista de dados que pode ser usada tanto para treinamento quanto
	 *         para classificação ou definição de saídas esperadas.
	 */
	public ArrayList<double[]> LerDeDiretorios(String caminhoPasta, boolean printLog) throws Exception {

		try {
			String caminho = caminhoPasta;
			ArrayList<double[]> listaDeTreinamento = new ArrayList<>();
			File raiz = new File(caminho);

			File[] arquivos = raiz.listFiles();
			FileReader leitorArquivo;
			BufferedReader leitorBuffer = null;
			for (File arquivo : arquivos) {
				leitorArquivo = new FileReader(arquivo);
				leitorBuffer = new BufferedReader(leitorArquivo);
				String linha = leitorBuffer.readLine();
				String stringSplit = linha;
				String[] numerosString = stringSplit.split(" ");
				double[] vetorDouble = new double[numerosString.length];

				for (int i = 0; i < numerosString.length; i++) {
					vetorDouble[i] = Double.parseDouble(numerosString[i]);
				}
				listaDeTreinamento.add(vetorDouble);
				if (printLog)
					System.out.println(arquivo.getAbsolutePath() + " carregado.");
			}
			leitorBuffer.close();
			arquivos.clone();

			return listaDeTreinamento;
		} catch (IOException | NumberFormatException e) {
			System.out.println("Ocorreu um erro, repita o processo");
			return LerDeDiretorios();

		}
	}

	public double[][] montarMatrizDeTreinamento() {
		ArrayList<String> lista = new ArrayList<>();
		colunas = qtdImg;
		linhas = dados.length;
		double[][] matrizDeTreinamento = new double[linhas][colunas];
		return matrizDeTreinamento;
	}

	public ArrayList<double[]> criarListaDeVetores() {

		return null;
	}

	/**
	 * @deprecated
	 */
	public void lerArquivoPath() throws IOException {
		try {
			System.out.print("Digite o caminho do arquivo path?\n-> ");
			Scanner entradaUsuario = new Scanner(System.in);
			String caminho = entradaUsuario.nextLine();
			FileReader arq = new FileReader(caminho);

			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			String stringSplit = linha;
			while (linha != null) {
				stringSplit += linha;
				linha = lerArq.readLine();
			}
			System.out.println("\ncarregado com sucesso:  " + caminho + "\n");
			arq.close();
			caminhos = stringSplit.split("\n");

			for (String caminho1 : caminhos) {
				System.out.println(caminho1 + "\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @deprecated
	 */
	public void lerArquivoPath(String caminhoArquivoPath) {
		;
		try {
			FileReader arq = new FileReader(caminhoArquivoPath);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			String stringSplit = linha;
			System.out.println("--" + linha);
			while (linha != null) {
				stringSplit += linha;
				linha = lerArq.readLine();
			}
			System.out.println("\ncarregado com sucesso:  " + caminhoArquivoPath + "\n");
			arq.close();
			caminhos = stringSplit.split("\n");

			for (String caminho1 : caminhos) {
				System.out.println(caminho1 + "\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @deprecated
	 */
	public ArrayList<double[]> carregarDadosEmLista(String caminhoPasta, String nomeDoArquivo, String extensao)
			throws IOException {

		System.out.println("Quantas imagens serão utilizadas para criar o conjunto de treinamento?");
		Scanner entradaUsuario = new Scanner(System.in);
		qtdImg = entradaUsuario.nextInt();
		caminhos = new String[qtdImg];
		for (int i = 0; i < qtdImg; i++) {
			caminhos[i] = caminhoPasta + "/" + nomeDoArquivo + (i + 1) + "." + extensao;
		}
		return carregarPassandoCaminhos();
	}

	/**
	 * @deprecated
	 */
	public ArrayList<double[]> carregarDadosEmLista() throws IOException {
		System.out.println("Quantas imagens serão utilizadas para criar o conjunto de treinamento?");
		Scanner s = new Scanner(System.in);
		qtdImg = s.nextInt();
		caminhos = new String[qtdImg];
		Scanner path = new Scanner(System.in);
		System.out.println("Digite os caminhos das imagens que serão utilizadas " + "para o conjunto de treinamento:");
		for (int i = 0; i < qtdImg; i++) {
			System.out.println("Imagem " + (i + 1) + ":");
			caminhos[i] = path.nextLine();
		}
		return carregarPassandoCaminhos();
	}

	/**
	 * Esse método carrega os arquivos de dentro de uma pasta denterminada e
	 * transforma em uma matriz. Nomear todos os arquivos com o mesmo nome, só
	 * incrementando números de 1 a N após o nome, sem pular nenhum valor entre esse
	 * intervalo. Ex: teste1,teste2 ou img_1, img_2 ou txt1, txt2, etc...
	 *
	 * @param caminhoDaPasta pasta de onde serão carregados os arquivos.
	 * @param nomeDoArquivo  nome padrão dos arquivos da pasta.
	 * @param extensao       extensão do aquivo. Ex: .sift, .txt ...
	 * @deprecated será melhorado em versões futuras.
	 */
	public double[][] carregarDadosEmMatriz(String caminhoDaPasta, String nomeDoArquivo, String extensao)
			throws FileNotFoundException, IOException {
		ArrayList<String[]> lista = new ArrayList<>();

		double[] matrizDeEntradasDouble = null;
		double matriz[][];
		System.out.println("Quantas imagens serão utilizadas para criar o conjunto de treinamento?");
		Scanner s = new Scanner(System.in);
		qtdImg = s.nextInt();
		String[][] matrizDeEntradasString = new String[qtdImg][];
		caminhos = new String[qtdImg];
		for (int i = 0; i < qtdImg; i++) {
			caminhos[i] = caminhoDaPasta + "/" + nomeDoArquivo + (i + 1) + "." + extensao;
		}
		for (int i = 0; i < qtdImg; i++) {
			FileReader arq = new FileReader(caminhos[i]);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			String linha2 = "";
			String linha3 = "";
			while (linha != null) {
				linha2 += linha;
				linha = lerArq.readLine();
				linha3 += linha;

			}
			System.out.println("carregado com sucesso:  " + caminhos[i] + "");
			String qqr = linha2;
			String[] split = linha2.split(" ");
			lista.add(split);
			matrizDeEntradasString[i] = linha2.split(" ");
			arq.close();
		}
		int lines = matrizDeEntradasString[0].length;
		matriz = new double[matrizDeEntradasString[0].length][qtdImg];
		for (int i = 0; i < qtdImg; i++) {
			for (int j = 0; j < matrizDeEntradasString[0].length; j++) {
				matriz[j][i] = Double.parseDouble(matrizDeEntradasString[i][j]);
			}
		}

		return matriz;
	}

	/**
	 * @deprecated substituído por carregarDados(String caminhoDaPasta, String
	 *             nomeDoArquivo), que não necessita que seja passada a extensão do
	 *             arquivo a ser carregado. Sendo também samanticamente mais
	 *             genérico, indicando que pode ser usado pra vários propósitos no
	 *             contexto da rede.
	 */
	public double[] carregarDadosParaClassificacao(String caminhoDaPasta, String nomeDoArquivo, String extensao)
			throws FileNotFoundException, IOException {

		double[] matrizDeEntradasDouble = null;
		String caminho = caminhoDaPasta + "/" + nomeDoArquivo + "." + extensao;

		FileReader arq = new FileReader(caminho);
		BufferedReader lerArq = new BufferedReader(arq);
		String linha = lerArq.readLine();
		System.out.println("carregado com sucesso:  " + caminho + "");
		arq.close();
		String[] matrizDeEntradasString = linha.split(" ");
		matrizDeEntradasDouble = new double[matrizDeEntradasString.length];

		for (int i = 0; i < matrizDeEntradasString.length; i++) {
			matrizDeEntradasDouble[i] = Double.parseDouble(matrizDeEntradasString[i]);
		}

		return matrizDeEntradasDouble;
	}

	/**
	 * Carrega dados de um único arquivo para a rede neural. Podem ser carregados
	 * dados para treinamento, classificação ou como dados de saídas esperadas.
	 * 
	 * @param caminhoDaPasta caminho da pasta de onde o arquivo será carregado
	 * @param nomeDoArquivo  nome do arquivo que será carregado.
	 * @return uma matriz com dados de entrada para alguma utilidade na rede.
	 */
	public double[] carregarDados(String caminhoDaPasta, String nomeDoArquivo)
			throws FileNotFoundException, IOException {

		double[] matrizDeEntradasDouble = null;
		String caminho = caminhoDaPasta + "/" + nomeDoArquivo + ".txt";

		FileReader arq = new FileReader(caminho);
		BufferedReader lerArq = new BufferedReader(arq);
		String linha = lerArq.readLine();
		System.out.println("carregado com sucesso:  " + caminho + "");
		arq.close();
		String[] matrizDeEntradasString = linha.split(" ");
		matrizDeEntradasDouble = new double[matrizDeEntradasString.length];

		for (int i = 0; i < matrizDeEntradasString.length; i++) {
			matrizDeEntradasDouble[i] = Double.parseDouble(matrizDeEntradasString[i]);
		}

		return matrizDeEntradasDouble;
	}

	/**
	 * @deprecated
	 */
	private ArrayList<double[]> carregarPassandoCaminhos() throws FileNotFoundException, IOException {
		System.out.println("------------------------------------------------------" + "\nCarregando imagens...\n"
				+ "------------------------------------------------------");

		listaDeTreinamento = new ArrayList<>();
		double[] matrizDeEntradasDouble = null;

		String[] matrizDeEntradasString;
		for (int i = 0; i < qtdImg; i++) {
			FileReader arq = new FileReader(caminhos[i]);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			String stringSplit = linha;
			System.out.println("carregado com sucesso:  " + caminhos[i] + "");
			arq.close();
			matrizDeEntradasString = stringSplit.split(" ");
			matrizDeEntradasDouble = new double[matrizDeEntradasString.length];
			System.out.println("numero totais de valores de pixels: " + matrizDeEntradasString.length
					+ "\n.......................................................");

			for (int j = 0; j < matrizDeEntradasString.length; j++) {
				matrizDeEntradasDouble[j] = Double.parseDouble(matrizDeEntradasString[j]);
				System.out.println(matrizDeEntradasDouble[j]);
			}
			listaDeTreinamento.add(matrizDeEntradasDouble);
		}
		return listaDeTreinamento;
	}

	/**
	 * @param lista lista da qual serão definidas as saídas esperadas para cada
	 *              item.
	 * @return uma vetor com os valores para cada item da lista passada por
	 *         parâmetro.
	 */
	public double[] valoresEsperados(ArrayList<double[]> lista) {
		valoresEsperados = new double[lista.size()];
		Scanner s = new Scanner(System.in);
		for (int i = 0; i < lista.size(); i++) {
			System.out.println("Valor esperado para a coluna " + i);
			valoresEsperados[i] = s.nextDouble();
		}
		String saidas = "Saídas esperadas: ";
		for (int i = 0; i < lista.size(); i++) {
			saidas += "[" + Double.toString(+valoresEsperados[i]) + "]";
		}
		System.out.println(saidas);

		return valoresEsperados;
	}
}
