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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {
    
    
    
    String[] nombre_nodos;

    Double[] distancias_i;

    List<String> lista_nombre_nodos = new ArrayList<>();

    String[] v_nodos = new String[lista_nombre_nodos.size()];
    
    public static void main(String[] args) {
        Principal p = new Principal();
        p.ejecuta();
    }

    public void ejecuta() {
        
        nombre_nodos = getCiudades("ciudades.txt");
        distancias_i = getDist("distancias.txt");

        Pasar_Arrays_a_Listas();
        Pasar_Listas_a_Arrays();

        int n2 = (int) Math.pow(Math.sqrt(distancias_i.length), 2);
        double[] v_distancias = new double[n2];
        v_distancias = getDistancia();

        TSPBruteForce tspfb = new TSPBruteForce(lista_nombre_nodos, v_distancias);
              
        tiempo();
        Mostrar_Listas();
        Mostrar_Tabla(v_distancias, "\nTabla distancias:");
        System.out.println("\nCalculando ruta...");
        System.out.println("\nRuta");
        System.out.println(tspfb.getRuta());
        System.out.println("\nDistancia recorrida:");
        System.out.println((int) tspfb.getDistanciaRecorrida());
        
        int dist = (int) tspfb.getDistanciaRecorrida()* 100000;
        double gasolina = tspfb.getDistanciaRecorrida() * 100000;
        
        int tot = (int) (gasolina - dist);
        
        System.out.println("\nCosto Gasolina:");
        System.out.println(tot);
        
        tiempo();
    }

    
    private  void tiempo(){
        Calendar calendario = new GregorianCalendar();
        int hora, minutos, segundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        System.out.println(hora + ":" + minutos + ":" + segundos);
    }

    private void Pasar_Arrays_a_Listas() {
        lista_nombre_nodos.addAll(Arrays.asList(nombre_nodos));
    }

    private void Pasar_Listas_a_Arrays() {
        v_nodos = lista_nombre_nodos.toArray(v_nodos);
    }

    private void Mostrar_Listas() {
        System.out.println("\nNodos:\n" + lista_nombre_nodos);
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

    public double[] getDistancia() {
        int cont = 0;
        double[] v1 = new double[distancias_i.length];
        for (int i = 0; i < Math.sqrt(distancias_i.length); i++) {
            for (int j = 0; j < Math.sqrt(distancias_i.length); j++) {
                v1[cont] = distancias_i[cont];
                cont++;
            }
        }
        return v1;
    }
     
    
    public String[] getCiudades(String Archivo){
        FileReader fr;
        
        try {
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
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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
}

