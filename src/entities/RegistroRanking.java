package entities;

public class  RegistroRanking {
    private int pontuacao;
    private String iniciais;

    public RegistroRanking(int pontuacao, String iniciais) {
        this.pontuacao = pontuacao;
        this.iniciais = iniciais;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getIniciais() {
        return iniciais;
    }

    public void setIniciais(String iniciais) {
        this.iniciais = iniciais;
    }
}
