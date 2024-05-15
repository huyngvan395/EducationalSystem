package org.example.educationalsystem.Controller.Student;

import com.sun.mail.imap.protocol.ID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCourseCellController implements Initializable {
    @FXML
    private Label name_class;
    @FXML
    private Label name_lecturer;
    @FXML
    private Button join_btn;

    private String IDClass;

    private StudentCourseController studentCourseController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        join_btn.setOnAction(event -> joinAction());
    }


    public void setName_class(String name_class){
        this.name_class.setText(name_class);
    }

    public void setName_lecturer(String name_lecturer){
        this.name_lecturer.setText(name_lecturer);
    }

    public void setIDClass(String IDClass){
        this.IDClass = IDClass;
    }

    public void setStudentCourseController(StudentCourseController studentCourseController){
        this.studentCourseController = studentCourseController;
    }

    public void joinAction(){
        String IDStudent= Model.getInstance().getStudent().id_StudentProperty().get();
        this.studentCourseController.joinCourse(this.IDClass, IDStudent);
    }

}
