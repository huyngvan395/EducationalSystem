package org.example.educationalsystem.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.example.educationalsystem.Controller.Student.StudentViewGradesCellController;
import org.example.educationalsystem.Model.Mark;

import static java.lang.String.format;

public class ViewGradesCellFactory extends ListCell<Mark> {
    @Override
    protected void updateItem(Mark item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Student/view_grades_cell.fxml"));
            try{
                setGraphic(fxmlLoader.load());
                StudentViewGradesCellController controller = fxmlLoader.getController();
                controller.setCourse_Name_lbl(item.courseNameProperty().get());
                if(item.exerciseGradesProperty().get()==null){
                    controller.setEx_Grades(null);
                }
                else{
                    controller.setEx_Grades(item.exerciseGradesProperty().get());
                }
                if(item.midtermGradesProperty().get()==null){
                    controller.setMidterm_Grades(null);
                }
                else{
                    controller.setMidterm_Grades(item.midtermGradesProperty().get());
                }
                if(item.finalGradesProperty().get()==null){
                    controller.setFinal_Grades(null);
                }
                else{
                    controller.setFinal_Grades(item.finalGradesProperty().get());
                }
                if(item.exerciseGradesProperty().get()!=null && item.midtermGradesProperty().get()!=null && item.finalGradesProperty().get()!=null){
                    controller.setAverage_Grades(item.exerciseGradesProperty().get()*0.2 + item.midtermGradesProperty().get()*0.2 + item.finalGradesProperty().get()*0.6);
                }
                setText(null);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
