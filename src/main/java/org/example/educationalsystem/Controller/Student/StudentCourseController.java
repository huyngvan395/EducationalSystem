package org.example.educationalsystem.Controller.Student;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.example.educationalsystem.Model.Course;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.CourseCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCourseController implements Initializable {
    @FXML
    private Label text;
    @FXML
    private ListView<Course> course_listview;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCoursesAvailable();
    }

    public void joinCourse(String IDClass,String IDStudent){
        Model.getInstance().getDataDriver().joinCourseInStudent(IDClass,IDStudent);
        getCoursesAvailable();
    }

    public void getCoursesAvailable(){
        String IDStudent= Model.getInstance().getStudent().id_StudentProperty().get();
        ObservableList<Course> courses=Model.getInstance().getDataDriver().getCourseListHaveNotJoined(IDStudent);
        course_listview.setItems(courses);
        course_listview.setCellFactory(e-> new CourseCellFactory(this));
        if(courses.isEmpty()){
            text.setVisible(true);
        }
        else{
            text.setVisible(false);
        }
    }

}
