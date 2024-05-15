package org.example.educationalsystem.Controller.Form;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.educationalsystem.Controller.Login.FormLoginController;
import org.example.educationalsystem.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {
    @FXML
    private BorderPane form_Page;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getLoginOptions().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case ForgetPass -> form_Page.setCenter(Model.getInstance().getViewFactory().getVertification());
                case NotAccount -> form_Page.setCenter(Model.getInstance().getViewFactory().getSignup());
                case SubmitVerification -> form_Page.setCenter(Model.getInstance().getViewFactory().getLoginChangepass());
                case SubmitPasswordChange,ReturnToLogin -> form_Page.setCenter(Model.getInstance().getViewFactory().getLogin());
            }
        });
    }
}
