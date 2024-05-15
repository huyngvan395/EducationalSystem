package org.example.educationalsystem.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.educationalsystem.Model.ShowPass;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @FXML
    private PasswordField new_pass;
    @FXML
    private PasswordField submit_new_pass;
    @FXML
    private Button submit_button;
    @FXML
    private CheckBox show_pass;
    @FXML
    private TextField new_pass_shown;
    @FXML
    private TextField submit_new_pass_shown;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new_pass_shown.setVisible(false);
        submit_new_pass_shown.setVisible(false);
        show_pass.setOnAction(e-> showPass());
    }

    public void showPass(){
        ShowPass.showPass(new_pass_shown, submit_new_pass_shown, new_pass, submit_new_pass);
    }
}
