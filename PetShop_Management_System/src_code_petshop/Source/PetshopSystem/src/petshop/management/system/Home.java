/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petshop.management.system;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Admin
 */
public class Home extends javax.swing.JFrame {
    public String email;
    public String department;
    public User user;
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        btnManageProduct.setEnabled(true);
            btnManagePet.setEnabled(true);
            btnVerifyUsers.setEnabled(true);
            btnManageEmployee.setEnabled(true);
            btnPlaceOrder.setEnabled(true);
            btnViewBillOrder.setEnabled(true);
            btnStatisticPet.setEnabled(true);
            btnProductStatistic.setEnabled(true);
            btnRevenueStatistic.setEnabled(true);
            btnCategoryPet.setEnabled(true);
            btnCategoryProduct.setEnabled(true);
            btnNewPet.setEnabled(true);
            btnNewProduct.setEnabled(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public Home(User user) {
        this.user = user;
        initComponents();
        email = user.getEmail();
        department = user.getDepartment_id();
        if(department.equals("AD")){
            btnManageProduct.setEnabled(true);
            btnManagePet.setEnabled(true);
            btnVerifyUsers.setEnabled(true);
            btnManageEmployee.setEnabled(true);
            btnPlaceOrder.setEnabled(true);
            btnViewBillOrder.setEnabled(true);
            btnStatisticPet.setEnabled(true);
            btnProductStatistic.setEnabled(true);
            btnRevenueStatistic.setEnabled(true);
            btnNewPet.setEnabled(true);
            btnNewProduct.setEnabled(true);
            btnCategoryPet.setEnabled(true);
            btnCategoryProduct.setEnabled(true);
        }
        
        if(department.equals("NV")|| department.equals("KD")){
            btnManageProduct.setEnabled(true);
            btnManagePet.setEnabled(true);
            btnNewPet.setEnabled(true);
            btnNewProduct.setEnabled(true);
            btnCategoryPet.setEnabled(true);
            btnCategoryProduct.setEnabled(true);
        }
        
        if(department.equals("NS")){
            btnVerifyUsers.setEnabled(true);
            btnManageEmployee.setEnabled(true);
        }
        
        if(department.equals("TC")){
            btnPlaceOrder.setEnabled(true);
            btnViewBillOrder.setEnabled(true);
        }
        
        if(department.equals("TN")){
            btnPlaceOrder.setEnabled(true);
            btnViewBillOrder.setEnabled(true);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnPlaceOrder = new javax.swing.JButton();
        btnViewBillOrder = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        btnChangeSQ = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnCategoryPet = new javax.swing.JButton();
        btnNewPet = new javax.swing.JButton();
        btnManagePet = new javax.swing.JButton();
        btnVerifyUsers = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnStatisticPet = new javax.swing.JButton();
        btnRevenueStatistic = new javax.swing.JButton();
        btnProductStatistic = new javax.swing.JButton();
        btnNewProduct = new javax.swing.JButton();
        btnCategoryProduct = new javax.swing.JButton();
        btnManageProduct = new javax.swing.JButton();
        btnManageEmployee = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        btnLogout.setText("Đăng xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 370, 270, 70));

        btnPlaceOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlaceOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/place order.png"))); // NOI18N
        btnPlaceOrder.setText("Lập đơn hàng");
        btnPlaceOrder.setEnabled(false);
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });
        getContentPane().add(btnPlaceOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 280, 50));

        btnViewBillOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnViewBillOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/View Bills & Order Placed Details.png"))); // NOI18N
        btnViewBillOrder.setText("Xem đơn hàng & chi tiết hóa đơn");
        btnViewBillOrder.setEnabled(false);
        btnViewBillOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBillOrderActionPerformed(evt);
            }
        });
        getContentPane().add(btnViewBillOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 300, 50));

        btnChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change Password.png"))); // NOI18N
        btnChangePassword.setText("Đổi mật khẩu");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });
        getContentPane().add(btnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 300, 270, 50));

        btnChangeSQ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChangeSQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change Security Question.png"))); // NOI18N
        btnChangeSQ.setText("Đổi câu hỏi bảo mật");
        btnChangeSQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeSQActionPerformed(evt);
            }
        });
        getContentPane().add(btnChangeSQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 230, 270, 50));

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 10, -1, -1));

        btnCategoryPet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCategoryPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/category.png"))); // NOI18N
        btnCategoryPet.setText("Quản lý loại thú cưng");
        btnCategoryPet.setEnabled(false);
        btnCategoryPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryPetActionPerformed(evt);
            }
        });
        getContentPane().add(btnCategoryPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 270, 50));

        btnNewPet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new product.png"))); // NOI18N
        btnNewPet.setText("Thêm thú cưng");
        btnNewPet.setEnabled(false);
        btnNewPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPetActionPerformed(evt);
            }
        });
        getContentPane().add(btnNewPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 280, 50));

        btnManagePet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnManagePet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view edit delete product.png"))); // NOI18N
        btnManagePet.setText("Quản lý thú cưng");
        btnManagePet.setEnabled(false);
        btnManagePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePetActionPerformed(evt);
            }
        });
        getContentPane().add(btnManagePet, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 300, 50));

        btnVerifyUsers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerifyUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/verify users.png"))); // NOI18N
        btnVerifyUsers.setText("Duyệt đăng ký");
        btnVerifyUsers.setEnabled(false);
        btnVerifyUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyUsersActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerifyUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 280, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tài khoản");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Kinh doanh");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Nhân sự");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tài chính");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Thống kê");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        btnStatisticPet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnStatisticPet.setText("Thống kê thú cưng");
        btnStatisticPet.setEnabled(false);
        btnStatisticPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticPetActionPerformed(evt);
            }
        });
        getContentPane().add(btnStatisticPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 280, 50));

        btnRevenueStatistic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRevenueStatistic.setText("Thống kê doanh thu");
        btnRevenueStatistic.setEnabled(false);
        btnRevenueStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevenueStatisticActionPerformed(evt);
            }
        });
        getContentPane().add(btnRevenueStatistic, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 520, 270, 50));

        btnProductStatistic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProductStatistic.setText("Thống kê sản phẩm");
        btnProductStatistic.setEnabled(false);
        btnProductStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductStatisticActionPerformed(evt);
            }
        });
        getContentPane().add(btnProductStatistic, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, 300, 50));

        btnNewProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new product.png"))); // NOI18N
        btnNewProduct.setText("Thêm sản phẩm");
        btnNewProduct.setEnabled(false);
        btnNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProductActionPerformed(evt);
            }
        });
        getContentPane().add(btnNewProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 280, 50));

        btnCategoryProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCategoryProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/category.png"))); // NOI18N
        btnCategoryProduct.setText("Quản lý loại sản phẩm");
        btnCategoryProduct.setEnabled(false);
        btnCategoryProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryProductActionPerformed(evt);
            }
        });
        getContentPane().add(btnCategoryProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, 270, 50));

        btnManageProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnManageProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view edit delete product.png"))); // NOI18N
        btnManageProduct.setText("Quản lý sản phẩm");
        btnManageProduct.setEnabled(false);
        btnManageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageProductActionPerformed(evt);
            }
        });
        getContentPane().add(btnManageProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 300, 50));

        btnManageEmployee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnManageEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btnManageEmployee.setText("Quản lý nhân sự");
        btnManageEmployee.setEnabled(false);
        btnManageEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageEmployeeActionPerformed(evt);
            }
        });
        getContentPane().add(btnManageEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 300, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/@reallygreatsite/4.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn đóng ?", "Select", JOptionPane.YES_NO_OPTION);
        if(a == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        // TODO add your handling code here:
        new ChangePassword(user).setVisible(true);
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnCategoryPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryPetActionPerformed
        // TODO add your handling code here:
        new ManagePetCategory(user).setVisible(true);
    }//GEN-LAST:event_btnCategoryPetActionPerformed

    private void btnNewPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPetActionPerformed
        // TODO add your handling code here:
        new AddNewPet(user).setVisible(true);
    }//GEN-LAST:event_btnNewPetActionPerformed

    private void btnManagePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePetActionPerformed
        // TODO add your handling code here:
        new ManagePet(user).setVisible(true);
    }//GEN-LAST:event_btnManagePetActionPerformed

    private void btnVerifyUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyUsersActionPerformed
        // TODO add your handling code here:
        new VerifyUsers(user).setVisible(true);
    }//GEN-LAST:event_btnVerifyUsersActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new PlaceOrder(user).setVisible(true);
    }//GEN-LAST:event_btnPlaceOrderActionPerformed

    private void btnChangeSQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeSQActionPerformed
        // TODO add your handling code here:
        new ChangeSecurityQuestion(user).setVisible(true);
    }//GEN-LAST:event_btnChangeSQActionPerformed

    private void btnCategoryProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryProductActionPerformed
        // TODO add your handling code here:
        new ManageProductCategory(user).setVisible(true);
    }//GEN-LAST:event_btnCategoryProductActionPerformed

    private void btnRevenueStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevenueStatisticActionPerformed
        // TODO add your handling code here:
        new RevenueStatistic().setVisible(true);
    }//GEN-LAST:event_btnRevenueStatisticActionPerformed

    private void btnProductStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductStatisticActionPerformed
        // TODO add your handling code here:
        new ProductStatistic().setVisible(true);
    }//GEN-LAST:event_btnProductStatisticActionPerformed

    private void btnStatisticPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticPetActionPerformed
        // TODO add your handling code here:
       new PetStatistic().setVisible(true);
    }//GEN-LAST:event_btnStatisticPetActionPerformed

    private void btnManageEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEmployeeActionPerformed
        // TODO add your handling code here:
        new ManageEmployee(user).setVisible(true);
    }//GEN-LAST:event_btnManageEmployeeActionPerformed

    private void btnManageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageProductActionPerformed
        // TODO add your handling code here:
        new ManageProduct(user).setVisible(true);
    }//GEN-LAST:event_btnManageProductActionPerformed

    private void btnNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProductActionPerformed
        // TODO add your handling code here:
        new AddNewProduct(user).setVisible(true);
    }//GEN-LAST:event_btnNewProductActionPerformed

    private void btnViewBillOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBillOrderActionPerformed
        // TODO add your handling code here:
        new ViewBillsOrderPlacedDetails(user).setVisible(true);
    }//GEN-LAST:event_btnViewBillOrderActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategoryPet;
    private javax.swing.JButton btnCategoryProduct;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnChangeSQ;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageEmployee;
    private javax.swing.JButton btnManagePet;
    private javax.swing.JButton btnManageProduct;
    private javax.swing.JButton btnNewPet;
    private javax.swing.JButton btnNewProduct;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnProductStatistic;
    private javax.swing.JButton btnRevenueStatistic;
    private javax.swing.JButton btnStatisticPet;
    private javax.swing.JButton btnVerifyUsers;
    private javax.swing.JButton btnViewBillOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
