package useCase;

import entities.Fliperama;
import entities.Jogo;
import entities.Ranking;
import entities.RegistroRanking;
import useCase.ports.JogarPartidaInput;
import useCase.ports.JogarPartidaOutput;
import useCase.ports.RepositoryRanking;

public class JogarPartidaUC implements JogarPartidaInput {
    private final JogarPartidaOutput presenter;
    private final Fliperama fliperama;
    private final RepositoryRanking ranking;
    private Jogo jogoAtual;


    public JogarPartidaUC (JogarPartidaOutput presenter, Fliperama fliperama, RepositoryRanking ranking){
        this.presenter = presenter;
        this.fliperama = fliperama;
        this.ranking = ranking;
    }

    @Override
    public void inserirFicha() {
        fliperama.adicionarCreditos();
        presenter.exibirCreditoAdicionado(fliperama.getCreditos());
    }

    @Override
    public void iniciarPartida(String nomeJogo) {
        Jogo jogo = fliperama.buscarJogoPorNome(nomeJogo);
        if(jogo == null){
            presenter.exibirMensagem("Jogo não encontrado!");
        }
        if(!fliperama.consumirCreditos()){
            presenter.exibirMensagem("Créditos insuficientes! Insira uma ficha.");
            return;
        }
        this.jogoAtual = jogo;
        presenter.exibirTelaJogo(jogoAtual.getNome());
    }


    @Override
    public void realizarJogada(String entradaJogador) {
        if(jogoAtual == null){
            presenter.exibirMensagem("Jogo ainda não iniciado.");
            return;
        }
        String resultado = jogoAtual.realizarJogada(entradaJogador);
        presenter.exibirResultadoRodada(resultado, jogoAtual.getPontuacao(), jogoAtual.getVidas());

        if(jogoAtual.isGameOver()){

        }
    }

    @Override
    public void responderSalvarRanking(boolean resposta) {

    }

    @Override
    public void informarIniciaisRanking(String iniciais) {

    }
}
