package org.example.educationalsystem.Controller.Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.ShowPass;
import org.example.educationalsystem.View.LoginOptions;
import org.example.educationalsystem.View.RoleUser;

import java.net.URL;
import java.util.ResourceBundle;

public class FormSignupController implements Initializable {
    @FXML
    private TextField confirmpass_shown;
    @FXML
    private TextField password_shown;
    @FXML
    private CheckBox showpass;
    @FXML
    private Button close_button;
    @FXML
    private ChoiceBox<RoleUser> role_Signup;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signupButton;
    @FXML
    private PasswordField confirmpass;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        close_button.setOnAction(event -> Model.getInstance().getViewFactory().closeStage((Stage) close_button.getScene().getWindow()));
        signupButton.setOnAction(event -> Submit());
        password_shown.setVisible(false);
        confirmpass_shown.setVisible(false);
    }

    public void Submit(){
        Model.getInstance().getViewFactory().getLoginOptions().set(LoginOptions.SubmitSignUp);
    }

    public void showPass(){
        ShowPass.showPass(password_shown, confirmpass_shown, password, confirmpass);
    }

}
