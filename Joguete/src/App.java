/** import Player.*;
import java.util.Random;
import java.util.Scanner;

public class App {


    public static String repeat(String str, int count) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < count; i++) {
        builder.append(str);
    }
    return builder.toString();
}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        

        int numPlayers = 10;
        String nhac;
        String zero = "0";
        String tres = "3";
        String quatro = "4";
        String cinco = "5";
        String seis = "6";
        Player[] players;
        boolean debug = false;
        while (true) {
            System.out.println("Digite quantos players irão jogar, de 3 a 6:");
            nhac = scanner.next();
            if (nhac.equals(zero) || nhac.equals(tres) || nhac.equals(quatro) || nhac.equals(cinco) || nhac.equals(seis)){
            numPlayers = Integer.valueOf(nhac);
            }
            if (numPlayers == 0){
                if (!debug){
                    System.out.println("Modo debug ativado");
                    debug = true;
                }else{
                    System.out.println("Modo debug desativado");
                    debug = false;
                }
            }else if (numPlayers >= 3 && numPlayers <= 6) {
                break;
            } else {
                System.out.println("Número inválido. O número de players deve ser entre 3 e 6.");
            }
        }
        int sortido = 0;
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Escolha a cor do Player " + (i + 1) + ":");
            String color = scanner.next();
            players[i] = new Player();
            players[i].cor = color;
            players[i].pos = 0;
            players[i].sorte = sortido;  //random.nextInt(3); // 0, 1 ou 2 para os tipos de sorte
            players[i].jogadas = 0;
            players[i].efeito1 = 0;
            if (sortido < 3){
                sortido++;
            }else{
                sortido = 0;
            }
        }

        String allColors = String.join(",", 
            java.util.Arrays.stream(players).map(player -> player.cor).toArray(String[]::new)
        );
        int maxLength = allColors.length();




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
        
        int[][] matriz = new int[11][11];
        int pos = 0;

        // Preenche as bordas do tabuleiro em sentido horário
        for (int j = 0; j < matriz[0].length; j++) { // Linha superior (da esquerda para a direita)
            matriz[0][j] = pos++;
        }
        for (int i = 1; i < matriz.length; i++) { // Coluna direita (de cima para baixo)
            matriz[i][10] = pos++;
        }
        for (int j = 9; j >= 0; j--) { // Linha inferior (da direita para a esquerda)
            matriz[10][j] = pos++;
        }
        for (int i = 9; i > 0; i--) { // Coluna esquerda (de baixo para cima)
            matriz[i][0] = pos++;
        }

        // Preenche as casas do meio com o valor 100
        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                matriz[i][j] = 100;
            }
        }   



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
            int dado1,dado2,somaDados;
            if(debug){
                System.out.println("digite o valor do primeiro dado");
                int choice = scanner.nextInt();
                dado1 = choice;
                System.out.println("digite o valor do segundo dado");
                choice = scanner.nextInt();
                dado2 = choice;
                somaDados = dado1 + dado2;
                
                if (dado1 == dado2){
                iguais = 1;
                players[jogadorDaVez].jogadas++;
                }else{
                iguais = 0;
                }

            }

            else{
            if(players[jogadorDaVez].sorte == 2){
                a = new RolarDadosBons();
            }else if (players[jogadorDaVez].sorte == 1){
                a = new RolarDadosRuins();
            }else{
                a = new RolarDados();
            }
            
            somaDados = a.rolarDados();
            dado1 = a.dado1;
            dado2 = a.dado2;
            
            if (dado1 == dado2){
                iguais = 1;
                players[jogadorDaVez].jogadas++;
            }else{
                iguais = 0;
            }
            }
            System.out.println("Você rolou: "+ dado1+ " e "+ dado2 + " totalizando: " + somaDados);


            



            int novaPosicao = players[jogadorDaVez].pos + somaDados;
            if (novaPosicao >= 40) {
                players[jogadorDaVez].pos = 40;
                matriz[0][0] = 40;
                //System.out.println("Parabéns, Jogador " + (jogadorDaVez + 1) + " (" + players[jogadorDaVez].cor + ") venceu! Com " + players[jogadorDaVez].jogadas + " Jogadas");
                
                System.out.println("O que você deseja escolher? (Pressione Enter)");
                System.out.println("1 - Jogar vendo o tabuleiro");
                System.out.println("2 - Continuar jogando");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                if(choice == 1) {
                System.out.println("\nEstado do tabuleiro:");
                for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz[i].length; j++) {
                        if (matriz[i][j] != 100) {
                            System.out.print("{");
                            StringBuilder colorsInCell = new StringBuilder();
                            for (int z = 0; z < numPlayers; z++) {
                                if (matriz[i][j] == players[z].pos) {
                                    if (colorsInCell.length() > 0) {
                                        colorsInCell.append(","); // Adiciona vírgula entre as cores
                                    }
                                    colorsInCell.append(players[z].cor);
                                }
                            }
                            System.out.print(colorsInCell);
                            // Preenche com espaços para alinhamento
                            System.out.print(repeat(" ", maxLength - colorsInCell.length()));
                            System.out.print("}");
                        } else {
                            System.out.print(repeat(" ", maxLength + 2)); // Espaço para células com valor 100
                        }
                    }
                    System.out.println(); // Quebra de linha
                }

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
                }else{
                    System.out.println("Por cair nessa casa,com azar, nada irá acontecer.");
                }
            }else if (novaPosicao == 13){
                System.out.println("Por cair na casa de número 13, sua sorte irá mudar");
                players[jogadorDaVez].sorte = random.nextInt(3);
            }else if (novaPosicao == 17 || novaPosicao == 27) {
                System.out.println("Por cair em uma casa especial.");
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
                }
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


        System.out.println("\nEstado do tabuleiro:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 100) {
                    System.out.print("{");
                    StringBuilder colorsInCell = new StringBuilder();
                    for (int z = 0; z < numPlayers; z++) {
                        if (matriz[i][j] == players[z].pos) {
                            if (colorsInCell.length() > 0) {
                                colorsInCell.append(","); // Adiciona vírgula entre as cores
                            }
                            colorsInCell.append(players[z].cor);
                        }
                    }
                    System.out.print(colorsInCell);
                    // Preenche com espaços para alinhamento
                    System.out.print(repeat(" ", maxLength - colorsInCell.length()));
                    System.out.print("}");
                } else {
                    System.out.print(repeat(" ", maxLength + 2)); // Espaço para células com valor 100
                }
            }
            System.out.println(); // Quebra de linha
        }
    




            System.out.println("Jogador " + (jogadorDaVez + 1) + " agora está na posição " + players[jogadorDaVez].pos);
     }
        scanner.close();
    }
} **/

