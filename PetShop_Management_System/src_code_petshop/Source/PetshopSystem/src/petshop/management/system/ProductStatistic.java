/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petshop.management.system;

import com.toedter.calendar.JDateChooser;
import dao.PetCategoryDao;
import dao.PetDao;
import dao.ProductCategoryDao;
import dao.ProductDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import model.Petcategory;
import model.Productcategory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class ProductStatistic extends javax.swing.JFrame {
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
    
    private Date fromDate = new Date();
    private Date toDate = new Date();

    /**
     * Creates new form PetStatictis
     */
    public ProductStatistic() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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
            System.err.println(toDate);
            
        }
        if(rbTime.isSelected()){
            dateFrom.setEnabled(true);
            dateTo.setEnabled(true);
            txtYear.setEnabled(false);
            cbMonth.setEnabled(false);
            
            fromDate = dateFrom.getDate();
            toDate = dateTo.getDate();
        }
        showImportChart(fromDate, toDate);
        showImportAndExportNumber(fromDate, toDate);
        
        int categoryId = (int)((CategoryItem)cbCategory.getSelectedItem()).getId();
        String typeDataString = cbTypeData.getSelectedItem().toString();
        showTypePieChart(fromDate, toDate, categoryId, typeDataString);
        showTable(fromDate, toDate, categoryId, typeDataString);
    }
