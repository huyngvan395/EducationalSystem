package org.example.educationalsystem.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Locale;

public class Lecturer {
    private StringProperty id_Lecturer;
    private StringProperty name;
    private StringProperty gender;
    private ObjectProperty<LocalDate> birthDate;
    private StringProperty address;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty degree;


    public Lecturer(String id_Lecturer,String name, String gender, LocalDate birthDate, String address,  String email,String phone, String degree){
        this.name = new SimpleStringProperty(this,"name",name);
        this.gender = new SimpleStringProperty(this,"gender",gender);
        this.birthDate = new SimpleObjectProperty<>(this,"birthDate",birthDate);
        this.address = new SimpleStringProperty(this,"address",address);
        this.phone = new SimpleStringProperty(this,"phone",phone);
        this.email = new SimpleStringProperty(this,"email",email);
        this.degree = new SimpleStringProperty(this,"degree",degree);
        this.id_Lecturer=new SimpleStringProperty(this,"id_Lecturer",id_Lecturer);
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

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty degreeProperty() {
        return degree;
    }

    public StringProperty id_LecturerProperty() {
        return id_Lecturer;
    }
}
