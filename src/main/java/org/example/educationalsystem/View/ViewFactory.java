package org.example.educationalsystem.View;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.educationalsystem.Controller.Lecturer.LecturerListStudentController;
import org.example.educationalsystem.Controller.Student.StudentViewNotificationController;
import org.example.educationalsystem.Model.DataDriver;

import java.io.IOException;

public class ViewFactory {

    public ViewFactory() {
        this.loginRole=RoleUser.Admin;
        this.AdminMenuItem=new SimpleObjectProperty<>();
        this.LecturerMenuItem=new SimpleObjectProperty<>();
        this.StudentMenuItem=new SimpleObjectProperty<>();
        this.LoginOptions= new SimpleObjectProperty<>();
        this.StudentButtonOptions=new SimpleObjectProperty<>();
        this.AdminButtonOptions=new SimpleObjectProperty<>();
        this.LecturerButtonOptions=new SimpleObjectProperty<>();
    }
// Page View
//    public void showLoginPage(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Login/login.fxml"));
//        creatStage(fxmlLoader);
//    }

    public void showFormPage(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/form.fxml"));
        creatStage(fxmlLoader);
    }

    public void showAdminPage(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/FXML/Admin/admin.fxml"));
        creatStage(fxmlLoader);
    }

    public void showLecturerPage(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/FXML/Lecturer/lecturer.fxml"));
        creatStage(fxmlLoader);
    }

    public void showStudentPage(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/FXML/Student/student.fxml"));
        creatStage(fxmlLoader);
    }

    public void closeStage(Stage stage){
        stage.close();
    }
//    Login & Signup View
    private AnchorPane Login;
    private AnchorPane Signup;
    private AnchorPane Verification;
    private AnchorPane LoginChangepass;
    private RoleUser loginRole;
    private final ObjectProperty<LoginOptions> LoginOptions;

