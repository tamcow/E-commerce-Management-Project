/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ProductOrder;

/**
 *
 * @author Admin
 */
public class ProductOrderDao {
    public static void save(ProductOrder productOrder){      
        String query = "insert into product_order("
                + "order_id, "
                + "product_id, "
                + "quantity, "
                + "total "
                + ") values("
                + ""+productOrder.getOrder_id()+","
                + ""+productOrder.getProduct_id()+","
                + ""+productOrder.getQuantity()+","
                +productOrder.getTotal()+")";
        DbOperations.setDataOrDelete(query, "Thêm đặt hàng sản phẩm thành công");      
    }
}
