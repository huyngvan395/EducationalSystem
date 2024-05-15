package org.example.educationalsystem.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.AdminMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    private Button signout_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button edit_btn;
    @FXML
    private Button account_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_btn.setOnAction(e -> onHome());
        edit_btn.setOnAction(e -> onEdit());
        account_btn.setOnAction(e -> onAccount());
        signout_btn.setOnAction(e -> onSignout());
    }

    public void onHome(){
        Model.getInstance().getViewFactory().getAdminMenuItem().set(AdminMenuOptions.Home);
    }

    public void onEdit(){
        Model.getInstance().getViewFactory().getAdminMenuItem().set(AdminMenuOptions.Edit);
    }

    public void onAccount(){
        Model.getInstance().getViewFactory().getAdminMenuItem().set(AdminMenuOptions.Account);
    }

    public void onSignout(){
        Model.getInstance().getViewFactory().closeStage((Stage) signout_btn.getScene().getWindow());
        Model.getInstance().getViewFactory().showFormPage();
    }
}
