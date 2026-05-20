package github.com.pixayo.unimob.view;

import github.com.pixayo.unimob.model.SceneName;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Gerenciador central de telas (scenes).
 * <p>
 * Esta classe é responsável por armazenar, criar (via {@link SceneFactory}) e
 * alternar as telas exibidas em um {@link Stage} principal.
 * </p>
 */
public class SceneManager {

    private final Stage stage;
    private final SceneName mainScene;
    private final Map<SceneName, Scene> scenes = new HashMap<>();

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
