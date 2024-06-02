/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import model.Pet;
import model.Product;

/**
 *
 * @author Admin
 */
public class PetDao {
    public static String EscapeBack(String path){
        String newpath = path.replaceAll("\\\\", "\\\\\\\\");
        return newpath;
    }
    public static int save(Pet pet){      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateBirthday = sdf.format(pet.getBirthday());
        String dateImportday = sdf.format(pet.getImportday());
        String query = "insert into pet("
                + "category_id, "
                + "type, "
                + "color, "
                + "birthday, "
                + "sex, "
                + "weight, "
                + "height, "
                + "retail_price, "
                + "vendor_price, "
                + "description, "
                + "importday, "
                + "adopted "
                + ") values("
                + ""+pet.getCategory_id()+",'"
                +pet.getType()+"','"
                +pet.getColor()+"','"
                +dateBirthday+"','"
                +pet.getSex()+"',"
                +pet.getWeight()+","
                +pet.getHeight()+","
                +pet.getRetail_price()+","
                +pet.getVendor_price()+",'"
                +pet.getDescription()+"','"
                +dateImportday+"',"
                +"0)";
        System.out.println(query);
        int id = DbOperations.setDataOrDeleteAndGetData(query);
        
        return id;
    }
    
    public static void updateImage(int id, String path){
        String query = "update pet set "
                + "image = '"+EscapeBack(path)+"' "
                + "where id = " + id;
        DbOperations.setDataOrDelete(query, "Cập nhật đường dẫn thú cưng thành công");
    }
    
