package org.example.educationalsystem.Controller.Lecturer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.LecturerButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatClassController implements Initializable {
    @FXML
    private TextField Id_class;
    @FXML
    private TextField class_Name;
    @FXML
    private Button creatClass;
    @FXML
    private Button left_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        left_btn.setOnAction(event -> returnToHome());
        creatClass.setOnAction(event -> createClass());
    }

    public void createClass() {
        String IDlecturer=Model.getInstance().getLecturer().id_LecturerProperty().get();
        Model.getInstance().getDataDriver().creatClass(Id_class.getText(), class_Name.getText(), IDlecturer);
        Model.getInstance().getViewFactory().getLecturerButtonOptions().set(LecturerButtonOptions.ReturnToHome);
    }

    public void returnToHome(){
        Model.getInstance().getViewFactory().getLecturerButtonOptions().set(LecturerButtonOptions.ReturnToHome);
    }
}
