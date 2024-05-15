package org.example.educationalsystem.Controller.Lecturer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.educationalsystem.Model.Class;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.LecturerButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerClassCellController implements Initializable {
    @FXML
    private Label id_class;
    @FXML
    private Label name_class;
    @FXML
    private Button view_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view_btn.setOnAction(e -> ViewStudentList());
    }

    public void ViewStudentList(){
        Model.getInstance().getViewFactory().getLecturerButtonOptions().set(LecturerButtonOptions.ViewListClass);
        LecturerListStudentController lecturerListStudentController = Model.getInstance().getViewFactory().getControllerListStudent();
        lecturerListStudentController.setIDClass(id_class.getText());
        lecturerListStudentController.setName_class(name_class.getText());
        lecturerListStudentController.getStudentListInClass();
    }

    public void setId_class(String id) {
        this.id_class.setText(id);
    }

    public void setName_class(String name) {
        this.name_class.setText(name);
    }

}