    public static ArrayList<Pet> getAllRecords(){
        ArrayList<Pet> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT pet.*,  pet_category.name as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id;");
            while(rs.next()){
                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setCategory_id(rs.getInt("category_id"));
                pet.setCategory_name(rs.getString("category_name"));
                pet.setType(rs.getString("type"));
                pet.setColor(rs.getString("color"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBirthday = (Date) formatter.parse(rs.getString("birthday"));
                pet.setBirthday(dateBirthday);
                pet.setSex(rs.getString("sex"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setHeight(rs.getDouble("height"));
                pet.setRetail_price(rs.getInt("retail_price"));
                pet.setVendor_price(rs.getInt("vendor_price"));
                pet.setDescription(rs.getString("description"));
                Date dateImportday = (Date) formatter.parse(rs.getString("importday"));
                pet.setImportday(dateImportday);
                pet.setImage(rs.getString("image"));
                
                arrayList.add(pet);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Pet> searchPet(String id, int category){
        ArrayList<Pet> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT pet.*,  pet_category.name as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet.id like '%"+id+"%' " +
                                                (category != 0 ? "AND pet_category.id = "  + category : " ") +
                                                ";");
            while(rs.next()){
                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setCategory_id(rs.getInt("category_id"));
                pet.setCategory_name(rs.getString("category_name"));
                pet.setType(rs.getString("type"));
                pet.setColor(rs.getString("color"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBirthday = (Date) formatter.parse(rs.getString("birthday"));
                pet.setBirthday(dateBirthday);
                pet.setSex(rs.getString("sex"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setHeight(rs.getDouble("height"));
                pet.setRetail_price(rs.getInt("retail_price"));
                pet.setVendor_price(rs.getInt("vendor_price"));
                pet.setDescription(rs.getString("description"));
                Date dateImportday = (Date) formatter.parse(rs.getString("importday"));
                pet.setImportday(dateImportday);
                pet.setImage(rs.getString("image"));
                
                arrayList.add(pet);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getBeginCountByPetCategory(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount,  pet_category.name as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND importday < '" + fromDateSql + "' " +
                                                "AND adopted = 0 " +
                                                "GROUP BY category_name;");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("pet_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getImportCountByPetCategory(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount,  pet_category.name as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND importday BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                "GROUP BY category_name;");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("pet_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getExportCountByPetCategory(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount,  pet_category.name as category_name " +
                                                "FROM pet, pet_category, pet_order, `order` " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet_order.pet_id = pet.id " +
                                                "AND pet_order.order_id = `order`.id " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                "GROUP BY category_name" +
                                                ";");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("pet_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getBeginCountByPetType(Date fromDate, Date toDate, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount, pet.type as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet.category_id = " +id+ " "+
                                                "AND importday < '" + fromDateSql + "' " +
                                                "GROUP BY pet.type;");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("pet_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getImportCountByPetType(Date fromDate, Date toDate, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount, pet.type as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet.category_id = " +id+ " "+
                                                "AND importday BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                "GROUP BY pet.type;");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("pet_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getExportCountByPetType(Date fromDate, Date toDate, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount, pet.type as category_name " +
                                                "FROM pet, pet_category, pet_order, `order` " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet_order.pet_id = pet.id " +
                                                "AND pet_order.order_id = `order`.id " +
                                                "AND pet.category_id = " +id+ " "+
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                "GROUP BY pet.type" +
                                                ";");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("pet_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getRevenueByDate(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("with recursive all_dates(dt) as (" +
                                                "    select '"+fromDateSql+"' dt " +
                                                "	union all " +
                                                "    select dt + interval 1 day from all_dates where dt + interval 1 day <= '"+toDateSql+"'" +
                                                ") " +
                                                "select d.dt date, coalesce(ord.sum , 0) total " +
                                                "from all_dates d " +
                                                "left join ( " +
                                                "	select `order`.created_at, sum(pet.retail_price) as sum " +
                                                "	from `order`, pet, pet_order " +
                                                "    where `order`.id = pet_order.order_id " +
                                                "    and pet.id = pet_order.pet_id " +
                                                "group by `order`.created_at " +
                                                ") ord " +
                                                "on ord.created_at = d.dt " +
                                                "order by d.dt");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("date"), rs.getInt("total")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void update(Pet pet){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateBirthday = sdf.format(pet.getBirthday());
        String dateImportday = sdf.format(pet.getImportday());
        String query = "update pet set "
                + "category_id = '"+pet.getCategory_id()+"',"
                + "type = '"+pet.getType()+"',"
                + "color = '"+pet.getColor()+"',"
                + "birthday = '"+dateBirthday+"',"
                + "sex = '"+pet.getSex()+"',"
                + "weight = '"+pet.getWeight()+"',"
                + "height = '"+pet.getHeight()+"',"
                + "retail_price = '"+pet.getRetail_price()+"',"
                + "vendor_price = '"+pet.getVendor_price()+"',"
                + "description = '"+pet.getDescription()+"',"
                + "importday = '"+dateImportday+"',"
                + "image = '"+EscapeBack(pet.getImage())+"' "
                + "where id = '"+pet.getId()+"'";
        DbOperations.setDataOrDelete(query, "Sửa thú cưng thành công");     
    }
    
    public static void delete(String id){
        String query = "delete from pet where id = '"+id+"'";
        DbOperations.setDataOrDelete(query, "Xóa thú cưng thành công");          
    }
    
    public static ArrayList<Pet> getAllRecordsByCategory(String searchKey, int petCategory_id){
        ArrayList<Pet> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT pet.*,  pet_category.name as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id "
                                                + "AND pet.adopted = 0 "
                                                + "AND pet.category_id = "+ petCategory_id +" "
                                                + "AND pet.id like'%"+searchKey+"%'");
            while(rs.next()){
                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setCategory_id(rs.getInt("category_id"));
                pet.setCategory_name(rs.getString("category_name"));
                pet.setType(rs.getString("type"));
                pet.setColor(rs.getString("color"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBirthday = (Date) formatter.parse(rs.getString("birthday"));
                pet.setBirthday(dateBirthday);
                pet.setSex(rs.getString("sex"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setHeight(rs.getDouble("height"));
                pet.setRetail_price(rs.getInt("retail_price"));
                pet.setVendor_price(rs.getInt("vendor_price"));
                pet.setDescription(rs.getString("description"));
                Date dateImportday = (Date) formatter.parse(rs.getString("importday"));
                pet.setImportday(dateImportday);
                pet.setImage(rs.getString("image"));
                
                arrayList.add(pet);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Product> filterProductByName(String name, String category){
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from product where name like'%"+name+"%' and category = '"+category+"'");
            while(rs.next()){
                Product product = new Product();
                product.setName(rs.getString("name"));
                arrayList.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static Pet getPetById(String id){
        Pet pet = new Pet();
        try {
            ResultSet rs = DbOperations.getData("SELECT pet.*,  pet_category.name as category_name " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id "
                                                + "AND pet.id = "+ id +";");
            while(rs.next()){
                pet.setId(rs.getInt("id"));
                pet.setCategory_id(rs.getInt("category_id"));
                pet.setCategory_name(rs.getString("category_name"));
                pet.setType(rs.getString("type"));
                pet.setColor(rs.getString("color"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateBirthday = (Date) formatter.parse(rs.getString("birthday"));
                pet.setBirthday(dateBirthday);
                pet.setSex(rs.getString("sex"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setHeight(rs.getDouble("height"));
                pet.setRetail_price(rs.getInt("retail_price"));
                pet.setVendor_price(rs.getInt("vendor_price"));
                pet.setDescription(rs.getString("description"));
                Date dateImportday = (Date) formatter.parse(rs.getString("importday"));
                pet.setImportday(dateImportday);
                pet.setImage(rs.getString("image"));                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return pet;
    }
    
    public static int getBeginPet(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        int beginNumber = 0;
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND importday < '" + fromDateSql + "' " +
                                                ";");
            while(rs.next()){             
                beginNumber = rs.getInt("pet_amount");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return beginNumber;
    }
    
    public static int getImportPet(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        int importNumber = 0;
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount " +
                                                "FROM pet, pet_category " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND importday BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                ";");
            while(rs.next()){             
                importNumber = rs.getInt("pet_amount");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return importNumber;
    }
    
    public static int getExportPet(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        int exportNumber = 0;
        try {
            ResultSet rs = DbOperations.getData("SELECT COUNT(pet.id) as pet_amount " +
                                                "FROM pet, pet_category, pet_order, `order` " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet_order.pet_id = pet.id " +
                                                "AND pet_order.order_id = `order`.id " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                ";");
            while(rs.next()){             
                exportNumber = rs.getInt("pet_amount");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return exportNumber;
    }
    
    public static ArrayList<Properties> getExportListByDate(Date fromDate, Date toDate){
        ArrayList<Properties> arrayList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        try {
            ResultSet rs = DbOperations.getData("SELECT pet.id, pet_category.name as category_name, pet.type, pet.retail_price, `order`.created_at " +
                                                "FROM pet, pet_category, pet_order, `order` " +
                                                "WHERE pet_category.id = pet.category_id " +
                                                "AND pet_order.pet_id = pet.id " +
                                                "AND pet_order.order_id = `order`.id " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                ";");
            while(rs.next()){             
                Properties properties = new Properties();
                properties.put("id", rs.getInt("id"));
                properties.put("category_name", rs.getString("category_name"));
                properties.put("type", rs.getString("type"));
                properties.put("retail_price", rs.getString("retail_price"));
                properties.put("created_at", rs.getString("created_at"));
                arrayList.add(properties);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
        
    public static void setSold(int id){
        String query = "update pet set "
                + "adopted = 1 "
                + "where id = " + id;
        DbOperations.setDataOrDelete(query, "Cập nhật thú cưng thành đã bán thành công");
    }
}
