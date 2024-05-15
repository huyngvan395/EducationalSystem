package org.example.educationalsystem.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Student {
    private StringProperty id_Student;
    private StringProperty name;
    private StringProperty gender;
    private ObjectProperty<LocalDate> birthDate;
    private StringProperty address;
    private StringProperty email;
    private StringProperty phone;

    public Student( String idStudent, String name, String gender, LocalDate birthDate, String address, String email, String phone) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.gender = new SimpleStringProperty(this, "gender", gender);
        this.birthDate = new SimpleObjectProperty<>(this, "birthDate", birthDate);
        this.address = new SimpleStringProperty(this, "address", address);
        this.email = new SimpleStringProperty(this, "email", email);
        this.id_Student = new SimpleStringProperty(this, "id_Student", idStudent);
        this.phone = new SimpleStringProperty(this, "phone", phone);
    }


    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty id_StudentProperty() {
        return id_Student;
    }

    public StringProperty phoneProperty() {
        return phone;
    }
}
