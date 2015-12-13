/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhanguera.redeneuralmlp;

import java.util.ArrayList;

/**
 *
 * @author Christian Santos
 */
public class ClassificacaoPortasLogicas {

    public static void main(String args[]) {

        double[][] CONJUNTO_TREINAMENTO = {
            {0, 1, 0, 1},
            {0, 0, 1, 1}
        };
        RedeNeuralMultilayerPerceptron redeneural = new RedeNeuralMultilayerPerceptron(3, 1);
        
       
        System.out.println("***Classificação portas lógicas***");
        /* porta and */
        System.out.println("Porta AND:");
        redeneural.inicializarConexoesSinapticasDaRede(2);
        redeneural.treinar(CONJUNTO_TREINAMENTO, new double[]{0, 0, 0, 1});
        redeneural.classificarDados(new double[]{0,0},0);
        redeneural.classificarDados(new double[]{0,1},0);
        redeneural.classificarDados(new double[]{1,0},0);
        redeneural.classificarDados(new double[]{1,1},1);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        
        /* porta nand */
        System.out.println("Porta NAND:");
        redeneural.inicializarConexoesSinapticasDaRede(2);
        redeneural.treinar(CONJUNTO_TREINAMENTO, new double[]{1, 1, 1, 0});
        redeneural.classificarDados(new double[]{0,0},1);
        redeneural.classificarDados(new double[]{0,1},1);
        redeneural.classificarDados(new double[]{1,0},1);
        redeneural.classificarDados(new double[]{1,1},0);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        
        System.out.println("Porta OR:");
        redeneural.inicializarConexoesSinapticasDaRede(2);
        redeneural.treinar(CONJUNTO_TREINAMENTO, new double[]{0, 1, 1, 1});
        redeneural.classificarDados(new double[]{0,0},0);
        redeneural.classificarDados(new double[]{0,1},1);
        redeneural.classificarDados(new double[]{1,0},1);
        redeneural.classificarDados(new double[]{1,1},1);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
        
        /* porta xor*/
        System.out.println("Porta XOR:");
        redeneural.inicializarConexoesSinapticasDaRede(2);// (0,0)(0,1)(1,0)(1,1)
        redeneural.treinar(CONJUNTO_TREINAMENTO, new double[]{1, 0, 0, 1});
        redeneural.classificarDados(new double[]{0,0},1);
        redeneural.classificarDados(new double[]{0,1},0);
        redeneural.classificarDados(new double[]{1,0},0);
        redeneural.classificarDados(new double[]{1,1},1);
        System.out.println("Épocas usadas para processamento: "+redeneural.getEpocas());
    }

}
