package entities;

public class RegistroRanking {
    private String jogador;
    private int pontuacao;
    private String nomeJogo;

    public RegistroRanking(String jogador, int pontuacao, Jogo jogo) {
        this.jogador = jogador;
        this.pontuacao = pontuacao;
        this.nomeJogo = nomeJogo;
    }

    public String getJogador() {
        return jogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }
}
