/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class UserDao {

    public static void save(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateStart = sdf.format(user.getDateStart());
        String dateBirth = sdf.format(user.getDateBirth());
        String query = "insert into user(name, email, mobileNumber, address, CCCD, dateBirth, dateStart, department_id, password, securityQuestion, answer, status)"
                + "values('" + user.getName() + "',"
                + "'" + user.getEmail() + "',"
                + "'" + user.getMobileNumber() + "',"
                + "'" + user.getAddress() + "',"
                + "'" + user.getCCCD() + "',"
                + "'" + dateBirth + "',"
                + "'" + dateStart + "',"
                + "'" + user.getDepartment_id() + "',"
                + "'" + user.getPassword() + "',"
                + "'" + user.getSecurityQuestion() + "',"
                + "'" + user.getAnswer() + "',"
                + "'false'"
                + ")";
        DbOperations.setDataOrDelete(query, "Đăng kí thành công, vui lòng chờ duyệt thông tin từ quản lý nhân sự");
    }

    public static User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select * from user where email ='" + email + "' and password='" + password + "'");
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setCCCD(rs.getString("CCCD"));
                user.setDepartment_id(rs.getString("department_id"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static User getSecurityQuestion(String email) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select * from user where email ='" + email + "'");
            while (rs.next()) {
                user = new User();
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void updatePassword(String email, String newPassword) {
        String query = "update user set password = '" + newPassword + "' where email='" + email + "'";
        DbOperations.setDataOrDelete(query, "Password Changed Sucessfully");
    }
    
    public static void update(User user){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateBirthday = sdf.format(user.getDateBirth());
        String dateImportday = sdf.format(user.getDateStart());
        String query = "update user set "
                + "name = '"+user.getName()+"',"
                + "mobileNumber = '"+user.getMobileNumber()+"',"
                + "address = '"+user.getAddress()+"',"
                + "email = '"+user.getEmail()+"',"
                + "CCCD = '"+user.getCCCD()+"',"
                + "department_id = '"+user.getDepartment_id()+"',"
                + "password = '"+user.getPassword()+"',"
                + "securityQuestion = '"+user.getSecurityQuestion()+"',"
                + "answer = '"+user.getAnswer()+"',"
                + "status = '"+user.getStatus()+"',"
                + "dateBirth = '"+dateBirthday+"',"
                + "dateStart = '"+dateImportday+"', "
                + "status = 'true' "
                + "where id = "+user.getId()+"";
        DbOperations.setDataOrDelete(query, "Sửa nhân viên thành công");     
    }
    
    public static void delete(String id){
        String query = "delete from user where id = '"+id+"'";
        DbOperations.setDataOrDelete(query, "Xóa nhân viên thành công");          
    }
    
    public static ArrayList<User> getAllRecords(String id, String email, String name, String phone, String CCCD, String department){
        ArrayList<User> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select user.*, department.id as department_id, department.name as department_name "
                                                + "from user, department "
                                                + "where user.department_id = department.id "
                                                + "and email like '%"+email+"%' "
                                                + "and mobileNumber like '%"+phone+"%' "
                                                + "and user.name like '%"+name+"%' "
                                                + "and CCCD like '%"+CCCD+"%' "
                                                + "and user.id like '%"+id+"%' "
                                                + "and status = 'true' "
                                                + (!department.equals("") ? "and department.id = "+department+"" : "")
                                                + "");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setCCCD(rs.getString("CCCD"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBirth = (Date) formatter.parse(rs.getString("dateBirth"));
                Date dateStart = (Date) formatter.parse(rs.getString("dateStart"));
                user.setDateBirth(dateBirth);
                user.setDateStart(dateStart);
                user.setDepartment_id(rs.getString("department_id"));
                user.setDepartment_name(rs.getString("department_name"));
                user.setPassword(rs.getString("password"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    public static ArrayList<User> getAllRecordsWithStatusFalse(String email){
        ArrayList<User> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from user where email like '%"+email+"%' and status = 'false'");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setCCCD(rs.getString("CCCD"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBirth = (Date) formatter.parse(rs.getString("dateBirth"));
                Date dateStart = (Date) formatter.parse(rs.getString("dateStart"));
                user.setDateBirth(dateBirth);
                user.setDateStart(dateStart);
                user.setDepartment_id(rs.getString("department_id"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    
    public static void changeStatus(String email, String status){
        String query = "update user set status ='"+status+"' where email ='"+email+"'";
        DbOperations.setDataOrDelete(query, "Status Changed Successfully");
    }
    
    public static void changePassword(String email, String oldPassword, String newPassword){
        try {
            ResultSet rs = DbOperations.getData("select * from user where email ='"+email+"' and password='"+oldPassword+"'");
            if(rs.next()){
                updatePassword(email, newPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Old Password is Wrong");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void changeSecurityQuestion(String email, String password, String securityQuestion, String answer){
        try {
            ResultSet rs = DbOperations.getData("select * from user where email'"+email+"'and password'"+password+"'");
            if(rs.next()){
                update(email, securityQuestion, answer);
            }else{
                JOptionPane.showMessageDialog(null, "Password is wrong");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void update(String email, String securityQuestion, String answer){
        String query = "update user set securityQuestion='"+securityQuestion+"', answer='"+answer+"' where email='"+email+"'";
        DbOperations.setDataOrDelete(query, "Security Question Changed Successfully");
    }
    
}
