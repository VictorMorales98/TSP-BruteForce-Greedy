/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Morales
 */
public class Principal {
    
    private static String[] nombre_nodos;
    private static Double[] distancias;
    
    public static void main(String[] args) throws IOException {  
        Principal p = new Principal();
        p.ejecuta();
    }
    
    public void ejecuta(){
        nombre_nodos = getCiudades("ciudades.txt");
        distancias = getDist("distancias.txt");
        
        int n2 = (int) Math.pow(Math.sqrt(distancias.length), 2);
        double[] v_distancias = new double[n2];
        v_distancias = getDistancia();
        
        TSPGreedy ts = new TSPGreedy(nombre_nodos.length, nombre_nodos);
        
        Mostrar_Nodos(nombre_nodos);
        System.out.println("");
        Mostrar_Tabla(v_distancias, "\nTabla distancias:");
        System.out.println("\nCalculando Ruta...");
        ts.getDistancias(v_distancias);
        ts.getRuta();
    }
    
    public void Mostrar_Nodos(String[] nodos){
        System.out.println("\nNodos:\n");
        for(int i = 0; i < nodos.length; i++){
            if(i == 0)
                System.out.print("[ " + nodos[i]);
            else
                if(i == (nodos.length - 1))
                    System.out.print(" - " + nodos[i] + "]");
                else
                    System.out.print(" - " + nodos[i]);
        }
    }
    
    public void Mostrar_Tabla(double[] v1, String msg) {
        System.out.println(msg);
        int n = (int) Math.sqrt(v1.length);
        double t1[][] = new double[n][n];
        int cont = 0;
        String aux = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t1[j][i] = v1[cont];
                aux += ((int) (t1[j][i])) + "\t";
                cont++;
            }
            System.out.println(aux);
            aux = "";
        }
    }
    
    public String[] getCiudades(String Archivo){
        try {
            FileReader fr;
            
            fr = new FileReader(Archivo);
            BufferedReader b = new BufferedReader(fr);
            
            String Temp = "";
            
            while(Temp != null){
                Temp = b.readLine();
                String x;
                x = Temp;
                String[] aux = x.split(", ");
                if(Temp == null){
                    break;
                }
                return aux;
            }
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TSPGreedy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(TSPGreedy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Double[] getDist(String Archivo){
       File f = new File(Archivo);
       Double[] dis = new Double[(int) Math.pow(nombre_nodos.length, 2)];
       int cont = 0;
       try(Scanner s = new Scanner(f)){
           while(s.hasNextDouble()){
               double d = s.nextDouble();
               dis[cont] = d;
               cont++;
           }
           return dis;
       }catch(Exception e){
           System.err.println(e.toString());
           return null;
       }
    }
   
    public double[] getDistancia() {
        int cont = 0;
        double[] v1 = new double[distancias.length];
        for (int i = 0; i < Math.sqrt(distancias.length); i++) {
            for (int j = 0; j < Math.sqrt(distancias.length); j++) {
                v1[cont] = distancias[cont];
                cont++;
            }
        }
        return v1;
    }
    
}
