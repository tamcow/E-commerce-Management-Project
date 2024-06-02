/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petshop.management.system;

import com.toedter.calendar.JDateChooser;
import dao.PetCategoryDao;
import dao.PetDao;
import dao.ProductDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.DateFormat.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import model.Petcategory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class RevenueStatistic extends javax.swing.JFrame implements ChangeListener{
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

    JScrollBar scroller;
    SlidingCategoryDataset slidingDataset;
    
    Date fromDate = new Date();
    Date toDate = new Date();

    /**
     * Creates new form PetStatictis
     */
    public RevenueStatistic() {
        initComponents(); 
    }
    
    public void loadDataByDate(){
        if(rbMonth.isSelected()){
            dateFrom.setEnabled(false);
            dateTo.setEnabled(false);
            txtYear.setEnabled(true);
            cbMonth.setEnabled(true);
            int monthSelected = Integer.parseInt(cbMonth.getSelectedItem().toString());
            int yearSelected = Integer.parseInt(txtYear.getText());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, yearSelected);
            calendar.set(Calendar.MONTH, monthSelected - 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1); 
            fromDate = calendar.getTime();
            System.err.println(fromDate);
            calendar.add(Calendar.MONTH, 1);  
            calendar.set(Calendar.DAY_OF_MONTH, 1);  
            calendar.add(Calendar.DATE, -1);
            toDate = calendar.getTime();
            
        }
        if(rbTime.isSelected()){
            dateFrom.setEnabled(true);
            dateTo.setEnabled(true);
            txtYear.setEnabled(false);
            cbMonth.setEnabled(false);
            
            fromDate = dateFrom.getDate();
            toDate = dateTo.getDate();
        }
        showRevenueChartByDate(fromDate, toDate);
        int totalImport = showExportPetTable(fromDate, toDate);
        int totalExport = showExportProductTable(fromDate, toDate);
        lblGrandTotal.setText(Integer.toString(totalImport + totalExport));
    }
    
    public void showRevenueChartByDate(Date fromDate, Date toDate){
        ArrayList<Object[]> petRevenueList = PetDao.getRevenueByDate(fromDate, toDate);
        ArrayList<Object[]> productRevenueList = ProductDao.getRevenueByDate(fromDate, toDate);
        String series1 = "Doanh thu thú cưng";  
        String series2 = "Doanh thu sản phẩm";  

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (Object[] categoryObj : petRevenueList) {
            dataset.addValue(Integer.parseInt(categoryObj[1].toString()), series1, (categoryObj[0].toString())); 
        }
        
        for (Object[] categoryObj : productRevenueList) {
            dataset.addValue(Integer.parseInt(categoryObj[1].toString()), series2, (categoryObj[0].toString())); 
        }
        
        
        slidingDataset = new SlidingCategoryDataset(dataset, 0, 10);
            // Create chart  
        JFreeChart chart = ChartFactory.createLineChart(  
            "Biểu đồ doanh thu theo ngày", // Chart title  
            "Ngày", // X-Axis Label  
            "Doanh thu", // Y-Axis Label  
            slidingDataset  
        );
        
        chart.setBackgroundPaint(Color.white);

        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        
        plot.setBackgroundPaint(Color.white);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMaximumCategoryLabelWidthRatio(0.8f);
        domainAxis.setLowerMargin(0.02);
        domainAxis.setUpperMargin(0.02);           
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getNumberInstance()));
        renderer.setBaseItemLabelsVisible(true);

            
        ChartPanel barChartPanel = new ChartPanel(chart);

        jPanel1.removeAll();
        jPanel1.add(barChartPanel, BorderLayout.CENTER);
        jPanel1.validate();
            
        barChartPanel.setPreferredSize(new Dimension(400, 400));
            
        this.scroller = new JScrollBar(SwingConstants.HORIZONTAL, 0, 10, 0, 50);
            
        this.scroller.getModel().addChangeListener(this);
            
        JPanel scrollPanel = new JPanel(new BorderLayout());
        scrollPanel.add(this.scroller);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        scrollPanel.setBackground(Color.white);
        jPanel1.add(scrollPanel, BorderLayout.SOUTH);
    }
    
    public void stateChanged(ChangeEvent e) {
        slidingDataset.setFirstCategoryIndex(this.scroller.getValue());
    }
    
    public int showExportPetTable(Date fromDate, Date toDate){
        DefaultTableModel dtm = (DefaultTableModel) tblPetExport.getModel();
        dtm.setRowCount(0);
        ArrayList<Properties> petExportList = PetDao.getExportListByDate(fromDate, toDate);
        int totalPetExport = 0;
        for (Properties petProp : petExportList) {
            dtm.addRow(new Object[]{
                petProp.get("id"),
                petProp.get("category_name"),
                petProp.get("type"),
                petProp.get("retail_price"),
                petProp.get("created_at")
            });
            totalPetExport += Integer.parseInt(petProp.get("retail_price").toString());
        }
        lblPetTotal.setText(Integer.toString(totalPetExport));
        return totalPetExport;
    }
    
    public int showExportProductTable(Date fromDate, Date toDate){
        DefaultTableModel dtm = (DefaultTableModel) tblProductExport.getModel();
        dtm.setRowCount(0);
        ArrayList<Properties> productExportList = ProductDao.getExportListByDate(fromDate, toDate);
        int totalProductExport = 0;
        for (Properties productProp : productExportList) {
            dtm.addRow(new Object[]{
                productProp.get("name"),
                productProp.get("product_category_name"),
                productProp.get("pet_category_name"),
                productProp.get("quantity"),
                productProp.get("total"),
                productProp.get("created_at")
            });
            totalProductExport += Integer.parseInt(productProp.get("total").toString());
        }
        lblProductTotal.setText(Integer.toString(totalProductExport));
        return totalProductExport;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        rbMonth = new javax.swing.JRadioButton();
        rbTime = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateTo = new com.toedter.calendar.JDateChooser();
        dateFrom = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        cbMonth = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPetExport = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductExport = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblPetTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProductTotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblGrandTotal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 255));
        setForeground(new java.awt.Color(255, 204, 204));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(rbMonth);
        rbMonth.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbMonth.setText("Tháng");
        rbMonth.setSelected(true);
        rbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMonthActionPerformed(evt);
            }
        });
        getContentPane().add(rbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        buttonGroup1.add(rbTime);
        rbTime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbTime.setText("Khoảng thời gian");
        rbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTimeActionPerformed(evt);
            }
        });
        getContentPane().add(rbTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Từ ngày");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Đến ngày");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, 20));

        dateTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateToPropertyChange(evt);
            }
        });
        getContentPane().add(dateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 160, -1));

        dateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateFromPropertyChange(evt);
            }
        });
        getContentPane().add(dateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 160, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tháng");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Năm");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, 20));

        txtYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtYearKeyPressed(evt);
            }
        });
        getContentPane().add(txtYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 160, -1));

        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        getContentPane().add(cbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 160, -1));

        tblPetExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Loài", "Loại", "Giá bán", "Ngày bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPetExport);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 530, 230));

        tblProductExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Loại", "Loài", "Số lượng", "Thành tiền", "Ngày bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblProductExport);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 530, 240));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Thú cưng bán được");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 180, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Sản phẩm bán được");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 640, 570));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tổng:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 290, -1, -1));

        lblPetTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPetTotal.setText("jLabel7");
        getContentPane().add(lblPetTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tổng:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 620, -1, -1));

        lblProductTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblProductTotal.setText("jLabel10");
        getContentPane().add(lblProductTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 620, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Tổng doanh thu:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 650, -1, -1));

        lblGrandTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGrandTotal.setText("jLabel10");
        getContentPane().add(lblGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 650, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 40, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/@reallygreatsite/4.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateFromPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            loadDataByDate();
        }
    }//GEN-LAST:event_dateFromPropertyChange

    private void txtYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYearKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            loadDataByDate();
        }       
    }//GEN-LAST:event_txtYearKeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        Date today = new Date();
        
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);
        txtYear.setText(Integer.toString(calendar.get(Calendar.YEAR)));
        cbMonth.setSelectedIndex(calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        Date firstDayOfMonth = calendar.getTime(); 
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);  

        Date lastDayOfMonth = calendar.getTime();
        showRevenueChartByDate(firstDayOfMonth, lastDayOfMonth);
        int totalImport = showExportPetTable(firstDayOfMonth, lastDayOfMonth);
        int totalExport = showExportProductTable(firstDayOfMonth, lastDayOfMonth);
        lblGrandTotal.setText(Integer.toString(totalImport + totalExport));
        
        cbMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataByDate();
            }
        });    
    }//GEN-LAST:event_formComponentShown

    private void dateToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateToPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            loadDataByDate();
        }
    }//GEN-LAST:event_dateToPropertyChange

    private void rbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMonthActionPerformed
        // TODO add your handling code here:
        loadDataByDate();
    }//GEN-LAST:event_rbMonthActionPerformed

    private void rbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTimeActionPerformed
        // TODO add your handling code here:
        loadDataByDate();
    }//GEN-LAST:event_rbTimeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RevenueStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RevenueStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RevenueStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RevenueStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RevenueStatistic().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbMonth;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGrandTotal;
    private javax.swing.JLabel lblPetTotal;
    private javax.swing.JLabel lblProductTotal;
    private javax.swing.JRadioButton rbMonth;
    private javax.swing.JRadioButton rbTime;
    private javax.swing.JTable tblPetExport;
    private javax.swing.JTable tblProductExport;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
