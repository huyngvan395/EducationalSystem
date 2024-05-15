package org.example.educationalsystem.Model;

import javafx.beans.property.*;

public class StudentMark {
    private final StringProperty studentID;
    private final StringProperty studentName;
    private final ObjectProperty<Double> ExGrades;
    private final ObjectProperty<Double> MidtermGrades;
    private final ObjectProperty<Double> FinalGrades;

    public StudentMark(String studentID, String studentName, Double exGrades, Double midtermGrades, Double finalGrades) {
        this.studentID = new SimpleStringProperty(this,"Student ID", studentID);
        this.studentName = new SimpleStringProperty(this, "Student Name",studentName);
        this.ExGrades = new SimpleObjectProperty<>(this, "Ex Grades",exGrades);
        this.MidtermGrades= new SimpleObjectProperty<>(this, "Midterm Grades",midtermGrades);
        this.FinalGrades = new SimpleObjectProperty<>(this, "Final Grades",finalGrades);
    }

    public StringProperty studentIDProperty() {
        return studentID;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public ObjectProperty<Double> ExGradesProperty() {
        return ExGrades;
    }

    public ObjectProperty<Double> MidtermGradesProperty() {
        return MidtermGrades;
    }

    public ObjectProperty<Double> FinalGradesProperty() {
        return FinalGrades;
    }

}
