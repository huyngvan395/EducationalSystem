package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.StudentButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentViewNotificationController implements Initializable {
    @FXML
    private Button left_btn;
    @FXML
    private Label lecturer_name_lbl;
    @FXML
    private Label subject_lbl;
    @FXML
    private TextArea content_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        left_btn.setOnAction(e-> Left());
    }

    public void Left(){
        Model.getInstance().getViewFactory().getStudentButtonOptions().set(StudentButtonOptions.ReturnToNotification);
    }

    public void setLecturer_name_lbl(String lecturer_name_lbl){
        this.lecturer_name_lbl.setText(lecturer_name_lbl);
    }

    public void setSubject_lbl(String subject_lbl){
        this.subject_lbl.setText(subject_lbl);
    }

    public void setContent_text(String content_text){
        this.content_text.setText(content_text);
    }
}
