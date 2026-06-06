package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

import java.util.List;

public interface RepositoryRanking {
    List<RegistroRanking> obterRankings(String nomeJogo);
    void salvarRegistroNoRanking(String nomeJogo, RegistroRanking registro);
}
