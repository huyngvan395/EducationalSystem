package org.example.educationalsystem.Controller.Lecturer;

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

public class LecturerAccountController implements Initializable {
    @FXML
    private CheckBox show_pass;
    @FXML
    private PasswordField new_pass;
    @FXML
    private TextField new_pass_shown;
    @FXML
    private PasswordField submit_new_pass;
    @FXML
    private TextField submit_new_pass_shown;
    @FXML
    private Button submit_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new_pass_shown.setVisible(false);
        submit_new_pass_shown.setVisible(false);
        show_pass.setOnAction(e->showPass());
        submit_button.setOnAction(e->changePass());
    }

    public void showPass(){
        ShowPass.showPass(new_pass_shown, submit_new_pass_shown, new_pass, submit_new_pass);
    }

    public void changePass(){
        if(new_pass.getText().equals(submit_new_pass.getText()) && new_pass_shown.getText().equals(submit_new_pass_shown.getText())){
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
            String email = Model.getInstance().getLecturer().emailProperty().get();
            String changepass= Security.enCode(new_pass.isVisible()?new_pass.getText():new_pass_shown.getText());
            Model.getInstance().getDataDriver().changePass(user,email,changepass);
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
