package org.example.educationalsystem.Controller.Student;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.example.educationalsystem.Model.Mark;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.ViewGradesCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentViewGradesController implements Initializable {
    @FXML
    private ListView<Mark> listGrades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String IDStudent=Model.getInstance().getStudent().id_StudentProperty().get();
        ObservableList<Mark> marks= Model.getInstance().getDataDriver().getMarkList(IDStudent);
        listGrades.setCellFactory(ListView-> new ViewGradesCellFactory());
        listGrades.setItems(marks);
    }
}
