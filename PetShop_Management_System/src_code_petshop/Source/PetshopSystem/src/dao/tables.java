/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class tables {
    public static void main(String[] args){
        try {
            String userTable = "create table user("
                    + "id int AUTO_INCREMENT primary key, "
                    + "name varchar(200), email varchar(200), "
                    + "mobileNumber varchar(200), "
                    + "address varchar(200), "
                    + "CCCD varchar(200), "
                    + "dateBirth date, "
                    + "dateStart date, "
                    + "department varchar(40), "
                    + "password varchar(200), "
                    + "securityQuestion varchar(200), "
                    + "answer varchar(200),"
                    + "status varchar(20),"
                    + "UNIQUE (email), "
                    + "CONSTRAINT fk_htk_id_sanpham" +
                    "  FOREIGN KEY (department)" +
                    "  REFERENCES department(id))";
            String departmentTable = "create table department("
                    + "id varchar(40) primary key not null,"
                    + "name varchar(200))";
            String adminDetails = "insert into user(name, email, mobileNumber, address, CCCD, dateBirth, dateStart, department, password, securityQuestion, answer, status) values('Nguyen Van Khang', 'admin@gmail.com', '1234567890', 'Vietnam', '1234567891234', '2002-02-12', '2022-05-05','AD', 'admin', 'What primary school did tou attend?', 'LBT', 'true')";
            String pet_categoryTable = "create table pet_category(id int AUTO_INCREMENT primary key, name varchar(200))";
            String billTable = "create table bill(id int AUTO_INCREMENT primary key, name varchar(200), mobileNumber varchar(200), email varchar(200), date varchar(50), total varchar(200), createdBy varchar(200))";
            String productTable = "create table product(id int AUTO_INCREMENT primary key, name varchar(200), category varchar(200), price varchar(200))";
//            DbOperations.setDataOrDelete(userTable, "User Table Created Successfully");
//            DbOperations.setDataOrDelete(departmentTable, "Department Table Created Successfully");  
//            DbOperations.setDataOrDelete(adminDetails, "Admin Details Added Successfully");           
//            DbOperations.setDataOrDelete(pet_categoryTable, "Category Table Created Successfully");    
//            DbOperations.setDataOrDelete(productTable, "Product Table Created Successfully");
//            DbOperations.setDataOrDelete(billTable, "Bill Table Created Successfully");           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
