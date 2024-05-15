package org.example.educationalsystem.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane card_lecturer;
    @FXML
    private AnchorPane card_student;
    @FXML
    private Label number_male;
    @FXML
    private Label number_female;
    @FXML
    private Label number_total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCountLecturer();
        card_lecturer.setOnMouseClicked(e-> getCountLecturer());
        card_student.setOnMouseClicked(e-> getCountStudent());
    }

    public void getCountLecturer(){
        int countMale= Model.getInstance().getDataDriver().countLecturer("Nam");
        int countFemale=Model.getInstance().getDataDriver().countLecturer("Nữ");
        number_male.setText(String.valueOf(countMale));
        number_female.setText(String.valueOf(countFemale));
        number_total.setText(String.valueOf(countFemale+countMale));
    }

    public void getCountStudent(){
        int countMale=Model.getInstance().getDataDriver().countStudent("Nam");
        int countFemale=Model.getInstance().getDataDriver().countStudent("Nữ");
        number_male.setText(String.valueOf(countMale));
        number_female.setText(String.valueOf(countFemale));
        number_total.setText(String.valueOf(countMale+countFemale));
    }
}
