/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhanguera.redeneuralmlp.test;

import com.anhanguera.neuralnetworks.MultilayerPerceptron;

/**
 *
 * @author Christian Santos
 */
public class TesteClassificacaoPortasLogicas {

    public static void main(String args[]) {

        double[][] CONJUNTO_TREINAMENTO = {
            {0, 1, 0, 1},
            {0, 0, 1, 1}
        };

        MultilayerPerceptron redeneural = new MultilayerPerceptron(3, 1);
        
        System.out.println("***Classificação portas lógicas***");
        
        /* porta and */
        System.out.println("\nPorta AND:");
        redeneural.inicializarConexoesSinapticas(2);
        redeneural.aprender(CONJUNTO_TREINAMENTO, new double[]{0, 0, 0, 1});
        redeneural.classificar(new double[]{0,0},0);
        redeneural.classificar(new double[]{0,1},0);
        redeneural.classificar(new double[]{1,0},0);
        redeneural.classificar(new double[]{1,1},1);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        
        /* porta nand */
        System.out.println("\nPorta NAND:");
        redeneural.inicializarConexoesSinapticas(2);
        redeneural.aprender(CONJUNTO_TREINAMENTO, new double[]{1, 1, 1, 0});
        redeneural.classificar(new double[]{0,0},1);
        redeneural.classificar(new double[]{0,1},1);
        redeneural.classificar(new double[]{1,0},1);
        redeneural.classificar(new double[]{1,1},0);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        
        System.out.println("\nPorta OR:");
        redeneural.inicializarConexoesSinapticas(2);
        redeneural.aprender(CONJUNTO_TREINAMENTO, new double[]{0, 1, 1, 1});
        redeneural.classificar(new double[]{0,0},0);
        redeneural.classificar(new double[]{0,1},1);
        redeneural.classificar(new double[]{1,0},1);
        redeneural.classificar(new double[]{1,1},1);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        
        /* porta xor*/
        System.out.println("\nPorta XOR:");
        redeneural.inicializarConexoesSinapticas(2);// (0,0)(0,1)(1,0)(1,1)
        redeneural.aprender(CONJUNTO_TREINAMENTO, new double[]{1, 0, 0, 1});
        redeneural.classificar(new double[]{0,0},1);
        redeneural.classificar(new double[]{0,1},0);
        redeneural.classificar(new double[]{1,0},0);
        redeneural.classificar(new double[]{1,1},1);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
    }

}
