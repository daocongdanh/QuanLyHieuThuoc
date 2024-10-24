/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.manager;

import bus.BatchBUS;
import bus.ProductBUS;
import bus.ProductTransactionHistoryBUS;
import bus.SupplierBUS;
import bus.UnitBUS;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import entity.Batch;
import entity.Product;
import entity.ProductTransactionHistory;
import entity.Supplier;
import entity.Unit;
import enums.ProductType;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.MessageDialog;
import util.ResizeImage;
import view.common.TableActionCellEditorOnlyDelete;
import view.common.TableActionCellRenderOnlyDelete;
import view.common.TableDesign;
import view.common.TableActionEventOnlyDelete;
import enums.ProductType;

/**
 *
 * @author Hoang
 */
public class TABProduct extends javax.swing.JPanel {

    private ProductBUS productBUS;
    private TableDesign tableDesign;
    private TableDesign tablleDesignTab2;
    private TableDesign tablleDesignTab3;
    private BatchBUS batchBUS;
    private ProductTransactionHistoryBUS transactionBUS;

    private String productEdit;
    private String supplierEdit;

    public TABProduct() {
        productBUS = new ProductBUS(ConnectDB.getEntityManager());
        batchBUS= new BatchBUS(ConnectDB.getEntityManager());
        transactionBUS = new ProductTransactionHistoryBUS(ConnectDB.getEntityManager());
        initComponents();
        setUIManager();
        fillTable();
        addIconFeature();
    }

    private void setUIManager() {
        txtSearchSupplier.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm theo tên, email, số điện thoại");
        txtCountryOfOrigin.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập xuất xứ");
        txtProductActive.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập hoat động");
        txtProductActiveIngre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập hoạt chất");
        txtProductDosage.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập liều lượng");
        txtProductManufacturer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên nhà cung cấp");
        txtProductName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên sản phẩm");
        txtProductPakaging.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập quy trình đóng gói");
        txtProductPurchasePrice.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập giá nhập vào");
        txtProductRegisNumber.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập số đăng kí");
        txtProductSellingPrice.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập giá bán");
        UIManager.put("Button.arc", 10);
    }

