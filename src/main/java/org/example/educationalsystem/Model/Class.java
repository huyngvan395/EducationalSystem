package org.example.educationalsystem.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Class {
    private final StringProperty IDClass;
    private final StringProperty nameClass;

    public Class(String IDClass, String nameClass) {
        this.IDClass = new SimpleStringProperty(this,"IDClass",IDClass);
        this.nameClass = new SimpleStringProperty(this,"nameClass",nameClass);
    }

    public StringProperty nameClassProperty() {
        return nameClass;
    }

    public StringProperty IDClassProperty() {
        return IDClass;
    }
}
