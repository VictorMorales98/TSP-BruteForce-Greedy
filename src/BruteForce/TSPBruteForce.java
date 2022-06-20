/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BruteForce;

/**
 *
 * @author Victor Morales
 */

import java.util.List;

public class TSPBruteForce {
    private final List<String> lista_nombre_nodos;
    private final double[] v_distancias;
    private float minimo = Float.MAX_VALUE;
    float sumatorio = 0;
    private String ruta_corta = "";

    //Contructor1
    public TSPBruteForce(List<String> lista_nombre_nodos, double[] v_distancias) {
        this.lista_nombre_nodos = lista_nombre_nodos;
        this.v_distancias = v_distancias;
    }

    public String getRuta() {
        int nNodos = this.lista_nombre_nodos.size();
        int[] vDist = new int[nNodos];
        for (int i = 0; i < nNodos; i++) {
            vDist[i] = i;
        }
        int[] ruta = null;        
        if (nNodos == Math.sqrt(v_distancias.length)) {
            double[][] tabla_distancias = conversorVT(v_distancias);
            do {
                sumatorio = 0;
                sumatorio += tabla_distancias[vDist[nNodos - 1]][vDist[0]];

                for (int i = 0; i < (nNodos - 1); i++) {
                    sumatorio += tabla_distancias[vDist[i]][vDist[i + 1]];
                    if (sumatorio > minimo) { 
                        break;
                    }
                }

                if (sumatorio < minimo) {
                    minimo = (float) sumatorio;
                    ruta = vDist.clone();
                }
            } while (nextPermutation(vDist) && vDist[0] == 0);
            for (int i = 0; i < nNodos; i++) {
                ruta_corta += lista_nombre_nodos.get(ruta[i]) + " - ";
                if(i == (nNodos - 1))
                    ruta_corta += lista_nombre_nodos.get(ruta[0]);
            }
            return ruta_corta;
        } else {
            return null;
        }
    }

    public double getDistanciaRecorrida() {
        return minimo;
    }
    
    public double[][] conversorVT(double[] vDistancias) {
        int nNodos = (int) Math.sqrt(vDistancias.length);
        double[][] tDistancias = new double[nNodos][nNodos];
        int cont = 0;
        for (int i = 0; i < nNodos; i++) {
            for (int j = 0; j < nNodos; j++) {
                tDistancias[j][i] = vDistancias[cont];
                cont++;
            }
        }
        return tDistancias;
    }

    private static boolean nextPermutation(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }
        int aux = array[i - 1];
        array[i - 1] = array[j];
        array[j] = aux;
        j = array.length - 1;
        while (i < j) {
            aux = array[i];
            array[i] = array[j];
            array[j] = aux;
            i++;
            j--;
        }
        return true;
    }
}
