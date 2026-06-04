package entities;

public abstract class Jogo {
    private String nome;
    private int pontuacaoMinimaRanking;

    public Jogo(String nome, int pontuacaoMinimaRanking){
        this.nome = nome;
        this.pontuacaoMinimaRanking =  pontuacaoMinimaRanking;
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
