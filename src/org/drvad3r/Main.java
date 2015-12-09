package org.drvad3r;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by wdyczko on 12/9/2015.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Shortcut Memorizer");
        primaryStage.setScene(new Scene(root, 700, 275));
        primaryStage.show();
        primaryStage.setResizable(false);
        StorageManager storageManager = new StorageManager();
        storageManager.getLessonList();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
