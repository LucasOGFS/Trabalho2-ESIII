package entities.jogos;

import entities.Jogo;

import java.util.Random;

public class PedraPapelTesoura extends Jogo {

    private static Random random = new Random();

    public enum Jogada {
        PEDRA,
        PAPEL,
        TESOURA
    }

    public enum Resultado {
        VITORIA,
        DERROTA,
        EMPATE,
    }

    public Resultado jogarPPT(Jogada jogador) {

        Jogada maquina = gerarJogadaMaquina();

        if (jogador == maquina) {
            return Resultado.EMPATE;
        }

        switch (jogador) {
            case PEDRA:
                return (maquina == Jogada.TESOURA) ? Resultado.VITORIA : Resultado.DERROTA;
            case PAPEL:
                return (maquina == Jogada.PEDRA) ? Resultado.VITORIA : Resultado.DERROTA;
            case TESOURA:
                return (maquina == Jogada.PAPEL) ? Resultado.VITORIA : Resultado.DERROTA;
            default: return Resultado.EMPATE;
        }

    }

    public static Jogada gerarJogadaMaquina() {
        return Jogada.values()[random.nextInt(3)];
    }

}
