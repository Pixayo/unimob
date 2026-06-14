package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaMenuController extends BaseController {

    @FXML
    private void handleConfiguracoes(ActionEvent actionEvent) {
        System.out.println("Botão Configurações clicado!");
    }

    @FXML
    private void handleSobre(ActionEvent actionEvent) {
        System.out.println("Botão Sobre o Aplicativo clicado!");
    }

    @FXML
    private void handleGalerinhaTI(ActionEvent actionEvent) {
        System.out.println("Botão Galerinha da TI clicado!");
    }

    @FXML
    private void abrirTelaMapa(ActionEvent actionEvent) {
        if (sceneManager != null) {
            sceneManager.displayScene(SceneName.TELA_MAPA);
        }
    }

    @FXML
    private void abrirTelaHorarios(ActionEvent actionEvent) {
        if (sceneManager != null) {
            sceneManager.displayScene(SceneName.TELA_HORARIOS);
        }
    }

    @FXML
    private void abrirTelaFavoritos(ActionEvent actionEvent) {
        if (sceneManager != null) {
            sceneManager.displayScene(SceneName.TELA_FAVORITOS);
        }
    }

    @FXML
    private void abrirTelaMenu(ActionEvent actionEvent) {
        if (sceneManager != null) {
            sceneManager.displayScene(SceneName.TELA_MENU);
        }
    }
}