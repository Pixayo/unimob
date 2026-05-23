package github.com.pixayo.unimob.model;

import github.com.pixayo.unimob.view.SceneManager;

/**
 * Mapeia todas as telas da aplicação aos seus respectivos arquivos fxml.
 *
 * <p>Cada tela é representada por uma constante que carrega o nome do arquivo com a extensão
 * {@code .fxml}, este arquivo deve estar presente em resources no diretório {@code view}</p>
 *
 * @see SceneManager
 */
public enum SceneName {
    TELA_MAPA("tela-mapa.fxml");

    private final String filename;

    SceneName(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
