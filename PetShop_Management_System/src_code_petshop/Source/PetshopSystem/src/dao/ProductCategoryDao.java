/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.mysql.cj.Query;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Petcategory;
import java.sql.*;
import model.Productcategory;
/**
 *
 * @author Admin
 */
public class ProductCategoryDao {
    public static void save(Productcategory productcategory){
        String query = "insert into product_category (name) values('"+productcategory.getName()+"')";
        DbOperations.setDataOrDelete(query, "Product Category Added Successfully"); 
    }
    
    public static ArrayList<Productcategory> getAllRecords(){
        ArrayList<Productcategory> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from product_category");
            while(rs.next()){
                Productcategory productcategory = new Productcategory();
                productcategory.setId(rs.getInt("id"));
                productcategory.setName(rs.getString("name"));
                arrayList.add(productcategory);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void delete(String id){
        String query = "delete from product_category where id='"+id+"'";
        DbOperations.setDataOrDelete(query, "Pet Category Deleted Successfully");
    }
}
