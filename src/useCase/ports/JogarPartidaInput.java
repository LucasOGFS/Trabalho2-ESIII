/* Essa classe define as entradas que o caso de uso vai poder receber*/

package useCase.ports;

public interface JogarPartidaInput {
    void iniciarPartida(String nomeJogo);
    void RegistrarNomeRanking (String nomeJogador);
    void inserirFicha();
    void realizarJogada(int linha, int coluna);
}
