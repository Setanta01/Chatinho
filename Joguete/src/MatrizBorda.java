import Player.*;
import java.lang.Math;

public class MatrizBorda {
    public static void main(String[] args) {
        
        int[][] matriz = new int[11][11];

        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                
                if (i == 0 || i == matriz.length - 1 || j == 0 || j == matriz[i].length - 1) {
                    matriz[i][j] = 1;
                } else {
                   
                    matriz[i][j] = 0;
                }
            }
        }

       
       // for (int i = 0; i < matriz.length; i++) {
      //      for (int j = 0; j < matriz[i].length; j++) {
      //          System.out.print(matriz[i][j] + " ");
      //      }
      //      System.out.println();
    //    }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                 if (i == 0 && j == 0) {
                 System.out.print("{azul}   "); 
                }
                else if (matriz[i][j] == 1) {
                    System.out.print("{}       ");
                } else {
                    System.out.print("         "); 
                }
            }
            
            System.out.println();
        }

    }
}
