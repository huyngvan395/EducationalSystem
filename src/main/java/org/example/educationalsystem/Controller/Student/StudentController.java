package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private BorderPane student_Page;
    @FXML
    private Button close_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        close_button.setOnAction(event -> Model.getInstance().getViewFactory().closeStage((Stage) student_Page.getScene().getWindow()));
        onMenuOptions();
        onButtonOptions();
    }

    public void onMenuOptions(){
        Model.getInstance().getViewFactory().getStudentMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case Course -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentCourse());
                case ViewGrades -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentViewGrades());
                case Account -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentAccount());
                case Profile -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentProfile());
                case Notification -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentNotification());
                default -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentHome());
            }
        });
    }

    public void onButtonOptions(){
        Model.getInstance().getViewFactory().getStudentButtonOptions().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case ReturnToNotification -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentNotification());
                case ViewNotification -> student_Page.setCenter(Model.getInstance().getViewFactory().getStudentNotificationView());
            }
        });
    }
}
