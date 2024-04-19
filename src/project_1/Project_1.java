package project_1;

import Controller.DashBoard_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Project_1 extends Application {
    public static DashBoard_Controller controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainFXMLLoader = new FXMLLoader(getClass().getResource("/FXML/DashBoard.fxml"));
        BorderPane root = mainFXMLLoader.load();

        // Load the content separately
        this.controller = mainFXMLLoader.getController();
        controller.changeCenterBorderPane("/FXML/Author.fxml");
        // Set the loaded content as the center of the BorderPane
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vocabulary!");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
        
}