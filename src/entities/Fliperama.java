/* Cada fliperama é uma máquina, portanto deve-se passar uma lista de jogos para cada máquina*/
package entities;

import java.util.ArrayList;
import java.util.List;

public class Fliperama {
//atributos
    private int creditos;
    List<Jogo> jogosCadastrados = new ArrayList<>();

//construtor
    public Fliperama(int creditos, List<Jogo> jogosCadastrados) {
        this.creditos = creditos;
        this.jogosCadastrados = jogosCadastrados;
    }

// Métodos
    public void adicionarCreditos(){
        creditos++;
    }

    public boolean consumirCreditos(){
        if(creditos > 0){
            creditos--;
            return true;
        }else{

            return false;
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