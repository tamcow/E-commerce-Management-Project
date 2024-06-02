/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Department;

/**
 *
 * @author Admin
 */
public class DepartmentDao {
    public static ArrayList<Department> getAllRecords() {
        ArrayList<Department> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("Select * from department where id != 'AD'");
            while(rs.next()){
                Department department = new Department();
                department.setId(rs.getString("id"));
                department.setName(rs.getString("name"));
                arrayList.add(department);
            }     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
}
