package org.example.educationalsystem.Controller.Lecturer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerProfileController implements Initializable {
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
    private TextField Degree;
    @FXML
    private Button updateLecturer_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialData();
        updateLecturer_btn.setOnAction(event -> updateInfo());
    }

    public void updateInfo(){
        Model.getInstance().getDataDriver().updateLecturer(ID_Lecturer.getText(), Name.getText(), Gender.getText(), birthDate.getValue(), Address.getText(),  Email.getText(), Phone.getText(), Degree.getText());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Update Successfully");
        alert.showAndWait();
    }
    public void initialData(){
        ID_Lecturer.setText(Model.getInstance().getLecturer().id_LecturerProperty().get());
        ID_Lecturer.setEditable(false);
        Name.setText(Model.getInstance().getLecturer().nameProperty().get());
        Gender.setText(Model.getInstance().getLecturer().genderProperty().get());
        Address.setText(Model.getInstance().getLecturer().addressProperty().get());
        birthDate.setValue(Model.getInstance().getLecturer().birthDateProperty().get());
        Phone.setText(Model.getInstance().getLecturer().phoneProperty().get());
        Email.setText(Model.getInstance().getLecturer().emailProperty().get());
        Degree.setText(Model.getInstance().getLecturer().degreeProperty().get());
    }
}