//    
//    public void loadDataByCategory(){
//        showPieChart(Date fromDate, Date toDate, int typeId, String typeData)
//    }
    
    public void showImportAndExportNumber(Date fromDate, Date toDate){
        int beginNumber = ProductDao.getBeginPet(fromDate, toDate);
        lblBegin.setText(Integer.toString(beginNumber));
        int importNumber = ProductDao.getImportPet(fromDate, toDate);
        lblImportNumber.setText(Integer.toString(importNumber));
        int exportNumber = ProductDao.getExportPet(fromDate, toDate);
        lblExportNumber.setText(Integer.toString(exportNumber));
        lblInventory.setText(Integer.toString(beginNumber + importNumber - exportNumber));
    }
    
    public void showImportChart(Date fromDate, Date toDate){
        ArrayList<Object[]> listBegin = ProductDao.getBeginCountByPetCategory(fromDate, toDate);
        ArrayList<Object[]> listImport = ProductDao.getImportCountByPetCategory(fromDate, toDate);
        ArrayList<Object[]> listExport = ProductDao.getExportCountByPetCategory(fromDate, toDate);
        
         // row keys...
        final String series0 = "Tồn đầu kì";
        final String series1 = "Nhập";
        final String series2 = "Xuất";
        final String series3 = "Tồn cuối kì";
        
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //init the dataset
        for (Object[] categoryObj : listBegin) {
            dataset.setValue(0, series0, categoryObj[0].toString());
            dataset.setValue(0, series1, categoryObj[0].toString());
            dataset.setValue(0, series2, categoryObj[0].toString());
            dataset.setValue(0, series3, categoryObj[0].toString());
        }
        for (Object[] categoryObj : listImport) {
            dataset.setValue(0, series0, categoryObj[0].toString());
            dataset.setValue(0, series1, categoryObj[0].toString());
            dataset.setValue(0, series2, categoryObj[0].toString());
            dataset.setValue(0, series3, categoryObj[0].toString());
        }
        
        //add value for dataset
        for (Object[] categoryObj : listBegin) {
            dataset.addValue(Integer.parseInt(categoryObj[1].toString()), series0, categoryObj[0].toString());
            dataset.incrementValue(Integer.parseInt(categoryObj[1].toString()), series3, categoryObj[0].toString());
        }
        
        for (Object[] categoryObj : listImport) {
            dataset.addValue(Integer.parseInt(categoryObj[1].toString()), series1, categoryObj[0].toString());
            dataset.incrementValue(Integer.parseInt(categoryObj[1].toString()), series3, categoryObj[0].toString());
        }
        
        for (Object[] categoryObj : listExport) {
            dataset.addValue(Integer.parseInt(categoryObj[1].toString()), series2, categoryObj[0].toString());
            dataset.incrementValue(- Integer.parseInt(categoryObj[1].toString()), series3, categoryObj[0].toString());
        }
        
//        for (int i = 0; i < listBegin.size(); i++) {
//            for (int j = 0; j < listImport.size(); j++) {
//                if(listBegin.get(i)[0].toString().equals(listImport.get(j)[0].toString())){
//                    dataset.addValue((Integer.parseInt(listBegin.get(i)[1].toString()) + Integer.parseInt(listImport.get(j)[1].toString()) - Integer.parseInt(listExport.get(i)[1].toString())), series3, listImport.get(j)[0].toString());
//                }
//                if(dataset.getColumnIndex(listBegin.get(i)[0].toString()) >= 0){
//                    dataset.addValue(( Integer.parseInt(listImport.get(j)[1].toString()) - Integer.parseInt(listExport.get(i)[1].toString())), series3, listImport.get(j)[0].toString());
//                }
//                if(dataset.getColumnIndex(listImport.get(j)[0].toString()) >= 0){
//                    dataset.addValue((Integer.parseInt(listBegin.get(i)[1].toString()) - Integer.parseInt(listExport.get(i)[1].toString())), series3, listImport.get(j)[0].toString());
//                }
//            }
//        }
        
        

         // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Biểu đồ nhập/xuất/tồn kho sản phẩm",       // chart title
            "Loại sản phẩm",               // domain axis label
            "Số lượng",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                    // include legend
            true,                     // tooltips?
            true                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...


        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.15);
        
        // disable bar outlines...
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);

        renderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelGenerator(1, new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelGenerator(2, new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelGenerator(3, new StandardCategoryItemLabelGenerator());

        renderer.setSeriesItemLabelsVisible(1, true);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseSeriesVisible(true);
        chart.getCategoryPlot().setRenderer(renderer);
        
        // OPTIONAL CUSTOMISATION COMPLETED.
        ChartPanel barChartPanel = new ChartPanel(chart);
        jPanel1.removeAll();
        jPanel1.add(barChartPanel, BorderLayout.CENTER);
        jPanel1.validate();
    }
    
    public void showTypePieChart(Date fromDate, Date toDate, int typeId, String typeData){
        DefaultPieDataset dataset = new DefaultPieDataset();
        ArrayList<Object[]> categoryList = null;
        if(typeId == 0){
            if(typeData.equals("Tồn đầu kì")){
                categoryList = ProductDao.getBeginCountByPetCategory(fromDate, toDate);
                for (Object[] categoryObj : categoryList) {
                    dataset.setValue((categoryObj[0].toString()), Integer.parseInt(categoryObj[1].toString()));
                }
            }
            if(typeData.equals("Nhập")){
                categoryList = ProductDao.getImportCountByPetCategory(fromDate, toDate);
                for (Object[] categoryObj : categoryList) {
                    dataset.setValue((categoryObj[0].toString()), Integer.parseInt(categoryObj[1].toString()));
                }
            }
            if(typeData.equals("Xuất")){
                categoryList = ProductDao.getExportCountByPetCategory(fromDate, toDate);
                for (Object[] categoryObj : categoryList) {
                    dataset.setValue((categoryObj[0].toString()), Integer.parseInt(categoryObj[1].toString()));
                }
            }
            if(typeData.equals("Tồn cuối kì")){
                ArrayList<Object[]> listBegin = ProductDao.getBeginCountByPetCategory(fromDate, toDate);
                ArrayList<Object[]> listImport = ProductDao.getImportCountByPetCategory(fromDate, toDate);
                ArrayList<Object[]> listExport = ProductDao.getExportCountByPetCategory(fromDate, toDate);

                for (Object[] categoryObj : listBegin) {
                    String key = categoryObj[0].toString();
                    int oldValue = dataset.getIndex(key) != -1 ? (dataset.getValue(key)).intValue() : 0;
                    int newValue = oldValue + Integer.parseInt(categoryObj[1].toString());
                    dataset.setValue(key, newValue);
                }

                for (Object[] categoryObj : listImport) {
                    String key = categoryObj[0].toString();
                    int oldValue = dataset.getIndex(key) != -1 ? (dataset.getValue(key)).intValue() : 0;
                    int newValue = oldValue + Integer.parseInt(categoryObj[1].toString());
                    dataset.setValue(key, newValue);
                }

                for (Object[] categoryObj : listExport) {
                    String key = categoryObj[0].toString();
                    int oldValue = dataset.getIndex(key) != -1 ? (dataset.getValue(key)).intValue() : 0;
                    int newValue = oldValue - Integer.parseInt(categoryObj[1].toString());                   
                    dataset.setValue(key, newValue);
                    if(newValue == 0){
                        dataset.remove(key);
                    }
                }       
            }
        } else {
            if(typeData.equals("Tồn đầu kì")){
                categoryList = ProductDao.getBeginCountByPetType(fromDate, toDate, typeId);
                for (Object[] categoryObj : categoryList) {
                    dataset.setValue((categoryObj[0].toString()), Integer.parseInt(categoryObj[1].toString()));
                }
            }
            if(typeData.equals("Nhập")){
                categoryList = ProductDao.getImportCountByPetType(fromDate, toDate, typeId);
                for (Object[] categoryObj : categoryList) {
                    dataset.setValue((categoryObj[0].toString()), Integer.parseInt(categoryObj[1].toString()));
                }
            }
            if(typeData.equals("Xuất")){
                categoryList = ProductDao.getExportCountByPetType(fromDate, toDate, typeId);
                for (Object[] categoryObj : categoryList) {
                    dataset.setValue((categoryObj[0].toString()), Integer.parseInt(categoryObj[1].toString()));
                }
            }    
            if(typeData.equals("Tồn cuối kì")){
                ArrayList<Object[]> listBegin = ProductDao.getBeginCountByPetType(fromDate, toDate, typeId);
                ArrayList<Object[]> listImport = ProductDao.getImportCountByPetType(fromDate, toDate, typeId);
                ArrayList<Object[]> listExport = ProductDao.getExportCountByPetType(fromDate, toDate, typeId);

                for (Object[] categoryObj : listBegin) {
                    String key = categoryObj[0].toString();
                    int oldValue = dataset.getIndex(key) != -1 ? (dataset.getValue(key)).intValue() : 0;
                    int newValue = oldValue + Integer.parseInt(categoryObj[1].toString());
                    dataset.setValue(key, newValue);
                }

                for (Object[] categoryObj : listImport) {
                    String key = categoryObj[0].toString();
                    int oldValue = dataset.getIndex(key) != -1 ? (dataset.getValue(key)).intValue() : 0;
                    int newValue = oldValue + Integer.parseInt(categoryObj[1].toString());
                    dataset.setValue(key, newValue);
                }

                for (Object[] categoryObj : listExport) {
                    String key = categoryObj[0].toString();
                    int oldValue = dataset.getIndex(key) != -1 ? (dataset.getValue(key)).intValue() : 0;
                    int newValue = oldValue - Integer.parseInt(categoryObj[1].toString());
                    dataset.setValue(key, newValue);
                    if(newValue == 0){
                        dataset.remove(key);
                    }
                }       
            }
        }
        //create dataset
        String headerPieChart = "";
        headerPieChart += ((CategoryItem)cbCategory.getSelectedItem()).getName();       
        headerPieChart += " " + typeData;
                      
        JFreeChart chart = ChartFactory.createPieChart(
            headerPieChart, 
            dataset, 
            true, 
            true, 
            false
        );
        
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        
        PiePlot plot = (PiePlot) chart.getPlot();
//        plot.setSectionPaint(KEY1, Color.green);
//        plot.setSectionPaint(KEY2, Color.red);
        plot.setBackgroundPaint(Color.white);
        plot.setSimpleLabels(true);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);

        ChartPanel pieCategoryChartPanel = new ChartPanel(chart);
        pnlPieCategoryChart.removeAll();
        pnlPieCategoryChart.add(pieCategoryChartPanel, BorderLayout.CENTER);
        pnlPieCategoryChart.validate();
    }
    
    public void showTable(Date fromDate, Date toDate, int typeId, String typeData){
//        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//        ArrayList<Properties> productExportList = null;
//        if(typeId == 0){
//            if(typeData.equals("Tồn đầu kì")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);
//            }
//            if(typeData.equals("Nhập")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);
//            }
//            if(typeData.equals("Xuất")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);
//            }
//            if(typeData.equals("Tồn cuối kì")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);
//            }
//        } else {
//            if(typeData.equals("Tồn đầu kì")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);              
//            }
//            if(typeData.equals("Nhập")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);               
//            }
//            if(typeData.equals("Xuất")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);               
//            }    
//            if(typeData.equals("Tồn cuối kì")){
//                productExportList = ProductDao.getBeginListByDateAndType(fromDate, toDate, typeId);                      
//            }
//        }
//
//        for (Properties productProp : productExportList) {
//            dtm.addRow(new Object[]{
//                productProp.get("name"),
//                productProp.get("product_category_name"),
//                productProp.get("pet_category_name"),
//                productProp.get("quantity"),
//                productProp.get("created_at")
//            });
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnlPieCategoryChart = new javax.swing.JPanel();
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
        cbCategory = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblImportNumber = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblExportNumber = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblInventory = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cbTypeData = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblBegin = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(255, 204, 204));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(255, 204, 153));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 650, 390));

        pnlPieCategoryChart.setBackground(new java.awt.Color(255, 255, 255));
        pnlPieCategoryChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlPieCategoryChart.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlPieCategoryChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, 530, 260));

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

        getContentPane().add(cbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 140, -1));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setForeground(new java.awt.Color(153, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Số lượng nhập");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        lblImportNumber.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblImportNumber.setText("jLabel7");
        jPanel4.add(lblImportNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 60));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 140, 100));

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Số lượng bán");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblExportNumber.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblExportNumber.setText("jLabel9");
        jPanel5.add(lblExportNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 60));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 140, 100));

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tồn kho cuối kì");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblInventory.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblInventory.setText("jLabel11");
        jPanel6.add(lblInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 60));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 150, 100));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Loại", "Loài", "Số lượng", "Ngày"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 530, 300));

        cbTypeData.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tồn đầu kì", "Nhập", "Xuất", "Tồn cuối kì" }));
        getContentPane().add(cbTypeData, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, 130, -1));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tồn kho đầu kì");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblBegin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblBegin.setText("jLabel9");
        jPanel3.add(lblBegin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 100));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 40, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/@reallygreatsite/4.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setPreferredSize(new java.awt.Dimension(1280, 700));
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
        dateFrom.setEnabled(false);
        dateTo.setEnabled(false);
        
        ArrayList<Productcategory> list = ProductCategoryDao.getAllRecords();
        Iterator<Productcategory> itr = list.iterator();
        cbCategory.addItem(new CategoryItem(0, "Tất cả"));
        while(itr.hasNext()){
            Productcategory categoryObj = itr.next();
            cbCategory.addItem(new CategoryItem(categoryObj.getId(), categoryObj.getName()));
        }
        
        int categoryId = (int)((CategoryItem)cbCategory.getSelectedItem()).getId();
                
        String typeDataString = cbTypeData.getSelectedItem().toString();
        
        showImportAndExportNumber(firstDayOfMonth, lastDayOfMonth);
        showImportChart(firstDayOfMonth, lastDayOfMonth);
        showTypePieChart(firstDayOfMonth, lastDayOfMonth, categoryId, typeDataString);
        
        cbMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataByDate();
            }
        });  
        
        cbCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataByDate();
            }
        });

        cbTypeData.addActionListener(new ActionListener() {
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
            java.util.logging.Logger.getLogger(ProductStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductStatistic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
            new ProductStatistic().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<CategoryItem> cbCategory;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbTypeData;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBegin;
    private javax.swing.JLabel lblExportNumber;
    private javax.swing.JLabel lblImportNumber;
    private javax.swing.JLabel lblInventory;
    private javax.swing.JPanel pnlPieCategoryChart;
    private javax.swing.JRadioButton rbMonth;
    private javax.swing.JRadioButton rbTime;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
