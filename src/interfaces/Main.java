package interfaces;

import adapters.controller.FliperamaController;
import adapters.gateway.MemoryRankingRepository;
import adapters.presenter.FliperamaPresenter;
import entities.Fliperama;
import entities.Jogo;
import entities.Ranking;
import entities.jogos.ParOuImpar;
import entities.jogos.PedraPapelTesoura;
import javafx.application.Application;
import javafx.stage.Stage;
import useCase.JogarPartidaUC;
import useCase.ports.RepositoryRanking;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Ranking ranking = new Ranking(new ArrayList<>());

        List<Jogo> jogos = new ArrayList<>();
        jogos.add(new PedraPapelTesoura(ranking));
        jogos.add(new ParOuImpar(ranking));

        Fliperama fliperama = new Fliperama(0, jogos);

        RepositoryRanking repositorio = new MemoryRankingRepository();

        FliperamaJavaFXUI ui = new FliperamaJavaFXUI();

        FliperamaPresenter presenter = new FliperamaPresenter(ui);

        JogarPartidaUC uc = new JogarPartidaUC(presenter, fliperama, repositorio);

        FliperamaController controller = new FliperamaController(uc);

        ui.setController(controller);
        ui.start(primaryStage);
    }
}