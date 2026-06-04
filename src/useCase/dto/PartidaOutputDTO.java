/* essa classe passa os dados para o presenter, para ele fazer a atualização da tela.
Assim a interface não conhece os valores direto da entidade*/

package useCase.dto;

public class PartidaOutputDTO {
    private final String nomeJogo;
    private final char[][] tabuleiro;
    private final String mensagemStatus;
    private final int pontuacaoAtual;
    private final boolean finalizado;
    private final boolean bateuRecorde;

    public PartidaOutputDTO(String nomeJogo, char[][] tabuleiro, String mensagemStatus, int pontuacaoAtual, boolean finalizado, boolean bateuRecorde) {
        this.nomeJogo = nomeJogo;
        this.tabuleiro = tabuleiro;
        this.mensagemStatus = mensagemStatus;
        this.pontuacaoAtual = pontuacaoAtual;
        this.finalizado = finalizado;
        this.bateuRecorde = bateuRecorde;
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getPontuacaoAtual() {
        return pontuacaoAtual;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public boolean isBateuRecorde() {
        return bateuRecorde;
    }
}