    private void fillTable() {
        String[] headers = {"Mã sản phẩm","VAT","Active","Hoạt Chất","Xuất xứ","Liều lượng","Nhà sản xuất","Tên","Bao bì","Loại sản phẩm", "Giá mua","Số đăng kí" ,"Giá bán","Ảnh"};
        List<Integer> tableWidths = Arrays.asList(30,30,30,30 , 30,30,30,30,30,30,30,30,30,30);
        tableDesign = new TableDesign(headers, tableWidths);
        ProductScrollPane.setViewportView(tableDesign.getTable());
        ProductScrollPane.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        //addEventBtnEditInTable();
        List<Product> products = productBUS.getAllProducts();
        fillContent(products);
        

    }
    private void fillContent(List<Product> products) {
    tableDesign.getModelTable().setRowCount(0);
    for (Product product : products) {
        tableDesign.getModelTable().addRow(new Object[]{
            product.getProductId(),          
            product.getVAT(),                
            product.isActive(),             
            product.getActiveIngredient(),  
            product.getCountryOfOrigin(),    
            product.getDosage(),             
            product.getManufacturer(),        
            product.getName(),               
            product.getPackaging(),          
            product.getProductType(),        
            product.getPurchasePrice(),      
            product.getRegistrationNumber(), 
            product.getSellingPrice(),     
            product.getImage() ,
            
        });
    }
    }
    private void updateTab1Data(){
        JTable table = tableDesign.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            supplierEdit = (String) table.getValueAt(selectedRow, 0);
            txtProductVATEdit.setText(String.valueOf(table.getValueAt(selectedRow, 1)));
            txtProductActiveEdit.setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            txtProductActiveIngreEdit.setText((String) table.getValueAt(selectedRow, 3));
            txtProductCountryOfOriginEdit.setText((String) table.getValueAt(selectedRow, 4));
            txtProductDosageEdit.setText((String) table.getValueAt(selectedRow, 5));
            txtProductManufacturerEdit.setText((String) table.getValueAt(selectedRow, 6));
            txtProductNameEdit.setText((String) table.getValueAt(selectedRow, 7));
            txtProductPakagingEdit.setText((String) table.getValueAt(selectedRow, 8));
            ProductType productType = (ProductType) table.getValueAt(selectedRow, 9);
            cbbProductType.setSelectedItem(productType);
            txtProductPurchasePriceEdit.setText(String.valueOf(table.getValueAt(selectedRow, 10)));
            txtProductRegisNumberEdit.setText((String) table.getValueAt(selectedRow, 11));
            txtProductSellingPriceEdit.setText(String.valueOf(table.getValueAt(selectedRow, 12)));
            txtImageFileEdit.setText((String) table.getValueAt(selectedRow, 13));

            if (table.getCellEditor() != null) {
                table.getCellEditor().stopCellEditing();
            }
        } else {
            MessageDialog.error(null, "Vui lòng chọn một sản phẩm để sửa.");
        }
     }
    
        private void updateTab2Data() {
    // Lấy dữ liệu giao dịch từ cơ sở dữ liệu
    String[] headers = {
        "Mã giao dịch", "Giá vốn", "Tồn kho", "Đối tác", 
        "Số lượng", "Ngày giao dịch", "Giá giao dịch", 
        "Loại giao dịch", "Mã sản phẩm"
    };

    List<Integer> tableWidths = Arrays.asList(30, 30, 30, 30, 30, 30, 30, 30, 30);
    tablleDesignTab2 = new TableDesign(headers, tableWidths);
    
    ScrollPaneTab2.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
    ScrollPaneTab2.setViewportView(tablleDesignTab2.getTable());
    
    List<ProductTransactionHistory> transactions = transactionBUS.getAllProductTransactionHistory();
            fillTab2Content(transactions);
    }
     private void fillTab2Content(List<ProductTransactionHistory> transactions) {
    tablleDesignTab2.getModelTable().setRowCount(0); // Xóa dữ liệu cũ trong bảng

    // Duyệt qua danh sách giao dịch và thêm vào bảng Tab 2
    for (ProductTransactionHistory transaction : transactions) {
        tablleDesignTab2.getModelTable().addRow(new Object[]{
            transaction.getTransactionId(),
            transaction.getCostPrice(),
            transaction.getFinalStock(),
            transaction.getPartner(),
            transaction.getQuantity(),
            transaction.getTransactionDate(),
            transaction.getTransactionPrice(),
            transaction.getTransactionType(),
            transaction.getProductId(),
        });
    }
    }
     
     
     private void updateTab3Data() {
        // Tiêu đề cột cho bảng batch
        String[] headers = {
            "Mã lô", "Ngày hết hạn", "Tên sản phẩm", "Tồn kho", "Mã sản phẩm"
        };

        // Độ rộng cho các cột
        List<Integer> tableWidths = Arrays.asList(30, 30, 30, 30, 30);
        tablleDesignTab3 = new TableDesign(headers, tableWidths);

        // Thiết lập ScrollPane cho Tab 3
        ScrollPaneTab3.setViewportView(tablleDesignTab3.getTable());
        ScrollPaneTab3.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        // Lấy danh sách các lô và điền dữ liệu vào bảng
        List<Batch> batches = batchBUS.getAllBatch();
        fillTab3Content(batches);
    }
    private void fillTab3Content(List<Batch> batches) {
    tablleDesignTab3.getModelTable().setRowCount(0); // Xóa dữ liệu cũ trong bảng

    // Duyệt qua danh sách các lô và thêm vào bảng Tab 3
    for (Batch batch : batches) {
        tablleDesignTab3.getModelTable().addRow(new Object[]{
            batch.getBatchId(),
            batch.getExpirationDate(),
            batch.getName(),
            batch.getStock(),
            batch.getProductId()
        });
    }
}

        


    private void addIconFeature() {
        btnAdd.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/addBtn.svg")), 35, 35));
        btnUpdate.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/editBtn.svg")), 35, 35));
    }
    
    public void clearData(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
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

        modelEditProduct = new javax.swing.JDialog();
        TabbedPane = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtProductNameEdit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtProductRegisNumberEdit = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtProductPurchasePriceEdit = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtProductActiveIngreEdit = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtProductSellingPriceEdit = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtProductPakagingEdit = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtProductDosageEdit = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtProductManufacturerEdit = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtProductActiveEdit = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtProductVATEdit = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtImageFileEdit = new javax.swing.JTextField();
        btnProductEdit = new javax.swing.JButton();
        btnExitEdit = new javax.swing.JButton();
        cbbProductType = new javax.swing.JComboBox<>();
        txtProductCountryOfOriginEdit = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        ScrollPaneTab2 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        ScrollPaneTab3 = new javax.swing.JScrollPane();
        modelProductAdd = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        btnAddImage = new javax.swing.JButton();
        txtProductName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtProductManufacturer = new javax.swing.JTextField();
        txtCountryOfOrigin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProductRegisNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProductPurchasePrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtProductActiveIngre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtProductSellingPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtProductDosage = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtProductVAT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtProductPakaging = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtProductActive = new javax.swing.JTextField();
        btnExitProductADD = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        txtImageFile = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbbProductTypeAdd = new javax.swing.JComboBox<>();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtSearchSupplier = new javax.swing.JTextField();
        btnOpenModalAddSup = new javax.swing.JButton();
        actionPanel = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        ProductScrollPane = new javax.swing.JScrollPane();

        TabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        TabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPaneStateChanged(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel9KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Tên sản phẩm");

        txtProductNameEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Loại sản phẩm");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setText("Số đăng ký");

        txtProductRegisNumberEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setText("Giá nhập");

        txtProductPurchasePriceEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel30.setText("Hoạt chất");

        txtProductActiveIngreEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setText("Giá bán");

        txtProductSellingPriceEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel32.setText("Quy trình đóng gói");

        txtProductPakagingEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel33.setText("Liều lượng");

        txtProductDosageEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel34.setText("Nhà sản xuất");

        txtProductManufacturerEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel35.setText("Active");

        txtProductActiveEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel36.setText("Xuất xứ");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel37.setText("VAT");

        txtProductVATEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel38.setText("Ảnh");

        txtImageFileEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnProductEdit.setText("Sửa");
        btnProductEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductEditActionPerformed(evt);
            }
        });

        btnExitEdit.setText("Thoát");
        btnExitEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitEditActionPerformed(evt);
            }
        });

        cbbProductType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEDICINE", "SERVICE", "MEDICALSUPPLIES", " ", " " }));
        cbbProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbProductTypeActionPerformed(evt);
            }
        });

        txtProductCountryOfOriginEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtImageFileEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(btnProductEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnExitEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductNameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductRegisNumberEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductActiveIngreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductDosageEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProductPakagingEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductSellingPriceEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductPurchasePriceEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbProductType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductManufacturerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductCountryOfOriginEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductVATEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductActiveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbProductType))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductNameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductRegisNumberEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductPurchasePriceEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductActiveIngreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductSellingPriceEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductPakagingEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductDosageEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductManufacturerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductActiveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductCountryOfOriginEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductVATEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImageFileEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(74, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExitEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        TabbedPane.addTab("tab1", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTab2, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTab2, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );

        TabbedPane.addTab("tab2", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTab3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTab3, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );

        TabbedPane.addTab("tab3", jPanel11);

        javax.swing.GroupLayout modelEditProductLayout = new javax.swing.GroupLayout(modelEditProduct.getContentPane());
        modelEditProduct.getContentPane().setLayout(modelEditProductLayout);
        modelEditProductLayout.setHorizontalGroup(
            modelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );
        modelEditProductLayout.setVerticalGroup(
            modelEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );

        modelProductAdd.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modelProductAdd.setModal(true);
        modelProductAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelProductAddMouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblImage.setBackground(new java.awt.Color(255, 255, 255));
        lblImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddImage.setBackground(new java.awt.Color(0, 0, 255));
        btnAddImage.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnAddImage.setText("Chọn Ảnh");
        btnAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImageActionPerformed(evt);
            }
        });

        txtProductName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Active");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Tên sản phẩm");

        txtProductManufacturer.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductManufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductManufacturerActionPerformed(evt);
            }
        });

        txtCountryOfOrigin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCountryOfOrigin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCountryOfOriginActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Liều lượng");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Số đăng ký");

        txtProductRegisNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductRegisNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductRegisNumberActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Giá nhập");

        txtProductPurchasePrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductPurchasePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductPurchasePriceActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Hoạt Chất");

        txtProductActiveIngre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductActiveIngre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductActiveIngreActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Giá bán");

        txtProductSellingPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductSellingPriceActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Ảnh");

        txtProductDosage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductDosage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductDosageActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Nhà sản xuất");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Loai sản phẩm");

        txtProductVAT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductVAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductVATActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setText("Quy trình đóng gói");

        txtProductPakaging.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductPakaging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductPakagingActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("VAT");

        txtProductActive.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductActiveActionPerformed(evt);
            }
        });

        btnExitProductADD.setText("Thoát");
        btnExitProductADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitProductADDActionPerformed(evt);
            }
        });

        btnAddProduct.setBackground(new java.awt.Color(51, 51, 255));
        btnAddProduct.setText("Thêm");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        txtImageFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImageFileActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Xuất xứ");

        cbbProductTypeAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEDICINE", "SERVICE", "MEDICALSUPPLIES", " ", " " }));
        cbbProductTypeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbProductTypeAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnExitProductADD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductRegisNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductActiveIngre, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductDosage, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCountryOfOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImageFile, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbbProductTypeAdd, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProductPurchasePrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProductSellingPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProductPakaging, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addComponent(txtProductActive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(cbbProductTypeAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductRegisNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductPurchasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductActiveIngre, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProductPakaging, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProductDosage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductActive, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCountryOfOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImageFile, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExitProductADD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout modelProductAddLayout = new javax.swing.GroupLayout(modelProductAdd.getContentPane());
        modelProductAdd.getContentPane().setLayout(modelProductAddLayout);
        modelProductAddLayout.setHorizontalGroup(
            modelProductAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        modelProductAddLayout.setVerticalGroup(
            modelProductAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(204, 204, 0));

        pnAll.setBackground(new java.awt.Color(255, 255, 255));
        pnAll.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(590, 100));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 16, 24));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(584, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        txtSearchSupplier.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearchSupplier.setMinimumSize(new java.awt.Dimension(300, 40));
        txtSearchSupplier.setPreferredSize(new java.awt.Dimension(300, 40));
        txtSearchSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSupplierActionPerformed(evt);
            }
        });
        jPanel6.add(txtSearchSupplier);

        btnOpenModalAddSup.setBackground(new java.awt.Color(115, 165, 71));
        btnOpenModalAddSup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOpenModalAddSup.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenModalAddSup.setText("Tìm kiếm");
        btnOpenModalAddSup.setMaximumSize(new java.awt.Dimension(150, 40));
        btnOpenModalAddSup.setMinimumSize(new java.awt.Dimension(150, 40));
        btnOpenModalAddSup.setPreferredSize(new java.awt.Dimension(150, 40));
        btnOpenModalAddSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenModalAddSupActionPerformed(evt);
            }
        });
        jPanel6.add(btnOpenModalAddSup);

        jPanel5.add(jPanel6);

        headerPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionPanel.setPreferredSize(new java.awt.Dimension(600, 100));
        actionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 5));

        btnAdd.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnAdd.setText("THÊM");
        btnAdd.setBorder(null);
        btnAdd.setBorderPainted(false);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusPainted(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 90));
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        actionPanel.add(btnAdd);

        btnUpdate.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnUpdate.setText("SỬA");
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUpdate.setPreferredSize(new java.awt.Dimension(100, 90));
        btnUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        actionPanel.add(btnUpdate);

        headerPanel.add(actionPanel, java.awt.BorderLayout.WEST);

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1226, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProductScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProductScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
        );

        pnAll.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenModalAddSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenModalAddSupActionPerformed
