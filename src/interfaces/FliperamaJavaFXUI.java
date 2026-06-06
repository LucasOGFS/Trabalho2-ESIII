package interfaces;

import adapters.controller.FliperamaController;
import entities.RegistroRanking;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.Optional;

public class FliperamaJavaFXUI {

    private FliperamaController controller;
    private Stage primaryStage;

    private final Label labelCreditos = new Label("Creditos: 0");
    private final Label labelMensagem = new Label("Insira uma ficha para começar!");
    private final Label labelPontuacao = new Label("Pontuação: 0");

    private final VBox painelJogos = new VBox(8);
    private final TableView<RegistroRanking> tabelaRanking = new TableView<>();

    private static final List<String> Jogos_Disponiveis = List.of("Pedra, Papel e Tesoura", "Par ou Impar");

    private static String status;

    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Fliperama");
        stage.setScene(new Scene(construirRaiz(), 420, 600));
        mostraTelaInicial(List.of());
        stage.show();
    }

    public void setController(FliperamaController controller) {
        this.controller = controller;
    }

    private VBox construirRaiz() {
        Label titulo = new Label("Fliperama");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Button btnFicha = new Button("Inserir Ficha");
        btnFicha.setMaxWidth(Double.MAX_VALUE);
        btnFicha.setOnAction(e -> onInserirFicha());

        Label tituloJogos = new Label("Jogos Disponíveis");
        tituloJogos.setStyle("-fx-font-weight: bold;");

        Label tituloRanking = new Label("Ranking:");
        tituloRanking.setStyle("-fx-font-weight: bold;");

        configurarTabelaRanking();

        VBox raiz = new VBox(12,
                titulo,
                labelCreditos,
                btnFicha,
                labelMensagem,
                labelPontuacao,
                tituloJogos, painelJogos,
                tituloRanking, tabelaRanking);
        raiz.setPadding(new Insets(24));
        raiz.setAlignment(Pos.TOP_CENTER);
        status = "Aguardando Ficha";
        return raiz;
    }

    private void configurarTabelaRanking() {
        TableColumn<RegistroRanking, String> colJogador = new TableColumn<>("Jogador");
        colJogador.setCellValueFactory(new PropertyValueFactory<>("iniciais"));

        TableColumn<RegistroRanking, Integer> colPontuacao = new TableColumn<>("Pontuação");
        colPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        tabelaRanking.getColumns().add(colJogador);
        tabelaRanking.getColumns().add(colPontuacao);
        tabelaRanking.setPlaceholder(new Label("Nenhum recorde ainda."));
    }

    public void mostraTelaInicial(List<RegistroRanking> recordes) {
        painelJogos.getChildren().clear();
        for (String nomeJogo : Jogos_Disponiveis) {
            Button btn = new Button(nomeJogo);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> onSelecionarJogo(nomeJogo));
            painelJogos.getChildren().add(btn);
        }

        // List (Java puro) -> ObservableList (JavaFX) -> TableView
        ObservableList<RegistroRanking> dados =
                FXCollections.observableArrayList(recordes == null ? List.of() : recordes);
        tabelaRanking.setItems(dados);
    }

    public void setLabelCreditos(int creditos) {
        labelCreditos.setText("Creditos: " + creditos);
    }

    public void setLabelMensagem(String msg) {
        labelMensagem.setText(msg);
    }

    public void setLabelPontuacao(int score) {
        labelPontuacao.setText("Pontuação: " + score);
    }

    public void abrirPromptIniciaisRanking() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Novo recorde!");
        dialog.setHeaderText("Voce entrou no ranking!");
        dialog.setContentText("Digite suas iniciais:");
        Optional<String> iniciais = dialog.showAndWait();
        iniciais.ifPresent(this::enviarIniciais);
    }

    private void onInserirFicha() {
        if (controller == null) {
            setLabelMensagem("Controller ainda nao conectado.");
            return;
        }
        controller.aoClicarAdicionarFicha();
    }

    private void onSelecionarJogo(String nomeJogo) {
        if (controller == null) {
            setLabelMensagem("Controller ainda nao conectado.");
            return;
        }
        controller.aoSelecionarJogo(nomeJogo);
    }

    private void enviarIniciais(String iniciais) {
        if (controller == null) return;
        controller.aoDigitarIniciais(iniciais);
    }
}