    public AnchorPane getVertification(){
        try {
            Verification=new FXMLLoader(getClass().getResource("/FXML/Login/verification.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Verification;
    }

    public AnchorPane getLoginChangepass(){
        try{
            LoginChangepass=new FXMLLoader(getClass().getResource("/FXML/Login/changepass.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return LoginChangepass;
    }

    public AnchorPane getLogin(){
        try{
            Login= new FXMLLoader(getClass().getResource("/FXML/Login/form_login.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Login;
    }

    public AnchorPane getSignup(){
        try {
            Signup= new FXMLLoader(getClass().getResource("/FXML/Signup/form_signup.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Signup;
    }

    public RoleUser getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(RoleUser loginRole) {
        this.loginRole=loginRole;
    }

    public ObjectProperty<LoginOptions> getLoginOptions() {
        return LoginOptions;
    }

//    Signup View
    private AnchorPane SignUpPage;

    public AnchorPane SignupView(){
        try {
            SignUpPage=new FXMLLoader(getClass().getResource("/FXML/Signup/signup.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SignUpPage;
    }
//    Admin View
    private AnchorPane AdminHome;
    private AnchorPane AdminEdit;
    private AnchorPane AdminAccount;
    private AnchorPane AdminProfile;
    private AnchorPane CreatLecturerForm;
    private AnchorPane CreatStudentForm;
    private final ObjectProperty<AdminMenuOptions> AdminMenuItem;
    private final ObjectProperty<AdminButtonOptions> AdminButtonOptions;

    public AnchorPane getAdminHome(){
        try {
            AdminHome=new FXMLLoader(getClass().getResource("/FXML/Admin/home.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AdminHome;
    }

    public AnchorPane getAdminEdit(){
        try {
            AdminEdit=new FXMLLoader(getClass().getResource("/FXML/Admin/edit.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AdminEdit;
    }

    public AnchorPane getAdminAccount() {
        try {
            AdminAccount=new FXMLLoader(getClass().getResource("/FXML/Admin/account.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return AdminAccount;
    }

    public AnchorPane getAdminProfile() {
        try {
            AdminProfile=new FXMLLoader(getClass().getResource("/FXML/Admin/profile.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return AdminProfile;
    }

    public AnchorPane getCreatLecturerForm(){
        try{
            CreatLecturerForm=new FXMLLoader(getClass().getResource("/FXML/Admin/create_lecturer.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CreatLecturerForm;
    }

    public AnchorPane getCreatStudentForm(){
        try{
            CreatStudentForm=new FXMLLoader(getClass().getResource("/FXML/Admin/creat_student.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CreatStudentForm;
    }

    public ObjectProperty<AdminMenuOptions> getAdminMenuItem(){
        return AdminMenuItem;
    }

    public ObjectProperty<AdminButtonOptions> getAdminButtonOptions(){
        return AdminButtonOptions;
    }

//    ----------------------------------Lecturer View-----------------------------
    private AnchorPane LecturerHome;
    private AnchorPane LecturerManageGrades;
    private AnchorPane LecturerAccount;
    private AnchorPane LecturerProfile;
    private AnchorPane LecturerSendNotification;
    private AnchorPane CreateCLass;
    private AnchorPane ListStudentInClass;
    private LecturerListStudentController controllerListStudent;
    private final ObjectProperty<LecturerMenuOptions> LecturerMenuItem;
    private final ObjectProperty<LecturerButtonOptions> LecturerButtonOptions;

    public AnchorPane getLecturerHome(){
        try {
            LecturerHome=new FXMLLoader(getClass().getResource("/FXML/Lecturer/home.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LecturerHome;
    }

    public AnchorPane getLecturerManageGrades(){
        try{
            LecturerManageGrades=new FXMLLoader(getClass().getResource("/FXML/Lecturer/manage_grades.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return LecturerManageGrades;
    }

    public AnchorPane getLecturerAccount(){
        try {
            LecturerAccount=new FXMLLoader(getClass().getResource("/FXML/Lecturer/account.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LecturerAccount;
    }

    public AnchorPane getLecturerProfile(){
        try{
            LecturerProfile=new FXMLLoader(getClass().getResource("/FXMl/Lecturer/profile.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return LecturerProfile;
    }

    public AnchorPane getLecturerSendNotification(){
        try {
            LecturerSendNotification=new FXMLLoader(getClass().getResource("/FXMl/Lecturer/sendnotification.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LecturerSendNotification;
    }

    public AnchorPane getCreateCLass(){
        try{
            CreateCLass=new FXMLLoader(getClass().getResource("/FXML/Lecturer/creat_class.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CreateCLass;
    }

    public AnchorPane getListStudentInClass(){
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/FXML/Lecturer/list_student.fxml"));
            ListStudentInClass=fxmlLoader.load();
            this.controllerListStudent=fxmlLoader.getController();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ListStudentInClass;
    }

    public LecturerListStudentController getControllerListStudent(){
        return this.controllerListStudent;
    }


    public ObjectProperty<LecturerMenuOptions> getLecturerMenuItem(){
        return LecturerMenuItem;
    }

    public ObjectProperty<LecturerButtonOptions> getLecturerButtonOptions(){
        return LecturerButtonOptions;
    }

//    ------------------------------Student View----------------------------
    private AnchorPane StudentHome;
    private AnchorPane StudentCourse;
    private AnchorPane StudentViewGrades;
    private AnchorPane StudentAccount;
    private AnchorPane StudentProfile;
    private AnchorPane StudentNotification;
    private AnchorPane StudentNotificationView;
    private StudentViewNotificationController controllerStudentViewNotification;
    private final ObjectProperty<StudentMenuOptions> StudentMenuItem;
    private final ObjectProperty<StudentButtonOptions> StudentButtonOptions;

    public AnchorPane getStudentHome(){
        try{
            StudentHome=new FXMLLoader(getClass().getResource("/FXML/Student/home.fxml")).load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return StudentHome;
    }

    public AnchorPane getStudentCourse(){
        try{
            StudentCourse=new FXMLLoader(getClass().getResource("/FXML/Student/course.fxml")).load();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return StudentCourse;
    }

    public AnchorPane getStudentViewGrades(){
        try{
            StudentViewGrades=new FXMLLoader(getClass().getResource("/FXML/Student/view_grades.fxml")).load();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return StudentViewGrades;
    }

    public AnchorPane getStudentAccount(){
        try{
            StudentAccount=new FXMLLoader(getClass().getResource("/FXML/Student/account.fxml")).load();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return StudentAccount;
    }

    public AnchorPane getStudentProfile(){
        try{
            StudentProfile=new FXMLLoader(getClass().getResource("/FXMl/Student/profile.fxml")).load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return StudentProfile;
    }

    public AnchorPane getStudentNotification() {
        try {
            StudentNotification=new FXMLLoader(getClass().getResource("/FXML/Student/notification.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StudentNotification;
    }

    public AnchorPane getStudentNotificationView() {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/FXML/Student/notification_view.fxml"));
            StudentNotificationView=fxmlLoader.load();
            controllerStudentViewNotification=fxmlLoader.getController();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return StudentNotificationView;
    }

    public StudentViewNotificationController getControllerStudentViewNotification(){
        return this.controllerStudentViewNotification;
    }

    public ObjectProperty<StudentMenuOptions> getStudentMenuItem() {
        return StudentMenuItem;
    }

    public ObjectProperty<StudentButtonOptions> getStudentButtonOptions() {
        return StudentButtonOptions;
    }
//    creat Stage
    private void creatStage(FXMLLoader fxmlLoader){
        Scene scene=null;
        try {
            scene=new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Educational System");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Image/img.png"))));
        stage.setResizable(false);
        stage.show();
    }

}
