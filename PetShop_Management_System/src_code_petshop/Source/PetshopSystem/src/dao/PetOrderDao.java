/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import model.PetOrder;

/**
 *
 * @author Admin
 */
public class PetOrderDao {
    public static String EscapeBack(String path){
        String newpath = path.replaceAll("\\\\", "\\\\\\\\");
        return newpath;
    }
    public static void save(PetOrder order){      
        String query = "insert into pet_order("
                + "order_id, "
                + "pet_id, "
                + "qr_to_web"
                + ") values("
                + ""+order.getOrder_id()+","
                + ""+order.getPet_id()+",'"
                +EscapeBack(order.getQr_to_web())+"')";
        System.err.println(query);
        DbOperations.setDataOrDelete(query, "Thêm đặt hàng thú cưng thành công");      
    }
}
