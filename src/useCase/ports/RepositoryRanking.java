package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

import java.util.List;

public interface RepositoryRanking {
    Ranking obterRankings(String nomeJogo);
    void salvarRegistroNoRanking(String nomeJogo, RegistroRanking registro);
}
