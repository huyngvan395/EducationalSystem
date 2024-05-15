package org.example.educationalsystem.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private BorderPane admin_page;
    @FXML
    private Button close_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        close_button.setOnAction(event -> Model.getInstance().getViewFactory().closeStage((Stage) admin_page.getScene().getWindow()));
        Model.getInstance().getViewFactory().getAdminMenuItem().addListener((observable,oldVal,newVal)->{
            switch (newVal){
                case Edit -> admin_page.setCenter(Model.getInstance().getViewFactory().getAdminEdit());
                case Account -> admin_page.setCenter(Model.getInstance().getViewFactory().getAdminAccount());
                default -> admin_page.setCenter(Model.getInstance().getViewFactory().getAdminHome());
            }
        });
        Model.getInstance().getViewFactory().getAdminButtonOptions().addListener((observable,oldVal,newVal)->{
            switch (newVal){
                case AddStudent -> admin_page.setCenter(Model.getInstance().getViewFactory().getCreatStudentForm());
                case AddLecturer -> admin_page.setCenter(Model.getInstance().getViewFactory().getCreatLecturerForm());
                case ReturnToEditStudent,ReturnToEditLecturer -> admin_page.setCenter(Model.getInstance().getViewFactory().getAdminEdit());
            }
        });
    }
}
