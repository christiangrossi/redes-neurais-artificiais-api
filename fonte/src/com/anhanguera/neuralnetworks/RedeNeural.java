package com.anhanguera.neuralnetworks;

import java.util.List;

public interface RedeNeural {
	
	public void inicializarConexoesSinapticas(int numeroDeEntradas);
	
	public void aprender(double[][] conjuntoTreinamento, double[] valoresEsperados);
	
	public void aprender(List<double[]> conjuntoTreinamento, double[] valoresEsperados);
	
	public double classificar(double[] entrada);
	
	public double classificar(double[] entrada, double esperado);
}
