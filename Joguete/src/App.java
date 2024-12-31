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
            players[i].jogadas = 0;
            players[i].efeito1 = 0;
        }

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("\n Jogador " + (i + 1) + " criado com cor " + players[i].cor);
        }
        int jogadorDaVez = random.nextInt(numPlayers);

        System.out.println("\nPressione Enter para iniciar o jogo:");
        scanner.nextLine(); 
        scanner.nextLine();

        boolean jogoAtivo = true;
        int iguais = 0;
        int ok = 0;
        
// matriz main
      //  int[][] matriz = new int[11][11];
     //   for (int i = 0; i < matriz.length; i++) {
    //        for (int j = 0; j < matriz[i].length; j++) {
   //             if (i == 0 || i == matriz.length - 1 || j == 0 || j == matriz[i].length - 1) {
   //                 matriz[i][j] = 1;
  //              } else {
  //                  matriz[i][j] = 0;
  //              }
  //          }
  //      } 

        // loop principal
        while (jogoAtivo) {
            
            if((jogadorDaVez != (numPlayers - 1)) && (iguais == 0)){
                jogadorDaVez++;
                players[jogadorDaVez].jogadas++;
            }else if (iguais == 0){
                jogadorDaVez = 0;
                players[jogadorDaVez].jogadas++;
            }

            if ((players[jogadorDaVez].efeito1 == 1) && (iguais != 0)){
                players[jogadorDaVez].jogadas--;
                System.out.println("Jogador " + players[jogadorDaVez].cor + " perdeu a vez por conta da casa em que caiu");
                    if((jogadorDaVez != (numPlayers - 1)) && (iguais == 0)){
                        jogadorDaVez++;
                        players[jogadorDaVez].jogadas++;
                    }else if (iguais == 0){
                        jogadorDaVez = 0;
                        players[jogadorDaVez].jogadas++;
            }
            }

            System.out.println("Vez da Rodada: Jogador " + (jogadorDaVez + 1) + " (" + players[jogadorDaVez].cor + ")!");
            System.out.println("Pressione Enter para jogar os dados...");
            scanner.nextLine();


            RolarDados a = new RolarDados();

            if(players[jogadorDaVez].sorte == 2){
                a = new RolarDadosBons();
            }else if (players[jogadorDaVez].sorte == 1){
                a = new RolarDadosRuins();
            }else{
                a = new RolarDados();
            }
            
            int somaDados = a.rolarDados();
            int dado1 = a.dado1;
            int dado2 = a.dado2;
            
            if (dado1 == dado2){
                iguais = 1;
                players[jogadorDaVez].jogadas++;
            }else{
                iguais = 0;
            }

            System.out.println("Você rolou: "+ dado1+ " e "+ dado2 + " totalizando: " + somaDados);

            int novaPosicao = players[jogadorDaVez].pos + somaDados;
            if (novaPosicao >= 40) {
            players[jogadorDaVez].pos = 40;    
            System.out.println("Parabéns, Jogador " + (jogadorDaVez + 1) + " (" + players[jogadorDaVez].cor + ") venceu! Com " + players[jogadorDaVez].jogadas + " Jogadas");
            for (int x = 0; x <= (numPlayers-1); x++){
                System.out.println("Jogador " + (x+1) + ", Cor: " + players[x].cor + ", Fez: " + players[x].jogadas + " Jogadas, Posição: " + players[x].pos);
            }
            
                jogoAtivo = false;
                break;
            }else if (novaPosicao == 10 || novaPosicao == 25 || novaPosicao == 38){
                players[jogadorDaVez].efeito1 = 1;
            }else if (novaPosicao == 15 || novaPosicao == 5 || novaPosicao == 30){
                if (players[jogadorDaVez].sorte != 1){
                System.out.println("Por cair,sem azar, nessa casa avance 3 posições.");
                novaPosicao = novaPosicao + 3;
                }
            }else if (novaPosicao == 13){
                System.out.println("Por cair na casa de número 13, sua sorte irá mudar");
                players[jogadorDaVez].sorte = random.nextInt(3);
            }else if (novaPosicao == 17 || novaPosicao == 27) {
                System.out.println("Escolha um jogador (sua cor) para voltar ao início do jogo");
                
                boolean corValida = false;

                while (!corValida) { 
                    String escolha = scanner.next();
                    for (int i = 0; i < numPlayers; i++) {
                        if (players[i].cor.equalsIgnoreCase(escolha)) { 
                             players[i].pos = 0; 
                             corValida = true;
                        break; 
                        }       
                    }

                    if (!corValida) {
                        System.out.println("Cor inválida! Por favor, escolha uma cor válida:");
                    }
                }
                
                continue; 
            }else if (novaPosicao == 20 || novaPosicao == 35) {
                 int ini = 0;
                 int wow; 
                 for (int i = 1; i < numPlayers; i++) { 
                    if (players[i].pos < players[ini].pos) {
                        ini = i; 
                    }
                }
                ok = ini; 
                wow = players[jogadorDaVez].pos;
                players[jogadorDaVez].pos = players[ok].pos;
                players[ok].pos = wow;
            }





            players[jogadorDaVez].pos = novaPosicao;


              


            //atualiza e mostra a matriz
     //       for (int i = 0; i < matriz.length; i++) {
    //            for (int j = 0; j < matriz[i].length; j++) {
    //                matriz[i][j] = (i == 0 || i == matriz.length - 1 || j == 0 || j == matriz[i].length - 1) ? 1 : 0;
    //            }
    //        }
            // add jogadores na matriz
     //       for (int i = 0; i < numPlayers; i++) {
    //            int x = (players[i].pos % 10) + 1;
    //            int y = (players[i].pos / 10) + 1;
    //            if (x < matriz.length && y < matriz[0].length) {
   //                 matriz[y][x] = i + 2; //add um valor fixo p cada jogador
   //             }
          //  }
  ////          System.out.println("\nEstado do tabuleiro:");
    //        for (int i = 0; i < matriz.length; i++) {
  //              for (int j = 0; j < matriz[i].length; j++) {
    //                if (matriz[i][j] == 1) {
    //                    System.out.print("{}       "); // casa de borda
   //                 } else if (matriz[i][j] > 1) {
   //                     int playerId = matriz[i][j] - 2;
  ///                      System.out.print("{" + players[playerId].cor + "}  "); // casa ocupada por um jogador
   //                 } else {
   //                     System.out.print("         "); // casa vazia
    //                }
     //           }
      //          System.out.println();
     //       }
            System.out.println("Jogador " + (jogadorDaVez + 1) + " agora está na posição " + players[jogadorDaVez].pos);
     }
        scanner.close();
    }
}