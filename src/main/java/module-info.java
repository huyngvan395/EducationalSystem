module org.example.educationalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.fontawesome;
    requires java.sql;
    requires jakarta.mail;

    opens org.example.educationalsystem to javafx.fxml;
    opens org.example.educationalsystem.Controller.Login to javafx.fxml;
    opens org.example.educationalsystem.Controller.Admin to javafx.fxml;
    opens org.example.educationalsystem.Controller.Lecturer to javafx.fxml;
    opens org.example.educationalsystem.Controller.Student to javafx.fxml;
    opens org.example.educationalsystem.Controller.Form to javafx.fxml;
    exports org.example.educationalsystem;
    exports org.example.educationalsystem.Model;
    exports org.example.educationalsystem.View;
    exports org.example.educationalsystem.Controller.Admin;
    exports org.example.educationalsystem.Controller.Lecturer;
    exports org.example.educationalsystem.Controller.Student;
    exports org.example.educationalsystem.Controller.Login;
    exports org.example.educationalsystem.Controller.Form;
}