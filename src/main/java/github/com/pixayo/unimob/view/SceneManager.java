package github.com.pixayo.unimob.view;

import github.com.pixayo.unimob.model.SceneName;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private final Stage stage;
    private final SceneName mainScene;
    private final Map<SceneName, Scene> scenes = new HashMap<>();

    public SceneManager(Stage stage, SceneName mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
        addScene(mainScene);
        displayMainScene();
    }

    public boolean displayMainScene() {
        return displayScene(mainScene);
    }

    public boolean displayScene(SceneName name) {
        stage.setScene(scenes.get(name));
        return true;
    }

    public boolean addScene(SceneName name) {
        Scene scene = SceneFactory.createScene(name);
        scenes.put(name, scene);
        return true;
    }
}
