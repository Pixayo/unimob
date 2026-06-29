package github.com.pixayo.unimob.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaAcessibilidadeController {

    @FXML
    private void handleAltoContraste(ActionEvent event) {
        System.out.println("Configuração de Alto Contraste modificada.");
    }

    @FXML
    private void handleTextoAmpliado(ActionEvent event) {
        System.out.println("Configuração de Texto Ampliado modificada.");
    }

    @FXML
    private void handleVoltarConfiguracoes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/github/com/pixayo/unimob/view/tela-configuracao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene novaCena = new Scene(root, 360, 640);
            stage.setScene(novaCena);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao retornar para a tela de configurações!");
            e.printStackTrace();
        }
    }

    @FXML private void abrirTelaMapa(ActionEvent event) {}
    @FXML private void abrirTelaHorarios(ActionEvent event) {}
    @FXML private void abrirTelaFavoritos(ActionEvent event) {}
    @FXML private void abrirTelaMenu(ActionEvent event) {}
}