package org.example.educationalsystem.Controller.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.Notification;
import org.example.educationalsystem.View.NotificationCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentNotificationController implements Initializable {
    @FXML
    private ListView<Notification> list_notification;
    @FXML
    private Label no_notification;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItem();
    }

    public void deleteNotification(Notification notification){
        Model.getInstance().getDataDriver().deleteNotification(notification.getIdNotification().get());
        list_notification.getItems().remove(notification);
        setItem();
    }

    public void setItem() {
        String IDStudent=Model.getInstance().getStudent().id_StudentProperty().get();
        ObservableList<Notification> notifications=Model.getInstance().getDataDriver().getNotificationList(IDStudent);
        list_notification.setItems(notifications);
        list_notification.setCellFactory(ListView-> new NotificationCellFactory(this));
        if(notifications.isEmpty()){
            no_notification.setVisible(true);
        }
        else {
            no_notification.setVisible(false);
        }
    }
}
