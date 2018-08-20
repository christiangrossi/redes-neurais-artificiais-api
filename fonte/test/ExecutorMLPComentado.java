
import com.anhanguera.neuralnetworks.MultilayerPerceptron;
import com.anhanguera.utils.CarregadorDeMatrizes;
import com.anhanguera.utils.Normalizador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Christian Santos
 */
public class ExecutorMLPComentado {

	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException, Exception {
		System.out.println("--- Hello, i'm MutiLayer Perceptron ---\n");

		/*
		 * Essa carrega arquivos e monta matrizes de entrada e treinamento,de teste e de
		 * valores esperados para saídas da rede.
		 */
		CarregadorDeMatrizes leitor = new CarregadorDeMatrizes();

		String caminhoCirculos = new File("./conjunto/treinamento/circ/").getCanonicalPath();
		String caminhoTriangulos = new File("./conjunto/treinamento/trian").getCanonicalPath();

		ArrayList<double[]> circulos = leitor.LerDeDiretorios(caminhoCirculos, true);
		ArrayList<double[]> triangulos = leitor.LerDeDiretorios(caminhoTriangulos, true);

		// Normalização de dados para valores entre 0 e 1
		Normalizador normalizador = new Normalizador();
		ArrayList<double[]> dadosCirculosNormalizados = normalizador.normalizarDados(circulos);
		ArrayList<double[]> dadosTriangulosNormalizados = normalizador.normalizarDados(triangulos);
		// normalizador.imprimirDados();

		// CRIANDO INSTÂNCIA DE LISTA PARA O CONJUNTO DE TREINAMENTO
		ArrayList<double[]> conjuntoDeTreinamento = new ArrayList<>();

		// ADICIONA CÍRCILOS À LISTA DE TREINAMETNO
		for (double[] item : dadosCirculosNormalizados) {
			conjuntoDeTreinamento.add(item);
		}
		// ADICIONA TRIÂNGULOS À LISTA DE TREINAMENTO
		for (double[] item : dadosTriangulosNormalizados) {
			conjuntoDeTreinamento.add(item);
		}

		MultilayerPerceptron redeneural = new MultilayerPerceptron(18, 1, 500000);
		redeneural.setTaxaDeAprendizado(0.01);
	
		System.out.println("Inicializando Conexões sinápticas...");
		redeneural.inicializarConexoesSinapticas(conjuntoDeTreinamento.get(0).length);

		double[] valoresEsperados = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

		redeneural.aprender(conjuntoDeTreinamento, valoresEsperados);

		String caminhoCirculosClassificacao = new File("./conjunto/classificacao/circ/").getCanonicalPath();
		String caminhoTriangulosClassificacao = new File("./conjunto/classificacao/trian").getCanonicalPath();

		System.out.println("\n\nCarregando dados para classificação...\n\n");
		ArrayList<double[]> cassificarCirculos = leitor.LerDeDiretorios(caminhoCirculosClassificacao, true);

		System.out.println("Carregando dados para classificação...");
		ArrayList<double[]> cassificarTriangulos = leitor.LerDeDiretorios(caminhoTriangulosClassificacao, true);

		System.out.println("*************************************************");
		System.out.println("Saida Classificação Círculos:");

		for (double[] item : cassificarCirculos) {
			redeneural.classificar(normalizador.normalizarDados(item));
		}
		System.out.println("*************************************************");
		System.out.println("Saída Classificação Triangulos");

		for (double[] item : cassificarTriangulos) {
			redeneural.classificar(normalizador.normalizarDados(item));
		}
		System.out.println("\n\nCarregando dados viciados...\n\n");
	}
}
