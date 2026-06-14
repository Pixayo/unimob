package github.com.pixayo.unimob.model;

import github.com.pixayo.unimob.view.SceneManager;

public enum SceneName {
    TELA_MAPA("tela-mapa.fxml"),
    TELA_HORARIOS("tela-horarios.fxml"),
    TELA_FAVORITOS("tela-favoritos.fxml"),
    TELA_MENU("tela-menu.fxml");

    private final String filename;

    SceneName(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}