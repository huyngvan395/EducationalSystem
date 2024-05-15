package org.example.educationalsystem.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.example.educationalsystem.Controller.Student.StudentCourseCellController;
import org.example.educationalsystem.Controller.Student.StudentCourseController;
import org.example.educationalsystem.Model.Course;

import java.io.IOException;

public class CourseCellFactory extends ListCell<Course> {
    private StudentCourseController studentCourseController;

    public CourseCellFactory(StudentCourseController studentCourseController) {
        this.studentCourseController = studentCourseController;
    }
    @Override
    protected void updateItem(Course item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Student/coursecell.fxml"));
            try{
                setGraphic(fxmlLoader.load());
                StudentCourseCellController controller=fxmlLoader.getController();
                controller.setName_class(item.classNameProperty().get());
                controller.setName_lecturer(item.lecturerNameProperty().get());
                controller.setIDClass(item.classIDProperty().get());
                controller.setStudentCourseController(this.studentCourseController);
                setText(null);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
