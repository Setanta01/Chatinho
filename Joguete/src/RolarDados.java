import java.util.Random;

public class RolarDados {
    public Random random = new Random();
        public int dado1 = random.nextInt(6) + 1; // Gera número entre 1 e 6
        public int dado2 = random.nextInt(6) + 1;
    
    public int rolarDados() {
        
        return dado1 + dado2;
    }
}

class RolarDadosBons extends RolarDados {
    @Override
    public int rolarDados() {
        do {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
        } while ((dado1 + dado2) <= 6);
        return dado1 + dado2;
    }
}

class RolarDadosRuins extends RolarDados {
    @Override
    public int rolarDados() {
        do {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
        } while ((dado1 + dado2) > 6);
        return dado1 + dado2;
    }
}
