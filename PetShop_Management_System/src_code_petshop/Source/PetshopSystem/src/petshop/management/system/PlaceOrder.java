/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petshop.management.system;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.OpenPdf;
import dao.CustomerDao;
import dao.OrderDao;
import dao.PetCategoryDao;
import dao.PetDao;
import dao.PetOrderDao;
import dao.ProductCategoryDao;
import dao.ProductDao;
import dao.ProductOrderDao;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Customer;
import model.Order;
import model.Pet;
import model.PetOrder;
import model.Petcategory;
import model.Product;
import model.ProductOrder;
import model.Productcategory;
import model.User;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Admin
 */
public class PlaceOrder extends javax.swing.JFrame implements Runnable, ThreadFactory {
    public User user;

    class CategoryItem {

        private int id = 0;
        private String name = "";

        public CategoryItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public int grandTotal = 0;
    public int petTotal = 0;
    public int productTotal = 0;
    public String emailPattern = "^(.+)@(.+)$";
    public String mobileNumberPattern = "^\\d{10}$";

    public String userEmail;

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private final Executor executor = Executors.newSingleThreadExecutor(this);

    /**
     * Creates new form PlaceOrder
     */
    public PlaceOrder() {
        initComponents();
        initWebcam();
        btnAddCustomer.setEnabled(false);

    }

    public PlaceOrder(User user) {
        this.user = user;
        initComponents();
        initWebcam();
        btnAddCustomer.setEnabled(false);
        userEmail = user.getEmail();
        lblUserName.setText(user.getName());
        lblUserId.setText(Integer.toString(user.getId()));
    }

    public static String createQrPDF(String pdfFilename, String myString) {
        Document doc = new Document();
        PdfWriter docWriter = null;
        try {
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.dir") + "\\src\\file\\petOrderQrcode\\" + pdfFilename + ".pdf"));
            doc.addAuthor("Pet88 Shop");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("");
            doc.addTitle("");
            doc.setPageSize(PageSize.LETTER);
            doc.open();
            
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode("memorynotfound.com//" + myString, 1, 1, null);
            com.itextpdf.text.Image codeQrImage = barcodeQRCode.getImage();
            codeQrImage.scaleAbsolute(100, 100);
            doc.add(codeQrImage);
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
        return "\\src\\file\\petOrderQrcode\\" + pdfFilename + ".pdf";
    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblImagePet.getWidth(), lblImagePet.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private void initWebcam() {
        BasicConfigurator.configure();
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 150));
        executor.execute(this);
    }

    public void petByCategory(String searchKey, int petCategory_id) {
        DefaultTableModel dtm = (DefaultTableModel) tblPet.getModel();
        dtm.setRowCount(0);
        ArrayList<Pet> list = PetDao.getAllRecordsByCategory(searchKey, petCategory_id);
        Iterator<Pet> itr = list.iterator();
        while (itr.hasNext()) {
            Pet petObj = itr.next();
            dtm.addRow(new Object[]{petObj.getId(), petObj.getCategory_name(), petObj.getType(), petObj.getColor(), petObj.getSex(), petObj.getDescription()});
        }
    }

    public void productBySearch(String keySearch, int pet_categoty_id, int product_category_id) {
        DefaultTableModel dtm = (DefaultTableModel) tblProduct.getModel();
        dtm.setRowCount(0);
        ArrayList<Product> list = ProductDao.filterProductByName(keySearch, pet_categoty_id, product_category_id);
        Iterator<Product> itr = list.iterator();
        while (itr.hasNext()) {
            Product productObj = itr.next();
            dtm.addRow(new Object[]{
                productObj.getId(),
                productObj.getName(),
                productObj.getProduct_category_name(),
                productObj.getPet_category_name(),
                productObj.getDescription(),
                productObj.getQuantity(),
                productObj.getRetail_price()
            });
        }
    }

