package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.Notification;
import org.example.educationalsystem.Model.Student;
import org.example.educationalsystem.View.StudentButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentNotificationCellController implements Initializable {
    @FXML
    private Button view_btn;
    @FXML
    private Label name_Lecturer_lbl;
    @FXML
    private Label subject_lbl;
    @FXML
    private Label content_lbl;
    @FXML
    private Button delete_btn;

    private Notification notification;

    private StudentNotificationController studentNotificationController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delete_btn.setOnAction(event -> deleteNotification());
        view_btn.setOnAction(event -> viewNotification());
    }


    public void deleteNotification(){
        studentNotificationController.deleteNotification(this.notification);
    }

    public void viewNotification(){
        Model.getInstance().getViewFactory().getStudentButtonOptions().set(StudentButtonOptions.ViewNotification);
        StudentViewNotificationController studentViewNotificationController =Model.getInstance().getViewFactory().getControllerStudentViewNotification();
        studentViewNotificationController.setLecturer_name_lbl(this.name_Lecturer_lbl.getText());
        studentViewNotificationController.setSubject_lbl(this.subject_lbl.getText());
        studentViewNotificationController.setContent_text(this.content_lbl.getText());

    }

    public void setNotification(Notification notification) {
        this.notification = notification;
        this.name_Lecturer_lbl.setText(notification.lecturerNameProperty().get());
        this.subject_lbl.setText(notification.subjectProperty().get());
        this.content_lbl.setText(notification.messageProperty().get());
    }

    public void setStudentNotificationController(StudentNotificationController controller) {
        this.studentNotificationController = controller;
    }

}
