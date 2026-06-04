package useCase;

import entities.Fliperama;
import useCase.ports.JogarPartidaInput;
import useCase.ports.JogarPartidaOutput;
import useCase.ports.RepositorieRanking;

public class JogarPartidaUC implements JogarPartidaInput {
    private final JogarPartidaOutput presenter;
    private final Fliperama fliperama;
    private final RepositorieRanking ranking;


    public JogarPartidaUC (JogarPartidaOutput presenter, Fliperama fliperama, RepositorieRanking ranking){
        this.presenter = presenter;
        this.fliperama = fliperama;
        this.ranking = ranking;
    }

    @Override
    public void iniciarPartida(String nomeJogo) {

    }

    @Override
    public void RegistrarNomeRanking(String nomeJogador) {

    }

    @Override
    public void inserirFicha() {

    }

    @Override
    public void realizarJogada(int linha, int coluna) {

    }
}