    public void getProductByBarCode(String barcode) {
        Product product = ProductDao.getProductByBarCode(barcode);
        int id = product.getId();
        String name = product.getName();
        int quantityInven = product.getQuantity();
        int price = product.getRetail_price();

        DefaultTableModel dtm = (DefaultTableModel) tblProductOrder.getModel();
        boolean hasProduct = false;
        for (int i = 0; i < tblProductOrder.getRowCount(); i++){
            if (tblProductOrder.getValueAt(i, 0).equals(id)){
                int newQuantity = Integer.parseInt(tblProductOrder.getValueAt(i, 3).toString()) + 1;
                if (newQuantity > quantityInven){
                    JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Quá số lượng sản phẩm</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int newTotal = Integer.parseInt(tblProductOrder.getValueAt(i, 2).toString()) * newQuantity;
                tblProductOrder.setValueAt(newQuantity, i, 3);
                tblProductOrder.setValueAt(newTotal, i, 4);
                hasProduct = true;
            }          
        }
        
        if(!hasProduct){
            dtm.addRow(new Object[]{id, name, price, 1, price * 1});
        }
        
        int total = 0;        
        for (int i = 0; i < tblProductOrder.getRowCount(); i++) {
            int amount = Integer.parseInt(String.valueOf(tblProductOrder.getValueAt(i, 4)));
            System.err.println(amount);
            total += amount;
        }

        productTotal = total;

        lblProTotal.setText(Integer.toString(productTotal));
        
        grandTotal = petTotal + productTotal;

        lblGrandTotal.setText(String.valueOf(grandTotal));

//        clearProductFields();
//        validateField();
    }

    public void clearProductFields() {
        txtProName.setText("");
        txtProPrice.setText("");
        txtProTotal.setText("");
        jSpinner1.setValue(1);
        btnAddToCart.setEnabled(false);
    }

    public void clearPetFields() {
        txtPetId.setText("");
        txtPetName.setText("");
        txtPetDescription.setText("");
        txtPetPrice.setText("");
        btnAddPet.setEnabled(false);
    }

    public void validateField() {
        String customerName = txtCusName.getText();
        String customerMobileNumber = txtCusMobileNumber.getText();
        String customerEmail = txtCusEmail.getText();
        if (!customerEmail.equals("") && customerMobileNumber.matches(mobileNumberPattern) && customerEmail.matches(emailPattern) && customerMobileNumber.length() == 10) {
            btnAddCustomer.setEnabled(true);
        } else {
            btnAddCustomer.setEnabled(false);
        }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCusName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCusMobileNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCusEmail = new javax.swing.JTextField();
        Loài = new javax.swing.JLabel();
        cbPetCategory = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtSearchPet = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPet = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtProName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtProPrice = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        txtProTotal = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductOrder = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        lblProTotal = new javax.swing.JLabel();
        btnGenaratePrint = new javax.swing.JButton();
        cbProductCategory = new javax.swing.JComboBox<>();
        txtSearchProduct = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPetOrder = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtPetName = new javax.swing.JTextField();
        lbl = new javax.swing.JLabel();
        txtPetPrice = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnAddPet = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPetDescription = new javax.swing.JTextField();
        txtPetId = new javax.swing.JTextField();
        lblImagePet = new javax.swing.JLabel();
        lblBarcode = new javax.swing.JLabel();
        cbPetProductCategory = new javax.swing.JComboBox<>();
        lblImageProduct = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtProId = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblPetTotal = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblGrandTotal = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtCusAddress = new javax.swing.JTextField();
        btnAddCustomer = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        lblCusId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblUserId = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1328, 6, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(143, 84, 46));
        jLabel3.setText("Thông tin khách hàng");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 830, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(143, 84, 46));
        jLabel4.setText("Tên");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        txtCusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusNameActionPerformed(evt);
            }
        });
        txtCusName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCusNameKeyReleased(evt);
            }
        });
        getContentPane().add(txtCusName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(143, 84, 46));
        jLabel5.setText("Số điện thoại");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        txtCusMobileNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusMobileNumberActionPerformed(evt);
            }
        });
        txtCusMobileNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCusMobileNumberKeyReleased(evt);
            }
        });
        getContentPane().add(txtCusMobileNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 180, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(143, 84, 46));
        jLabel6.setText("Email");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txtCusEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCusEmailKeyReleased(evt);
            }
        });
        getContentPane().add(txtCusEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 180, -1));

        Loài.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Loài.setForeground(new java.awt.Color(143, 84, 46));
        Loài.setText("Loài");
        getContentPane().add(Loài, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        cbPetCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPetCategoryActionPerformed(evt);
            }
        });
        getContentPane().add(cbPetCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 440, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(143, 84, 46));
        jLabel9.setText("Tìm kiếm");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, 20));

        txtSearchPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchPetActionPerformed(evt);
            }
        });
        txtSearchPet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchPetKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearchPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 440, -1));

        tblPet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Loài", "Loại", "Màu", "Giới tính", "Mô tả"
            }
        ));
        tblPet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPet);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 500, 120));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(143, 84, 46));
        jLabel10.setText("Tên");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, -1, -1));
        getContentPane().add(txtProName, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 340, 190, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(143, 84, 46));
        jLabel11.setText("Giá");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 380, -1, -1));
        getContentPane().add(txtProPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 380, 190, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(143, 84, 46));
        jLabel12.setText("Số lượng");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, -1, 20));

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 190, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(143, 84, 46));
        jLabel13.setText("Tổng tiền");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 460, -1, 20));
        getContentPane().add(txtProTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 460, 190, -1));

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        btnClear.setText("Xóa");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, -1, -1));

        btnAddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add to cart.png"))); // NOI18N
        btnAddToCart.setText("Thêm vào hóa đơn");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 500, 160, -1));

        tblProductOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Giá", "Số lượng", "Tổng tiền"
            }
        ));
        tblProductOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductOrderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductOrder);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 540, 550, 150));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(143, 84, 46));
        jLabel14.setText("Tổng tiền sản phẩm:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 690, 180, 30));

        lblProTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProTotal.setForeground(new java.awt.Color(143, 84, 46));
        getContentPane().add(lblProTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 690, -1, 30));

        btnGenaratePrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/generate bill & print.png"))); // NOI18N
        btnGenaratePrint.setText("Lưu & In hóa đơn");
        btnGenaratePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenaratePrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenaratePrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 740, -1, -1));

        cbProductCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductCategoryActionPerformed(evt);
            }
        });
        getContentPane().add(cbProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 110, 180, -1));

        txtSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProductActionPerformed(evt);
            }
        });
        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearchProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 490, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(143, 84, 46));
        jLabel15.setText("Tìm kiếm");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(143, 84, 46));
        jLabel1.setText("Loài");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(143, 84, 46));
        jLabel16.setText("Loại sản phẩm");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, -1, 20));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tên", "Loại", "Loài", "Mô tả", "Số lượng", "Giá"
            }
        ));
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProduct);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, 550, 120));

        jPanel1.setPreferredSize(new java.awt.Dimension(470, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 180, 150));

        tblPetOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Mô tả", "Giá"
            }
        ));
        tblPetOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPetOrderMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPetOrder);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 500, 150));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(143, 84, 46));
        jLabel17.setText("Đơn hàng");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(143, 84, 46));
        jLabel8.setText("Sản phẩm");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, -1, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(143, 84, 46));
        jLabel18.setText("Thú cưng");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 510, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(143, 84, 46));
        jLabel19.setText("Tên");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 40, 20));
        getContentPane().add(txtPetName, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 180, -1));

        lbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl.setForeground(new java.awt.Color(143, 84, 46));
        lbl.setText("Giá");
        getContentPane().add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 40, 20));
        getContentPane().add(txtPetPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 180, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, -1, -1));

        btnAddPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add to cart.png"))); // NOI18N
        btnAddPet.setText("Thêm vào hóa đơn");
        btnAddPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPetActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(143, 84, 46));
        jLabel20.setText("Mô tả");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, -1, 20));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(143, 84, 46));
        jLabel21.setText("Mã");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, -1, -1));
        getContentPane().add(txtPetDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, 180, -1));
        getContentPane().add(txtPetId, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 180, -1));
        getContentPane().add(lblImagePet, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 240, 150));
        getContentPane().add(lblBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 490, -1, -1));

        cbPetProductCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPetProductCategoryActionPerformed(evt);
            }
        });
        getContentPane().add(cbPetProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, 190, -1));
        getContentPane().add(lblImageProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 340, 270, 150));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(143, 84, 46));
        jLabel23.setText("Quét mã vạch sản phẩm");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 180, -1));
        getContentPane().add(txtProId, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(143, 84, 46));
        jLabel24.setText("Tổng tiền thú cưng:");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 690, 180, 30));

        lblPetTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPetTotal.setForeground(new java.awt.Color(143, 84, 46));
        getContentPane().add(lblPetTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 690, -1, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(143, 84, 46));
        jLabel25.setText("Tổng tiền hóa đơn:");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 730, -1, -1));

        lblGrandTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGrandTotal.setForeground(new java.awt.Color(143, 84, 46));
        getContentPane().add(lblGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 730, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(143, 84, 46));
        jLabel26.setText("Địa chỉ");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));
        getContentPane().add(txtCusAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 180, -1));

        btnAddCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnAddCustomer.setText("Thêm khách hàng");
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(143, 84, 46));
        jLabel27.setText("Mã");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        lblCusId.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCusId.setForeground(new java.awt.Color(143, 84, 46));
        lblCusId.setText("--");
        getContentPane().add(lblCusId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(143, 84, 46));
        jLabel2.setText("Thu ngân thực hiện:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 30));

        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 0, 0));
        lblUserName.setText("jLabel7");
        getContentPane().add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 30));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(143, 84, 46));
        jLabel28.setText("Mã:");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, 30));

        lblUserId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUserId.setForeground(new java.awt.Color(255, 0, 0));
        lblUserId.setText("jLabel29");
        getContentPane().add(lblUserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 120, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(143, 84, 46));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/@reallygreatsite/4.png"))); // NOI18N
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        webcam.close();
        setVisible(false);
        new Home(user).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:

        ArrayList<Petcategory> list = PetCategoryDao.getAllRecords();
        for (Petcategory categoryObj : list) {
            cbPetCategory.addItem(new CategoryItem(categoryObj.getId(), categoryObj.getName()));
        }

        ArrayList<Petcategory> list3 = PetCategoryDao.getAllRecords();
        for (Petcategory categoryObj : list3) {
            cbPetProductCategory.addItem(new CategoryItem(categoryObj.getId(), categoryObj.getName()));
        }

        ArrayList<Productcategory> list2 = ProductCategoryDao.getAllRecords();
        for (Productcategory categoryObj : list2) {
            cbProductCategory.addItem(new CategoryItem(categoryObj.getId(), categoryObj.getName()));
        }

        int petCategory_id = ((CategoryItem) cbPetCategory.getSelectedItem()).getId();
        petByCategory("", petCategory_id);

        int petProductCategory_id = ((CategoryItem) cbPetProductCategory.getSelectedItem()).getId();
        int productCategory_id = ((CategoryItem) cbProductCategory.getSelectedItem()).getId();

        productBySearch("", petProductCategory_id, productCategory_id);

        cbPetProductCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int petCategory_id = ((CategoryItem) cbPetProductCategory.getSelectedItem()).getId();
                int productCategory_id = ((CategoryItem) cbProductCategory.getSelectedItem()).getId();
                String searchKey = txtSearchProduct.getText();
                productBySearch(searchKey, petCategory_id, productCategory_id);
            }
        });

        cbProductCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int petCategory_id = ((CategoryItem) cbPetProductCategory.getSelectedItem()).getId();
                int productCategory_id = ((CategoryItem) cbProductCategory.getSelectedItem()).getId();
                String searchKey = txtSearchProduct.getText();
                productBySearch(searchKey, petCategory_id, productCategory_id);
            }
        });
    }//GEN-LAST:event_formComponentShown

    private void txtSearchPetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchPetKeyReleased
        // TODO add your handling code here:
        int petCategory_id = ((CategoryItem) cbPetCategory.getSelectedItem()).getId();
        String searchKey = txtSearchPet.getText();
        petByCategory(searchKey, petCategory_id);

    }//GEN-LAST:event_txtSearchPetKeyReleased

    private void tblPetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPetMouseClicked
        // TODO add your handling code here:
        int index = tblPet.getSelectedRow();
        TableModel model = tblPet.getModel();
        String petId = model.getValueAt(index, 0).toString();
        Pet pet = PetDao.getPetById(petId);
        txtPetId.setText(Integer.toString(pet.getId()));
        txtPetName.setText(pet.getCategory_name() + " " + pet.getType() + " " + pet.getColor() + " " + pet.getSex());
        txtPetDescription.setText(pet.getDescription());
        txtPetPrice.setText(Integer.toString(pet.getRetail_price()));
        lblImagePet.setIcon(ResizeImage(System.getProperty("user.dir") + String.valueOf(pet.getImage())));
        btnAddPet.setEnabled(true);

    }//GEN-LAST:event_tblPetMouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        int quantity = (Integer) jSpinner1.getValue();
        if (quantity <= 1) {
            jSpinner1.setValue(1);
            quantity = 1;
        }
        int proTotal = Integer.parseInt(txtProPrice.getText()) * quantity;
        txtProTotal.setText(String.valueOf(proTotal));
    }//GEN-LAST:event_jSpinner1StateChanged

    private void cbPetCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPetCategoryActionPerformed
        // TODO add your handling code here:
        int petCategory_id = ((CategoryItem) cbPetCategory.getSelectedItem()).getId();
        String searchKey = txtSearchPet.getText();
        petByCategory(searchKey, petCategory_id);
    }//GEN-LAST:event_cbPetCategoryActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        String name = txtProName.getText();
        String id = txtProId.getText();
        int price = Integer.parseInt(txtProPrice.getText());
        int quantity = Integer.parseInt(String.valueOf(jSpinner1.getValue()));
        
        boolean hasProduct = false;
        for (int i = 0; i < tblProductOrder.getRowCount(); i++){
            if (tblProductOrder.getValueAt(i, 0).equals(id)){
                int newQuantity = Integer.parseInt(tblProductOrder.getValueAt(i, 3).toString()) + quantity;
                if (newQuantity > Integer.parseInt(tblProduct.getValueAt(i, 5).toString())){
                    JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Quá số lượng sản phẩm</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int newTotal = Integer.parseInt(tblProductOrder.getValueAt(i, 2).toString()) * newQuantity;
                tblProductOrder.setValueAt(newQuantity, i, 3);
                tblProductOrder.setValueAt(newTotal, i, 4);
                hasProduct = true;
            }          
        }
        
        if(!hasProduct){
            DefaultTableModel dtm = (DefaultTableModel) tblProductOrder.getModel();
            dtm.addRow(new Object[]{id, name, price, quantity, price * quantity});
        }
               
        int total = 0;        
        for (int i = 0; i < tblProductOrder.getRowCount(); i++) {
            int amount = Integer.parseInt(String.valueOf(tblProductOrder.getValueAt(i, 4)));
            System.err.println(amount);
            total += amount;
        }

        productTotal = total;

        lblProTotal.setText(Integer.toString(productTotal));

        grandTotal = productTotal + petTotal;

        lblGrandTotal.setText(Integer.toString(grandTotal));

        clearProductFields();
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearProductFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtCusNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCusNameKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtCusNameKeyReleased

    private void txtCusMobileNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCusMobileNumberKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtCusMobileNumberKeyReleased

    private void txtCusEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCusEmailKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtCusEmailKeyReleased

    private void btnGenaratePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenaratePrintActionPerformed
        // TODO add your handling code here:
        if(lblGrandTotal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Chưa có sản phẩm nào được chọn</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(lblCusId.getText().equals("--")){
            JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Vui lòng nhập thông tin khách hàng</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cusId = Integer.parseInt(lblCusId.getText());
        Order order = new Order();
        order.setCustomer_id(cusId);
        order.setTotal(grandTotal);
        order.setUser_id(Integer.parseInt(lblUserId.getText()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date();
        String dateCreatedAt = sdf.format(today);
        order.setCreated_at(today);
        Order createdOrder = OrderDao.save(order);
        
        
                
        String path = System.getProperty("user.dir") + "\\src\\file\\bill\\" + createdOrder.getId() + ".pdf";
        //Creating document
        FontFactory.register("font/SVN-Arial.ttf");
        com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10); //10 is the size

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            Paragraph cafeName = new Paragraph("Pet88 Shop\n", textFont);
            doc.add(cafeName);
            Paragraph starLine = new Paragraph("============================================", textFont);
            doc.add(starLine);

            Paragraph paragraph3 = new Paragraph(
                    "\tMã hóa đơn: " + createdOrder.getId()
                    + "\nTên thu ngân thực hiện: "+ user.getName()
                    + "\nNgày: " + dateCreatedAt
                    + ""
            , textFont);
            doc.add(paragraph3);
            doc.add(starLine);
            //Pet Table
            Paragraph paragraph4 = new Paragraph(
                    "Thú cưng\n"
            , textFont);
            doc.add(paragraph4);
            PdfPTable tbl = new PdfPTable(4);
            tbl.addCell(new Phrase("Mã", textFont));
            tbl.addCell(new Phrase("Tên", textFont));
            tbl.addCell(new Phrase("Mô tả", textFont));
            tbl.addCell(new Phrase("Tổng tiền", textFont));
            for (int i = 0; i < tblPetOrder.getRowCount(); i++) {
                String m = tblPetOrder.getValueAt(i, 0).toString();
                String t = tblPetOrder.getValueAt(i, 1).toString();
                String mt = tblPetOrder.getValueAt(i, 2).toString();
                String g = tblPetOrder.getValueAt(i, 3).toString();
                tbl.addCell(new Phrase(m, textFont));
                tbl.addCell(new Phrase(t, textFont));
                tbl.addCell(new Phrase(mt, textFont));
                tbl.addCell(new Phrase(g, textFont));
            }
            doc.add(tbl);
            Paragraph paragraph5 = new Paragraph(
                    "\nTổng tiền thú cưng: " + petTotal + "\n"
            , textFont);
            doc.add(paragraph5);
            doc.add(starLine);

            //Product Table
            Paragraph paragraph6 = new Paragraph(
                    "\nSản phẩm\n"
            , textFont);
            doc.add(paragraph6);
            PdfPTable proTable = new PdfPTable(5);
            proTable.addCell(new Phrase("Mã", textFont));
            proTable.addCell(new Phrase("Tên", textFont));
            proTable.addCell(new Phrase("Giá", textFont));
            proTable.addCell(new Phrase("Số lượng", textFont));
            proTable.addCell(new Phrase("Tổng tiền", textFont));

            for (int i = 0; i < tblProductOrder.getRowCount(); i++) {
                String m = tblProductOrder.getValueAt(i, 0).toString();
                String t = tblProductOrder.getValueAt(i, 1).toString();
                String g = tblProductOrder.getValueAt(i, 2).toString();
                String sl = tblProductOrder.getValueAt(i, 3).toString();
                String tt = tblProductOrder.getValueAt(i, 4).toString();
                proTable.addCell(new Phrase(m, textFont));
                proTable.addCell(new Phrase(t, textFont));
                proTable.addCell(new Phrase(g, textFont));
                proTable.addCell(new Phrase(sl, textFont));
                proTable.addCell(new Phrase(tt, textFont));
            }
            doc.add(proTable);
            Paragraph paragraph7 = new Paragraph(
                    "\nTổng tiền sản phẩm: " + productTotal + "\n"
            , textFont);
            doc.add(paragraph7);
            doc.add(starLine);
            Paragraph paragraph8 = new Paragraph(
                    "\nTổng tiền: " + grandTotal + "\n"
            , textFont);
            doc.add(paragraph8);
            doc.add(starLine);
            Paragraph thanskMsg = new Paragraph("Cảm ơn bạn đã mua hàng, mong bạn sẽ quay lại để mua thêm các sản phẩm ở cửa hàng", textFont);
            doc.add(thanskMsg);
            doc.close();
            //open pdf
            OrderDao.updatePath(createdOrder.getId(), "" + createdOrder.getId() );

            for (int i = 0; i < tblPetOrder.getRowCount(); i++) {
                PetOrder petOrder = new PetOrder();
                int id = Integer.parseInt(tblPetOrder.getValueAt(i, 0).toString());
                petOrder.setOrder_id(createdOrder.getId());
                petOrder.setPet_id(id);

                String qrcode_path = createQrPDF(Integer.toString(id), Integer.toString(id));
                petOrder.setQr_to_web(qrcode_path);
                PetOrderDao.save(petOrder);
                PetDao.setSold(id);
            }
            for (int i = 0; i < tblProductOrder.getRowCount(); i++) {
                String m = tblProductOrder.getValueAt(i, 0).toString();
                String sl = tblProductOrder.getValueAt(i, 3).toString();
                String tt = tblProductOrder.getValueAt(i, 4).toString();
                ProductOrder productOrder = new ProductOrder();
                productOrder.setOrder_id(createdOrder.getId());
                productOrder.setProduct_id(Integer.parseInt(m));
                productOrder.setQuantity(Integer.parseInt(sl));
                productOrder.setTotal(Integer.parseInt(tt));
                ProductOrderDao.save(productOrder);
                ProductDao.reduceQuantity(Integer.parseInt(m), Integer.parseInt(sl));
            }
            webcam.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        OpenPdf.openBill("" + createdOrder.getId());
        setVisible(false);
        new PlaceOrder(user).setVisible(true);
    }//GEN-LAST:event_btnGenaratePrintActionPerformed

    private void tblProductOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductOrderMouseClicked
        // TODO add your handling code here:
        int index = tblProductOrder.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phâm ?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            ((DefaultTableModel) tblProductOrder.getModel()).removeRow(index);
            int total = 0;        
            for (int i = 0; i < tblProductOrder.getRowCount(); i++) {
                int amount = Integer.parseInt(String.valueOf(tblProductOrder.getValueAt(i, 4)));
                System.err.println(amount);
                total += amount;
            }

            productTotal = total;

            lblProTotal.setText(Integer.toString(productTotal));

            grandTotal = productTotal + petTotal;

            lblGrandTotal.setText(Integer.toString(grandTotal));
        }
    }//GEN-LAST:event_tblProductOrderMouseClicked

    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed
        // TODO add your handling code here:
        int petCategory_id = ((CategoryItem) cbPetProductCategory.getSelectedItem()).getId();
        int productCategory_id = ((CategoryItem) cbProductCategory.getSelectedItem()).getId();
        String searchKey = txtSearchProduct.getText();
        productBySearch(searchKey, petCategory_id, productCategory_id);
    }//GEN-LAST:event_txtSearchProductActionPerformed

    private void btnAddPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPetActionPerformed
        // TODO add your handling code here:
        String id = txtPetId.getText();
        String name = txtPetName.getText();
        String description = txtPetDescription.getText();
        String price = txtPetPrice.getText();

        DefaultTableModel dtm = (DefaultTableModel) tblPetOrder.getModel();
        dtm.addRow(new Object[]{id, name, description, price});
        int total = 0;
        for (int i = 0; i < tblPetOrder.getRowCount(); i++) {
            int amount = Integer.parseInt(String.valueOf(tblPetOrder.getValueAt(i, 3)));
            total += amount;
        }
        petTotal = total;

        lblPetTotal.setText(Integer.toString(petTotal));

        grandTotal = petTotal + productTotal;

        lblGrandTotal.setText(Integer.toString(grandTotal));

        clearPetFields();
        validateField();
    }//GEN-LAST:event_btnAddPetActionPerformed

    private void cbProductCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductCategoryActionPerformed
