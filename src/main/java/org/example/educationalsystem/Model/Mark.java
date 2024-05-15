package org.example.educationalsystem.Model;

import javafx.beans.property.*;

public class Mark {
    private final StringProperty courseName;
    private final ObjectProperty<Double> exerciseGrades;
    private final ObjectProperty<Double> midtermGrades;
    private final ObjectProperty<Double> finalGrades;

    public Mark(String courseName, Double exerciseGrades, Double midtermGrades, Double finalGrades){
        this.courseName = new SimpleStringProperty(this,"CourseName",courseName);
        this.exerciseGrades = new SimpleObjectProperty<>(this,"exerciseGrades", exerciseGrades);
        this.midtermGrades = new SimpleObjectProperty<>(this,"midtermGrades", midtermGrades);
        this.finalGrades = new SimpleObjectProperty<>(this,"finalGrades", finalGrades);
    }

    public StringProperty courseNameProperty(){
        return courseName;
    }

    public ObjectProperty<Double> exerciseGradesProperty() {
        return exerciseGrades;
    }

    public ObjectProperty<Double> midtermGradesProperty() {
        return midtermGrades;
    }

    public ObjectProperty<Double> finalGradesProperty() {
        return finalGrades;
    }
}
