/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import static dao.PetDao.EscapeBack;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Product;
import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;
/**
 *
 * @author Admin
 */
public class ProductDao {
    public static int save(Product product){
        String query = "insert into product("
                + "name, "
                + "product_category_id, "
                + "pet_category_id, "
                + "description, "
                + "quantity, "
                + "vendor_price, "
                + "retail_price, "
                + "barcode, "
                + "discount "
                + ") values("
                + "'"+product.getName()+"',"
                +product.getProduct_category_id()+","
                +product.getPet_category_id()+",'"
                +product.getDescription()+"',"
                +product.getQuantity()+","
                +product.getVendor_price()+","
                +product.getRetail_price()+",'"
                +product.getBarcode()+"',"
                +product.getDiscount()+")";
        System.out.println(query);
        int id = DbOperations.setDataOrDeleteAndGetData(query);
        
        return id;
    }
    
    public static void updateImage(int id, String path){
        String query = "update product set "
                + "image = '"+EscapeBack(path)+"' "
                + "where id = " + id;
        DbOperations.setDataOrDelete(query, "Cập nhật đường dẫn sản phẩm thành công");
    }
    
    public static ArrayList<Product> getAllRecords(){
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT product.*,  pet_category.name as pet_category_name, product_category.name as product_category_name " +
                                                "FROM product, pet_category, product_category " +
                                                "WHERE pet_category.id = product.pet_category_id " +
                                                "AND product_category.id = product.product_category_id; ");
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setProduct_category_id(rs.getInt("product_category_id"));
                product.setProduct_category_name(rs.getString("product_category_name"));
                product.setPet_category_id(rs.getInt("pet_category_id"));
                product.setPet_category_name(rs.getString("pet_category_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVendor_price(rs.getInt("vendor_price"));
                product.setRetail_price(rs.getInt("retail_price"));
                product.setBarcode(rs.getString("barcode"));
                product.setDiscount(rs.getInt("discount"));
                product.setImage(rs.getString("image"));
                arrayList.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void update(Product product){
        String query = "update product set "
                + "name = '"+product.getName()+"',"
                + "product_category_id = '"+product.getProduct_category_id()+"',"
                + "pet_category_id = '"+product.getPet_category_id()+"',"
                + "description = '"+product.getDescription()+"',"
                + "quantity = '"+product.getQuantity()+"',"
                + "vendor_price = '"+product.getVendor_price()+"',"
                + "retail_price = '"+product.getRetail_price()+"',"
                + "barcode = '"+product.getBarcode()+"',"
                + "discount = '"+product.getDiscount()+"',"
                + "image = '"+EscapeBack(product.getImage())+"' "
                + "where id = '"+product.getId()+"'";
        DbOperations.setDataOrDelete(query, "Chỉnh sửa sản phẩm thành công");     
    }
    
    public static void delete(String id){
        String query = "delete from product where id = '"+id+"'";
        DbOperations.setDataOrDelete(query, "Xóa sản phẩm thành công");          
    }
    
    public static ArrayList<Product> getAllRecordsByCategory(String category){
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from product where category ='"+category+"'");
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
    
    public static ArrayList<Object[]> getBeginCountByPetCategory(java.util.Date fromDate, java.util.Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_import.quantity) as product_amount, product_category.name as category_name " +
                                                "FROM product, product_category, product_import " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND product_import.product_id = product.id " +
                                                "AND product_import.created_at < '"+fromDateSql+"' " +
                                                "GROUP BY category_name");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("product_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getImportCountByPetCategory(java.util.Date fromDate, java.util.Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_import.quantity) as product_amount, product_category.name as category_name " +
                                                "FROM product, product_category, product_import " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND product_import.product_id = product.id " +
                                                "AND product_import.created_at between '"+fromDateSql+"' and '"+toDateSql+"' " +
                                                "GROUP BY category_name");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("product_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getExportCountByPetCategory(java.util.Date fromDate, java.util.Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_order.quantity) as product_amount, product_category.name as category_name " +
                                                "FROM product, product_category, `order`, product_order " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND  product_order.product_id = product.id " +
                                                "AND  `order`.id = product_order.order_id " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                "GROUP BY category_name" +
                                                ";");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("product_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getBeginCountByPetType(java.util.Date fromDate, java.util.Date toDate, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_import.quantity) as product_amount, pet_category.name as category_name " +
                                                "FROM product, product_category, product_import, pet_category " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND pet_category.id = product.pet_category_id " +
                                                "AND product_import.product_id = product.id " +
                                                "AND  product_category.id = "+id+" " +
                                                "AND product_import.created_at < '"+fromDateSql+"' " +
                                                "GROUP BY category_name");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("product_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getImportCountByPetType(java.util.Date fromDate, java.util.Date toDate, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_import.quantity) as product_amount, pet_category.name as category_name " +
                                                "FROM product, product_category, product_import, pet_category " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND pet_category.id = product.pet_category_id " +
                                                "AND product_import.product_id = product.id " +
                                                "AND  product_category.id = "+id+" " +
                                                "AND product_import.created_at between '"+fromDateSql+"' and '"+toDateSql+"' " +
                                                "GROUP BY category_name");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("product_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static ArrayList<Object[]> getExportCountByPetType(java.util.Date fromDate, java.util.Date toDate, int id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_order.quantity) as product_amount, pet_category.name as category_name " +
                                                "FROM product, product_category, `order`, product_order, pet_category " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND pet_category.id = product.pet_category_id " +
                                                "AND  product_order.product_id = product.id " +
                                                "AND  `order`.id = product_order.order_id " +
                                                "AND  product_category.id = "+id+" " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                "GROUP BY category_name" +
                                                ";");
            while(rs.next()){             
                arrayList.add(new Object[]{rs.getString("category_name"), rs.getString("product_amount")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static int getBeginPet(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        int beginNumber = 0;
        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(product_import.quantity) as product_amount " +
                                                "FROM product, product_category, product_import " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND product_import.product_id = product.id " +
                                                "AND product_import.created_at < '"+fromDateSql+"' ");
            while(rs.next()){             
                beginNumber = rs.getInt("product_amount");
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
            ResultSet rs = DbOperations.getData("SELECT SUM(product_import.quantity) as product_amount " +
                                                "FROM product, product_category, product_import " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND product_import.product_id = product.id " +
                                                "AND product_import.created_at between '"+fromDateSql+"' and '"+toDateSql+"' ");
            while(rs.next()){             
                importNumber = rs.getInt("product_amount");
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
            ResultSet rs = DbOperations.getData("SELECT SUM(product_order.quantity) as product_amount " +
                                                "FROM product, product_category, `order`, product_order " +
                                                "WHERE product_category.id = product.product_category_id " +
                                                "AND  product_order.product_id = product.id " +
                                                "AND  `order`.id = product_order.order_id " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                ";");
            while(rs.next()){             
                exportNumber = rs.getInt("product_amount");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return exportNumber;
    }
    
    public static ArrayList<Product> filterProductByName(String keySearch, int pet_categoty_id, int product_category_id){
        ArrayList<Product> arrayList = new ArrayList<>();

        try {
            ResultSet rs = DbOperations.getData("SELECT product.*,  pet_category.name as pet_category_name, product_category.name as product_category_name " +
                                                "FROM product, pet_category, product_category " +
                                                "WHERE pet_category.id = product.pet_category_id " +
                                                "AND product_category.id = product.product_category_id " +
                                                "AND product.pet_category_id = "+Integer.toString(pet_categoty_id)+ " " +
                                                "AND product.product_category_id = "+Integer.toString(product_category_id)+ " " +
                                                "AND product.name like '%"+keySearch+"%';");
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setProduct_category_id(rs.getInt("product_category_id"));
                product.setProduct_category_name(rs.getString("product_category_name"));
                product.setPet_category_id(rs.getInt("pet_category_id"));
                product.setPet_category_name(rs.getString("pet_category_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVendor_price(rs.getInt("vendor_price"));
                product.setRetail_price(rs.getInt("retail_price"));
                product.setBarcode(rs.getString("barcode"));
                product.setDiscount(rs.getInt("discount"));
                product.setImage(rs.getString("image"));
                arrayList.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static Product getProductById(String id){
        Product product = new Product();
        try {
            ResultSet rs = DbOperations.getData("SELECT product.*,  pet_category.name as pet_category_name, product_category.name as product_category_name " +
                                                "FROM product, pet_category, product_category " +
                                                "WHERE pet_category.id = product.pet_category_id " +
                                                "AND product_category.id = product.product_category_id " + 
                                                "AND product.id = "+id);
            while(rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setProduct_category_id(rs.getInt("product_category_id"));
                product.setProduct_category_name(rs.getString("product_category_name"));
                product.setPet_category_id(rs.getInt("pet_category_id"));
                product.setPet_category_name(rs.getString("pet_category_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVendor_price(rs.getInt("vendor_price"));
                product.setRetail_price(rs.getInt("retail_price"));
                product.setBarcode(rs.getString("barcode"));
                product.setDiscount(rs.getInt("discount"));
                product.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return product;
    }
    
    public static Product getProductByBarCode(String barcode){
        Product product = new Product();
        try {
            ResultSet rs = DbOperations.getData("SELECT product.*,  pet_category.name as pet_category_name, product_category.name as product_category_name " +
                                                "FROM product, pet_category, product_category " +
                                                "WHERE pet_category.id = product.pet_category_id " +
                                                "AND product_category.id = product.product_category_id " + 
                                                "AND barcode = '"+barcode+"'");
            while(rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setProduct_category_id(rs.getInt("product_category_id"));
                product.setProduct_category_name(rs.getString("product_category_name"));
                product.setPet_category_id(rs.getInt("pet_category_id"));
                product.setPet_category_name(rs.getString("pet_category_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setVendor_price(rs.getInt("vendor_price"));
                product.setRetail_price(rs.getInt("retail_price"));
                product.setBarcode(rs.getString("barcode"));
                product.setDiscount(rs.getInt("discount"));
                product.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return product;
    }
    
    public static ArrayList<Object[]> getRevenueByDate(java.util.Date fromDate, java.util.Date toDate){
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
                                                "select d.dt date, coalesce(ord.sum, 0) total " +
                                                "from all_dates d " +
                                                "left join ( " +
                                                "	select `order`.created_at, sum(product_order.total) sum " +
                                                "	from `order`, product_order " +
                                                "    where `order`.id = product_order.order_id " +
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
    
//    public static ArrayList<Properties> getBeginListByDateAndType(java.util.Date fromDate, java.util.Date toDate, int typeId){
//        String typeIdSql = "";
//        if(typeId == 0){
//            typeIdSql = "";
//        }else{
//            typeIdSql = Integer.toString(typeId);
//        }
//        ArrayList<Properties> arrayList = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String fromDateSql = sdf.format(fromDate);
//        String toDateSql = sdf.format(toDate);
//        try {
//            ResultSet rs = DbOperations.getData("SELECT product.id, product.name, product_category.name as product_category_name, pet_category.name as pet_category_name, product_order.quantity, product_order.total, `order`.created_at " +
//                                                "FROM product, product_category, product_import, pet_category " +
//                                                "WHERE product_category.id = product.product_category_id " +
//                                                "AND pet_category.id = product.pet_category_id " +
//                                                "AND product_import.product_id = product.id " +
//                                                "AND  product_category.id like '%"+typeIdSql+"%' " +
//                                                "AND product_import.created_at < '"+fromDateSql+"' ");
//            while(rs.next()){             
//                Properties properties = new Properties();
//                properties.put("id", rs.getInt("id"));
//                properties.put("name", rs.getString("name"));
//                properties.put("product_category_name", rs.getString("product_category_name"));
//                properties.put("pet_category_name", rs.getString("pet_category_name"));
//                properties.put("quantity", rs.getInt("quantity"));
//                properties.put("total", rs.getInt("total"));
//                properties.put("created_at", rs.getString("created_at"));
//                arrayList.add(properties);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return arrayList;
//    }
//    
//    public static ArrayList<Properties> getImportListByDateAndType(java.util.Date fromDate, java.util.Date toDate, int typeId){
//        String typeIdSql = "";
//        if(typeId == 0){
//            typeIdSql = "";
//        }else{
//            typeIdSql = Integer.toString(typeId);
//        }
//        ArrayList<Properties> arrayList = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String fromDateSql = sdf.format(fromDate);
//        String toDateSql = sdf.format(toDate);
//        try {
//            ResultSet rs = DbOperations.getData("SELECT product.id, product.name, product_category.name as product_category_name, pet_category.name as pet_category_name, product_order.quantity, product_order.total, `order`.created_at " +
//                                                "FROM product, product_category, product_import, pet_category, product_order " +
//                                                "WHERE product_category.id = product.product_category_id " +
//                                                "AND pet_category.id = product.pet_category_id " +
//                                                "AND product_import.product_id = product.id " +
//                                                "AND  product_category.id like '%"+typeIdSql+"%' " +
//                                                "AND product_import.created_at between '"+fromDateSql+"' and '"+toDateSql+"' ");
//            while(rs.next()){             
//                Properties properties = new Properties();
//                properties.put("id", rs.getInt("id"));
//                properties.put("name", rs.getString("name"));
//                properties.put("product_category_name", rs.getString("product_category_name"));
//                properties.put("pet_category_name", rs.getString("pet_category_name"));
//                properties.put("quantity", rs.getInt("quantity"));
//                properties.put("total", rs.getInt("total"));
//                properties.put("created_at", rs.getString("created_at"));
//                arrayList.add(properties);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return arrayList;
//    }
//    
//    public static ArrayList<Properties> getExportListByDateAndType(java.util.Date fromDate, java.util.Date toDate, int typeId){
//        String typeIdSql = "";
//        if(typeId == 0){
//            typeIdSql = "";
//        }else{
//            typeIdSql = Integer.toString(typeId);
//        }
//        ArrayList<Properties> arrayList = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String fromDateSql = sdf.format(fromDate);
//        String toDateSql = sdf.format(toDate);
//        try {
//            ResultSet rs = DbOperations.getData("select product.id, product.name, product_category.name as product_category_name, pet_category.name as pet_category_name, product_order.quantity, product_order.total, `order`.created_at " +
//                                                "from `order`, product, product_order, product_category, pet_category " +
//                                                "where `order`.id = product_order.order_id " +
//                                                "AND pet_category.id = product.pet_category_id " +
//                                                "and product.id = product_order.product_id " +
//                                                "and product.product_category_id = product_category.id " +
//                                                "AND  product_category.id like '%"+typeIdSql+"%' " +
//                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
//                                                ";");
//            while(rs.next()){             
//                Properties properties = new Properties();
//                properties.put("id", rs.getInt("id"));
//                properties.put("name", rs.getString("name"));
//                properties.put("product_category_name", rs.getString("product_category_name"));
//                properties.put("pet_category_name", rs.getString("pet_category_name"));
//                properties.put("quantity", rs.getInt("quantity"));
//                properties.put("total", rs.getInt("total"));
//                properties.put("created_at", rs.getString("created_at"));
//                arrayList.add(properties);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return arrayList;
//    }
    
    public static ArrayList<Properties> getExportListByDate(java.util.Date fromDate, java.util.Date toDate){
        ArrayList<Properties> arrayList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fromDateSql = sdf.format(fromDate);
        String toDateSql = sdf.format(toDate);
        try {
            ResultSet rs = DbOperations.getData("select product.id, product.name, product_category.name as product_category_name, pet_category.name as pet_category_name, product_order.quantity, product_order.total, `order`.created_at " +
                                                "from `order`, product, product_order, product_category, pet_category " +
                                                "where `order`.id = product_order.order_id " +
                                                "and product.id = product_order.product_id " +
                                                "and product.product_category_id = product_category.id " +
                                                "and product.pet_category_id = pet_category.id " +
                                                "AND `order`.created_at BETWEEN '" + fromDateSql + "' and '" + toDateSql + "' " +
                                                ";");
            while(rs.next()){             
                Properties properties = new Properties();
                properties.put("id", rs.getInt("id"));
                properties.put("name", rs.getString("name"));
                properties.put("product_category_name", rs.getString("product_category_name"));
                properties.put("pet_category_name", rs.getString("pet_category_name"));
                properties.put("quantity", rs.getInt("quantity"));
                properties.put("total", rs.getInt("total"));
                properties.put("created_at", rs.getString("created_at"));
                arrayList.add(properties);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void reduceQuantity(int id, int quantity){
        String query = "update product set "
                + "quantity = quantity - "+quantity+" "
                + "where id = "+id+";";
        DbOperations.setDataOrDelete(query, "Chỉnh sửa sản phẩm thành công");     
    }
    
    public static void increseQuantity(int id, int quantity){
        String query = "update product set "
                + "quantity = quantity + "+quantity+" "
                + "where id = "+id+";";
        DbOperations.setDataOrDelete(query, "Chỉnh sửa sản phẩm thành công");     
    }
    
    
    public static void importQuantityOfProduct(int id, int quantity, Date today){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todaySql = sdf.format(today);
        String query = "insert into product_import("
                + "product_id, "
                + "quantity, "
                + "created_at "
                + ") values("
                + ""+id+","
                +quantity+",'"
                +todaySql+"'"
                +")";
        int idReturn = DbOperations.setDataOrDeleteAndGetData(query);
    }
}