import Player.*;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static String repeat(String str, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(str);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numPlayers = 0;
        Player[] players;
        boolean modoRapido = false;

        // Escolha de número de jogadores
        while (true) {
            System.out.println("Digite quantos players irão jogar (de 3 a 6):");
            numPlayers = scanner.nextInt();
            if (numPlayers >= 3 && numPlayers <= 6) {
                break;
            }
            System.out.println("Número inválido. O número de players deve ser entre 3 e 6.");
        }

        // Inicialização dos jogadores
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Escolha a cor do Player " + (i + 1) + ":");
            String color = scanner.next();
            players[i] = new Player();
            players[i].cor = color;
            players[i].pos = 0;
            players[i].jogadas = 0;
        }

        // Escolha do modo de jogo
        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Jogo Completo (com tabuleiro)");
        System.out.println("2 - Jogo Rápido (sem tabuleiro)");
        int choice = scanner.nextInt();
        if (choice == 2) {
            modoRapido = true;
        }

        // Inicialização do tabuleiro
        int[][] matriz = new int[11][11];
        int pos = 0;

        for (int j = 0; j < matriz[0].length; j++) { // Linha superior
            matriz[0][j] = pos++;
        }
        for (int i = 1; i < matriz.length; i++) { // Coluna direita
            matriz[i][10] = pos++;
        }
        for (int j = 9; j >= 0; j--) { // Linha inferior
            matriz[10][j] = pos++;
        }
        for (int i = 9; i > 0; i--) { // Coluna esquerda
            matriz[i][0] = pos++;
        }

        // Preenche as casas do meio com o valor 100
        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                matriz[i][j] = 100;
            }
        }

        // Se não for modo rápido, exiba o estado inicial do tabuleiro
        if (!modoRapido) {
            aguardarEnter(scanner); // Enter inicial sem mensagem
            System.out.println("\nEstado inicial do tabuleiro:");
            exibirTabuleiro(matriz, players, numPlayers);
            aguardarEnter(scanner); // Enter antes da primeira jogada
        }

        boolean jogoAtivo = true;

        while (jogoAtivo) {
            // Escolhe aleatoriamente quem vai jogar baseado na sorte
            int jogadorDaVez = random.nextInt(numPlayers);
            Player jogadorAtual = players[jogadorDaVez];

            System.out.println("\nVez do Jogador " + (jogadorDaVez + 1) + " (" + jogadorAtual.cor + ").");
            aguardarEnter(scanner); // Enter antes de rolar os dados

            // Rolar os dados
            RolarDados rolarDados = new RolarDados();
            int dado1 = rolarDados.dado1;
            int dado2 = rolarDados.dado2;
            int somaDados = rolarDados.rolarDados();

            System.out.println("Você rolou: " + dado1 + " e " + dado2 + " (Total: " + somaDados + ")");

            // Atualizar posição do jogador
            jogadorAtual.pos += somaDados;

            // Verificação das casas específicas
            if (jogadorAtual.pos == 10 || jogadorAtual.pos == 25 || jogadorAtual.pos == 38) {
                System.out.println("Você caiu numa casa que te impede de jogar a próxima rodada!");
                continue; // Passa para a próxima rodada sem o jogador jogar
            }

            if (jogadorAtual.pos == 13) {
                System.out.println("Você caiu numa casa surpresa! Tirando carta...");
                // Aqui você pode sortear uma carta e aplicar um efeito, mas o jogador não sabe o que vai acontecer.
            }

            // Sorte e azar são decididos aleatoriamente
            if (jogadorAtual.pos == 5 || jogadorAtual.pos == 15 || jogadorAtual.pos == 30) {
                // Sorte: 50% de chance de andar mais 3 casas
                if (random.nextBoolean()) {
                    System.out.println("Você caiu numa casa da sorte! Andando 3 casas para frente.");
                    jogadorAtual.pos += 3;
                } else {
                    System.out.println("Você caiu numa casa da sorte, mas não teve sorte suficiente.");
                }
            }

            if (jogadorAtual.pos == 17 || jogadorAtual.pos == 27) {
                System.out.println("Você caiu numa casa que permite escolher um jogador para voltar ao início.");
                // Implementar a lógica para escolher outro jogador e levá-lo ao início
            }

            if (jogadorAtual.pos == 20 || jogadorAtual.pos == 35) {
                System.out.println("Você caiu numa casa mágica! Troque de lugar com o jogador mais atrás.");
                // Implementar a lógica para trocar de lugar com o jogador mais atrás
            }

            // Condição de vitória
            if (jogadorAtual.pos >= 40) {
                System.out.println("Parabéns, Jogador " + (jogadorDaVez + 1) + " (" + jogadorAtual.cor + ") venceu o jogo!");
                jogoAtivo = false;
                break;
            }

            // Exibir o estado do tabuleiro atualizado, se não for modo rápido
            if (!modoRapido) {
                System.out.println("\nEstado do tabuleiro:");
                exibirTabuleiro(matriz, players, numPlayers);
            }
        }

        scanner.close();
    }

    public static void exibirTabuleiro(int[][] matriz, Player[] players, int numPlayers) {
        int maxLength = 10;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 100) {
                    System.out.print("{");
                    StringBuilder colorsInCell = new StringBuilder();
                    for (int z = 0; z < numPlayers; z++) {
                        if (matriz[i][j] == players[z].pos) {
                            if (colorsInCell.length() > 0) {
                                colorsInCell.append(",");
                            }
                            colorsInCell.append(players[z].cor);
                        }
                    }
                    System.out.print(colorsInCell);
                    System.out.print(repeat(" ", maxLength - colorsInCell.length()));
                    System.out.print("}");
                } else {
                    System.out.print(repeat(" ", maxLength + 2)); // Espaço para células com valor 100
                }
            }
            System.out.println();
        }
    }

    public static void aguardarEnter(Scanner scanner) {
        scanner.nextLine(); // Aguarda Enter
    }
}
