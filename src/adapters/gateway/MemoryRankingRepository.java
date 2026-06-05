package adapters.gateway;

import entities.Ranking;
import entities.RegistroRanking;
import useCase.ports.RepositoryRanking;

public class MemoryRankingRepository implements RepositoryRanking {

    @Override
    public Ranking buscarRankingDoJogo(String nomeJogo) {
        return null;
    }

    @Override
    public void salvarRegistroNoRanking(String nomeJogo, RegistroRanking registro) {

    }
}
