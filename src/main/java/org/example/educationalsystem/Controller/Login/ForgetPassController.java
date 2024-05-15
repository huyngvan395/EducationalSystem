package org.example.educationalsystem.Controller.Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.SendMail;
import org.example.educationalsystem.View.LoginOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPassController implements Initializable {
    @FXML
    private Button left_btn;
    @FXML
    private Button send_code_btn;
    @FXML
    private TextField email;
    @FXML
    private Button submit_btn;
    @FXML
    private TextField c1;
    @FXML
    private TextField c2;
    @FXML
    private TextField c3;
    @FXML
    private TextField c4;
    @FXML
    private TextField c5;
    @FXML
    private TextField c6;

    private String verificationCode;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        left_btn.setOnAction(e-> Model.getInstance().getViewFactory().getLoginOptions().set(LoginOptions.ReturnToLogin));
        send_code_btn.setOnAction(e-> sendVerificationCode());
        setupTextField(c1,c2);
        setupTextField(c2,c3);
        setupTextField(c3,c4);
        setupTextField(c4,c5);
        setupTextField(c5,c6);
        submit_btn.setOnAction(e-> checkVerificationCode());
    }

    public void sendVerificationCode(){
        String userEmail = email.getText();
        if(!userEmail.isEmpty()){
            verificationCode= SendMail.randomCode();
            SendMail.sendCode(userEmail,verificationCode);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Verification Code");
            alert.setHeaderText(null);
            alert.setContentText("Verification Code Sent");
            alert.showAndWait();
        }
        else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your email to send code!");
            alert.showAndWait();
        }
    }

    public void checkVerificationCode(){
        String code=c1.getText()+c2.getText()+c3.getText()+c4.getText()+c5.getText()+c6.getText();
        if(verificationCode.equals(code)){
            Model.getInstance().setUserEmail(email.getText());
            Model.getInstance().getViewFactory().getLoginOptions().set(LoginOptions.SubmitVerification);
        }
        else {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect code!");
            alert.showAndWait();
        }
    }


    public void setupTextField(TextField current, TextField next){
        current.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if(current.getText().length()==1 ){
                keyEvent.consume();
            }
        });
        current.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()==1 && next!=null){
                next.requestFocus();
            }
        });
    }
}
