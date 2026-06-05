package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

public interface RepositoryRanking {
    Ranking buscarRankingDoJogo(String nomeJogo);
    void salvarRegistroNoRanking(String nomeJogo, RegistroRanking registro);
}
