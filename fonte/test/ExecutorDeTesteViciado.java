

import com.anhanguera.neuralnetworks.MultilayerPerceptron;
import com.anhanguera.utils.CarregadorDeMatrizes;
import com.anhanguera.utils.Normalizador;

import java.util.ArrayList;

/**
 *
 * @author Christian Santos
 */
public class ExecutorDeTesteViciado {

    public static void main(String[] args) throws Exception {
        CarregadorDeMatrizes leitor = new CarregadorDeMatrizes();
        String triangulo = "C:\\Users\\Christian Santos\\Desktop\\conjuntoV3\\txt Final\\mist\\";
        String circulo =   "C:\\Users\\Christian Santos\\Desktop\\conjuntoV3\\txt Final\\mist\\";
        Normalizador normalizador = new Normalizador();
        double[] circuloForm = normalizador.normalizarDados(leitor.carregarDados(circulo, "f00"));
        double[] trianguloForm = normalizador.normalizarDados(leitor.carregarDados(triangulo, "f01"));
        ArrayList<double[]> conjuntoDeTreinamento = new ArrayList<>();

        conjuntoDeTreinamento.add(circuloForm);
        conjuntoDeTreinamento.add(trianguloForm);

        MultilayerPerceptron rede = new MultilayerPerceptron(3, 1, 500000);
        rede.inicializarConexoesSinapticas(circuloForm.length);
        rede.aprender(conjuntoDeTreinamento, new double[]{1, 0});

        rede.classificar(circuloForm);
        rede.classificar(trianguloForm);
        double[] circuloForm2 = normalizador.normalizarDados(leitor.carregarDados(triangulo, "f00"));
        double[] circuloForm3 = normalizador.normalizarDados(leitor.carregarDados(triangulo, "f01"));
        //rede.classificarDados(circuloForm2);
        //rede.classificarDados(circuloForm3);
        System.out.println("Épocas: "+rede.getEpocas()+" Erro:"+rede.getErro());
    }

}
