package entities;

public abstract class Jogo {
//atributos
    private String nome;
    private int pontuacao;
    private int vidas;
    private Ranking ranking;

//construtor
    public Jogo(String nome, int pontuacao, int vidas, Ranking ranking) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.vidas = vidas;
        this.ranking = ranking;
    }

//métodos
    public String getNome() {
        return nome;
    }

    public void pontuarVitoria(){
        pontuacao = pontuacao + 100;
    }

    public void pontuarEmpate() {
        pontuacao = pontuacao + 50;
    }

    public int getPontuacao()
    {
        return pontuacao;
    }

    public void pontuarDerrota(){
        vidas--;
    }

    public int getVidas(){
        return vidas;
    }

    public boolean isGameOver() {
        return vidas <= 0;
    }

    public abstract String realizarJogada(String entradaJogar);
}
