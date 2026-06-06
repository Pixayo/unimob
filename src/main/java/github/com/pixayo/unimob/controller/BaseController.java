package github.com.pixayo.unimob.controller;

import github.com.pixayo.unimob.view.SceneManager;

public abstract class BaseController {

    protected SceneManager sceneManager;

    public final void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
