/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;
import javax.swing.*;

/**
 *
 * @author deepa
 */
public class UserDao {

    public static void save(User user) {
        String query = " insert into user(name,email,mobileNumber,address,password,securityQuestion,answer,status)values('" + user.getName() + "','" + user.getEmail() + "','" + user.getMobilenumber() + "','" + user.getAddress() + "','" + user.getPassword() + "','" + user.getSecurityquestion() + "','" + user.getAnswer() + "','false')";
        System.out.println("1");
        DbOperations.setDataOrDelete(query, "Registration done wait for admin approval");
        System.out.println("2");
    }

    public static User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("Select * from user where email= '" + email + "' and password='" + password + "' ");
            while (rs.next()) {
                user = new User();
                user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
        }
        return user;
    }

    public static User getSecurityQuestion(String email) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("Select * from user where email = '" + email + "'     ");
            while (rs.next()) {
                user = new User();
                user.setSecurityquestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void update(String email, String newPassword) {
        String query = "update user set password = '" + newPassword + "' where email= '" + email + "' ";
        DbOperations.setDataOrDelete(query, "Password changed Successfully");
    }
    
    public static ArrayList<User> getAllRecords(String email){
        ArrayList<User> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from user where email like  '%"+email+"%'  ");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobilenumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityquestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void changeStatus(String email, String status){
        String query = "update user set status = '"+status+"' where email='"+email+"' ";
        DbOperations.setDataOrDelete(query, "status changed successfully");
    }
}
