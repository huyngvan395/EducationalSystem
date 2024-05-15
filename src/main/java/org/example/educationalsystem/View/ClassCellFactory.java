package org.example.educationalsystem.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.example.educationalsystem.Controller.Lecturer.LecturerClassCellController;
import org.example.educationalsystem.Controller.Lecturer.LecturerListStudentController;
import org.example.educationalsystem.Model.Class;

public class ClassCellFactory extends ListCell<Class> {

    @Override
    protected void updateItem(Class item, boolean empty) {
        super.updateItem(item, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Lecturer/classcell.fxml"));
            try{
                setGraphic(fxmlLoader.load());
                LecturerClassCellController controller =fxmlLoader.getController();
                controller.setId_class(item.IDClassProperty().get());
                controller.setName_class(item.nameClassProperty().get());
                setText(null);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
