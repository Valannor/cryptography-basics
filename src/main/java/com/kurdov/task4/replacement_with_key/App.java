package com.kurdov.task4.replacement_with_key;

import com.kurdov.task4.replacement_with_key.controller.MainViewController;
import com.kurdov.task4.replacement_with_key.data_store.FileDataService;
import com.kurdov.task4.replacement_with_key.encoder.Encoder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App extends Application{

    private static Encoder encoder;
    private static FileDataService fileDataService;

    private BorderPane root;
    private Stage primaryStage;

    public BorderPane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public FileDataService getFileDataService() {
        return fileDataService;
    }

    public void setFileDataService(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    public String readFromFile(String filename) {
        String data = null;

        try {
            data = fileDataService.readFromFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void writeToFile(String filename, String data) {
        try {
            fileDataService.writeStringsToFile(filename, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encryptData(String data, String key) {
        return encoder.encrypt(data, key);
    }

    public String decryptData(String data, String key) {
        return encoder.decrypt(data, key);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-config.xml");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Kurdov A.: Laboratory project 4 - Replacement with key");

        initRootLayout();
        showMainView();
    }

    //Init views
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/root.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/main_view.fxml"));
            AnchorPane pane = loader.load();

            root.setCenter(pane);
            MainViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
