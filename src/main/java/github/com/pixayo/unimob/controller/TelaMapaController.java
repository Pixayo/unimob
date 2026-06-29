package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TelaMapaController extends BaseController {

    @FXML
    private WebView mapaWebView;

    private boolean mapInitialized = false;
    private static final int INITIALIZATION_TIMEOUT_MS = 5000;

    @FXML
    public void initialize() {
        if (mapaWebView == null) {
            System.err.println("[ERRO] WebView 'mapaWebView' não foi injetada pelo FXML");
            return;
        }

        try {
            configurarWebView();
            configurarRedimensionamentoComSeguranca();
            carregarMapaComValidacao();
        } catch (Exception e) {
            System.err.println("[ERRO] Falha ao inicializar mapa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void configurarWebView() {
        mapaWebView.setContextMenuEnabled(false);

        WebEngine engine = mapaWebView.getEngine();
        engine.setJavaScriptEnabled(true);

        engine.setOnError(errorEvent -> {
            System.err.println("[ERRO JS] " + errorEvent.getMessage());
            errorEvent.consume();
        });

        System.out.println("[INFO] WebView configurado com sucesso");
    }

    private void configurarRedimensionamentoComSeguranca() {
        WebEngine engine = mapaWebView.getEngine();

        mapaWebView.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (mapInitialized) {
                tentarRedimensionarMapa(engine);
            }
        });

        mapaWebView.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (mapInitialized) {
                tentarRedimensionarMapa(engine);
            }
        });

        System.out.println("[INFO] Listeners de redimensionamento registrados");
    }

    private void tentarRedimensionarMapa(WebEngine engine) {
        try {
            Object resultado = engine.executeScript(
                    "typeof resizeMap === 'function' ? resizeMap() : false"
            );

            if (resultado != null && (Boolean) resultado || resultado == null) {
                System.out.println("[DEBUG] Mapa redimensionado");
            }
        } catch (Exception e) {
            System.err.println("[AVISO] Falha ao redimensionar: " + e.getMessage());
        }
    }

    private void carregarMapaComValidacao() {
        WebEngine engine = mapaWebView.getEngine();

        try {
            String resourcePath = "/github/com/pixayo/unimob/view/Mapa.html";

            if (getClass().getResource(resourcePath) == null) {
                throw new IllegalArgumentException(
                        "Arquivo HTML não encontrado: " + resourcePath
                );
            }

            String mapaUrl = getClass().getResource(resourcePath).toExternalForm();
            System.out.println("[INFO] Carregando mapa de: " + mapaUrl);

            inicializadorComTimeout(engine);

            engine.load(mapaUrl);

        } catch (Exception e) {
            System.err.println("[ERRO] Falha ao carregar mapa: " + e.getMessage());
            throw new RuntimeException("Impossível carregar Mapa.html", e);
        }
    }

    private void inicializadorComTimeout(WebEngine engine) {
        new Thread(() -> {
            try {
                Thread.sleep(INITIALIZATION_TIMEOUT_MS);

                Object inicializado = engine.executeScript(
                        "window.getMapInitialized ? window.getMapInitialized() : false"
                );

                if (inicializado == null || !(Boolean) inicializado) {
                    System.err.println("[AVISO] Mapa não inicializou após " +
                            INITIALIZATION_TIMEOUT_MS + "ms");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @FXML
    private void abrirTelaMapa(ActionEvent a) {
        mapInitialized = false;
        sceneManager.displayScene(SceneName.TELA_MAPA);
    }

    @FXML
    private void abrirTelaHorarios(ActionEvent a) {
        sceneManager.displayScene(SceneName.TELA_HORARIOS);
    }

    @FXML
    private void abrirTelaFavoritos(ActionEvent a) {
        sceneManager.displayScene(SceneName.TELA_FAVORITOS);
    }

    @FXML
    private void abrirTelaMenu(ActionEvent a) {
        sceneManager.displayScene(SceneName.TELA_MENU);
    }

    public void notifyMapInitialized() {
        mapInitialized = true;
        System.out.println("[INFO] Mapa inicializado com sucesso (notificado por JS)");
    }
}