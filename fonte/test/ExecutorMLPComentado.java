

import com.anhanguera.redeneuralmlp.RedeNeuralMultilayerPerceptron;
import com.anhanguera.redeneuralmlp.Normalizador;
import com.anhanguera.redeneuralmlp.CarregadorDeMatrizes;
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
        CarregadorDeMatrizes leitor = new CarregadorDeMatrizes();

        //ArrayList<double[]> circulos = leitor.LerDeDiretorios();
        //ArrayList<double[]> triangulos = leitor.LerDeDiretorios();
        //Carregamento direto pelo método
        String caminhoCirculos = ""
                + "C:\\Users\\Christian Santos\\Desktop\\conjuntoV3\\txt Final\\circulo";
        String caminhoTriangulos = ""
                + "C:\\Users\\Christian Santos\\Desktop\\conjuntoV3\\txt Final\\triangulo";

        ArrayList<double[]> circulos = leitor.LerDeDiretorios(caminhoCirculos);
        ArrayList<double[]> triangulos = leitor.LerDeDiretorios(caminhoTriangulos);

        Normalizador normalizador = new Normalizador();

        //REALIZA NORMALIZAÇÃO DOS DADOS DOS CÍRCULOS PARA QUE FIQUEM ENTRE 0 E 1
        //System.out.println("**********Pré Normalização Circulos**********");
        //normalizador.imprimirDados(circulos);
        //System.out.println("**********Pós Normalização**********");
        ArrayList<double[]> dadosCirculosNormalizados = normalizador.normalizarDados(circulos);
        // normalizador.imprimirDados();

        //REALIZA NORMALIZAÇÃO DOS DADOS DOS TRIÂNGULOS PARA QUE FIQUEM ENTRE 0 E 1
        //System.out.println("**********Pré Normalização Triangulos**********");
        //normalizador.imprimirDados(triangulos);
        //System.out.println("**********Pós Normalização**********");
        ArrayList<double[]> dadosTriangulosNormalizados = normalizador.normalizarDados(triangulos);
        //normalizador.imprimirDados();

        // CRIANDO INSTÂNCIA DE LISTA PARA O CONJUNTO DE TREINAMENTO
        ArrayList<double[]> conjuntoDeTreinamento = new ArrayList<>();

        //ADICIONA CÍRCILOS À LISTA DE TREINAMETNO
        for (double[] item : dadosCirculosNormalizados) {
            conjuntoDeTreinamento.add(item);
        }
        //ADICIONA TRIÂNGULOS À LISTA DE TREINAMENTO
        for (double[] item : dadosTriangulosNormalizados) {
            conjuntoDeTreinamento.add(item);
        }

        RedeNeuralMultilayerPerceptron redeneural = new RedeNeuralMultilayerPerceptron(18, 1, 500000);
        redeneural.setTaxaDeAprendizado(0.01);
        /*
         System.out.println("Taxa de aprendizado desejada:");
         Scanner s = new Scanner(System.in);
         double taxa = s.nextDouble();
        
         redeneural.setTaxaDeAprendizado(taxa);
        
         System.out.println("Taxa de erro aceitável:");
         double taxaErro = s.nextDouble();
         redeneural.setPorcentagemDeErroAceitavel(taxaErro);

         System.out.println("taxa de aprendizado aplicada: " + taxa);
         System.out.println("taxa de aprendizado Aceitavel aplicada: " + taxaErro);*/

        System.out.println("Inicializando Conexões sinápticas...");
        redeneural.inicializarConexoesSinapticasDaRede(conjuntoDeTreinamento.get(0).length);

        double[] valoresEsperados = {
            
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        };

        redeneural.treinar(conjuntoDeTreinamento, valoresEsperados);

        System.out.println("\n\nCarregando dados para classificação...\n\n");
        ArrayList<double[]> cassificarCirculos = leitor.LerDeDiretorios("C:\\Users\\Christian Santos\\Desktop\\conjuntoV3\\txt Final\\classificar cir");

        System.out.println("Carregando dados para classificação...");
        ArrayList<double[]> cassificarTriangulos = leitor.LerDeDiretorios("C:\\Users\\Christian Santos\\Desktop\\conjuntoV3\\txt Final\\classicicar trian");

        System.out.println("*************************************************");
        System.out.println("Saida Classificação Círculos:");

        for (double[] item : cassificarCirculos) {
            redeneural.classificarDados(normalizador.normalizarDados(item));
        }
        System.out.println("*************************************************");
        System.out.println("Saída Classificação Triangulos");

        for (double[] item : cassificarTriangulos) {
            redeneural.classificarDados(normalizador.normalizarDados(item));
        }
        System.out.println("\n\nCarregando dados viviados...\n\n");
        ArrayList<double[]> circulosDoTreinamento = leitor.LerDeDiretorios();
        ArrayList<double[]> triangulosDoTreinamento = leitor.LerDeDiretorios();

        String nextLine = s.nextLine();
    }
}
