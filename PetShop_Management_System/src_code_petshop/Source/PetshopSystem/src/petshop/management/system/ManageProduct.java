/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petshop.management.system;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import common.OpenPdf;
import dao.PetCategoryDao;
import dao.PetDao;
import dao.ProductCategoryDao;
import dao.ProductDao;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import model.Pet;
import model.Petcategory;
import model.Product;
import model.Productcategory;
import model.User;

/**
 *
 * @author Admin
 */
public class ManageProduct extends javax.swing.JFrame {
    public User user;

    /**
     * Creates new form ViewEditDeleteProduct
     */
    public ManageProduct() {
        initComponents();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public ManageProduct(User user) {
        this.user = user;
        initComponents();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    class CategoryItem
    {
        private int id = 0;
        private String name = "";
        public CategoryItem(int id, String name) {
            this.id = id;
            this.name = name;
        } 
        public int getId(){
            return id;
        }
        public String getName(){
            return name;
        }
        
        @Override
        public String toString() {
            return name;
        }
    }
    
    public static void createPDF(String pdfFilename,String myString) {
	    Document doc = new Document();
	    PdfWriter docWriter = null;
	    try {
	        docWriter = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir") + "\\src\\file\\productBarcode\\" + pdfFilename));
	        doc.addAuthor("2K");
	        doc.addCreationDate();
	        doc.addProducer();
	        doc.addCreator("chillyfacts.com");
	        doc.addTitle("chillyfacts Barcode test");
	        doc.setPageSize(PageSize.LETTER);
	        doc.open();
	        PdfContentByte cb = docWriter.getDirectContent();

	        Barcode128 code128 = new Barcode128();
	        code128.setCode(myString.trim());
	        code128.setCodeType(Barcode128.CODE128);
	        com.itextpdf.text.Image code128Image = code128.createImageWithBarcode(cb, null, null);
	        code128Image.setAbsolutePosition(10, 700);
	        code128Image.scalePercent(125);
	        doc.add(code128Image);

	        BarcodeEAN codeEAN = new BarcodeEAN();
	        codeEAN.setCode(myString.trim());
	        codeEAN.setCodeType(BarcodeEAN.EAN13);      
	        com.itextpdf.text.Image codeEANImage = code128.createImageWithBarcode(cb, null, null);
	        codeEANImage.setAbsolutePosition(10, 600);
	        codeEANImage.scalePercent(125);
	        doc.add(codeEANImage);
	    } catch (DocumentException dex) {
	        dex.printStackTrace();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        if (doc != null) {
	            doc.close();
	        }
	        if (docWriter != null) {
	            docWriter.close();
	        }
	    }
	}
    
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    public void validateFields(){
//        String name = txtName.getText();
//        String price = txtPrice.getText();
//        String category = (String)jComboBox1.getSelectedItem();
//        if(!name.equals("") && !price.equals("") && category != null){
//            btnUpdate.setEnabled(true);
//        } else {
//            btnUpdate.setEnabled(false);
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblImgPath = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cbPetCategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtVendorPrice = new javax.swing.JTextField();
        txtRetailPrice = new javax.swing.JTextField();
        btnImage = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        lblImgPath1 = new javax.swing.JLabel();
        cbProductCategory = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lblBarcode = new javax.swing.JLabel();
        btnPrintBarcode = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        spQuantityImport = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 40, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        lblId.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblId.setText("00");
        getContentPane().add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 20, -1));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 80, -1));

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btnClear.setText("Hủy");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 80, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Mã loại", "Loại", "Mã loài", "Loài", "Số lượng", "Mô tả", "Giá nhập", "Giá bán", "Mã code", "Giảm giá", "Đường dẫn"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 1200, 260));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(143, 84, 46));
        jLabel20.setText("Quản lý sản phẩm thú cưng");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setText("Thêm sản phẩm thú cưng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        lblImgPath.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        getContentPane().add(lblImgPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, 410, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 47, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Loài:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 190, -1));

        cbPetCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPetCategoryActionPerformed(evt);
            }
        });
        getContentPane().add(cbPetCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 190, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Loại:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 20));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Số lượng:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, 20));

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        getContentPane().add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 190, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giá nhập:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Giá bán:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mô tả:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jScrollPane3.setHorizontalScrollBar(null);

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane3.setViewportView(txtDescription);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 230, 60));
        getContentPane().add(txtVendorPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 230, -1));
        getContentPane().add(txtRetailPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 230, -1));

        btnImage.setText("Chọn ảnh");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });
        getContentPane().add(btnImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 90, -1));

        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 410, 220));

        lblImgPath1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        getContentPane().add(lblImgPath1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 410, 20));

        getContentPane().add(cbProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 190, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Giảm giá:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 20));
        getContentPane().add(txtDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 230, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Code mã vạch:");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, -1, 20));
        getContentPane().add(lblBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 190, 20));

        btnPrintBarcode.setText("In mã vạch");
        btnPrintBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintBarcodeActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrintBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Thêm số lượng cho sản phẩm này:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, 20));
        getContentPane().add(spQuantityImport, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 100, -1));

        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 100, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/@reallygreatsite/4.png"))); // NOI18N
        jLabel6.setText(" ");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // TODO add your handling code here:
            //        Product product = new Product();
            //        int id = Integer.parseInt(lblId.getText());
            //        product.setId(id);
            //        product.setName(txtName.getText());
            //        product.setCategory((String)jComboBox1.getSelectedItem());
            //        product.setPrice(txtPrice.getText());
            //        ProductDao.update(product);
            //
            Product product = new Product();
            int id = Integer.parseInt(lblId.getText());
            product.setId(id);
            product.setPet_category_id((int)((CategoryItem)cbPetCategory.getSelectedItem()).getId());
            product.setProduct_category_id((int)((CategoryItem)cbProductCategory.getSelectedItem()).getId());
            product.setName(txtName.getText());
            product.setDescription(txtDescription.getText());
            product.setQuantity(Integer.parseInt(txtQuantity.getText()));
            product.setVendor_price(Integer.parseInt(txtVendorPrice.getText()));
            product.setRetail_price(Integer.parseInt(txtRetailPrice.getText()));
            product.setBarcode(lblBarcode.getText());
            product.setDiscount(Integer.parseInt(txtDiscount.getText()));
            createPDF(lblBarcode.getText() + ".pdf", lblBarcode.getText());
            product.setImage(lblImgPath.getText());

            String imageProductPath = lblImgPath.getText();
            String newPath = System.getProperty("user.dir") + "\\src\\file\\productImage\\";

            File directory = new File(newPath);
            if(!directory.exists()){
                directory.mkdir();
            }

            File sourceFile = null;
            File destinationFile = null;
            String extension = imageProductPath.substring(imageProductPath.lastIndexOf('.') + 1);
            sourceFile = new File(imageProductPath);
            destinationFile = new File(newPath + Integer.toString(id) + "." + extension);

            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            product.setImage("\\src\\file\\productImage\\" + id + "." + extension);

            ProductDao.update(product);
            setVisible(false);
            new ManageProduct().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ManageProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        ArrayList<Product> list = ProductDao.getAllRecords();
        Iterator<Product> itr = list.iterator();
        while(itr.hasNext()){           
            Product productObj = itr.next();
            dtm.addRow(new Object[]{
                productObj.getId(),
                productObj.getName(),
                productObj.getProduct_category_id(), 
                productObj.getProduct_category_name(),
                productObj.getPet_category_id(),
                productObj.getPet_category_name(),
                productObj.getQuantity(),
                productObj.getDescription(),
                productObj.getVendor_price(),
                productObj.getRetail_price(),
                productObj.getBarcode(),
                productObj.getDiscount(),
                productObj.getImage(),
            });           
        }
        jTable1.getColumnModel().getColumn(2).setMinWidth(0);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
    }//GEN-LAST:event_formComponentShown

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here
        int index = jTable1.getSelectedRow();
        if(jTable1.getRowSorter() != null){
            index = jTable1.getRowSorter().convertRowIndexToModel(index);
        }
        
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        lblId.setText(id);
        String name = model.getValueAt(index, 1).toString();
        txtName.setText(name);
        String productCategory = model.getValueAt(index, 3).toString();
        String petCategory = model.getValueAt(index, 5).toString();
        String quantity = model.getValueAt(index, 6).toString();
        txtQuantity.setText(quantity);
        String description = model.getValueAt(index, 7).toString();
        txtDescription.setText(description);
        String vendor_price = model.getValueAt(index, 8).toString();
        txtVendorPrice.setText(vendor_price);
        String retail_price = model.getValueAt(index, 9).toString();
        txtRetailPrice.setText(retail_price);
        String barcode = model.getValueAt(index, 10).toString();
        lblBarcode.setText(barcode);
        String discount = model.getValueAt(index, 11).toString();
        txtDiscount.setText(discount);
        lblImage.setIcon(ResizeImage(String.valueOf(System.getProperty("user.dir") + model.getValueAt(index, 12).toString())));
        lblImgPath.setText(System.getProperty("user.dir") + model.getValueAt(index, 12).toString());
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        cbProductCategory.removeAllItems();
        cbProductCategory.addItem(new CategoryItem(Integer.parseInt(model.getValueAt(index, 2).toString()), productCategory));
        ArrayList<Productcategory> productCategoryList = ProductCategoryDao.getAllRecords();
        Iterator<Productcategory> productCategoryItr = productCategoryList.iterator();
        while(productCategoryItr.hasNext()){
            Productcategory categoryObj = productCategoryItr.next();
            if(!categoryObj.getName().equals(productCategory)){
                cbProductCategory.addItem(new CategoryItem(categoryObj.getId(), categoryObj.getName()));
            }
        }
        cbPetCategory.removeAllItems();
        cbPetCategory.addItem(new CategoryItem(Integer.parseInt(model.getValueAt(index, 4).toString()), petCategory));
        ArrayList<Petcategory> petCategoryList = PetCategoryDao.getAllRecords();
        Iterator<Petcategory> petCategoryItr = petCategoryList.iterator();
        while(petCategoryItr.hasNext()){
            Petcategory categoryObj = petCategoryItr.next();
            if(!categoryObj.getName().equals(petCategory)){
                cbPetCategory.addItem(new CategoryItem(categoryObj.getId(), categoryObj.getName()));
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        String id = lblId.getText();
        int a = JOptionPane.showConfirmDialog(null, "Bạn có chắn muốn XÓA sản phẩm này", "Select", JOptionPane.YES_NO_OPTION);
        if( a == 0 ){
            ProductDao.delete(id);
            setVisible(false);
            new ManageProduct().setVisible(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ManageProduct().setVisible(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtNameKeyReleased

    private void cbPetCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPetCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPetCategoryActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser f = new JFileChooser(new File("C:\\"));
            int result = f.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = f.getSelectedFile();
                lblImage.setIcon(ResizeImage(String.valueOf(selectedFile)));
                lblImgPath.setText(selectedFile.getPath());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnImageActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new AddNewPet().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPrintBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintBarcodeActionPerformed
        // TODO add your handling code here:
        String barcode = lblBarcode.getText();
        if(!barcode.equals("")){
            OpenPdf.openBarcodeByBarcode(barcode);
        }
    }//GEN-LAST:event_btnPrintBarcodeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(lblId.getText());
        int quantity = Integer.parseInt(String.valueOf(spQuantityImport.getValue()));
        Date today = new Date();       
        ProductDao.importQuantityOfProduct(id, quantity, today);
        ProductDao.increseQuantity(id, quantity);
        setVisible(false);
        new ManageProduct().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnPrintBarcode;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<CategoryItem> cbPetCategory;
    private javax.swing.JComboBox<CategoryItem> cbProductCategory;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImgPath;
    private javax.swing.JLabel lblImgPath1;
    private javax.swing.JSpinner spQuantityImport;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRetailPrice;
    private javax.swing.JTextField txtVendorPrice;
    // End of variables declaration//GEN-END:variables
}
