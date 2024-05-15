package org.example.educationalsystem.Controller.Lecturer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.Student;
import org.example.educationalsystem.View.LecturerButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerListStudentController implements Initializable {
    @FXML
    private Button left_btn;
    @FXML
    private Label name_class;
    @FXML
    private TableView<Student> table_StudentCLass;
    @FXML
    private TableColumn<Student, String> IDStudent;
    @FXML
    private TableColumn<Student, String> NameStudent;

    private String IDClass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        left_btn.setOnAction(e->Model.getInstance().getViewFactory().getLecturerButtonOptions().set(LecturerButtonOptions.ReturnToHome));
    }

    public void getStudentListInClass(){
        IDStudent.setCellValueFactory(cellData-> cellData.getValue().id_StudentProperty());
        NameStudent.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        table_StudentCLass.setItems(Model.getInstance().getDataDriver().getStudentInClass(IDClass));
    }

    public void setName_class(String nameclass){
        name_class.setText(nameclass);
    }

    public void setIDClass(String IDclass){
        this.IDClass = IDclass;
    }

    public String getIDClass(){
        return IDClass;
    }

}
