package useCase;

import adapters.gateway.MemoryRankingRepository;
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
            return;
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
            presenter.exibirPontuacaoFinal(jogoAtual.getPontuacao());
            verificarRanking();
        }
    }

    @Override
    public void responderSalvarRanking(boolean resposta) {
        if (resposta) {
            // Passo 12: Jogador quer salvar: Solicitar nome/iniciais
            presenter.solicitarNomeJogador();
        } else {
            // Fluxo Alternativo b-e: Jogador não quer salvar
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
        // Passo 15: Limpar estado e retornar à tela inicial
        jogoAtual = null;
        presenter.exibirTelaInicial();
    }
}
