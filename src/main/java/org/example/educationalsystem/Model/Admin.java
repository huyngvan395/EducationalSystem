package org.example.educationalsystem.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    private final StringProperty name;
    private final StringProperty phone;
    private final StringProperty email;
    private final StringProperty password;

    public Admin(String name, String phone, String email, String password) {
        this.name = new SimpleStringProperty(this,"name",name);
        this.phone = new SimpleStringProperty(this,"phone",phone);
        this.email = new SimpleStringProperty(this,"email",email);
        this.password = new SimpleStringProperty(this,"password",password);
    }

    public void displayInfoTotal(){

    }
    public void addLecturer(){

    }
    public void addStudent(){

    }
    public void deleteLecturer(){

    }
    public void deleteStudent(){

    }
    public void search(){

    }
    public void searchLecturer(){

    }
    public void searchStudent(){

    }
}
