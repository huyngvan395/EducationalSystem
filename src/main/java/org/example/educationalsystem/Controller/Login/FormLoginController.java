package org.example.educationalsystem.Controller.Login;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.educationalsystem.Controller.Form.FormController;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.Security;
import org.example.educationalsystem.View.LoginOptions;
import org.example.educationalsystem.View.RoleUser;

import java.net.URL;
import java.util.ResourceBundle;

public class FormLoginController implements Initializable {
    @FXML
    private Button hiddenPass;
    @FXML
    private TextField pass_shown;
    @FXML
    private Hyperlink notAccount;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Button close_button;
    @FXML
    private ChoiceBox<RoleUser> role_Login;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink forgotPassword;
    @FXML
    private Button showPass;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role_Login.setItems(FXCollections.observableArrayList(RoleUser.Admin,RoleUser.Lecturer,RoleUser.Student));
        role_Login.setValue(Model.getInstance().getViewFactory().getLoginRole());
        role_Login.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginRole(role_Login.getValue()));
        loginButton.setOnAction(e-> onLogin());
        close_button.setOnAction(e-> Model.getInstance().getViewFactory().closeStage((Stage)loginPane.getScene().getWindow()));
        pass_shown.setVisible(false);
        hiddenPass.setVisible(false);
        showPass.setOnAction(e-> showPass());
        hiddenPass.setOnAction(e-> hiddenPass());
        forgotPassword.setOnAction(e-> forgetPass());
    }

    public void onLogin(){
        String passwordValue = pass.isVisible() ? pass.getText() : pass_shown.getText();
        if(Model.getInstance().getViewFactory().getLoginRole()==RoleUser.Admin){
            Model.getInstance().evaluateAdminInfo(username.getText(),Security.enCode(passwordValue));
            if(Model.getInstance().isCheckLoginAdmin()){
                Model.getInstance().getViewFactory().showAdminPage();
                Model.getInstance().getViewFactory().closeStage((Stage) loginPane.getScene().getWindow());
                Model.getInstance().setRole_Login(role_Login.getValue());
                showAlertSuccess();
            }
            else {
                showAlertFail();
            }
        }
        else if(Model.getInstance().getViewFactory().getLoginRole()==RoleUser.Lecturer){
            Model.getInstance().evaluateLecturerInfo(username.getText(),Security.enCode(passwordValue));
            if(Model.getInstance().isCheckLoginLecturer()){
                Model.getInstance().getViewFactory().showLecturerPage();
                Model.getInstance().getViewFactory().closeStage((Stage) loginPane.getScene().getWindow());
                showAlertSuccess();
            }
            else{
                showAlertFail();
            }
        }
        else if(Model.getInstance().getViewFactory().getLoginRole()==RoleUser.Student){
            Model.getInstance().evaluateStudentInfo(username.getText(), Security.enCode(passwordValue));
            if(Model.getInstance().isCheckLoginStudent()){
                Model.getInstance().getViewFactory().showStudentPage();
                Model.getInstance().getViewFactory().closeStage((Stage) loginPane.getScene().getWindow());
                showAlertSuccess();
            }
            else {
                showAlertFail();
            }
        }
    }

    public void showPass(){
            pass_shown.setText(pass.getText());
            pass_shown.setVisible(true);
            hiddenPass.setVisible(true);
            showPass.setVisible(false);
            pass.setVisible(false);
    }

    public void hiddenPass(){
        pass.setText(pass_shown.getText());
        pass_shown.setVisible(false);
        hiddenPass.setVisible(false);
        showPass.setVisible(true);
        pass.setVisible(true);
    }

    public void showAlertSuccess(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Success");
        alert.setHeaderText(null);
        alert.setContentText("Login Success");
        alert.showAndWait();
    }

    public void showAlertFail(){
        String passwordValue = pass.isVisible() ? pass.getText() : pass_shown.getText();
        if(username.getText().isEmpty() && passwordValue.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username and password");
            alert.showAndWait();
        }
        else if(username.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username");
            alert.showAndWait();
        }
        else if(passwordValue.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a password");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username or Password is incorrect");
            alert.showAndWait();
        }
    }

    public void forgetPass(){
        Model.getInstance().setRole_Login(role_Login.getValue());
        Model.getInstance().getViewFactory().getLoginOptions().set(LoginOptions.ForgetPass);
    }
//    public void notAccount(){
//        Model.getInstance().getViewFactory().getLoginOptions().set(LoginOptions.NotAccount);
//    }
}
