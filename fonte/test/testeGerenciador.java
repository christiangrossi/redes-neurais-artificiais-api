
import com.anhanguera.redeneuralmlp.GerenciadorDeConhecimento;
import com.anhanguera.redeneuralmlp.RedeNeuralMultilayerPerceptron;
import java.io.IOException;

/**
 * Esse é um exemplo de como salvar e carregar dados de conexões sinápticas após
 * para a rede neural.
 *
 * @author Christian Santos
 */
public class testeGerenciador {

    public static void main(String[] args) throws IOException {

        // nova rede neural instânciada.
        RedeNeuralMultilayerPerceptron rede = new RedeNeuralMultilayerPerceptron(3, 1);

        // para salvar os valores das conexões, é necessário inicialmente que as 
        // mesmas tenham sido inicializadas.
        rede.inicializarConexoesSinapticasDaRede(3);

        /*
         INICIAR PROCESSO DE TREINAMENTO... OU AS SINAPSES SERÃO SALVAS EM VÃO,
         O PROPÓSITO AQUI É SALVAR O CONHECIMENTO DE UMA REDE JÁ TREINADA.
         ENTRETANTO, PODE-SE FAZER UMA CÓPIA PREVENTIVA DOS DADOS CASO A REDE 
         NÃO CONVIRJA NO TEMPO ESTIPULADO. NESSE O VALOR INICIAL DAS ÉPOCAS DEVE
         SER O VALOR DA ÚLTIMA ÉPOCA UTILIZADA PELA REDE.
         ...*/
        System.out.println("Exemplo de carregamento de pesos para a rede"
                + "\nPesos camada de saída antes dos dados serem carregados");

        // imprime os valores das conexões atuais
        for (int i = 0; i < rede.getCamadaDeSaida().getNeuronio(0).getPesos().length; i++) {
            System.out.println(rede.getCamadaDeSaida().getNeuronio(0).getPeso(i));
        }
        System.out.println("Pesos camada intermediaria");
        for (int i = 0; i < rede.getCamadaIntermediaria().getNeuronios().length; i++) {
            System.out.println("---neuronio " + i + "---");
            double[] pesos = rede.getCamadaIntermediaria().getNeuronio(i).getPesos();
            for (int j = 0; j < pesos.length; j++) {
                System.out.println("" + pesos[j]);
            }
        }
        
        
        // instancia objeto gerenciador de conhecimento.
        GerenciadorDeConhecimento conhecimento = new GerenciadorDeConhecimento();
        
        /* 
            //Isto serviria para gravar os dados atuais... se não tem salvo,
            //descomente a chamada desse método passando os parâmetros 
            // corretamente
        conhecimento.guardarConhecimento(rede,
                "C:/Users/Christian Santos/Desktop/Backup/",
                "teste1");
        */
        
        // carrega os dados para a rede.
        conhecimento.carregarConhecimento(rede,
                "C:/Users/Christian Santos/Desktop/Backup/",
                "teste1");
       
        System.out.println("\nPesos da camada após serem carregados dos arquivos.. ");
        System.out.println("Pesos camada de saída");
        for (int i = 0; i < rede.getCamadaDeSaida().getNeuronio(0).getPesos().length; i++) {
            System.out.println(rede.getCamadaDeSaida().getNeuronio(0).getPeso(i));
        }
        System.out.println("Pesos camada intermediaria");
        for (int i = 0; i < rede.getCamadaIntermediaria().getNeuronios().length; i++) {
            System.out.println("---neuronio " + i + "---");
            double[] pesos = rede.getCamadaIntermediaria().getNeuronio(i).getPesos();
            for (int j = 0; j < pesos.length; j++) {
                System.out.println("" + pesos[j]);
            }
        }

    }

}
