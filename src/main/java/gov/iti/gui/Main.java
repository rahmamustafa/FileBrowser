package gov.iti.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    Scene scene;
    static Scene chatScene;
    static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fileBrowser.fxml"));

        Parent fileBrowserPane = loader.load();
        scene = new Scene(fileBrowserPane);
        this.stage = stage;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("File Browser APP");
        stage.show();

    }
   
    public static void main(String[] args) {
        launch();
    }

}