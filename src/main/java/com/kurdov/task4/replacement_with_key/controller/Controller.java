package com.kurdov.task4.replacement_with_key.controller;

import com.kurdov.task4.replacement_with_key.App;
import javafx.stage.Stage;

public abstract class Controller {

    private App mainApp;
    private Stage stage;

    public App getMainApp() {
        return mainApp;
    }

    public void setMainApp(App app) {
        this.mainApp = app;
    }

    public Stage getStage() {
        return stage;
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
}
