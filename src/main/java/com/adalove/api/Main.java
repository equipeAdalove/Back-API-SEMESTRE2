package com.adalove.api;

import com.adalove.api.controller.DatabaseConfigController;
import com.adalove.api.model.factory.DatabaseInitialize;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static final String CONFIG_FILE = "db_config.properties"; // Caminho do arquivo de configuração

    @Override
    public void start(Stage stage) throws IOException {
        if (!DatabaseInitialize.isConfigFileExists()) {

            openDatabaseConfigView(stage);

        } else {

            openLoginView(stage);
        }
    }

    private void openDatabaseConfigView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatabaseConfigView.fxml"));
        Parent parent = loader.load();
        DatabaseConfigController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(parent);
        stage.setTitle("MindDoc Analyzer - Bem vindo!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagens/icon.png"))));
        stage.show();
    }

    private void openLoginView(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginView.fxml")));
        Scene scene = new Scene(parent);
        stage.setTitle("MindDoc Analyzer - Login");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagens/icon.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
