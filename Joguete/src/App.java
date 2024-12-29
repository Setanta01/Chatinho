import Player.*;
import java.util.Random;
import java.util.Scanner;

public class App {
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
            players[i].sorte = random.nextInt(3); // 0, 1 ou 2 para os tipos de sorte
        }

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("\n Jogador " + (i + 1) + " criado com cor " + players[i].cor);
        }

        System.out.println("\nPressione Enter para iniciar o jogo:");
        scanner.nextLine(); 
        scanner.nextLine();

        boolean jogoAtivo = true;
// matriz main
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

        // loop principal
        while (jogoAtivo) {
            int jogadorDaVez = random.nextInt(numPlayers);
            System.out.println("Vez da Rodada: Player" + (jogadorDaVez + 1) + " (" + players[jogadorDaVez].cor + ")!");
            System.out.println("Pressione Enter para jogar os dados...");
            scanner.nextLine();

            int somaDados = random.nextInt(6) + 1 + random.nextInt(6) + 1;
            System.out.println("Você rolou " + somaDados);
            
            int novaPosicao = players[jogadorDaVez].pos + somaDados;
            if (novaPosicao >= 40) {
                System.out.println("Parabéns, Player" + (jogadorDaVez + 1) + " (" + players[jogadorDaVez].cor + ") venceu!");
                jogoAtivo = false;
                break;
            }
            players[jogadorDaVez].pos = novaPosicao;
              
            //atualiza e mostra a matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = (i == 0 || i == matriz.length - 1 || j == 0 || j == matriz[i].length - 1) ? 1 : 0;
                }
            }
            // add jogadores na matriz
            for (int i = 0; i < numPlayers; i++) {
                int x = (players[i].pos % 10) + 1;
                int y = (players[i].pos / 10) + 1;
                if (x < matriz.length && y < matriz[0].length) {
                    matriz[y][x] = i + 2; //add um valor fixo p cada jogador
                }
            }
            System.out.println("\nEstado do tabuleiro:");
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    if (matriz[i][j] == 1) {
                        System.out.print("{}       "); // casa de borda
                    } else if (matriz[i][j] > 1) {
                        int playerId = matriz[i][j] - 2;
                        System.out.print("{" + players[playerId].cor + "}  "); // casa ocupada por um jogador
                    } else {
                        System.out.print("         "); // casa vazia
                    }
                }
                System.out.println();
            }
            System.out.println("Player" + (jogadorDaVez + 1) + " agora está na posição " + players[jogadorDaVez].pos);
     }
        scanner.close();
    }
}