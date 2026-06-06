package github.com.pixayo.unimob.view;

import github.com.pixayo.unimob.controller.BaseController;
import github.com.pixayo.unimob.model.SceneName;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.Objects;

/**
 * Fábrica responsável pela instanciação de novas telas da aplicação.
 */
class SceneFactory {

    /**
     * Cria e configura uma nova cena com base na tela especificada.
     *
     * <p>Este método localiza o arquivo FXML associado através do {@link SceneName},
     * realiza o carregamento da árvore de nós via {@link FXMLLoader} e aplica as
     * folhas de estilo (CSS) globais da aplicação antes de retornar a cena configurada.</p>
     *
     * @param sceneName
     * o enumerador que identifica a tela a ser instanciada.
     * @return
     * uma nova instância de {@link Scene} com a hierarquia de componentes
     * carregada e estilizada.
     * @throws RuntimeException
     * se houver falha no carregamento do arquivo ou {@code sceneName} é nulo.
     * @see SceneName
     */
    static Scene createScene(SceneName sceneName, SceneManager sceneManager) {
        String filename = sceneName.getFilename();
        String stylesheet = "css/style.css";

        try {
            FXMLLoader loader = new FXMLLoader(SceneFactory.class.getResource(filename));
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
