import Player.*;
import java.lang.Math;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        int start = 0;
        
        System.out.println("Digite quantos players de 3 a 6.");
        Scanner a = new Scanner(System.in);
        int b = a.nextInt();
        Player[] players = new Player[b];
        if (b < 3 || b > 6) {
            System.out.println("Número inválido. O número de players deve ser entre 3 e 6.");
            return;
        }
        if (b == 3){
        for (int i = 0; i < b; i++) {
            players[i] = new Player();
            System.out.println("Jogador " + (i + 1) + " criado.");
        }
            start = 1;
        }
        if (b == 4){
        for (int i = 0; i < b; i++) {
            players[i] = new Player();
            System.out.println("Jogador " + (i + 1) + " criado.");
        }
            start = 1;           
        }
        if (b == 5){
        for (int i = 0; i < b; i++) {
            players[i] = new Player();
            System.out.println("Jogador " + (i + 1) + " criado.");
        }
            start = 1;
        }
        if (b == 6){
        for (int i = 0; i < b; i++) {
            players[i] = new Player();
            System.out.println("Jogador " + (i + 1) + " criado.");
        }
            start = 1;
        }
         for (int i = 0; i < b; i++) {
            System.out.println("Escolha a cor do Player" + (i + 1));
            Scanner colorchoose = new Scanner(System.in);
            String color = colorchoose.next();
            players[i].pos = 0;
            players[i].cor = color;
            int randomNumber = (int) (Math.random() * 3);
            players[i].sorte = randomNumber;

        }

    }
}


