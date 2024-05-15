package org.example.educationalsystem.Model;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.ObservableList;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

public class SendMail {
    private static final String email="educationalsystem8@gmail.com";
    private static final String password="napwawijicdklifm";
    
    public static String randomCode(){
        StringBuilder code=new StringBuilder();
        for(int i=0;i<6;i++){
            Random random=new Random();
            code.append(random.nextInt(0,9));
        }
        return code.toString();
    }

    private static Properties getProperties() {
        Properties pros=new Properties();
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.enable", "true");
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port", "587");
        return pros;
    }

    public static void sendCode(String user_mail,String code){
        Properties pros = getProperties();

        Session session=Session.getInstance(pros, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,password);
            }
        });

        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(email,"Educational System"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user_mail));
            message.setSubject("Verification Code");
            message.setText("Your confirmation code is: "+code);
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    public static void sendNotification(String nameLecturer,ObservableList<String> list_student_mail, String subject, String content){
        Properties pros=getProperties();

        Session session=Session.getInstance(pros,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, nameLecturer));
            InternetAddress[] addresses = new InternetAddress[list_student_mail.size()];
            for(int i=0;i<list_student_mail.size();i++){
                addresses[i] = new InternetAddress(list_student_mail.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=UTF-8");
            Transport.send(message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        sendCode("ngvanhuy0000@gmail.com");
//    }
}
