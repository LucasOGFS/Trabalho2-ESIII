package entities;

public class  RegistroRanking {
    private int puntuacao;
    private String iniciais;
    private String nomeJogo;

    public int getPuntuacao() {
        return puntuacao;
    }

    public void setPuntuacao(int puntuacao) {
        this.puntuacao = puntuacao;
    }

    public String getIniciais() {
        return iniciais;
    }

    public void setIniciais(String iniciais) {
        this.iniciais = iniciais;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }
}
