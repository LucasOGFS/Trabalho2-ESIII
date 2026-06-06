package useCase;

import entities.Fliperama;
import entities.Jogo;
import entities.Ranking;
import entities.RegistroRanking;
import useCase.ports.JogarPartidaInput;
import useCase.ports.JogarPartidaOutput;
import useCase.ports.RepositoryRanking;

import java.util.List;

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
        this.jogoAtual = fliperama.buscarJogoPorNome(nomeJogo);
        if(this.jogoAtual == null){
            presenter.exibirMensagem("Jogo não encontrado!");
            return;
        }
        if(!fliperama.consumirCreditos()){
            presenter.exibirMensagem("Créditos insuficientes! Insira uma ficha.");
            return;
        }
        this.jogoAtual.reiniciar();
        presenter.exibirTelaJogo(this.jogoAtual.getNome());
        presenter.exibirCreditoAdicionado(fliperama.getCreditos());
        presenter.exibirResultadoRodada("Partida iniciada! Boa sorte.", this.jogoAtual.getPontuacao(), this.jogoAtual.getVidas());
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
            presenter.exibirPontuacaoFinal(jogoAtual.getPontuacao());
            verificarRanking();
        }
    }

    @Override
    public void responderSalvarRanking(boolean resposta) {
        if (resposta) {
            presenter.solicitarNomeJogador();
        } else {
            presenter.exibirMensagem("Obrigado e volte sempre!");
            encerrar();
        }
    }

    @Override
    public void informarIniciaisRanking(String iniciais) {
        if (jogoAtual == null) return;

        // Passo 13-14: Registrar nome e pontuação no ranking
        RegistroRanking registro = new RegistroRanking(jogoAtual.getPontuacao(), iniciais);
        this.ranking.salvarRegistroNoRanking(jogoAtual.getNome(), registro);

        presenter.exibirMensagem("Ranking salvo com sucesso!");
        encerrar();
    }

    private void verificarRanking() {
        Ranking ranking = this.ranking.obterRankings(jogoAtual.getNome());
        // Se não existe ranking ainda, qualquer pontuação é elegível
        boolean elegivel = (ranking == null) || ranking.isElegivel(jogoAtual.getPontuacao());
        if (elegivel) {
            presenter.perguntarSalvarRanking();
        } else {
            presenter.exibirMensagem("Obrigado e volte sempre!");
            encerrar();
        }
    }

    private void encerrar() {
        List<RegistroRanking> recordes = List.of();
        if (jogoAtual != null) {
            Ranking r = ranking.obterRankings(jogoAtual.getNome());  // ranking do jogo jogado
            if (r != null) {
                recordes = r.getRanking();
            }
        }
        jogoAtual = null;
        presenter.exibirTelaInicial(fliperama.getCreditos(), recordes);
    }
}
