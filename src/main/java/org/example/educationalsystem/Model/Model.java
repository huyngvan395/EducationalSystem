package org.example.educationalsystem.Model;

import javafx.scene.control.Alert;
import org.example.educationalsystem.View.RoleUser;
import org.example.educationalsystem.View.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class  Model {
    public static Model model;
    private final ViewFactory viewFactory;
    private final DataDriver dataDriver;
    private RoleUser role_Login;
    private final Admin admin;
    private Lecturer lecturer;
    private Student student;
    private final Mark mark;
    private boolean checkLoginAdmin;
    private boolean checkLoginLecturer;
    private boolean checkLoginStudent;
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private Model() {
        this.dataDriver =new DataDriver();
        this.viewFactory =new ViewFactory();
        this.admin=new Admin("", "","","");
        this.lecturer=new Lecturer("","","",null,"","","","");
        this.student=new Student("","","",null,"","","");
        this.mark=new Mark("",0.0,0.0,0.0);
        this.checkLoginAdmin=false;
        this.checkLoginLecturer=false;
        this.checkLoginStudent=false;
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DataDriver getDataDriver() {
        return dataDriver;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

//    public void setLecturer(){
//        this.lecturer.id_LecturerProperty().set("");
//        this.lecturer.nameProperty().set("");
//        this.lecturer.genderProperty().set("");
//        this.lecturer.birthDateProperty().set(null);
//        this.lecturer.addressProperty().set("");
//        this.lecturer.emailProperty().set("");
//        this.lecturer.phoneProperty().set("");
//        this.lecturer.degreeProperty().set("");
//    }

    public Student getStudent() {
        return student;
    }

    public boolean isCheckLoginAdmin() {
        return checkLoginAdmin;
    }

    public void setCheckLoginAdmin(boolean checkLoginAdmin) {
        this.checkLoginAdmin = checkLoginAdmin;
    }

    public boolean isCheckLoginLecturer() {
        return checkLoginLecturer;
    }

    public void setCheckLoginLecturer(boolean checkLoginLecturer) {
        this.checkLoginLecturer = checkLoginLecturer;
    }

    public boolean isCheckLoginStudent() {
        return checkLoginStudent;
    }

    public void setCheckLoginStudent(boolean checkLoginStudent) {
        this.checkLoginStudent = checkLoginStudent;
    }

    public void evaluateAdminInfo(String username, String password){
        ResultSet rs;
        rs= dataDriver.getAdminData(username, password);
        try {
            if(rs.isBeforeFirst()){
                this.checkLoginAdmin=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void evaluateLecturerInfo(String username, String password){
        ResultSet rs;
        rs= dataDriver.getLecturerData(username,password);
        try{
            if(rs.next()){
                this.lecturer.id_LecturerProperty().setValue(rs.getString("Id_Lecturer"));
                this.lecturer.nameProperty().setValue(rs.getString("Name"));
                this.lecturer.genderProperty().setValue(rs.getString("Gender"));
                this.lecturer.birthDateProperty().set(rs.getDate("Date_of_birth").toLocalDate());
                this.lecturer.addressProperty().set(rs.getString("Address"));
                this.lecturer.emailProperty().setValue(rs.getString("Email"));
                this.lecturer.phoneProperty().setValue(rs.getString("Phone"));
                this.lecturer.degreeProperty().setValue(rs.getString("Degree"));
                this.checkLoginLecturer=true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void evaluateStudentInfo(String username, String password){
        ResultSet rs;
        rs= dataDriver.getStudentData(username,password);
        try{
            if(rs.next()){
                this.student.id_StudentProperty().setValue(rs.getString("Id_Student"));
                this.student.nameProperty().setValue(rs.getString("Name"));
                this.student.genderProperty().setValue(rs.getString("Gender"));
                this.student.birthDateProperty().set(rs.getDate("Date_of_birth").toLocalDate());
                this.student.addressProperty().setValue(rs.getString("Address"));
                this.student.emailProperty().setValue(rs.getString("Email"));
                this.student.phoneProperty().setValue(rs.getString("Phone"));
                this.checkLoginStudent=true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//----------------------get role login-----------------------------
    public RoleUser getRole_Login() {
        return role_Login;
    }

    public void setRole_Login(RoleUser role_Login) {
        this.role_Login = role_Login;
    }
}
