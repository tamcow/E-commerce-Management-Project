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
/**
 *
 * @author Admin
 */
public class PetCategoryDao {
    public static void save(Petcategory petcategory){
        String query = "insert into pet_category (name) values('"+petcategory.getName()+"')";
        DbOperations.setDataOrDelete(query, "Pet Category Added Successfully"); 
    }
    
    public static ArrayList<Petcategory> getAllRecords(){
        ArrayList<Petcategory> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from pet_category");
            while(rs.next()){
                Petcategory petcategory = new Petcategory();
                petcategory.setId(rs.getInt("id"));
                petcategory.setName(rs.getString("name"));
                arrayList.add(petcategory);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void delete(String id){
        String query = "delete from pet_category where id='"+id+"'";
        DbOperations.setDataOrDelete(query, "Pet Category Deleted Successfully");
    }
}
