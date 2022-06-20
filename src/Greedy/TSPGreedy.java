/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

/**
 *
 * @author Victor Morales
 */
public class TSPGreedy {
    private int nodos; 
    private double d;
    private String[] nombre_nodos;
    private double[][] distancia;
   
    private int[] colable;
    private int[] row;

    public TSPGreedy(int nodos, String[] nombre_nodos) {
        this.nodos = nodos;
        this.nombre_nodos = nombre_nodos;
    }


   
    public void getDistancias(double[] distancias_i){  
               
        distancia = new double[nodos][nodos];   
       
        for (int i = 0; i < nodos - 1; i++) {  
            for (int j = i + 1; j < nodos; j++) {  
                double rij = distancias_i[j]; 
                int tij = (int) Math.round(rij);  
                if (tij < rij) {  
                    distancia[i][j] = rij + 1;  
                    distancia[j][i] = distancia[i][j];  
                } else {  
                    distancia[i][j] = rij;  
                    distancia[j][i] = distancia[i][j];  
                }  
            }  
        }  
   
        distancia[nodos - 1][nodos - 1] = 0;  
   
        colable = new int[nodos];  
        colable[0] = 0;  
        for (int i = 1; i < nodos; i++) {  
            colable[i] = 1;  
        }  
   
        row = new int[nodos];  
        for (int i = 0; i < nodos; i++) {  
            row[i] = 1;  
        }  
   
    }  
       
    public void getRuta(){  
           
        double[] temp = new double[nodos];  
        String path= nombre_nodos[0];  
           
        int s=0;
        int i=0; 
        int j=0; 
        
        while(row[i]==1){  
            
            for (int k = 0; k < nodos; k++) {  
                temp[k] = distancia[i][k];  
                
            }  
            
            j = (int)getMin(temp);  
             
            row[i] = 0;
            colable[j] = 0;
               
            path+=" - " + nombre_nodos[j];  
            
            s += distancia[i][j];  
            d += distancia[i][j];  
            i = j;
            
        }  
        System.out.println("\nRuta\n" + path);  
        System.out.println("\nDistancia Recorrida:\n" + s);
        
        double g = d*100000;
        int res = (int)g - (s * 100000);
        
        System.out.println("\nGasolina\n" + res);
        
           
    }  
    
       
    public double getMin(double[] p){  
        int j = 0;
        double m = p[0];
        double k = 0;  
        
        while (colable[j] == 0) {  
            j++;  
            
            if(j>=nodos){  
                
                m = p[0];  
                break;  
               
            }  
            else{  
                m = p[j];  
            }  
        }  
        
        for (; j < nodos; j++) {  
            if (colable[j] == 1) {  
                if (m >= p[j]) {  
                    m = p[j];  
                    k = j;  
                }  
            }  
        }  
        return k;  
    }  

}
