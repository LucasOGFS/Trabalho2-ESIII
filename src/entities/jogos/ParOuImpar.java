package entities.jogos;

import entities.Jogo;

import java.util.Random;

public class ParOuImpar extends Jogo {

    public ParOuImpar(String nome, int pontuacaoMinimaRanking) {
        super("Par ou Impar", 0);
    }

    public enum Escolha {
        PAR,
        IMPAR
    }

    public enum Resultado {
        VITORIA,
        DERROTA
    }

    private final Random random = new Random();

    //Retorna um Resultado. Precisamos armazenar esse retorno em StringBuilder para transformar em uma String
    public Resultado jogarParOuImpar(Escolha escolhaJogador, int numeroJogador) {

        int maquina = random.nextInt(200);

        // Valida se o número é PAR uma única vez. Se for par, ehPar == true
        boolean ehPar = (numeroJogador + maquina) % 2 == 0;

        switch (escolhaJogador) {
            case PAR:
                //Fuciona como um IF-ELSE resumido. Pergunta == ?. Potanto: [ehPar] ? [condicao se verdaeiro] : [condicao se falso]
                return ehPar ? Resultado.VITORIA : Resultado.DERROTA;

            case IMPAR:
                return !ehPar ? Resultado.VITORIA : Resultado.DERROTA;

            default:
                throw new IllegalStateException("Escolha inesperada: " + escolhaJogador);
        }
    }
}
