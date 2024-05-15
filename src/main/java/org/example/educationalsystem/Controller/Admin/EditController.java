package org.example.educationalsystem.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.LocalDateStringConverter;
import org.example.educationalsystem.Model.Lecturer;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.Model.Student;
import org.example.educationalsystem.View.AdminButtonOptions;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditController implements Initializable {
    @FXML
    private TableColumn<Lecturer, String> IDLecturer;
    @FXML
    private TableColumn<Lecturer, String> NameLecturer;
    @FXML
    private TableColumn<Lecturer, String> GenderLecturer;
    @FXML
    private TableColumn<Lecturer, LocalDate> dateBirthLecturer;
    @FXML
    private TableColumn<Lecturer, String> AddressLecturer;
    @FXML
    private TableColumn<Lecturer, String> EmailLecturer;
    @FXML
    private TableColumn<Lecturer, String> PhoneLecturer;
    @FXML
    private TableColumn<Lecturer, String> DegreeLecturer;
    @FXML
    private TableColumn<Student, String> IDStudent;
    @FXML
    private TableColumn<Student, String> NameStudent;
    @FXML
    private TableColumn<Student, String> GenderStudent;
    @FXML
    private TableColumn<Student, LocalDate> dateBirthStudent;
    @FXML
    private TableColumn<Student, String> AddressStudent;
    @FXML
    private TableColumn<Student, String> EmailStudent;
    @FXML
    private TableColumn<Student, String> PhoneStudent;
    @FXML
    private Button search_btn;
    @FXML
    private Button add_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private AnchorPane card_lecturer;
    @FXML
    private AnchorPane card_student;
    @FXML
    private TableView<Lecturer> table_Lecturer;
    @FXML
    private TableView<Student> table_Student;
    @FXML
    private TextField searchText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchText.textProperty().addListener((observable, oldvalue, newvalue)->{
            if(newvalue.isEmpty()){
                if(table_Lecturer.isVisible()){
                    getTableLectureData();
                }
                else{
                    getTableStudentData();
                }
            }
        });
        add_btn.setText("Add Lecturer");
        table_Student.setVisible(false);
        card_lecturer.setOnMouseClicked(e -> getTableLecturer());
        card_student.setOnMouseClicked(e -> getTableStudent());
        search_btn.setOnAction(e->searchAction());
        add_btn.setOnAction(e -> manage());
        delete_btn.setOnAction(e->deleteAction());
        Model.getInstance().getViewFactory().getAdminButtonOptions().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case ReturnToEditStudent -> {
                    table_Lecturer.setVisible(false);
                    table_Student.setVisible(true);
                }
                case ReturnToEditLecturer -> {
                    table_Lecturer.setVisible(true);
                    table_Student.setVisible(false);
                }
            }
        });
        getTableLectureData();
        getTableStudentData();
        if(table_Lecturer.isVisible()){
            editTableLecturer();
        }
        else{
            editTableStudent();
        }
    }


    public void manage() {
        if (table_Lecturer.isVisible()) {
            Model.getInstance().getViewFactory().getAdminButtonOptions().set(AdminButtonOptions.AddLecturer);
        } else if (table_Student.isVisible()) {
            Model.getInstance().getViewFactory().getAdminButtonOptions().set(AdminButtonOptions.AddStudent);
        }
    }

    public void searchAction() {
        if(searchText.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter name to search!");
            alert.showAndWait();
        }
        else{
            String wordSearch =searchText.getText().toLowerCase() ;
            if (table_Lecturer.isVisible()) {
                ObservableList<Lecturer> lecturersSearchList =
                        Model.getInstance().getDataDriver().getLecturerList().stream().filter(
                                        lecturer -> lecturer.nameProperty().get().toLowerCase().contains(wordSearch))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                table_Lecturer.setItems(lecturersSearchList);
            }
            else if(table_Student.isVisible()){
                ObservableList<Student> studentsSearchList =
                        Model.getInstance().getDataDriver().getStudentList().stream().filter(
                                        lecturer -> lecturer.nameProperty().get().toLowerCase().contains(wordSearch))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                table_Student.setItems(studentsSearchList);
            }
        }
}

