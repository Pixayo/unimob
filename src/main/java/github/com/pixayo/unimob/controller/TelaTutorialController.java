package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaTutorialController extends BaseController {

    @FXML
    private void abrirTelaMapa(ActionEvent actionEvent) {
        sceneManager.displayScene(SceneName.TELA_MAPA);
    }

    @FXML
    private void abrirTelaHorarios(ActionEvent actionEvent) {
        sceneManager.displayScene(SceneName.TELA_HORARIOS);
    }

    @FXML
    private void abrirTelaFavoritos(ActionEvent actionEvent) {
        sceneManager.displayScene(SceneName.TELA_FAVORITOS);
    }

    @FXML
    private void abrirTelaMenu(ActionEvent actionEvent) {
        sceneManager.displayScene(SceneName.TELA_MENU);
    }
}