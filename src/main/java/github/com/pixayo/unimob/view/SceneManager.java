package github.com.pixayo.unimob.view;

import github.com.pixayo.unimob.model.SceneName;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Gerenciador central de telas (scenes).
 */
public class SceneManager {

    private static SceneManager instance;

    private final Stage stage;
    private final SceneName mainScene;
    private final Map<SceneName, Scene> scenes = new HashMap<>();
    private final Deque<SceneName> previousScenes = new ArrayDeque<>();

    public SceneManager(Stage stage, SceneName mainScene) {
        instance = this;
        this.stage = stage;
        this.mainScene = mainScene;
        addScene(mainScene);
        displayScene(mainScene);
    }

    // ---> MÉTODO NOVO: Puxa o gerenciador sem dar erro
    public static SceneManager getInstance() {
        return instance;
    }

    public boolean displayScene(SceneName name) {
        if (name == null) {
            return false;
        }

        if (!scenes.containsKey(name)) {
            addScene(name);
        }

        // TODO: add scene to previousScene queue if needed.

        stage.setScene(scenes.get(name));
        return true;
    }

    public boolean displayPreviousScene() {
        if (previousScenes.isEmpty()) {
            return false;
        }

        return displayScene(previousScenes.pop());
    }

    public boolean addScene(SceneName name) {
        if (scenes.containsKey(name)) {
            return false;
        }

        scenes.put(name, SceneFactory.createScene(name, this));
        return true;
    }
}