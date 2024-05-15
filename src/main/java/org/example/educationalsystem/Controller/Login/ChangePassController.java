package org.example.educationalsystem.Controller.Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.Security;
import org.example.educationalsystem.Model.ShowPass;
import org.example.educationalsystem.View.LoginOptions;
import org.example.educationalsystem.View.RoleUser;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassController implements Initializable {
    @FXML
    private Button submit_btn;
    @FXML
    private TextField pass_shown;
    @FXML
    private PasswordField pass_submit;
    @FXML
    private TextField pass_submit_shown;
    @FXML
    private CheckBox show_pass;
    @FXML
    private PasswordField pass;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pass_shown.setVisible(false);
        pass_submit_shown.setVisible(false);
        show_pass.setOnAction(e-> showPass());
        submit_btn.setOnAction(e-> Submit());
    }

    public void showPass(){
        ShowPass.showPass(pass_shown, pass_submit_shown, pass, pass_submit);
    }

    public void Submit(){
        if(pass.getText().equals(pass_submit.getText()) && pass_shown.getText().equals(pass_submit_shown.getText())){
            String user;
            if(Model.getInstance().getRole_Login()== RoleUser.Admin){
                user="admin";
            }
            else if(Model.getInstance().getRole_Login()==RoleUser.Lecturer){
                user="lecturer";
            }
            else {
                user="student";
            }
            String email = Model.model.getUserEmail();
            String changepass= Security.enCode(pass.isVisible()?pass.getText():pass_shown.getText());
            Model.getInstance().getDataDriver().changePass(user,email,changepass);
            Model.getInstance().getViewFactory().getLoginOptions().set(LoginOptions.SubmitPasswordChange);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Your password has been changed");
            alert.showAndWait();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Confirmation password is incorrect");
            alert.showAndWait();
        }
    }
}
