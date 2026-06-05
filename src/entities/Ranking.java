package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {
    private static final int tamanho = 10;
    private List<RegistroRanking> ranking = new ArrayList<>();

    public Ranking(List<RegistroRanking> registrosIniciais) {
        if (registrosIniciais != null) {
            this.ranking.addAll(registrosIniciais);
            ordenarRanking();
        }
    }

    private void ordenarRanking() {
        ranking.sort(Comparator.comparingInt(RegistroRanking::getPontuacao).reversed());
    }

    public boolean isElegivel(int pontuacao) {
        if (ranking.size() < tamanho) {
            return true;
        }
        int menorPontuacaoAtual = ranking.get(ranking.size() - 1).getPontuacao();
        return pontuacao > menorPontuacaoAtual;
    }

    public List<RegistroRanking> getRanking() {
        return Collections.unmodifiableList(ranking);
    }
}
