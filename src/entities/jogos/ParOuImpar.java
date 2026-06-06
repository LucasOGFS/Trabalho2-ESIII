package entities.jogos;

import entities.Jogo;
import entities.Ranking;

import java.util.Random;

public class ParOuImpar extends Jogo {

    public enum Escolha {
        PAR,
        IMPAR
    }
    public enum Resultado {
        VITORIA,
        DERROTA
    }
    private final Random random = new Random();

    // Construtor ajustado para a superclasse
    public ParOuImpar(Ranking ranking) {
        super("Par ou Impar", 0, 3, ranking);
    }


    private Resultado CalcularValor(Escolha escolhaJogador, int numeroJogador, int maquina) {
        // Valida se o número é PAR uma única vez. Se for par, ehPar == true
        boolean ehPar = (numeroJogador + maquina) % 2 == 0;
        switch (escolhaJogador) {
            case PAR:
                // Funciona como um IF-ELSE resumido
                return ehPar ? Resultado.VITORIA : Resultado.DERROTA;
            case IMPAR:
                return !ehPar ? Resultado.VITORIA : Resultado.DERROTA;
            default:
                throw new IllegalStateException("Escolha inesperada: " + escolhaJogador);
        }
    }
    // O método novo que atende ao Use Case funciona como um "Tradutor" (Adapter)

    @Override
    public String realizarJogada(String entradaJogador) {
        if (isGameOver()) {
            return "O jogo já acabou! Você não tem mais vidas.";
        }
        // Lê a string (ex: "PAR 5")
        String[] partes = entradaJogador.toUpperCase().split(" ");
        if (partes.length != 2) {
            return "Entrada inválida! Digite 'PAR <numero>' ou 'IMPAR <numero>'.";
        }
        Escolha escolhaJogador;
        try {
            // Usa a inteligência do próprio Enum original para traduzir a palavra
            escolhaJogador = Escolha.valueOf(partes[0]);
        } catch (IllegalArgumentException e) {
            return "Escolha inválida! Digite PAR ou IMPAR.";
        }
        int numeroJogador;
        try {
            numeroJogador = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            return "Número inválido! Digite um número.";
        }
        int maquina = random.nextInt(200);
        Resultado resultado = CalcularValor(escolhaJogador, numeroJogador, maquina);
        if (resultado == Resultado.VITORIA) {
            this.pontuarVitoria();
            return String.format("Você: %d | Máquina: %d. Total: %d. Resultado: %s",
                    numeroJogador, maquina, (numeroJogador + maquina), "VITÓRIA!");
        } else {
            this.pontuarDerrota();
            return String.format("Você: %d | Máquina: %d. Total: %d. Resultado: %s",
                    numeroJogador, maquina, (numeroJogador + maquina), "DERROTA!");
        }
    }
}
