package interfaces;

import adapters.controller.FliperamaController;
import adapters.presenter.FliperamaView;
import entities.RegistroRanking;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class FliperamaJavaFXUI implements FliperamaView {

    private FliperamaController controller;
    private Stage primaryStage;
    private Scene scene;
    private VBox menuRoot;

    // ----- Labels da tela inicial (menu) -----
    private final Label labelCreditos = new Label("Creditos: 0");
    private final Label labelMensagem = new Label("Insira uma ficha para começar!");
    private final Label labelPontuacao = new Label("Pontuação: 0");

    private final VBox painelJogos = new VBox(8);
    private final TableView<RegistroRanking> tabelaRanking = new TableView<>();

    // ----- Labels da tela de jogo (atualizados durante a partida) -----
    private final Label labelJogoTitulo = new Label();
    private final Label labelJogoMensagem = new Label("Faça sua jogada!");
    private final Label labelJogoPontuacao = new Label("Pontuação: 0");
    private final Label labelJogoVidas = new Label("Vidas: 0");

    private static final List<String> JOGOS_DISPONIVEIS =
            List.of("Pedra, Papel e Tesoura", "Par ou Impar");

    // =========================================================
    //  Ciclo de vida
    // =========================================================
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.menuRoot = construirTelaInicial();
        this.scene = new Scene(menuRoot, 440, 640);
        stage.setTitle("Fliperama");
        stage.setScene(scene);
        mostraTelaInicial(List.of());
        stage.show();
    }

    public void setController(FliperamaController controller) {
        this.controller = controller;
    }

    // =========================================================
    //  TELA INICIAL (menu)
    // =========================================================
    private VBox construirTelaInicial() {
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
                titulo, labelCreditos, btnFicha, labelMensagem, labelPontuacao,
                tituloJogos, painelJogos, tituloRanking, tabelaRanking);
        raiz.setPadding(new Insets(24));
        raiz.setAlignment(Pos.TOP_CENTER);
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

    @Override
    public void mostraTelaInicial(List<RegistroRanking> recordes) {
        painelJogos.getChildren().clear();
        for (String nomeJogo : JOGOS_DISPONIVEIS) {
            Button btn = new Button(nomeJogo);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> onSelecionarJogo(nomeJogo));
            painelJogos.getChildren().add(btn);
        }

        ObservableList<RegistroRanking> dados =
                FXCollections.observableArrayList(recordes == null ? List.of() : recordes);
        tabelaRanking.setItems(dados);

        if (scene != null) {
            scene.setRoot(menuRoot);   // garante que voltamos para o menu
        }
    }

    // =========================================================
    //  TELAS DE JOGO (mesmo padrao da tela inicial)
    // =========================================================
    @Override
    public void mostrarTelaJogo(String nomeJogo) {
        labelJogoTitulo.setText(nomeJogo);
        labelJogoMensagem.setText("Faça sua jogada!");

        VBox telaJogo;
        if (nomeJogo.equalsIgnoreCase("Par ou Impar")) {
            telaJogo = construirTelaParOuImpar();
        } else {
            telaJogo = construirTelaPedraPapelTesoura();
        }
        scene.setRoot(telaJogo);
    }

    private VBox construirTelaPedraPapelTesoura() {
        labelJogoTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button btnPedra = new Button("Pedra");
        Button btnPapel = new Button("Papel");
        Button btnTesoura = new Button("Tesoura");
        for (Button b : new Button[]{btnPedra, btnPapel, btnTesoura}) {
            b.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(b, javafx.scene.layout.Priority.ALWAYS);
        }
        btnPedra.setOnAction(e -> onJogada("PEDRA"));
        btnPapel.setOnAction(e -> onJogada("PAPEL"));
        btnTesoura.setOnAction(e -> onJogada("TESOURA"));

        HBox jogadas = new HBox(8, btnPedra, btnPapel, btnTesoura);
        jogadas.setAlignment(Pos.CENTER);

        VBox raiz = new VBox(12,
                labelJogoTitulo,
                labelJogoPontuacao,
                labelJogoVidas,
                labelJogoMensagem,
                new Label("Escolha sua jogada:"),
                jogadas,
                criarBotaoVoltar());
        raiz.setPadding(new Insets(24));
        raiz.setAlignment(Pos.TOP_CENTER);
        return raiz;
    }

    private VBox construirTelaParOuImpar() {
        labelJogoTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField campoNumero = new TextField();
        campoNumero.setPromptText("Digite um número");
        campoNumero.setMaxWidth(180);

        Button btnPar = new Button("PAR");
        Button btnImpar = new Button("IMPAR");
        btnPar.setOnAction(e -> onJogada("PAR " + campoNumero.getText().trim()));
        btnImpar.setOnAction(e -> onJogada("IMPAR " + campoNumero.getText().trim()));

        HBox escolhas = new HBox(8, btnPar, btnImpar);
        escolhas.setAlignment(Pos.CENTER);

        VBox raiz = new VBox(12,
                labelJogoTitulo,
                labelJogoPontuacao,
                labelJogoVidas,
                labelJogoMensagem,
                new Label("Escolha PAR ou IMPAR e um número:"),
                campoNumero,
                escolhas,
                criarBotaoVoltar());
        raiz.setPadding(new Insets(24));
        raiz.setAlignment(Pos.TOP_CENTER);
        return raiz;
    }

    private Button criarBotaoVoltar() {
        Button btnVoltar = new Button("Voltar ao menu");
        btnVoltar.setOnAction(e -> mostraTelaInicial(List.of()));
        return btnVoltar;
    }

    @Override
    public void atualizarTelaJogo(String mensagem, int pontuacao, int vidas) {
        labelJogoMensagem.setText(mensagem);
        labelJogoPontuacao.setText("Pontuação: " + pontuacao);
        labelJogoVidas.setText("Vidas: " + vidas);
    }

    // =========================================================
    //  Dialogos
    // =========================================================
    @Override
    public void abrirPromptIniciaisRanking() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Novo recorde!");
        dialog.setHeaderText("Voce entrou no ranking!");
        dialog.setContentText("Digite suas iniciais:");
        Optional<String> iniciais = dialog.showAndWait();
        iniciais.ifPresent(this::enviarIniciais);
    }

    @Override
    public void perguntarSalvarRanking() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                "Deseja salvar sua pontuação no ranking?", ButtonType.YES, ButtonType.NO);
        alerta.setTitle("Ranking");
        alerta.setHeaderText("Você fez uma boa pontuação!");
        Optional<ButtonType> resposta = alerta.showAndWait();
        boolean salvar = resposta.isPresent() && resposta.get() == ButtonType.YES;
        if (controller != null) {
            controller.aoResponderSalvarRanking(salvar);
        }
    }

    // =========================================================
    //  Metodos da View chamados pelo Presenter (menu)
    // =========================================================
    @Override
    public void setLabelCreditos(int creditos) {
        labelCreditos.setText("Creditos: " + creditos);
    }

    @Override
    public void setLabelMensagem(String msg) {
        labelMensagem.setText(msg);
    }

    @Override
    public void setLabelPontuacao(int score) {
        labelPontuacao.setText("Pontuação: " + score);
    }

    // =========================================================
    //  ESTIMULOS -> Controller
    // =========================================================
    private void onInserirFicha() {
        if (controller == null) { setLabelMensagem("Controller ainda nao conectado."); return; }
        controller.aoClicarAdicionarFicha();
    }

    private void onSelecionarJogo(String nomeJogo) {
        if (controller == null) { setLabelMensagem("Controller ainda nao conectado."); return; }
        controller.aoSelecionarJogo(nomeJogo);
    }

    private void onJogada(String entrada) {
        if (controller == null) { labelJogoMensagem.setText("Controller ainda nao conectado."); return; }
        controller.aoRealizarJogada(entrada);
    }

    private void enviarIniciais(String iniciais) {
        if (controller == null) return;
        controller.aoDigitarIniciais(iniciais);
    }
}
