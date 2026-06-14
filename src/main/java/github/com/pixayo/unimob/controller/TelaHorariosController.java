package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;

public class TelaHorariosController extends BaseController {

    @FXML
    private ScrollPane capsuleScrollPane;

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

    @FXML
    private void handleScrollHorizontal(ScrollEvent event) {
        if (event.getDeltaY() != 0) {
            double scrollSpeed = 0.003;

            double currentH = capsuleScrollPane.getHvalue();
            double newH = currentH - (event.getDeltaY() * scrollSpeed);

            capsuleScrollPane.setHvalue(newH);

            event.consume();
        }
    }
}