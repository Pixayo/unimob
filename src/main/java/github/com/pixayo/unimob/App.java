package github.com.pixayo.unimob;

import github.com.pixayo.unimob.model.SceneName;
import github.com.pixayo.unimob.view.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = new SceneManager(stage, SceneName.TELA_MAPA);

        sceneManager.addScene(SceneName.TELA_HORARIOS);
        // ...

        stage.setTitle("Unimob");
        stage.setMinWidth(250);
        stage.setMinHeight(250);
        stage.sizeToScene();
        stage.show();
    }
}
