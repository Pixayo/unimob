package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TelaMapaController extends BaseController {

    @FXML
    private WebView mapaWebView;

    @FXML
    public void initialize() {
        if (mapaWebView != null) {
            mapaWebView.setContextMenuEnabled(false);
            WebEngine engine = mapaWebView.getEngine();
            engine.setJavaScriptEnabled(true);

            engine.load(getClass().getResource("/mapa.html").toExternalForm());

            mapaWebView.widthProperty().addListener((o, old, n) -> engine.executeScript("resizeMap()"));
            mapaWebView.heightProperty().addListener((o, old, n) -> engine.executeScript("resizeMap()"));
        }
    }

    @FXML private void abrirTelaMapa(ActionEvent a) { sceneManager.displayScene(SceneName.TELA_MAPA); }
    @FXML private void abrirTelaHorarios(ActionEvent a) { sceneManager.displayScene(SceneName.TELA_HORARIOS); }
    @FXML private void abrirTelaFavoritos(ActionEvent a) { sceneManager.displayScene(SceneName.TELA_FAVORITOS); }
    @FXML private void abrirTelaMenu(ActionEvent a) { sceneManager.displayScene(SceneName.TELA_MENU); }
}