package org.example.educationalsystem.Controller.Lecturer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.LecturerMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerMenuController implements Initializable {
    @FXML
    private Button signout_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button manage_grade_btn;
    @FXML
    private Button profile_btn;
    @FXML
    private Button account_btn;
    @FXML
    private Button notification_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_btn.setOnAction(e -> onHome());
        manage_grade_btn.setOnAction(e->onManageGrades());
        profile_btn.setOnAction(e->onProfile());
        account_btn.setOnAction(e->onAccount());
        notification_btn.setOnAction(e->onSendNotification());
        signout_btn.setOnAction(e->onSignout());
    }

    public void onHome(){
        Model.getInstance().getViewFactory().getLecturerMenuItem().set(LecturerMenuOptions.Home);
    }

    public void onManageGrades(){
        Model.getInstance().getViewFactory().getLecturerMenuItem().set(LecturerMenuOptions.ManageGrades);
    }

    public void onAccount(){
        Model.getInstance().getViewFactory().getLecturerMenuItem().set(LecturerMenuOptions.Account);
    }

    public void onProfile(){
        Model.getInstance().getViewFactory().getLecturerMenuItem().set(LecturerMenuOptions.Profile);
    }

    public void onSendNotification(){
        Model.getInstance().getViewFactory().getLecturerMenuItem().set(LecturerMenuOptions.SendNotification);
    }

    public void onSignout(){
        Model.getInstance().setCheckLoginLecturer(false);
        Model.getInstance().getViewFactory().closeStage((Stage)signout_btn.getScene().getWindow());
        Model.getInstance().getViewFactory().showFormPage();
    }
}
