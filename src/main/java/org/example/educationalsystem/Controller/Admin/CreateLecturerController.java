package org.example.educationalsystem.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.AdminButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateLecturerController implements Initializable {
    @FXML
    private TextField ID_Lecturer;
    @FXML
    private TextField Name;
    @FXML
    private TextField Gender;
    @FXML
    private TextField Address;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField Phone;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Degree;
    @FXML
    private Button creatLecturer_btn;
    @FXML
    private Button left_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creatLecturer_btn.setOnAction(e->{
            Model.getInstance().getDataDriver().creatLecturer(ID_Lecturer.getText(),Name.getText(),Gender.getText(),birthDate.getValue(),Address.getText(),Email.getText(),Phone.getText(),Password.getText(),Degree.getText());
        });
        left_btn.setOnAction(e->{
            Model.getInstance().getViewFactory().getAdminButtonOptions().set(AdminButtonOptions.ReturnToEditLecturer);
        });
    }
}
