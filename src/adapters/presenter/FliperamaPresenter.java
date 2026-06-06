package adapters.presenter;

import entities.RegistroRanking;
import useCase.ports.JogarPartidaOutput;

import java.util.List;

public class FliperamaPresenter implements JogarPartidaOutput {

    private final FliperamaView ui;

    public FliperamaPresenter(FliperamaView ui) {
        this.ui = ui;
    }

    @Override
    public void exibirCreditoAdicionado(int creditosAtuais) {
        ui.setLabelCreditos(creditosAtuais);
    }

    @Override
    public void exibirTelaSelecaoJogos(List<String> jogosDisponiveis) {

    }

    @Override
    public void exibirTelaJogo(String nomeJogo) {
        ui.mostrarTelaJogo(nomeJogo);
    }

    @Override
    public void exibirResultadoRodada(String mensagemRodada, int pontuacao, int vidas) {
        ui.atualizarTelaJogo(mensagemRodada, pontuacao, vidas);
    }

    @Override
    public void exibirPontuacaoFinal(int pontuacaoFinal) {
        ui.atualizarTelaJogo("Fim de jogo! Pontuação final: " + pontuacaoFinal, pontuacaoFinal, 0);
    }

    @Override
    public void perguntarSalvarRanking() {
        ui.perguntarSalvarRanking();
    }

    @Override
    public void solicitarNomeJogador(){
        ui.abrirPromptIniciaisRanking();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        ui.setLabelMensagem(mensagem);
    }

    @Override
    public void exibirTelaInicial(int creditos, List<RegistroRanking> recordes) {
        ui.setLabelCreditos(creditos);
        ui.mostraTelaInicial(recordes);
    }
}
