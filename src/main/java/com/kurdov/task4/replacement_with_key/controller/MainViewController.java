package com.kurdov.task4.replacement_with_key.controller;

import com.kurdov.task4.replacement_with_key.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.File;

public class MainViewController extends Controller {

    private String storedText = "";

    //Key field
    @FXML
    private TextField keyField;

    //Encryption
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;
    @FXML
    private Button cancelButton;

    //Data storage
    @FXML
    private Button saveToFileButton;
    @FXML
    private Button loadFileButton;

    @FXML
    private TextArea textArea;

    @FXML
    private void initialize() {
    }

    public void setMainApp(App mainApp) {
        super.setMainApp(mainApp);
    }

    @FXML
    private void handleEncryptButton() {
        String data = textArea.getText();
        String key = keyField.getText();
        String encryptedData = getMainApp().encryptData(data, key);
        textArea.setText(encryptedData);
    }

    @FXML
    private void handleDecryptButton() {
        String data = textArea.getText();
        String key = keyField.getText();
        String decryptedData = getMainApp().decryptData(data, key);
        textArea.setText(decryptedData);
    }

    @FXML
    private void handleCancelButton() {
        textArea.setText(storedText);
    }

    @FXML
    private void handleSaveToFileButton() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(getMainApp().getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".txt"))
                file = new File(file.getPath() + ".txt");

            getMainApp().writeToFile(file.getAbsolutePath(), textArea.getText());
        }

        storedText = "";
    }

    @FXML
    private void handleLoadFileButton() {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(getMainApp().getPrimaryStage());

        if (file != null) {
            String data = getMainApp().readFromFile(file.getAbsolutePath());
            textArea.setText(data);
        }

        storedText = textArea.getText();
    }

}
