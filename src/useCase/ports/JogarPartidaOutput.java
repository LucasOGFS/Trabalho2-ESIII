package useCase.ports;

import entities.Ranking;
import entities.RegistroRanking;

public interface JogarPartidaOutput {
void finalizarPartida();
void RegistrarNomeRanking (RegistroRanking registro, Ranking ranking);
}

