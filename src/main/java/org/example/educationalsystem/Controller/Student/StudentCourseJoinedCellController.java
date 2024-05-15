package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCourseJoinedCellController implements Initializable {
    @FXML
    private Label name_class;
    @FXML
    private Label name_lecturer;
    @FXML
    private Button left_btn;

    private String classID;

    private StudentHomeController studentHomeController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        left_btn.setOnAction(event -> leftClass());
    }

    public void setName_class(String name_class) {
        this.name_class.setText(name_class);
    }

    public void setName_lecturer(String name_lecturer) {
        this.name_lecturer.setText(name_lecturer);
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public void setStudentHomeController(StudentHomeController studentHomeController) {
        this.studentHomeController = studentHomeController;
    }

    public void leftClass(){
        String IDStudent= Model.getInstance().getStudent().id_StudentProperty().get();
        studentHomeController.leftClass(IDStudent, this.classID);
    }
}
