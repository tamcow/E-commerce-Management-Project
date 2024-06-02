/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Customer;

/**
 *
 * @author Admin
 */

public class CustomerDao {
    public static Customer save(Customer customer){      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateCreatedAt = sdf.format(customer.getCreated_at());
        String query = "insert into customer("
                + "name, "
                + "email, "
                + "phonenumber, "
                + "address, "
                + "created_at )"
                + " values("
                + "'"+customer.getName()+"','"
                +customer.getEmail()+"','"
                +customer.getPhonenumber()+"','"
                +customer.getAddress()+"','"
                +dateCreatedAt+"');";
//                + "";
        int id = DbOperations.setDataOrDeleteAndGetData(query);
        Customer createdCustomer = new Customer();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM customer WHERE id = "+id+";");

            while(rs.next()){
                createdCustomer.setId(rs.getInt("id"));
                createdCustomer.setName(rs.getString("name"));
                createdCustomer.setEmail(rs.getString("email"));
                createdCustomer.setPhonenumber(rs.getString("phonenumber"));
                createdCustomer.setAddress(rs.getString("address"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateHaveCreatedAt = (Date) formatter.parse(rs.getString("created_at"));
                createdCustomer.setCreated_at(dateHaveCreatedAt);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    return createdCustomer;
    }
}
