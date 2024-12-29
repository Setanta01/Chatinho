import Player.*;
import java.util.Random;
import java.util.Scanner;

public class tempCodeRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numPlayers;
        Player[] players;

        while (true) {
            System.out.println("Digite quantos players irão jogar, de 3 a 6:");
            numPlayers = scanner.nextInt();
            if (numPlayers >= 3 && numPlayers <= 6) {
                break;
            } else {
                System.out.println("Número inválido. O número de players deve ser entre 3 e 6.");
            }
        }

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Escolha a cor do Player " + (i + 1) + ":");
            String color = scanner.next();
            players[i] = new Player();
            players[i].cor = color;
            players[i].pos = 0;
            players[i].sorte = random.nextInt(3); // 0 1 ou 2 para os tipos de sorte
            System.out.println("Jogador " + (i + 1) + " criado com cor " + color);
        }

        System.out.println("\nPressione Enter para iniciar o jogo:");
        scanner.nextLine(); 
        scanner.nextLine();

        boolean jogoAtivo = true;

        // loop principal
        while (jogoAtivo) {
            int jogadorDaVez = random.nextInt(numPlayers);
            System.out.println("\nVez da Rodada: Player" + (jogadorDaVez + 1) + " (" + players[jogadorDaVez].cor + ")!");
            System.out.println("Pressione Enter para jogar os dados...");
            scanner.nextLine();
        }
    }
}