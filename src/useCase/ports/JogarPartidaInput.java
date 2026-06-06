/* Essa classe define as entradas que o caso de uso vai poder receber*/

package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

public interface JogarPartidaInput {
    void inserirFicha();                            // Passo 1
    void iniciarPartida(String nomeJogo);            // Passo 4
    void realizarJogada(String entradaJogador);      // Passo 7

    void responderSalvarRanking(boolean resposta);

    void informarIniciaisRanking(String iniciais);   // Passo 13
}
