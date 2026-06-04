package entities;

import java.util.ArrayList;
import java.util.List;

public class Fliperama {

    private int creditos;
    private List<Jogo> jogosCadastrados = new ArrayList<>();
    private List<RegistroRanking> ranking = new ArrayList<>();

    // Métodos

    public void adicionarCreditos(int n){

    }

    public void consumirCreditos(){

    }

    public int getCreditos() {
        return creditos;
    }

    public List<Jogo> getJogosCadastrados(){
        return jogosCadastrados;
    }

//    public Jogo buscarNomePorJogo(String nome){
//        return jogo;
//    }

    public void cadastrarJogo(Jogo jogo){

    }

    public boolean entrouNoRanking(int pontuacao){
        return false;
    }

}
