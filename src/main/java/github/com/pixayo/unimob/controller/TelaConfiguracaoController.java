package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.SceneName;
import github.com.pixayo.unimob.view.SceneManager;
import github.com.pixayo.unimob.view.MenuAcessibilidade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.stage.Window;

public class TelaConfiguracaoController {

    @FXML
    private ToggleButton btnToggleAcessibilidade;

    @FXML
    public void initialize() {
        boolean ativo = ConfiguracaoSistema.isAcessibilidadeAtiva();
        if (btnToggleAcessibilidade != null) {
            btnToggleAcessibilidade.setSelected(ativo);
            atualizarEstiloBotao(ativo);
        }
    }

    @FXML
    private void handleToggleAcessibilidade(ActionEvent event) {
        if (btnToggleAcessibilidade != null) {
            boolean selecionado = btnToggleAcessibilidade.isSelected();
            ConfiguracaoSistema.setAcessibilidadeAtiva(selecionado);
            atualizarEstiloBotao(selecionado);

            Window janelaAtual = btnToggleAcessibilidade.getScene().getWindow();

            MenuAcessibilidade.toggle(selecionado, janelaAtual);
        }
    }

    private void atualizarEstiloBotao(boolean ativo) {
        if (ativo) {
            btnToggleAcessibilidade.setText("LIGADO");
            btnToggleAcessibilidade.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight: bold;");
        } else {
            btnToggleAcessibilidade.setText("DESLIGADO");
            btnToggleAcessibilidade.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight: bold;");
        }
    }

    @FXML
    private void handleVoltarMenu(ActionEvent event) {
        mudarCena(SceneName.TELA_MENU);
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