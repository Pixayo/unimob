package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.model.LinhaService;
import github.com.pixayo.unimob.model.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaHorariosController extends BaseController {

    @FXML private ScrollPane capsuleScrollPane;
    @FXML private SVGPath star006;
    @FXML private SVGPath star082;
    @FXML private SVGPath star3213;

    @FXML
    public void atualizarEstrelas() {
        for (LinhaService.LinhaOnibus l : LinhaService.getTodasAsLinhas()) {
            SVGPath star = null;
            if (l.getNumero().equals("0.006")) star = star006;
            if (l.getNumero().equals("0.082")) star = star082;
            if (l.getNumero().equals("3213")) star = star3213;

            if (star != null) {
                if (l.isFavoritado()) {
                    star.setStyle("-fx-fill: #f1c40f; -fx-stroke: #f1c40f; -fx-stroke-width: 2;");
                } else {
                    star.setStyle("-fx-fill: transparent; -fx-stroke: #aaa; -fx-stroke-width: 2;");
                }
            }
        }
    }

    @FXML private void abrirTelaMapa(ActionEvent actionEvent) { sceneManager.displayScene(SceneName.TELA_MAPA); }
    @FXML private void abrirTelaHorarios(ActionEvent actionEvent) { sceneManager.displayScene(SceneName.TELA_HORARIOS); }
    @FXML private void abrirTelaFavoritos(ActionEvent actionEvent) { sceneManager.displayScene(SceneName.TELA_FAVORITOS); }
    @FXML private void abrirTelaMenu(ActionEvent actionEvent) { sceneManager.displayScene(SceneName.TELA_MENU); }

    @FXML
    private void handleScrollHorizontal(ScrollEvent event) {
        if (event.getDeltaY() != 0 && capsuleScrollPane != null) {
            capsuleScrollPane.setHvalue(capsuleScrollPane.getHvalue() - (event.getDeltaY() * 0.003));
            event.consume();
        }
    }

    @FXML private void mostrarInfo006(ActionEvent event) { mostrarAlerta("0.006", "Cruzeiro / Sudoeste / W3 Sul / Octogonal", "06:00, 06:40, 07:20, 12:00, 13:15, 17:45, 18:30"); }
    @FXML private void mostrarInfo082(ActionEvent event) { mostrarAlerta("0.082", "Núcleo Bandeirante (Metropolitana)", "05:30, 06:15, 07:00, 07:45, 11:30, 12:15, 17:10, 18:00"); }
    @FXML private void mostrarInfo3213(ActionEvent event) { mostrarAlerta("3213", "Brt Gama / DF-483 / Santa Maria Sul", "05:00, 05:20, 05:40, 06:00, 06:20, 07:00, 16:40, 17:20"); }

    @FXML private void toggleFavorito006(ActionEvent event) { alternarFavorito("0.006"); }
    @FXML private void toggleFavorito082(ActionEvent event) { alternarFavorito("0.082"); }
    @FXML private void toggleFavorito3213(ActionEvent event) { alternarFavorito("3213"); }

    private void alternarFavorito(String numeroLinha) {
        for (LinhaService.LinhaOnibus l : LinhaService.getTodasAsLinhas()) {
            if (l.getNumero().trim().equals(numeroLinha)) {
                l.setFavoritado(!l.isFavoritado());
                break;
            }
        }
        atualizarEstrelas();
    }

    private void mostrarAlerta(String numero, String trajeto, String horariosString) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.TRANSPARENT);

        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: white; -fx-padding: 25; -fx-background-radius: 15; -fx-border-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 4);");
        layout.setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Linha " + numero);
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #175898;");

        VBox boxTrajeto = new VBox(8);
        boxTrajeto.setAlignment(Pos.CENTER_LEFT);

        Label lblTrajetoTitle = new Label("Trajeto");
        lblTrajetoTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #333; -fx-font-size: 15px;");

        Label lblTrajeto = new Label(trajeto);
        lblTrajeto.setWrapText(true);
        lblTrajeto.setStyle("-fx-text-fill: #555; -fx-font-size: 13px;");
        boxTrajeto.getChildren().addAll(lblTrajetoTitle, lblTrajeto);

        VBox boxHorarios = new VBox(10);
        boxHorarios.setAlignment(Pos.CENTER_LEFT);

        Label lblHorariosTitle = new Label("Horários de Saída");
        lblHorariosTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #333; -fx-font-size: 15px;");

        FlowPane flowHorarios = new FlowPane();
        flowHorarios.setHgap(8);
        flowHorarios.setVgap(8);

        String[] horariosArray = horariosString.split(",");
        for (String h : horariosArray) {
            Label lblHora = new Label(h.trim());
            lblHora.setStyle("-fx-background-color: #e3f2fd; -fx-text-fill: #175898; -fx-font-weight: bold; -fx-padding: 6 12; -fx-background-radius: 6; -fx-font-size: 13px;");
            flowHorarios.getChildren().add(lblHora);
        }

        boxHorarios.getChildren().addAll(lblHorariosTitle, flowHorarios);

        Button btnFechar = new Button("Fechar");
        btnFechar.setStyle("-fx-background-color: #175898; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 10 30; -fx-font-weight: bold; -fx-cursor: hand;");
        btnFechar.setOnAction(e -> dialog.close());

        layout.getChildren().addAll(lblTitulo, boxTrajeto, boxHorarios, btnFechar);

        Scene scene = new Scene(layout, 320, Region.USE_COMPUTED_SIZE);
        scene.setFill(Color.TRANSPARENT);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}