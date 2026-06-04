package entities;

public abstract class Jogo {
    private String nome;
    private int pontuacaoMinimaRanking;

    public Jogo(String nome, int pontuacaoMinimaRanking){
        nome = this.nome;
        pontuacaoMinimaRanking = this.pontuacaoMinimaRanking;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacaoMinimaRanking() {
        return pontuacaoMinimaRanking;
    }

    public void iniciar(){

    }

    public boolean isFinalizado(){
        return true;
    }

    public int getPontuacaoFinal(){
        return 0;
    }
}
