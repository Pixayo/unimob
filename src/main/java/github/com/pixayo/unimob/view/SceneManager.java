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
 * <p>
 * Esta classe é responsável por armazenar, alterar e criar (via {@link SceneFactory})
 * as telas exibidas em um {@link Stage} principal.
 * </p>
 */
public class SceneManager {

    private final Stage stage;
    private final SceneName mainScene;
    private final Map<SceneName, Scene> scenes = new HashMap<>();
    private final Deque<SceneName> previousScenes = new ArrayDeque<>();

    /**
     * Constrói o gerenciador e registra a tela principal.
     *
     * @param stage     o palco JavaFX principal da aplicação.
     * @param mainScene o identificador da tela que será definida como inicial.
     */
    public SceneManager(Stage stage, SceneName mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
        addScene(mainScene);
        displayScene(mainScene);
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
