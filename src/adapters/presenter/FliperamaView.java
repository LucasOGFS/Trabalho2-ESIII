package adapters.presenter;

import entities.RegistroRanking;

import java.util.List;

public interface FliperamaView {
    // Tela inicial (menu)
    void setLabelCreditos(int creditos);
    void setLabelMensagem(String mensagem);
    void setLabelPontuacao(int pontuacao);
    void mostraTelaInicial(List<RegistroRanking> recordes);

    // Tela de jogo
    void mostrarTelaJogo(String nomeJogo);
    void atualizarTelaJogo(String mensagem, int pontuacao, int vidas);

    // Dialogos
    void abrirPromptIniciaisRanking();
    void perguntarSalvarRanking();
}
