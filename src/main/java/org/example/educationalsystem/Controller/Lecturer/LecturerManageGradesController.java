package org.example.educationalsystem.Controller.Lecturer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.example.educationalsystem.Model.Class;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.StudentMark;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerManageGradesController implements Initializable {
    public TableColumn<StudentMark, String> IDStudent;
    public TableColumn<StudentMark, String> NameStudent;
    public TableColumn<StudentMark, Double> ExGrades;
    public TableColumn<StudentMark, Double> MidGrades;
    public TableColumn<StudentMark, Double> FinalGrades;
    public TableColumn<StudentMark, Double> AveGrades;
    public TableView<StudentMark> tableGrades;
    @FXML
    private ComboBox<String> class_Name;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getGradesData();
        editTable();
    }

    public void getGradesData(){
        class_Name.setItems(Model.getInstance().getDataDriver().getClassName(Model.getInstance().getLecturer().id_LecturerProperty().get()));
        class_Name.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                IDStudent.setCellValueFactory(cellData-> cellData.getValue().studentIDProperty());
                NameStudent.setCellValueFactory(cellData-> cellData.getValue().studentNameProperty());
                ExGrades.setCellValueFactory(cellData->{
                    Double exGrades=cellData.getValue().ExGradesProperty().get();
                    return new SimpleObjectProperty<>(exGrades);
                });
                MidGrades.setCellValueFactory(cellData->{
                    Double midtermGrades=cellData.getValue().MidtermGradesProperty().get();
                    return new SimpleObjectProperty<>(midtermGrades);
                });
                FinalGrades.setCellValueFactory(cellData->{
                    Double finalGrades=cellData.getValue().FinalGradesProperty().get();
                    return new SimpleObjectProperty<>(finalGrades);
                });
                AveGrades.setCellValueFactory(cellData->{
                    Double exGrades=cellData.getValue().ExGradesProperty().get();
                    Double midtermGrades=cellData.getValue().MidtermGradesProperty().get();
                    Double finalGrades=cellData.getValue().FinalGradesProperty().get();
                    if(exGrades!=null && midtermGrades!=null && finalGrades!=null){
                        Double ave=exGrades*0.2+midtermGrades*0.2+finalGrades*0.6;
                        Double average=Double.valueOf(String.format("%.1f",ave));
                        return new SimpleObjectProperty<>(average);
                    }
                    return new SimpleObjectProperty<>(null);
                });
                tableGrades.setItems(Model.getInstance().getDataDriver().getStudentMarkListByClass(Model.getInstance().getLecturer().id_LecturerProperty().get(), newValue));
            }
        });
    }

    public void editTable(){
        tableGrades.setEditable(true);
        ExGrades.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        ExGrades.setOnEditCommit(event-> {
            StudentMark studentMark= event.getRowValue();
            studentMark.ExGradesProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateExGrades(studentMark.studentIDProperty().get(), class_Name.getValue(),studentMark.ExGradesProperty().get());
            getGradesData();
        });
        MidGrades.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        MidGrades.setOnEditCommit(event-> {
            StudentMark studentMark= event.getRowValue();
            studentMark.MidtermGradesProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateMidtermGrades(studentMark.studentIDProperty().get(), class_Name.getValue(), studentMark.MidtermGradesProperty().get());
            getGradesData();
        });
        FinalGrades.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        FinalGrades.setOnEditCommit(event-> {
            StudentMark studentMark= event.getRowValue();
            studentMark.FinalGradesProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateFinalGrades(studentMark.studentIDProperty().get(), class_Name.getValue(), studentMark.FinalGradesProperty().get());
            getGradesData();
        });
    }

}
