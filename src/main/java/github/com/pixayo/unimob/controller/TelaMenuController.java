package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import github.com.pixayo.unimob.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaMenuController {

    @FXML
    private void handleConfiguracoes(ActionEvent event) {
        mudarCena(SceneName.TELA_CONFIGURACAO);
    }

    @FXML
    private void handleSobre(ActionEvent event) {
        mudarCena(SceneName.TELA_SOBRE);
    }

    @FXML
    private void handleGalerinhaTI(ActionEvent event) {
        mudarCena(SceneName.TELA_TUTORIAL);
    }

    @FXML
    private void abrirTelaMapa(ActionEvent event) {
        mudarCena(SceneName.TELA_MAPA);
    }

    @FXML
    private void abrirTelaHorarios(ActionEvent event) {
        mudarCena(SceneName.TELA_HORARIOS);
    }

    @FXML
    private void abrirTelaFavoritos(ActionEvent event) {
        mudarCena(SceneName.TELA_FAVORITOS);
    }

    @FXML
    private void abrirTelaMenu(ActionEvent event) {
        mudarCena(SceneName.TELA_MENU);
    }

    private void mudarCena(SceneName cena) {
        if (SceneManager.getInstance() != null) {
            SceneManager.getInstance().displayScene(cena);
        } else {
            System.err.println("Erro: O SceneManager não foi iniciado ainda!");
        }
    }
}