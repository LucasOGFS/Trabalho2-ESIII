package entities;

public abstract class Jogo {
    private String nome;
    private int pontuacao;
    private int vidas;
    private Ranking ranking;

    public Jogo(String nome, int pontuacao, int vidas, Ranking ranking) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.vidas = vidas;
        this.ranking = ranking;
    }

    public String getNome() {
        return nome;
    }

    public void pontuarVitoria(){
        pontuacao = pontuacao + 100;
    }

    public void pontuarEmpate(){
        pontuacao = pontuacao + 50;
    }

    public int getPontuacao(){
        return pontuacao;
    }

    public void pontuarDerrota(){
        vidas--;
    }

}
