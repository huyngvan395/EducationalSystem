package org.example.educationalsystem.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.AdminButtonOptions;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CreatStudentController implements Initializable {
    @FXML
    private Button left_btn;
    @FXML
    private TextField ID_Student;
    @FXML
    private TextField Name;
    @FXML
    private TextField Gender;
    @FXML
    private TextField Address;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField Email;
    @FXML
    private TextField Phone;
    @FXML
    private PasswordField Password;
    @FXML
    private Button creatStudent_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creatStudent_btn.setOnAction(event -> Model.getInstance().getDataDriver().creatStudent(ID_Student.getText(),Name.getText(),Gender.getText(), birthDate.getValue(), Address.getText(), Phone.getText(),Email.getText(),Password.getText()));
        left_btn.setOnAction(e-> {
            Model.getInstance().getViewFactory().getAdminButtonOptions().set(AdminButtonOptions.ReturnToEditStudent);
        }
        );
    }

}
