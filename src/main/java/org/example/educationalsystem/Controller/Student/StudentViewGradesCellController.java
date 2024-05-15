package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentViewGradesCellController implements Initializable {
    @FXML
    private Label course_Name_lbl;
    @FXML
    private Label ex_Grades;
    @FXML
    private Label final_Grades;
    @FXML
    private Label midterm_Grades;
    @FXML
    private Label average_Grades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCourse_Name_lbl(String courseName) {
        this.course_Name_lbl.setText(courseName);
    }

    public void setEx_Grades(Double exGrades) {
        if(exGrades!=null){
            this.ex_Grades.setText(String.valueOf(exGrades));
        }
    }

    public void setMidterm_Grades(Double midtermGrades) {
        if(midtermGrades!=null){
            this.midterm_Grades.setText(String.valueOf(midtermGrades));
        }
    }

    public void setFinal_Grades(Double finalGrades) {
        if(finalGrades!=null){
            this.final_Grades.setText(String.valueOf(finalGrades));
        }
    }

    public void setAverage_Grades(Double averageGrades) {
        this.average_Grades.setText(String.valueOf(averageGrades));
    }
}
