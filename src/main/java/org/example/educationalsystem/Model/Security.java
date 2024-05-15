package org.example.educationalsystem.Model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static String enCode(String pass) {
        String hashPass;
        try {
            MessageDigest md= MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(pass.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            hashPass = sb.toString();
            return hashPass;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
////        String pass="edusys8888";
////        System.out.println(enCode(pass));
////        String passstudent="student1";
////        System.out.println(enCode(passstudent));
////        String passlecturer="lecturer1";
////        System.out.println(enCode(passlecturer));
////        if(enCode("student1").equals("fe627eeced3bcd4bf40e759c1511e2d4d48065d7b0280af938d816cfae8cb8f7de51477b6225cbd9284e777b7c886e096918f8d81b40379fe06a07531ed632c2")){
////            System.out.println("Success");
////        }
////        String passstudent2="student3";
////        System.out.println(enCode(passstudent2));
////        String passlecturer2="lecturer2";
////        System.out.println(enCode(passlecturer2));
//    }
}
