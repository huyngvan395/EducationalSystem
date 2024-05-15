package org.example.educationalsystem.Model;

import com.sun.mail.imap.protocol.ID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataDriver {
    private static String url="jdbc:mysql://localhost:3306/educational_system";
    private static String user="root";
    private static String password="";
    private static Connection con;
    public static Connection getConnection(){
        try {
            con= DriverManager.getConnection(url,user,password);
//            System.out.println("Successfully connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Failed to connect to database");
        }
        return con;
    }

//    ------------------------Get information-----------------------

    public ResultSet getAdminData(String username, String password){
        PreparedStatement preparedStatement;
        ResultSet rs=null;

        try {
            preparedStatement=getConnection().prepareStatement("SELECT * FROM admin WHERE Email='"+username+"' AND Password='"+password+"'");
            rs=preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getLecturerData(String username, String password){
        PreparedStatement preparedStatement;
        ResultSet rs=null;

        try{
            preparedStatement=getConnection().prepareStatement("SELECT * FROM lecturer WHERE Email=? AND Password=?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            rs=preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getStudentData(String username, String password){
        PreparedStatement preparedStatement;
        ResultSet rs=null;

        try{
            preparedStatement=getConnection().prepareStatement("SELECT * FROM student WHERE Email=? AND Password=?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            rs=preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

// --------------------------get student and lecturer list--------------------------------
    public ObservableList<Lecturer> getLecturerList(){
        ObservableList<Lecturer> lecturerList=FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT * FROM lecturer");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                String IDlecturer=rs.getString("ID_Lecturer");
                String name=rs.getString("Name");
                String gender=rs.getString("Gender");
                LocalDate date=rs.getDate("Date_of_birth").toLocalDate();
                String address=rs.getString("Address");
                String email=rs.getString("Email");
                String phone=rs.getString("Phone");
                String degree=rs.getString("Degree");
                Lecturer lecturer=new Lecturer(IDlecturer,name,gender,date,address,email,phone,degree);
                lecturerList.add(lecturer);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lecturerList;
    }

    public ObservableList<Student> getStudentList(){
        ObservableList<Student> studentList=FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT * FROM student");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                String IDstudent=rs.getString("ID_Student");
                String name=rs.getString("Name");
                String gender=rs.getString("Gender");
                LocalDate date=rs.getDate("Date_of_birth").toLocalDate();
                String address=rs.getString("Address");
                String email=rs.getString("Email");
                String phone=rs.getString("Phone");
                Student student=new Student(IDstudent,name,gender,date,address,email,phone);
                studentList.add(student);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return studentList;
    }

//    --------------------------------creat lecturer information-------------------------------
    public void creatLecturer(String ID_lecturer, String name, String gender, LocalDate birthDate, String address, String phone, String email, String degree,String password){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("INSERT INTO lecturer(ID_Lecturer, Name, Gender, Date_of_birth, Address, Email, Phone, Password, Degree) VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,ID_lecturer);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,gender);
            preparedStatement.setDate(4,Date.valueOf(birthDate));
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,email);
            preparedStatement.setString(7,phone);
            preparedStatement.setString(8,Security.enCode(password));
            preparedStatement.setString(9,degree);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

//    -------------------------------creat student information----------------------------------
    public void creatStudent(String ID_lecturer, String name, String gender, LocalDate birthDate, String address, String phone, String email,String password){
        PreparedStatement preparedStatement;

        try{
            preparedStatement=getConnection().prepareStatement("INSERT INTO student(ID_Student, Name, Gender, Date_of_birth, Address, Email, Phone, Password) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,ID_lecturer);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,gender);
            preparedStatement.setDate(4,Date.valueOf(birthDate));
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,phone);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,Security.enCode(password));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

//    --------------------------------------creat class----------------------------------
    public void creatClass(String ID_class, String className,String lecturerName){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("INSERT INTO class(ID_Class, Name, ID_Lecturer) VALUES (?,?,?)");
            preparedStatement.setString(1, ID_class);
            preparedStatement.setString(2, className);
            preparedStatement.setString(3,lecturerName);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

//    -----------------------------delete Student--------------------------------------------
    public void deleteStudent(String IDStudent){
        PreparedStatement preparedStatement;
        try{
            //------------delete studentclass-------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM studentclass WHERE ID_Student=?");
            preparedStatement.setString(1,IDStudent);
            preparedStatement.executeUpdate();
            //--------------delete mark of student---------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM mark WHERE ID_Student=?");
            preparedStatement.setString(1,IDStudent);
            preparedStatement.executeUpdate();
            //------------------delete notification of student----------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM notification WHERE ID_Student=?");
            preparedStatement.setString(1,IDStudent);
            preparedStatement.executeUpdate();
            //-----------------delete student-----------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM student WHERE ID_Student=?");
            preparedStatement.setString(1,IDStudent);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    -------------------------------delete Lecturer-------------------------------------------
    public void deleteLecturer(String IDlecturer){
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            //----------delete class in studentclass------------------
            List<String> IDClasses=new ArrayList<String>();
            preparedStatement=getConnection().prepareStatement("SELECT ID_Class FROM class WHERE ID_Lecturer=?");
            preparedStatement.setString(1,IDlecturer);
            rs=preparedStatement.executeQuery();
            while (rs.next()){
                IDClasses.add(rs.getString("ID_Class"));
            }
            for(String IDClass:IDClasses){
                preparedStatement=getConnection().prepareStatement("DELETE FROM studentclass WHERE ID_Class=?");
                preparedStatement.setString(1,IDClass);
                preparedStatement.executeUpdate();
            }
            //------------delete class in class------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM class WHERE ID_Lecturer=?");
            preparedStatement.setString(1,IDlecturer);
            preparedStatement.executeUpdate();
            //------------delete notification --------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM notification WHERE ID_Lecturer=?");
            preparedStatement.setString(1,IDlecturer);
            preparedStatement.executeUpdate();
            //-------------delete lecturer----------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM lecturer WHERE ID_Lecturer=?");
            preparedStatement.setString(1,IDlecturer);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    ---------------------------count lecturer--------------------------------------------
    public int countLecturer(String gender){
        int count=0;
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT COUNT(*) as count FROM lecturer WHERE Gender=?");
            preparedStatement.setString(1, gender);
            rs=preparedStatement.executeQuery();
            if (rs.next()){
                count=rs.getInt("count");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

//    ------------------------------count student--------------------------------------------
    public int countStudent(String gender){
        int count=0;
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT COUNT(*) as count FROM student WHERE Gender=?");
            preparedStatement.setString(1, gender);
            rs=preparedStatement.executeQuery();
            if(rs.next()){
                count=rs.getInt("count");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

//    ---------------------------get all course available have not joined------------------------------------
    public ObservableList<Course> getCourseListHaveNotJoined(String IDStudent){
        ObservableList<Course> courses= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
                preparedStatement=getConnection().prepareStatement("SELECT class.ID_Class as classID, class.Name as className,lecturer.Name as lecturerName FROM class JOIN lecturer ON class.ID_Lecturer=lecturer.ID_Lecturer  WHERE NOT EXISTS(SELECT * FROM studentclass WHERE class.ID_Class=studentclass.ID_Class AND studentclass.ID_Student=?) ");
                preparedStatement.setString(1,IDStudent);
                rs=preparedStatement.executeQuery();
                while(rs.next()){
                    String classID = rs.getString("classID");
                    String className = rs.getString("className");
                    String lecturerName = rs.getString("lecturerName");
                    Course course = new Course(classID,className,lecturerName);
                    courses.add(course);
                }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return  courses;
    }

//   ------------------------- get course student joined-----------------------------
    public ObservableList<Course> getCourseListStudent(String IDstudent){
        ObservableList<Course> courses= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT class.ID_Class as classID, class.Name as className,lecturer.Name as lecturerName FROM class JOIN lecturer ON class.ID_Lecturer=lecturer.ID_Lecturer JOIN studentclass ON class.ID_Class=studentclass.ID_Class WHERE ID_Student=? ");
            preparedStatement.setString(1,IDstudent);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                String classID = rs.getString("classID");
                String className = rs.getString("className");
                String lecturerName = rs.getString("lecturerName");

                Course course = new Course(classID, className,lecturerName);
                courses.add(course);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return courses;
    }

//    ---------------------------------get all class of lecturer----------------------------------------
    public ObservableList<Class> getClassList(String lecturerID){
        ObservableList<Class> classes= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT ID_Class,Name FROM class WHERE ID_Lecturer=?");
            preparedStatement.setString(1,lecturerID);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                String classID = rs.getString("ID_Class");
                String className = rs.getString("Name");
                Class class_item=new Class(classID,className);
                classes.add(class_item);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return  classes;
    }

//    -------------------------------get all notification of student-------------------------
    public ObservableList<Notification> getNotificationList(String studentID){
        ObservableList<Notification> notifications= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT ID_Notification,lecturer.Name as lecturerName, Subject, Content FROM notification JOIN lecturer ON notification.ID_Lecturer=lecturer.ID_Lecturer WHERE ID_Student=?");
            preparedStatement.setString(1, studentID);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                int ID = rs.getInt("ID_Notification");
                String lecturerName = rs.getString("lecturerName");
                String subject = rs.getString("Subject");
                String content = rs.getString("Content");
                Notification notification = new Notification(ID,lecturerName,subject,content);
                notifications.add(notification);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return  notifications;
    }

//    --------------------------------delete notification in student-----------------------
    public void deleteNotification(int IDnotification){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("DELETE FROM notification WHERE ID_Notification=?");
            preparedStatement.setInt(1,IDnotification);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    ---------------------------------get student list in class---------------------------
    public ObservableList<Student> getStudentInClass(String IDClass){
        ObservableList<Student> students= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT student.ID_Student as IDStudent,student.Name as NameStudent FROM student JOIN studentclass ON student.ID_Student=studentclass.ID_Student WHERE studentclass.ID_Class=?");
            preparedStatement.setString(1,IDClass);
            rs=preparedStatement.executeQuery();
            while (rs.next()){
                String IDStudent = rs.getString("IDStudent");
                String Name = rs.getString("NameStudent");
                Student student=new Student(IDStudent,Name,"",null,"","","");
                students.add(student);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }

//    Update lecturer
    public void updateLecturer(String IDLecturer,String name,String gender, LocalDate date, String address, String email, String phone, String degree){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Name=?, Gender=?, Date_of_birth=?, Address=?, Email=?, Phone=?, Degree=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,gender);
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, degree);
            preparedStatement.setString(8, IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    Update student
    public void updateStudent(String IDStudent, String name, String gender, LocalDate date, String address, String email, String phone){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET Name=?, Gender=?, Date_of_birth=?, Address=?, Email=?, Phone=? WHERE ID_Student=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,gender);
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, IDStudent);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    get Grades for student
    public ObservableList<Mark> getMarkList(String IDStudent){
        ObservableList<Mark> marks= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT class.Name as className, Ex_Grades, Midterm_Grades, Final_Grades FROM class JOIN mark ON class.ID_Class=mark.ID_Class WHERE ID_Student=?");
            preparedStatement.setString(1,IDStudent);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                String className = rs.getString("className");
                Double exGrades = rs.getObject("Ex_Grades")!=null?rs.getDouble("Ex_Grades") : null;
                Double midGrades = rs.getObject("Midterm_Grades")!=null?rs.getDouble("Midterm_Grades") : null;
                Double finalGrades = rs.getObject("Final_Grades")!=null?rs.getDouble("Final_Grades") : null;
                Mark mark=new Mark(className, exGrades, midGrades, finalGrades);
                marks.add(mark);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return marks;
    }

    //    ---------------------------get grades of student in class managed by lecturer------------------------
    public ObservableList<StudentMark> getStudentMarkListByClass(String IDLecturer,String className){
        ObservableList<StudentMark> marks= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT student.ID_Student as idStudent, student.Name as studentName, Ex_Grades, Midterm_Grades, Final_Grades FROM class JOIN lecturer ON class.ID_Lecturer=lecturer.ID_Lecturer JOIN studentclass ON class.ID_Class=studentclass.ID_Class JOIN student ON studentclass.ID_Student=student.ID_Student JOIN mark ON student.ID_Student=mark.ID_Student AND class.ID_Class=mark.ID_Class WHERE lecturer.ID_Lecturer=? AND class.Name=?");
            preparedStatement.setString(1,IDLecturer);
            preparedStatement.setString(2,className);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                String idStudent=rs.getString("idStudent");
                String studentName = rs.getString("studentName");
                Double exGrades = rs.getObject("Ex_Grades")!=null?rs.getDouble("Ex_Grades") : null;
                Double midGrades = rs.getObject("Midterm_Grades")!=null?rs.getDouble("Midterm_Grades") : null;
                Double finalGrades = rs.getObject("Final_Grades")!=null?rs.getDouble("Final_Grades") : null;
                StudentMark studentMark=new StudentMark(idStudent, studentName, exGrades, midGrades, finalGrades);
                marks.add(studentMark);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return marks;
    }

//    ---------------------------get class name list for lecturer----------------------------
    public ObservableList<String> getClassName(String IDLecturer){
        ObservableList<String> classes= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT class.Name as className FROM class JOIN lecturer ON class.ID_Lecturer=lecturer.ID_Lecturer WHERE lecturer.ID_Lecturer=?");
            preparedStatement.setString(1,IDLecturer);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                classes.add(rs.getString("className"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return classes;
    }

//    ---------------------------change pass when forget pass--------------------------------
    public void changePass(String user,String email,String password){
        PreparedStatement preparedStatement;
        try{
            String sql="UPDATE "+user+" SET Password=? WHERE Email=?";
            preparedStatement=getConnection().prepareStatement(sql);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    --------------------get list mail of students in class-------------------
    public ObservableList<String> getListMail(String IDLecturer, String className){
        ObservableList<String> mails= FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT student.Email as studentMail FROM lecturer JOIN class ON lecturer.ID_Lecturer=class.ID_Lecturer JOIN studentclass ON studentclass.ID_Class=class.ID_Class JOIN student ON studentclass.ID_Student=student.ID_Student WHERE lecturer.ID_Lecturer=? AND class.Name=?");
            preparedStatement.setString(1,IDLecturer);
            preparedStatement.setString(2,className);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                mails.add(rs.getString("studentMail"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return mails;
    }


//    ------------------------add notifications------------------------
    //-------------------function getIDStudent----------------------
    public String getIDStudent(String email){
        PreparedStatement preparedStatement;
        ResultSet rs;
        try{
            preparedStatement=getConnection().prepareStatement("SELECT ID_Student FROM student WHERE email=?");
            preparedStatement.setString(1,email);
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getString("ID_Student");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    //-------------------function add---------------------
    public void addNotification(String IDLecturer, String IDStudent, String Subject, String Content){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("INSERT INTO notification(ID_Lecturer, ID_Student, Subject, Content) VALUES(?,?,?,?)");
            preparedStatement.setString(1,IDLecturer);
            preparedStatement.setString(2,IDStudent);
            preparedStatement.setString(3,Subject);
            preparedStatement.setString(4,Content);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

////    ---------------------join class---------------------------
//    public void joinClass(String IDClass, String ID_Student){
//        PreparedStatement preparedStatement;
//        try{
//            preparedStatement=getConnection().prepareStatement("INSERT INTO studentclass(ID_Class, ID_Student) VALUES(?,?)");
//            preparedStatement.setString(1,IDClass);
//            preparedStatement.setString(2,ID_Student);
//            preparedStatement.executeUpdate();
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

//    ---------------------left course-------------------
    public void leftCourseInStudent(String IDStudent, String IDClass){
        PreparedStatement preparedStatement;
        try{
            //------------------ left class ------------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM studentclass WHERE ID_Student=? AND ID_Class=?");
            preparedStatement.setString(1,IDStudent);
            preparedStatement.setString(2,IDClass);
            preparedStatement.executeUpdate();
            //--------------------delete grades of student in class-------------------
            preparedStatement=getConnection().prepareStatement("DELETE FROM mark WHERE ID_Student=? AND ID_Class=?");
            preparedStatement.setString(1,IDStudent);
            preparedStatement.setString(2,IDClass);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

//    -------------------------join course--------------------------
    public void joinCourseInStudent(String IDClass, String ID_Student){
        PreparedStatement preparedStatement;
        try{
            //-------------------- creat student-class relationship-----------
            preparedStatement=getConnection().prepareStatement("INSERT INTO studentclass(ID_Class, ID_Student) VALUES(?,?)");
            preparedStatement.setString(1,IDClass);
            preparedStatement.setString(2,ID_Student);
            preparedStatement.executeUpdate();
            //---------------------- creat mark of student in class
            preparedStatement=getConnection().prepareStatement("INSERT INTO mark(ID_Class, ID_Student, Ex_Grades, Midterm_Grades, Final_Grades) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1,IDClass);
            preparedStatement.setString(2,ID_Student);
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

//    ----------------update grades----------------------------
    public void updateExGrades(String IDStudent, String namClass, Double grades){
        PreparedStatement preparedStatement;
        ResultSet rs;
        String IDClass="";
        try{
            preparedStatement=getConnection().prepareStatement("SELECT ID_Class FROM class WHERE Name=?");
            preparedStatement.setString(1,namClass);
            rs=preparedStatement.executeQuery();
            if(rs.next()){
                IDClass=rs.getString("ID_Class");
            }
            preparedStatement=getConnection().prepareStatement("UPDATE mark SET Ex_Grades=? WHERE ID_Student=? AND ID_Class=?");
            preparedStatement.setObject(1,grades);
            preparedStatement.setString(2,IDStudent);
            preparedStatement.setString(3,IDClass);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateMidtermGrades(String IDStudent, String nameClass, Double grades){
        PreparedStatement preparedStatement;
        ResultSet rs;
        String IDClass="";
        try{
            preparedStatement=getConnection().prepareStatement("SELECT ID_Class FROM class WHERE Name=?");
            preparedStatement.setString(1,nameClass);
            rs=preparedStatement.executeQuery();
            if(rs.next()){
                IDClass=rs.getString("ID_Class");
            }
            preparedStatement=getConnection().prepareStatement("UPDATE mark SET Midterm_Grades=? WHERE ID_Student=? AND ID_Class=?");
            preparedStatement.setObject(1,grades);
            preparedStatement.setString(2,IDStudent);
            preparedStatement.setString(3,IDClass);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateFinalGrades(String IDStudent, String nameClass, Double grades){
        PreparedStatement preparedStatement;
        ResultSet rs;
        String IDClass="";
        try{
            preparedStatement=getConnection().prepareStatement("SELECT ID_Class FROM class WHERE Name=?");
            preparedStatement.setString(1,nameClass);
            rs=preparedStatement.executeQuery();
            if(rs.next()){
                IDClass=rs.getString("ID_Class");
            }
            preparedStatement=getConnection().prepareStatement("UPDATE mark SET Final_Grades=? WHERE ID_Student=? AND ID_Class=?");
            preparedStatement.setObject(1,grades);
            preparedStatement.setString(2,IDStudent);
            preparedStatement.setString(3,IDClass);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

//    -------------------------update information of lecturer in table -------------------------
    public void updateNameLecturer(String IDLecturer,String name){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Name=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateGenderLecturer(String IDLecturer, String gender){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Gender=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1, gender);
            preparedStatement.setString(2, IDLecturer);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateBirthDateLecturer(String IDLecturer, LocalDate birthDate){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET BirthDate=? WHERE ID_Lecturer=?");
            preparedStatement.setDate(1, Date.valueOf(birthDate));
            preparedStatement.setString(2, IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateAddressLecturer(String IDLecturer, String address){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Address=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateEmailLecturer(String IDLecturer, String email){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Email=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updatePhoneLecturer(String IDLecturer, String phone){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Phone=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateDegreeLecturer(String IDLecturer, String degree){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE lecturer SET Degree=? WHERE ID_Lecturer=?");
            preparedStatement.setString(1, degree);
            preparedStatement.setString(2, IDLecturer);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

//    ---------------------update data of student in table-------------------
    public void updateNameStudent(String IDStudent,String name){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET Name=? WHERE ID_Student=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,IDStudent);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateGenderStudent(String IDStudent, String gender){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET Gender=? WHERE ID_Student=?");
            preparedStatement.setString(1, gender);
            preparedStatement.setString(2, IDStudent);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateBirthDateStudent(String IDStudent, LocalDate birthDate){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET BirthDate=? WHERE ID_Student=?");
            preparedStatement.setDate(1, Date.valueOf(birthDate));
            preparedStatement.setString(2, IDStudent);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateAddressStudent(String IDStudent, String address){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET Address=? WHERE ID_Student=?");
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, IDStudent);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateEmailStudent(String IDStudent, String email){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET Email=? WHERE ID_Student=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, IDStudent);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updatePhoneStudent(String IDStudent, String phone){
        PreparedStatement preparedStatement;
        try{
            preparedStatement=getConnection().prepareStatement("UPDATE student SET Phone=? WHERE ID_Student=?");
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, IDStudent);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
