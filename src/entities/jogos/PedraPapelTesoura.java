package entities.jogos;

import entities.Jogo;
import entities.Ranking;

import java.util.Random;

public class PedraPapelTesoura extends Jogo {

    private static final Random random = new Random();

    public enum Jogada {
        PEDRA,
        PAPEL,
        TESOURA
    }
    public enum Resultado {
        VITORIA,
        DERROTA,
        EMPATE
    }
    public PedraPapelTesoura(Ranking ranking) {
        super("Pedra, Papel e Tesoura", 0, 3, ranking);
    }
    private Resultado jogarPPT(Jogada jogador) {
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
            default:
                return Resultado.EMPATE;
        }
    }
    private static Jogada gerarJogadaMaquina() {
        return Jogada.values()[random.nextInt(3)];
    }

    @Override
    public String realizarJogada(String entradaJogador) {
        if (isGameOver()) {
            return "O jogo já acabou! Você não tem mais vidas.";
        }
        Jogada jogadaJogador;
        try {
            jogadaJogador = Jogada.valueOf(entradaJogador.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return "Jogada inválida! Digite PEDRA, PAPEL ou TESOURA.";
        }
        Resultado resultado = jogarPPT(jogadaJogador);
        switch (resultado) {
            case VITORIA:
                this.pontuarVitoria();
                return "Resultado: VITÓRIA!";
            case DERROTA:
                this.pontuarDerrota();
                return "Resultado: DERROTA!";
            case EMPATE:
                this.pontuarEmpate();
                return "Resultado: EMPATE!";
            default:
                return "Erro inesperado.";
        }
    }
}