//        // TODO add your handling code here:
//        int petCategory_id = ((CategoryItem)cbPetProductCategory.getSelectedItem()).getId();
//        int productCategory_id = ((CategoryItem)cbProductCategory.getSelectedItem()).getId();
//        String searchKey = txtSearchProduct.getText();
//        productBySearch(searchKey, petCategory_id, productCategory_id);  
    }//GEN-LAST:event_cbProductCategoryActionPerformed

    private void txtSearchPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchPetActionPerformed
        // TODO add your handling code here:
        int petCategory_id = ((CategoryItem) cbPetCategory.getSelectedItem()).getId();
        String searchKey = txtSearchPet.getText();
        petByCategory(searchKey, petCategory_id);
    }//GEN-LAST:event_txtSearchPetActionPerformed

    private void cbPetProductCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPetProductCategoryActionPerformed
        // TODO add your handling code here:
//        int petCategory_id = ((CategoryItem)cbPetProductCategory.getSelectedItem()).getId();
//        int productCategory_id = ((CategoryItem)cbProductCategory.getSelectedItem()).getId();
//        String searchKey = txtSearchProduct.getText();
//        productBySearch(searchKey, petCategory_id, productCategory_id);  
    }//GEN-LAST:event_cbPetProductCategoryActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        // TODO add your handling code here:
        int index = tblProduct.getSelectedRow();
        TableModel model = tblProduct.getModel();
        String productId = model.getValueAt(index, 0).toString();
        Product product = ProductDao.getProductById(productId);
        txtProId.setText(Integer.toString(product.getId()));
        txtProName.setText(product.getName() + " " + product.getProduct_category_name() + " " + product.getPet_category_name());
        txtProPrice.setText(Integer.toString(product.getRetail_price()));
        lblImageProduct.setIcon(ResizeImage(System.getProperty("user.dir") + String.valueOf(product.getImage())));
        btnAddToCart.setEnabled(true);
        jSpinner1.setValue(1);
        int proTotal = Integer.parseInt(txtProPrice.getText()) * 1;
        txtProTotal.setText(String.valueOf(proTotal));
    }//GEN-LAST:event_tblProductMouseClicked

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        // TODO add your handling code here:
        String customerName = txtCusName.getText();
        String customerPhonenumber = txtCusMobileNumber.getText();
        String customerEmail = txtCusEmail.getText();
        String customerAddress = txtCusAddress.getText();

        Date todaydate = new Date();

        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setPhonenumber(customerPhonenumber);
        customer.setEmail(customerEmail);
        customer.setAddress(customerAddress);
        customer.setCreated_at(todaydate);

        Customer createdCustomer = CustomerDao.save(customer);
        lblCusId.setText(Integer.toString(createdCustomer.getId()));
        txtCusName.setEnabled(false);
        txtCusEmail.setEnabled(false);
        txtCusAddress.setEnabled(false);
        txtCusMobileNumber.setEnabled(false);
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void tblPetOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPetOrderMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        int index = tblProduct.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thú cưng này ra khỏi giỏ hàng ?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            TableModel model = tblProduct.getModel();

            ((DefaultTableModel) tblProductOrder.getModel()).removeRow(index);
            int total = 0;
            for (int i = 0; i < tblPetOrder.getRowCount(); i++) {
                int amount = Integer.parseInt(String.valueOf(tblPetOrder.getValueAt(i, 3)));
                total += amount;
            }
            petTotal = total;

            lblPetTotal.setText(Integer.toString(petTotal));

            grandTotal = petTotal + productTotal;

            lblGrandTotal.setText(Integer.toString(grandTotal));
        }
    }//GEN-LAST:event_tblPetOrderMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        clearPetFields();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtSearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyReleased
        // TODO add your handling code here:
        int petCategory_id = ((CategoryItem) cbPetProductCategory.getSelectedItem()).getId();
        int productCategory_id = ((CategoryItem) cbProductCategory.getSelectedItem()).getId();
        String searchKey = txtSearchProduct.getText();
        productBySearch(searchKey, petCategory_id, productCategory_id);
    }//GEN-LAST:event_txtSearchProductKeyReleased

    private void txtCusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusNameActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCusNameActionPerformed

    private void txtCusMobileNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusMobileNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusMobileNumberActionPerformed

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
            java.util.logging.Logger.getLogger(PlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlaceOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PlaceOrder().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Loài;
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnAddPet;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnGenaratePrint;
    private javax.swing.JComboBox<CategoryItem> cbPetCategory;
    private javax.swing.JComboBox<CategoryItem> cbPetProductCategory;
    private javax.swing.JComboBox<CategoryItem> cbProductCategory;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JLabel lblCusId;
    private javax.swing.JLabel lblGrandTotal;
    private javax.swing.JLabel lblImagePet;
    private javax.swing.JLabel lblImageProduct;
    private javax.swing.JLabel lblPetTotal;
    private javax.swing.JLabel lblProTotal;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTable tblPet;
    private javax.swing.JTable tblPetOrder;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblProductOrder;
    private javax.swing.JTextField txtCusAddress;
    private javax.swing.JTextField txtCusEmail;
    private javax.swing.JTextField txtCusMobileNumber;
    private javax.swing.JTextField txtCusName;
    private javax.swing.JTextField txtPetDescription;
    private javax.swing.JTextField txtPetId;
    private javax.swing.JTextField txtPetName;
    private javax.swing.JTextField txtPetPrice;
    private javax.swing.JLabel txtProId;
    private javax.swing.JTextField txtProName;
    private javax.swing.JTextField txtProPrice;
    private javax.swing.JTextField txtProTotal;
    private javax.swing.JTextField txtSearchPet;
    private javax.swing.JTextField txtSearchProduct;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
//                if(!lblBarcode.getText().equals(result.getText())){
//                    lblBarcode.setText(result.getText());
//                    getProductByBarCode(result.getText());
//                }
                lblBarcode.setText(result.getText());
                getProductByBarCode(result.getText());
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
