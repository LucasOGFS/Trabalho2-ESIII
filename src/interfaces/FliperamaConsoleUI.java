package interfaces;

import adapters.controller.FliperamaController;
import entities.RegistroRanking;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import adapters.controller.FliperamaController;

public class FliperamaConsoleUI {

    private FliperamaController controller;
    private Stage primaryStage;

    private final Label labelCreditos = new Label("Creditos: 0");
    private final Label labelMensagem = new Label("Insira uma ficha para começar!");
    private final Label labelPontuacao = new Label("Pontuação: 0");

    private final VBox painelJogos = new VBox(8);
    private final VBox painelRanking = new VBox(4);

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

        VBox raiz = new VBox(12,
                titulo,
                labelCreditos,
                btnFicha,
                labelMensagem,
                labelPontuacao,
                tituloJogos, painelJogos,
                tituloRanking, painelRanking);
        raiz.setPadding(new Insets(24));
        raiz.setAlignment(Pos.TOP_CENTER);
        status = "Aguardando Ficha";
        return raiz;
    }

    public void mostraTelaInicial(List<RegistroRanking> recordes) {
        painelRanking.getChildren().clear();
        for (String nomeJogo : Jogos_Disponiveis) {
            Button btn = new Button(nomeJogo);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> onSelecionarJogo(nomeJogo));
            painelJogos.getChildren().add(btn);
        }

        painelRanking.getChildren().clear();
        if (recordes == null || recordes.isEmpty()) {
            painelRanking.getChildren().add(new Label("Nenhum recorde ainda."));
        } else {
            int posicao = 1;
            for (RegistroRanking ignored : recordes) {
                //Precisa exibir os nomes/pontuação quando o RegistroRanking tiver getters
                painelRanking.getChildren().add(new Label(posicao++ + "o recorde"));
            }
        }
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
        // TODO integracao: if (controller != null) controller.aoClicarAdicionarFicha();
        //previewInserirFicha();
    }

    private void onSelecionarJogo(String nomeJogo) {
        // TODO integracao: if (controller != null) controller.aoSelecionarJogo(nomeJogo);
        //previewSelecionarJogo(nomeJogo);
    }

    private void enviarIniciais(String iniciais) {
        // TODO integracao: if (controller != null) controller.aoDigitarIniciais(iniciais);
        //setLabelMensagem("Recorde salvo para " + iniciais + "!");
    }
    }