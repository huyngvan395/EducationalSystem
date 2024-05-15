package org.example.educationalsystem.Controller.Lecturer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerController implements Initializable {
    @FXML
    private BorderPane lecturer_page;
    @FXML
    private Button close_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        close_button.setOnAction(event -> Model.getInstance().getViewFactory().closeStage((Stage) close_button.getScene().getWindow()));
        Model.getInstance().getViewFactory().getLecturerMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case ManageGrades -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getLecturerManageGrades());
                case Account -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getLecturerAccount());
                case Profile -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getLecturerProfile());
                case SendNotification -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getLecturerSendNotification());
                default -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getLecturerHome());
            }
        });
        Model.getInstance().getViewFactory().getLecturerButtonOptions().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case CreatClass -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getCreateCLass());
                case ReturnToHome -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getLecturerHome());
                case ViewListClass -> lecturer_page.setCenter(Model.getInstance().getViewFactory().getListStudentInClass());
            }
        });
    }
}
