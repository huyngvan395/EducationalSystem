package org.example.educationalsystem;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model.getInstance().getViewFactory().showFormPage();
    }

}
