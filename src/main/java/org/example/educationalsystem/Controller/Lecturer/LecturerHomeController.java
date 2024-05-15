package org.example.educationalsystem.Controller.Lecturer;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.example.educationalsystem.Model.Class;
import org.example.educationalsystem.Model.Model;
import org.example.educationalsystem.View.ClassCellFactory;
import org.example.educationalsystem.View.LecturerButtonOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerHomeController implements Initializable {
    @FXML
    private ListView<Class> class_listview;
    @FXML
    private Button creat_class_btn;
    @FXML
    private Button refresh_btn;
    @FXML
    private Label total_Class;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh_btn.setOnAction(e-> onShowClassList());
        creat_class_btn.setOnAction(e-> creatClass());
        onShowClassList();
    }

    public void onShowClassList(){
        String ID=Model.getInstance().getLecturer().id_LecturerProperty().get();
        ObservableList<Class> classes= Model.getInstance().getDataDriver().getClassList(ID);
        class_listview.setItems(classes);
        class_listview.setCellFactory(e-> new ClassCellFactory());
        int count=0;
        for(Class c:classes){
            count++;
        }
        total_Class.setText(String.valueOf(count));
    }

    public void creatClass(){
        Model.getInstance().getViewFactory().getLecturerButtonOptions().set(LecturerButtonOptions.CreatClass);
    }
}
