package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.LecturerMenuOptions;
import org.example.educationalsystem.View.StudentMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    @FXML
    private Button view_grades_btn;
    @FXML
    private Button signout_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button course_btn;
    @FXML
    private Button account_btn;
    @FXML
    private Button profile_btn;
    @FXML
    private Button notification_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signout_btn.setOnAction(e -> onSignout());
        home_btn.setOnAction(e-> onHome());
        course_btn.setOnAction(e-> onCourse());
        view_grades_btn.setOnAction(e-> onViewGrades());
        account_btn.setOnAction(e-> onAccount());
        profile_btn.setOnAction(e-> onProfile());
        notification_btn.setOnAction(e-> onNotification());
    }

    public void onSignout() {
        Model.getInstance().setCheckLoginStudent(false);
        Model.getInstance().getViewFactory().closeStage((Stage) signout_btn.getScene().getWindow());
        Model.getInstance().getViewFactory().showFormPage();
    }

    public void onHome() {
        Model.getInstance().getViewFactory().getStudentMenuItem().set(StudentMenuOptions.Home);
    }

    public void onCourse() {
        Model.getInstance().getViewFactory().getStudentMenuItem().set(StudentMenuOptions.Course);
    }

    public void onViewGrades(){
        Model.getInstance().getViewFactory().getStudentMenuItem().set(StudentMenuOptions.ViewGrades);
    }

    public void onAccount() {
        Model.getInstance().getViewFactory().getStudentMenuItem().set(StudentMenuOptions.Account);
    }

    public void onProfile() {
        Model.getInstance().getViewFactory().getStudentMenuItem().set(StudentMenuOptions.Profile);
    }

    public void onNotification() {
        Model.getInstance().getViewFactory().getStudentMenuItem().set(StudentMenuOptions.Notification);
    }
}
