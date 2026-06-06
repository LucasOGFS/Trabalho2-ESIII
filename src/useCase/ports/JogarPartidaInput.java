/* Essa classe define as entradas que o caso de uso vai poder receber*/

package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

public interface JogarPartidaInput {
    void iniciarPartida(String nomeJogo);
    void inserirFicha();
    void realizarJogada(String entradaJogador);
}
