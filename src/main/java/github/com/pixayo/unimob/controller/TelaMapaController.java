package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaMapaController extends BaseController {

    @FXML
    private void abrirTelaHorarios(ActionEvent actionEvent) {
        sceneManager.displayScene(SceneName.TELA_HORARIOS);
    }

    public void abriTelaFavoritos(ActionEvent actionEvent) {
        sceneManager.displayScene(SceneName.TELA_FAVORITOS);
    }
}
