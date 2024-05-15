package org.example.educationalsystem.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.example.educationalsystem.Controller.Student.StudentNotificationCellController;
import org.example.educationalsystem.Controller.Student.StudentNotificationController;
import org.example.educationalsystem.Model.Notification;

import java.io.IOException;
import java.util.List;

public class NotificationCellFactory extends ListCell<Notification> {
    private final StudentNotificationController studentNotificationController;

    public NotificationCellFactory(StudentNotificationController controller) {
        this.studentNotificationController = controller;
    }
    @Override
    protected void updateItem(Notification item, boolean empty){
        super.updateItem(item, empty);
        if(empty || item == null){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Student/notificationcell.fxml"));
             try{
                 setGraphic(fxmlLoader.load());
                 StudentNotificationCellController controller=fxmlLoader.getController();
                 controller.setStudentNotificationController(this.studentNotificationController);
                 controller.setNotification(item);
                 fxmlLoader.setController(controller);
                 setText(null);
             }
             catch(IOException e){
                 e.printStackTrace();
             }
        }
    }

}
