package github.com.pixayo.unimob.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import github.com.pixayo.unimob.view.MenuAcessibilidade;

public class TelaSobreController {

    @FXML
    private void voltarParaMenu(ActionEvent event) {
        mudarTela(event, "/TelaMenuView.fxml");
    }

    @FXML private void abrirTelaMapa(ActionEvent e) { mudarTela(e, "/TelaMapaView.fxml"); }
    @FXML private void abrirTelaHorarios(ActionEvent e) { mudarTela(e, "/TelaHorariosView.fxml"); }
    @FXML private void abrirTelaFavoritos(ActionEvent e) { mudarTela(e, "/TelaFavoritosView.fxml"); }
    @FXML private void abrirTelaMenu(ActionEvent e) { mudarTela(e, "/TelaMenuView.fxml"); }

    private void mudarTela(ActionEvent event, String fxmlPath) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            java.net.URL url = getClass().getResource(fxmlPath);
            if (url == null) {
                url = getClass().getResource("/github/com/pixayo/unimob/view" + fxmlPath);
            }
            if (url == null && fxmlPath.startsWith("/")) {
                url = getClass().getResource(fxmlPath.substring(1));
            }

            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            MenuAcessibilidade.cfg(scene);

            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}