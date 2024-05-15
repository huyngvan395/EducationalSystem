package org.example.educationalsystem.Controller.Student;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.example.educationalsystem.Model.Class;
import org.example.educationalsystem.Model.Course;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.CourseJoinedCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {
    @FXML
    private ListView<Course> listView_Class;
    @FXML
    private Label total_Course;
    @FXML
    private Text text;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getClassList();
    }

    public void getClassList(){
        String IDStudent= Model.getInstance().getStudent().id_StudentProperty().get();
        ObservableList<Course> courses= Model.getInstance().getDataDriver().getCourseListStudent(IDStudent);
        listView_Class.setItems(courses);
        listView_Class.setCellFactory(e-> new CourseJoinedCellFactory(this));
        if(courses.isEmpty()){
            text.setVisible(true);
        }
        else{
            text.setVisible(false);
        }
        int count=0;
        for(Course c: courses){
            count++;
        }
        total_Course.setText(String.valueOf(count));
    }

    public void leftClass(String IDStudent, String IDClass){
        Model.getInstance().getDataDriver().leftCourseInStudent(IDStudent, IDClass);
        getClassList();
    }

}