public void deleteAction(){
        if(table_Lecturer.isVisible()){
            Lecturer lecturerSelected=table_Lecturer.getSelectionModel().getSelectedItem();
            if(lecturerSelected!=null){
                String IDLecturer=lecturerSelected.id_LecturerProperty().get();
                Model.getInstance().getDataDriver().deleteLecturer(IDLecturer);
                ObservableList<Lecturer> lecturers=Model.getInstance().getDataDriver().getLecturerList();
                table_Lecturer.setItems(lecturers);
            }
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a lecturer to delete!");
            }
        }
        else if(table_Student.isVisible()){
            Student studentSelected=table_Student.getSelectionModel().getSelectedItem();
            if(studentSelected!=null){
                String IDStudent=studentSelected.id_StudentProperty().get();
                Model.getInstance().getDataDriver().deleteStudent(IDStudent);
                ObservableList<Student> students=Model.getInstance().getDataDriver().getStudentList();
                table_Student.setItems(students);
            }
            else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a student to delete!");
            }
        }
}

    public void getTableLecturer(){
        table_Lecturer.setVisible(true);
        table_Student.setVisible(false);
        add_btn.setText("Add Lecturer");
    }

    public void getTableStudent(){
        table_Student.setVisible(true);
        table_Lecturer.setVisible(false);
        add_btn.setText("Add Student");
    }

    public void getTableLectureData(){
        IDLecturer.setCellValueFactory(cellData-> cellData.getValue().id_LecturerProperty());
        NameLecturer.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        GenderLecturer.setCellValueFactory(cellData-> cellData.getValue().genderProperty());
        dateBirthLecturer.setCellValueFactory(cellData->cellData.getValue().birthDateProperty());
        AddressLecturer.setCellValueFactory(cellData-> cellData.getValue().addressProperty());
        EmailLecturer.setCellValueFactory(cellData-> cellData.getValue().emailProperty());
        PhoneLecturer.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        DegreeLecturer.setCellValueFactory(cellData-> cellData.getValue().degreeProperty());
        table_Lecturer.setItems(Model.getInstance().getDataDriver().getLecturerList());
    }

    public void getTableStudentData(){
        IDStudent.setCellValueFactory(cellData-> cellData.getValue().id_StudentProperty());
        NameStudent.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        GenderStudent.setCellValueFactory(cellData-> cellData.getValue().genderProperty());
        dateBirthStudent.setCellValueFactory(cellData-> cellData.getValue().birthDateProperty());
        AddressStudent.setCellValueFactory(cellData-> cellData.getValue().addressProperty());
        EmailStudent.setCellValueFactory(cellData-> cellData.getValue().emailProperty());
        PhoneStudent.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        table_Student.setItems(Model.getInstance().getDataDriver().getStudentList());
    }

    public void editTableLecturer(){
        table_Lecturer.setEditable(true);
        NameLecturer.setCellFactory(TextFieldTableCell.forTableColumn());
        NameLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.nameProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateNameLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
        GenderLecturer.setCellFactory(TextFieldTableCell.forTableColumn());
        GenderLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.genderProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateGenderLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
        dateBirthLecturer.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        dateBirthLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.birthDateProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateBirthDateLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
        AddressLecturer.setCellFactory(TextFieldTableCell.forTableColumn());
        AddressLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.addressProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateAddressLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
        EmailLecturer.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.emailProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateEmailLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
        PhoneLecturer.setCellFactory(TextFieldTableCell.forTableColumn());
        PhoneLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.phoneProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updatePhoneLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
        DegreeLecturer.setCellFactory(TextFieldTableCell.forTableColumn());
        DegreeLecturer.setOnEditCommit(event->{
            Lecturer lecturer=event.getRowValue();
            lecturer.degreeProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateDegreeLecturer(lecturer.id_LecturerProperty().get(), event.getNewValue());
        });
    }

    public void editTableStudent(){
        table_Student.setEditable(true);
        NameStudent.setCellFactory(TextFieldTableCell.forTableColumn());
        NameStudent.setOnEditCommit(event->{
            Student student=event.getRowValue();
            student.nameProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateNameStudent(student.id_StudentProperty().get(), event.getNewValue());
        });
        GenderStudent.setCellFactory(TextFieldTableCell.forTableColumn());
        GenderStudent.setOnEditCommit(event->{
            Student student=event.getRowValue();
            student.genderProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateGenderStudent(student.id_StudentProperty().get(), event.getNewValue());
        });
        dateBirthStudent.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        dateBirthStudent.setOnEditCommit(event->{
            Student student=event.getRowValue();
            student.birthDateProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateBirthDateStudent(student.id_StudentProperty().get(), event.getNewValue());
        });
        AddressStudent.setCellFactory(TextFieldTableCell.forTableColumn());
        AddressStudent.setOnEditCommit(event->{
            Student student=event.getRowValue();
            student.addressProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateAddressStudent(student.id_StudentProperty().get(), event.getNewValue());
        });
        EmailStudent.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailStudent.setOnEditCommit(event->{
            Student student=event.getRowValue();
            student.emailProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updateEmailStudent(student.id_StudentProperty().get(), event.getNewValue());
        });
        PhoneStudent.setCellFactory(TextFieldTableCell.forTableColumn());
        PhoneStudent.setOnEditCommit(event->{
            Student student=event.getRowValue();
            student.phoneProperty().set(event.getNewValue());
            Model.getInstance().getDataDriver().updatePhoneStudent(student.id_StudentProperty().get(), event.getNewValue());
        });
    }
}
