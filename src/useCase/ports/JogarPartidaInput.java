/* Essa classe define as entradas que o caso de uso vai poder receber*/

package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

public interface JogarPartidaInput {
    void iniciarPartida(String nomeJogo);
    void RegistrarNomeRanking (RegistroRanking registro, Ranking ranking);
    void inserirFicha();
    void realizarJogada(int linha, int coluna);
}