//        searchSuppliers();
    }//GEN-LAST:event_btnOpenModalAddSupActionPerformed

    private void txtSearchSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSupplierActionPerformed
//        searchSuppliers();
    }//GEN-LAST:event_txtSearchSupplierActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sửa Sản phẩm", jPanel9); 
        tabbedPane.addTab("Lịch Sử thay đổi số lượng sản phẩm", jPanel10);
        tabbedPane.addTab("Lô", jPanel11);
        tabbedPane.addChangeListener(e -> {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex == 0) {
            updateTab1Data();
        } else if (selectedIndex == 1) {
            updateTab2Data();
        } else if (selectedIndex == 2) {
           updateTab3Data(); 
        }
        });
        modelEditProduct.setContentPane(tabbedPane);
        modelEditProduct.setSize(1100, 900);
        modelEditProduct.setLocationRelativeTo(null);
        modelEditProduct.setVisible(true);

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        modelProductAdd.setSize(1217, 806);
        modelProductAdd.setTitle("Thêm nhà cung cấp");
        modelProductAdd.setLocationRelativeTo(null);
        modelProductAdd.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnProductEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductEditActionPerformed
        // TODO add your handling code here:
       String name = txtProductNameEdit.getText().trim();
       String manufacturer = txtProductManufacturerEdit.getText().trim();
       String activeIngredient = txtProductActiveIngreEdit.getText().trim();
       String dosage = txtProductDosageEdit.getText().trim();
       String countryOfOrigin = txtProductCountryOfOriginEdit.getText().trim();
       String packaging = txtProductPakagingEdit.getText().trim();
       String productTypeString = cbbProductType.getSelectedItem().toString().trim(); // Lấy kiểu sản phẩm từ ComboBox
       String purchasePrice = txtProductPurchasePrice.getText().trim();
       String sellingPrice = txtProductSellingPrice.getText().trim();
       String registrationNumber = txtProductRegisNumber.getText().trim();
       String vat = txtProductVAT.getText().trim();
       String active = txtProductActive.getText().trim();
       String imageFileName = txtImageFile.getText().trim();

       if (!name.equals("") && !manufacturer.equals("") && !activeIngredient.equals("") && 
           !dosage.equals("") && !countryOfOrigin.equals("") && !packaging.equals("") &&
           !productTypeString.equals("") && !purchasePrice.equals("") && !sellingPrice.equals("") &&
           !registrationNumber.equals("") && !vat.equals("") && !active.equals("") && 
           !imageFileName.equals("")) {

           Product product = productBUS.getProductById(productEdit);

           if (productBUS.getProByName(name) != null && !product.getName().equals(name)) {
               MessageDialog.warning(null, "Tên sản phẩm đã tồn tại trong hệ thống.");
               return;
           }

           product.setActive(true); 
           product.setActiveIngredient(activeIngredient); 
           product.setCountryOfOrigin(countryOfOrigin); 
           product.setDosage(dosage); 
           product.setImage(imageFileName); 
           product.setManufacturer(manufacturer); 
           product.setName(name); 
           product.setPackaging(packaging); 

           
           product.setProductType(ProductType.valueOf(productTypeString.toUpperCase())); 

           product.setPurchasePrice(Double.parseDouble(purchasePrice)); 
           product.setSellingPrice(Double.parseDouble(sellingPrice)); 
           product.setRegistrationNumber(registrationNumber); 
           product.setVAT(Double.parseDouble(vat)); 

           productBUS.updateProduct(product);

           
           MessageDialog.info(null, "Cập nhật sản phẩm thành công.");

           
           txtProductNameEdit.setText("");
           txtProductManufacturerEdit.setText("");
           txtProductActiveIngreEdit.setText("");
           txtProductDosageEdit.setText("");
           txtProductCountryOfOriginEdit.setText("");
           txtProductPakagingEdit.setText("");
           txtProductPurchasePrice.setText("");
           txtProductSellingPrice.setText("");
           txtProductRegisNumber.setText("");
           txtProductVAT.setText("");
           txtProductActive.setText("");
           txtImageFile.setText("");

           // Cập nhật bảng sản phẩm
           fillContent(productBUS.getAllProducts());
       } else {
           MessageDialog.info(null, "Các trường không được rỗng.");
       }
    }//GEN-LAST:event_btnProductEditActionPerformed

    private void jPanel9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel9KeyReleased

    private void TabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPaneStateChanged

    }//GEN-LAST:event_TabbedPaneStateChanged

    private void btnAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImageActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn hình ảnh");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            String imagePath = fileToOpen.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(imagePath);
            imageIcon.setDescription(imagePath); 
            Image image = imageIcon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(image));
            lblImage.setText(""); 
            txtImageFile.setText(fileToOpen.getName());
        }


    }//GEN-LAST:event_btnAddImageActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void txtProductManufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductManufacturerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductManufacturerActionPerformed

    private void txtCountryOfOriginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCountryOfOriginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCountryOfOriginActionPerformed

    private void txtProductRegisNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductRegisNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductRegisNumberActionPerformed

    private void txtProductPurchasePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductPurchasePriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductPurchasePriceActionPerformed

    private void txtProductActiveIngreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductActiveIngreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductActiveIngreActionPerformed

    private void txtProductSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductSellingPriceActionPerformed

    private void txtProductDosageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductDosageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductDosageActionPerformed

    private void txtProductVATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductVATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductVATActionPerformed

    private void txtProductPakagingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductPakagingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductPakagingActionPerformed

    private void txtProductActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductActiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductActiveActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        // TODO add your handling code here:
        String name = txtProductName.getText().trim();
        String manufacturer = txtProductManufacturer.getText().trim();
        String activeIngredient = txtProductActiveIngre.getText().trim();
        String dosage = txtProductDosage.getText().trim();
        String countryOfOrigin = txtCountryOfOrigin.getText().trim();
        String packaging = txtProductPakaging.getText().trim();
        String productTypeString = cbbProductTypeAdd.getSelectedItem().toString().trim();
        String registrationNumber = txtProductRegisNumber.getText().trim();
        String imageFileName = txtImageFile.getText().trim();

        try {
            // Chuyển đổi từ String sang double
            double purchasePrice = Double.parseDouble(txtProductPurchasePrice.getText().trim());
            double sellingPrice = Double.parseDouble(txtProductSellingPrice.getText().trim());
            double vat = Double.parseDouble(txtProductVAT.getText().trim());
            boolean active = Boolean.parseBoolean(txtProductActive.getText().trim());

            // Chuyển đổi productTypeString thành ProductType
            ProductType productType = ProductType.valueOf(productTypeString.toUpperCase());

            // Tạo đối tượng Product
            Product product = new Product(
                null,
                name,
                registrationNumber,
                activeIngredient,
                dosage,
                packaging,
                countryOfOrigin,
                manufacturer,
                purchasePrice,
                sellingPrice,
                active,
                productType,
                null
            );

            productBUS.createProduct1(product);

            // Làm sạch các trường nhập
            txtProductName.setText("");
            txtProductManufacturer.setText("");
            txtProductActiveIngre.setText("");
            txtProductDosage.setText("");
            txtCountryOfOrigin.setText("");
            txtProductPakaging.setText("");
            cbbProductTypeAdd.setSelectedIndex(0);
            txtProductPurchasePrice.setText("");
            txtProductSellingPrice.setText("");
            txtProductRegisNumber.setText("");
            txtProductVAT.setText("");
            txtProductActive.setText("");
            txtImageFile.setText("");

            // Cập nhật bảng sản phẩm
            fillContent(productBUS.getAllProducts());
        } catch (NumberFormatException e) {
            MessageDialog.error(null, "Giá trị không hợp lệ: " + e.getMessage());
        } catch (Exception e) {
            MessageDialog.error(null, e.getMessage());
        }
     
        
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void txtImageFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImageFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImageFileActionPerformed

    private void modelProductAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelProductAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_modelProductAddMouseClicked

    private void cbbProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbProductTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbProductTypeActionPerformed

    private void cbbProductTypeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbProductTypeAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbProductTypeAddActionPerformed

    private void btnExitEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitEditActionPerformed
        // TODO add your handling code here:
        modelEditProduct.dispose();
    }//GEN-LAST:event_btnExitEditActionPerformed

    private void btnExitProductADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitProductADDActionPerformed
        // TODO add your handling code here:
        modelProductAdd.dispose();
    }//GEN-LAST:event_btnExitProductADDActionPerformed

    //private String unitIdEdit;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ProductScrollPane;
    private javax.swing.JScrollPane ScrollPaneTab2;
    private javax.swing.JScrollPane ScrollPaneTab3;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddImage;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnExitEdit;
    private javax.swing.JButton btnExitProductADD;
    private javax.swing.JButton btnOpenModalAddSup;
    private javax.swing.JButton btnProductEdit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbProductType;
    private javax.swing.JComboBox<String> cbbProductTypeAdd;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblImage;
    private javax.swing.JDialog modelEditProduct;
    private javax.swing.JDialog modelProductAdd;
    private javax.swing.JPanel pnAll;
    private javax.swing.JTextField txtCountryOfOrigin;
    private javax.swing.JTextField txtImageFile;
    private javax.swing.JTextField txtImageFileEdit;
    private javax.swing.JTextField txtProductActive;
    private javax.swing.JTextField txtProductActiveEdit;
    private javax.swing.JTextField txtProductActiveIngre;
    private javax.swing.JTextField txtProductActiveIngreEdit;
    private javax.swing.JTextField txtProductCountryOfOriginEdit;
    private javax.swing.JTextField txtProductDosage;
    private javax.swing.JTextField txtProductDosageEdit;
    private javax.swing.JTextField txtProductManufacturer;
    private javax.swing.JTextField txtProductManufacturerEdit;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductNameEdit;
    private javax.swing.JTextField txtProductPakaging;
    private javax.swing.JTextField txtProductPakagingEdit;
    private javax.swing.JTextField txtProductPurchasePrice;
    private javax.swing.JTextField txtProductPurchasePriceEdit;
    private javax.swing.JTextField txtProductRegisNumber;
    private javax.swing.JTextField txtProductRegisNumberEdit;
    private javax.swing.JTextField txtProductSellingPrice;
    private javax.swing.JTextField txtProductSellingPriceEdit;
    private javax.swing.JTextField txtProductVAT;
    private javax.swing.JTextField txtProductVATEdit;
    private javax.swing.JTextField txtSearchSupplier;
    // End of variables declaration//GEN-END:variables

    
}
