package org.example.educationalsystem.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Notification {
    private final IntegerProperty idNotification;
    private final StringProperty lecturerName;
    private final StringProperty subject;
    private final StringProperty message;
    public Notification(int idNo,String lecturerName, String subject, String message) {
        this.idNotification = new SimpleIntegerProperty(this,"ID_notification",idNo);
        this.lecturerName = new SimpleStringProperty(this,"lecturerName",lecturerName);
        this.subject = new SimpleStringProperty(this,"subject",subject);
        this.message = new SimpleStringProperty(this,"message",message);
    }

    public IntegerProperty getIdNotification() {
        return idNotification;
    }

    public StringProperty lecturerNameProperty() {
        return lecturerName;
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty messageProperty() {
        return message;
    }
}
