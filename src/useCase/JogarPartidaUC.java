package useCase;

import entities.Fliperama;
import entities.Ranking;
import entities.RegistroRanking;
import useCase.ports.JogarPartidaInput;
import useCase.ports.JogarPartidaOutput;
import useCase.ports.RepositoryRanking;

public class JogarPartidaUC implements JogarPartidaInput {
    private final JogarPartidaOutput presenter;
    private final Fliperama fliperama;
    private final RepositoryRanking ranking;


    public JogarPartidaUC (JogarPartidaOutput presenter, Fliperama fliperama, RepositoryRanking ranking){
        this.presenter = presenter;
        this.fliperama = fliperama;
        this.ranking = ranking;
    }

    @Override
    public void iniciarPartida(String nomeJogo) {

    }

    @Override
    public void inserirFicha() {

    }

    @Override
    public void realizarJogada(String entradaJogador) {

    }
}
