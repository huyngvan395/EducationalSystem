package org.example.educationalsystem.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private  final StringProperty classID;
    private final StringProperty className;
    private final StringProperty lecturerName;

    public Course(String classID,String className, String lecturerName) {
        this.classID = new SimpleStringProperty(classID);
        this.className = new SimpleStringProperty(this, "className",className);
        this.lecturerName = new SimpleStringProperty(this, "lecturerName",lecturerName);
    }

    public StringProperty classIDProperty() {
        return classID;
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public StringProperty lecturerNameProperty(){
        return lecturerName;
    }
}
