package org.example.educationalsystem.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.example.educationalsystem.Controller.Student.StudentController;
import org.example.educationalsystem.Controller.Student.StudentCourseController;
import org.example.educationalsystem.Controller.Student.StudentCourseJoinedCellController;
import org.example.educationalsystem.Controller.Student.StudentHomeController;
import org.example.educationalsystem.Model.Course;

public class CourseJoinedCellFactory extends ListCell<Course> {
    private StudentHomeController studentHomeController;

    public CourseJoinedCellFactory(StudentHomeController studentHomeController) {
        this.studentHomeController = studentHomeController;
    }
    @Override
    protected void updateItem(Course item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Student/coursejoinedcell.fxml"));
            try{
                setGraphic(fxmlLoader.load());
                StudentCourseJoinedCellController controller = fxmlLoader.getController();
                controller.setName_class(item.classNameProperty().get());
                controller.setName_lecturer(item.lecturerNameProperty().get());
                controller.setClassID(item.classIDProperty().get());
                controller.setStudentHomeController(this.studentHomeController);
                setText(null);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
