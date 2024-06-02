/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import javax.swing.JOptionPane;
import java.io.File;

/**
 *
 * @author Admin
 */
public class OpenPdf {
    public static void openBarcodeByBarcode(String barcode) {
        try {
            if((new File(System.getProperty("user.dir") + "\\src\\file\\productBarcode\\" + barcode + ".pdf")).exists()){
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll, FileProtocolHandler " + System.getProperty("user.dir") + "\\src\\file\\productBarcode\\" + barcode + ".pdf");
            } else {
                JOptionPane.showMessageDialog(null, "File is not Exists");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void openBill(String path) {
        try {
            if((new File(System.getProperty("user.dir") + "\\src\\file\\bill\\" + path + ".pdf")).exists()){
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll, FileProtocolHandler " + System.getProperty("user.dir") + "\\src\\file\\bill\\" + path + ".pdf");
            } else {
                JOptionPane.showMessageDialog(null, "File is not Exists");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
