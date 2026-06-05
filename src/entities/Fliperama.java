package entities;

import java.util.ArrayList;
import java.util.List;

public class Fliperama {

    private int creditos;
    List<Jogo> jogosCadastrados = new ArrayList<>();

    public Fliperama(int creditos, List<Jogo> jogosCadastrados) {
        this.creditos = creditos;
        this.jogosCadastrados = jogosCadastrados;
    }

// Métodos

    public void adicionarCreditos(int n){
        creditos = creditos + n;
    }

    public void consumirCreditos(){
        if(creditos > 0){
            creditos--;
        }else{
            return;
        }
    }

    public int getCreditos() {
        return creditos;
    }

    public Jogo buscarJogoPorNome(String nome){
        for (Jogo jogo : jogosCadastrados) {
            if (jogo.getNome().equalsIgnoreCase(nome)) {
                return jogo;
            }
        }
        return null;
    }
}
