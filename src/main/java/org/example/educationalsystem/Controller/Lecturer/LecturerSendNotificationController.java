package org.example.educationalsystem.Controller.Lecturer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.SendMail;

import java.net.URL;
import java.util.ResourceBundle;


public class LecturerSendNotificationController implements Initializable {
    @FXML
    private AnchorPane Send_Notification_Pane;
    @FXML
    private ComboBox<String> choose_Class;
    @FXML
    private TextField notification_Title;
    @FXML
    private TextArea content_Notification;
    @FXML
    private Button send_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getClassName();
        send_btn.setOnAction(event -> {
            sendNotification();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Send Notification");
            alert.setHeaderText(null);
            alert.setContentText("Send Notification Successfully");
            alert.showAndWait();
        });
    }

    public void getClassName(){
        choose_Class.setItems(Model.getInstance().getDataDriver().getClassName(Model.getInstance().getLecturer().id_LecturerProperty().get()));
    }

    public void sendNotification(){
        String className = choose_Class.getValue();
        ObservableList<String> emails=Model.getInstance().getDataDriver().getListMail(Model.getInstance().getLecturer().id_LecturerProperty().get(), className);
        if(className!=null){
            if(!notification_Title.getText().isEmpty() && !content_Notification.getText().isEmpty()){
                SendMail.sendNotification(Model.getInstance().getLecturer().nameProperty().get(), emails, notification_Title.getText(), content_Notification.getText());
                for (String email : emails) {
                    String IDstudent=Model.getInstance().getDataDriver().getIDStudent(email);
                    if(IDstudent!=null){
                        Model.getInstance().getDataDriver().addNotification(Model.getInstance().getLecturer().id_LecturerProperty().get(),IDstudent,notification_Title.getText(),content_Notification.getText());
                    }
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter all the fields.");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a class.");
            alert.showAndWait();
        }
     };
}

