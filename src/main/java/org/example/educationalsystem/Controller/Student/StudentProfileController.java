package org.example.educationalsystem.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {
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
    private TextField Phone;
    @FXML
    private TextField Email;
    @FXML
    private Button updateStudent_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialData();
        updateStudent_btn.setOnAction(event -> updateInfo());
    }

    public void updateInfo(){
        Model.getInstance().getDataDriver().updateStudent(ID_Student.getText(), Name.getText(), Gender.getText(), birthDate.getValue(),Address.getText(),Email.getText(), Phone.getText());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Student");
        alert.setHeaderText(null);
        alert.setContentText("Update Successfully");
        alert.showAndWait();
    }

    public void initialData(){
        ID_Student.setText(Model.getInstance().getStudent().id_StudentProperty().get());
        ID_Student.setEditable(false);
        Name.setText(Model.getInstance().getStudent().nameProperty().get());
        Gender.setText(Model.getInstance().getStudent().genderProperty().get());
        Address.setText(Model.getInstance().getStudent().addressProperty().get());
        birthDate.setValue(Model.getInstance().getStudent().birthDateProperty().get());
        Phone.setText(Model.getInstance().getStudent().phoneProperty().get());
        Email.setText(Model.getInstance().getStudent().emailProperty().get());
    }
}
