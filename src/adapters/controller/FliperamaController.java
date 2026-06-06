package adapters.controller;

import useCase.ports.JogarPartidaInput;

public class FliperamaController {

    private final JogarPartidaInput useCase;

    public FliperamaController(JogarPartidaInput useCase) {
        this.useCase = useCase;
    }

    public void aoClicarAdicionarFicha() {
        useCase.inserirFicha();
    }

    public void aoSelecionarJogo(String nomeJogo) {
        useCase.iniciarPartida(nomeJogo);
    }

    public void aoDigitarIniciais(String iniciais) {
        useCase.informarIniciaisRanking(iniciais);
    }
}
