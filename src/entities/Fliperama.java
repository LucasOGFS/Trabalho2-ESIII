/* Cada fliperama é uma máquina, portanto deve-se passar uma lista de jogos para cada máquina*/
package entities;

import java.util.ArrayList;
import java.util.List;

public class Fliperama {
//atributos
    private int creditos;
    List<Jogo> jogosCadastrados = new ArrayList<>();
    String status = "Aguardando Ficha";

//construtor
    public Fliperama(int creditos, List<Jogo> jogosCadastrados) {
        this.creditos = creditos;
        this.jogosCadastrados = jogosCadastrados;
    }

    public Fliperama (int creditos, List<Jogo> jogosCadastrados, String status ){
        this.creditos = creditos;
        this.jogosCadastrados = jogosCadastrados;
        this.status = status;
    }

// Métodos
    public void adicionarCreditos(){
        creditos++;
        this.status = "Aguardando Seleção de Jogo";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println(status);
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