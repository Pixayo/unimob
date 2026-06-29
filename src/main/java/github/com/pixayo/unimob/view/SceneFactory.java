package github.com.pixayo.unimob.view;

import github.com.pixayo.unimob.controller.BaseController;
import github.com.pixayo.unimob.model.SceneName;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

class SceneFactory {

    static Scene createScene(SceneName sceneName, SceneManager sceneManager) {
        String filename = sceneName.getFilename();
        String stylesheet = "css/style.css";

        try {
            String path = filename.startsWith("/") ? filename : "/github/com/pixayo/unimob/view/" + filename;
            URL fxmlUrl = SceneFactory.class.getResource(path);

            if (fxmlUrl == null) {
                throw new IllegalArgumentException("Arquivo FXML não encontrado no caminho: " + path);
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            String stylePath = Objects.requireNonNull(SceneFactory.class.getResource(stylesheet)).toExternalForm();
            Region root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(stylePath);

            if (loader.getController() instanceof BaseController) {
                ((BaseController) loader.getController()).setSceneManager(sceneManager);
            }

            return scene;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar a cena: " + filename, e);
        }
    }
}