/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.SimpleDateFormat;
import model.Order;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class OrderDao {
    public static String EscapeBack(String path){
        String newpath = path.replaceAll("\\\\", "\\\\\\\\");
        return newpath;
    }
    public static Order save(Order order){      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateCreate = sdf.format(order.getCreated_at());
        String query = "insert into `order`("
                + "user_id, "
                + "customer_id, "
                + "total, "
                + "created_at "
                + ") values("
                + ""+order.getUser_id()+","
                +order.getCustomer_id()+","
                +order.getTotal()+",'"
                +dateCreate+"')";
        int id = DbOperations.setDataOrDeleteAndGetData(query);
        Order createdOrder = new Order();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM `order` WHERE id = "+id+";");

            while(rs.next()){
                createdOrder.setId(rs.getInt("id"));
                createdOrder.setUser_id(rs.getInt("user_id"));
                createdOrder.setCustomer_id(rs.getInt("customer_id"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateHaveCreatedAt = (Date) formatter.parse(rs.getString("created_at"));
                createdOrder.setCreated_at(dateHaveCreatedAt);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return createdOrder;        
    }
    
    public static ArrayList<Order> getAllRecordsByDesc(Date fromDate, Date toDate, String customerName, String customerPhone, String customerEmail, String userName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Order> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT `order`.id, customer.name as customer_name, customer.email as customer_email, customer.phonenumber as customer_phonenumber, customer.address as customer_address, total, bill_path, `order`.created_at, user.name as user_name " +
                                                "FROM customer,`order`, user " +
                                                "where `order`.customer_id = customer.id " +
                                                "and `order`.user_id = user.id " +
                                                "and `order`.created_at between '"+fromDateSql+"' and '"+toDateSql+"' " +
                                                "and customer.name like '%"+customerName+"%' " +
                                                "and customer.phonenumber like '%"+customerPhone+"%' " +
                                                "and customer.email like '%"+customerEmail+"%' " +
                                                "and user.name like '%"+userName+"%' " +
                                                "order by `order`.id desc");
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer_name(rs.getString("customer_name"));
                order.setCustomer_phonenumber(rs.getString("customer_phonenumber"));
                order.setCustomer_email(rs.getString("customer_email"));
                order.setCustomer_address(rs.getString("customer_address"));
                order.setTotal(rs.getInt("total"));
                order.setBill_path(rs.getString("bill_path"));
                order.setCreated_at(sdf2.parse(rs.getString("created_at")));
                order.setUser_name(rs.getString("user_name"));
                arrayList.add(order);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Order> getAllRecordsByInc(Date fromDate, Date toDate, String customerName, String customerPhone, String customerEmail, String userName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Order> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT `order`.id, customer.name as customer_name, customer.email as customer_email, customer.phonenumber as customer_phonenumber, customer.address as customer_address, total, bill_path, `order`.created_at, user.name as user_name " +
                                                "FROM customer,`order`, user " +
                                                "where `order`.customer_id = customer.id " +
                                                "and `order`.user_id = user.id " +
                                                "and `order`.created_at between '"+fromDateSql+"' and '"+toDateSql+"' " +
                                                "and customer.name like '%"+customerName+"%' " +
                                                "and customer.phonenumber like '%"+customerPhone+"%' " +
                                                "and customer.email like '%"+customerEmail+"%' " +
                                                "and user.name like '%"+userName+"%' ");
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer_name(rs.getString("customer_name"));
                order.setCustomer_phonenumber(rs.getString("customer_phonenumber"));
                order.setCustomer_email(rs.getString("customer_email"));
                order.setCustomer_address(rs.getString("customer_address"));
                order.setTotal(rs.getInt("total"));
                order.setBill_path(rs.getString("bill_path"));
                order.setCreated_at(sdf2.parse(rs.getString("created_at")));
                order.setUser_name(rs.getString("user_name"));
                arrayList.add(order);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void updatePath(int id, String path){
        String query = "update `order` set "
                + "bill_path = '"+path+"' "
                + "where id = " + id;
        DbOperations.setDataOrDelete(query, "Cập nhật đường dẫn hóa đơn thành công");
    }
}
