package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

import java.util.List;

public interface JogarPartidaOutput {
    void exibirCreditoAdicionado(int creditosAtuais);
    void exibirTelaJogo(String nomeJogo);
    void exibirResultadoRodada(String mensagemRodada, int pontuacao, int vidas);
    void exibirPontuacaoFinal(int pontuacaoFinal);
    void perguntarSalvarRanking();
    void solicitarNomeJogador();
    void exibirMensagem(String mensagem);
    void exibirTelaInicial(int creditos, List<RegistroRanking> ranking);
}

