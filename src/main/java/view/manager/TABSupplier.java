/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.manager;

import bus.SupplierBUS;
import bus.UnitBUS;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import entity.Supplier;
import entity.Unit;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import util.MessageDialog;
import util.ResizeImage;
import view.common.TableActionCellEditorOnlyDelete;
import view.common.TableActionCellRenderOnlyDelete;
import view.common.TableDesign;
import view.common.TableActionEventOnlyDelete;

/**
 *
 * @author Hoang
 */
public class TABSupplier extends javax.swing.JPanel {

    private SupplierBUS supplierBUS;
    private TableDesign tableDesign;
    private String supplierEdit;

    public TABSupplier() {
        supplierBUS = new SupplierBUS(ConnectDB.getEntityManager());
        initComponents();
        setUIManager();
        fillTable();
        addIconFeature();
    }

    private void setUIManager() {
        txtSearchSupplier.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm theo tên, email, số điện thoại");
        txtSupAddressAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập địa chỉ");
        txtSupNameAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên nhà cung cấp");
        txtSupPhoneAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập số điện thoại");
        txtSupEmailAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email");
        txtSupTaxCodeAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập taxcode");
        UIManager.put("Button.arc", 10);
    }

    private void fillTable() {
        String[] headers = {"Mã nhân viên","Tên", "Địa Chỉ","SDT","Email" ,"Mã số Thuế"};
        List<Integer> tableWidths = Arrays.asList(40,130,130 , 40,80,40);
        tableDesign = new TableDesign(headers, tableWidths);
        SupplierScroll.setViewportView(tableDesign.getTable());
        SupplierScroll.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        //addEventBtnEditInTable();
        List<Supplier> suppliers = supplierBUS.getAllSuppliers();
        fillContent(suppliers);
        

    }
    private void fillContent(List<Supplier> suppliers) {
    
        tableDesign.getModelTable().setRowCount(0);
        for (Supplier supplier : suppliers) {
            tableDesign.getModelTable().addRow(new Object[]{
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getTaxCode(),
                null 
            });
        }
        
    }

    private void addIconFeature() {
        btnAdd.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/addBtn.svg")), 35, 35));
        btnUpdate.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/editBtn.svg")), 35, 35));
        btnDelete.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/deleteBtn.svg")), 35, 35));
        btnImport.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/import.svg")), 35, 35));
        btnExport.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/export.svg")), 35, 35));
    }
    
    public void clearData(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
    
    public boolean checkValidData (JTextField txtName, JTextField txtAddress, JTextField txtPhone, JTextField txtEmail, JTextField txtTaxCode){
        
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String taxcode = txtTaxCode.getText();
        
        modelSupplierEdit.setPreferredSize(new Dimension(800, 600));
        modelSupplierEdit.pack();
        
        if(name.trim().isEmpty()){
            MessageDialog.warring(null, "Tên nhà cung cấp không được trống.");
            txtName.requestFocus();
            return false;
        }
        if(address.trim().isEmpty()){
            MessageDialog.warring(null, "Địa chỉ không được trống.");
            txtAddress.requestFocus();
            return false;
        }
        if(!phone.matches("^(0[1-9]{1}[0-9]{8}|0[1-9]{1}[0-9]{9})$")){
            MessageDialog.warring(null, "Số điện thoại 10-11 số.");
            txtPhone.requestFocus();
            return false;
        }
        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            MessageDialog.warring(null, "Email không được trống.");
            txtPhone.requestFocus();
            return false;
        }
        if(taxcode.trim().isEmpty()){
            MessageDialog.warring(null, "TaxCode không được trống.");
            txtPhone.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void searchSuppliers() {
       String searchText = txtSearchSupplier.getText().trim();
    
        List<Supplier> suppliers;
        if (searchText.isEmpty()) {
        suppliers = supplierBUS.getAllSuppliers();
        } else {
            suppliers = supplierBUS.searchSuppliersByText(searchText);
        }
    
        fillContent(suppliers);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modelSupplierAdd = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSupPhoneAdd = new javax.swing.JTextField();
        txtSupNameAdd = new javax.swing.JTextField();
        txtSupAddressAdd = new javax.swing.JTextField();
        txtSupTaxCodeAdd = new javax.swing.JTextField();
        txtSupEmailAdd = new javax.swing.JTextField();
        btnSupplierAdd = new javax.swing.JButton();
        btnSupplierExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        modelSupplierEdit = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtSupPhoneEdit = new javax.swing.JTextField();
        txtSupNameEdit = new javax.swing.JTextField();
        txtSupAddressEdit = new javax.swing.JTextField();
        txtSupTaxCodeEdit = new javax.swing.JTextField();
        txtSupEmailEdit = new javax.swing.JTextField();
        btnSupplierEdit = new javax.swing.JButton();
        btnSupplierExit2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtSearchSupplier = new javax.swing.JTextField();
        btnOpenModalAddSup = new javax.swing.JButton();
        actionPanel = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        SupplierScroll = new javax.swing.JScrollPane();

        modelSupplierAdd.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modelSupplierAdd.setModal(true);
        modelSupplierAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelSupplierAddMouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tên nhà cung cấp :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("SDT :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Địa chỉ :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Mã số Thuế :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Email :");

        txtSupPhoneAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupPhoneAddActionPerformed(evt);
            }
        });

        txtSupNameAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSupNameAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupNameAddActionPerformed(evt);
            }
        });

        txtSupAddressAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupAddressAddActionPerformed(evt);
            }
        });

        txtSupTaxCodeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupTaxCodeAddActionPerformed(evt);
            }
        });

        txtSupEmailAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupEmailAddActionPerformed(evt);
            }
        });

        btnSupplierAdd.setBackground(new java.awt.Color(0, 51, 255));
        btnSupplierAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSupplierAdd.setText("Thêm");
        btnSupplierAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierAddActionPerformed(evt);
            }
        });

        btnSupplierExit.setBackground(new java.awt.Color(255, 0, 0));
        btnSupplierExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSupplierExit.setText("Thoát");
        btnSupplierExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Thêm nhà cung cấp");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSupEmailAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupTaxCodeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupAddressAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupPhoneAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSupplierAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnSupplierExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupAddressAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupPhoneAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupEmailAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupTaxCodeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupplierAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplierExit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modelSupplierAddLayout = new javax.swing.GroupLayout(modelSupplierAdd.getContentPane());
        modelSupplierAdd.getContentPane().setLayout(modelSupplierAddLayout);
        modelSupplierAddLayout.setHorizontalGroup(
            modelSupplierAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modelSupplierAddLayout.setVerticalGroup(
            modelSupplierAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        modelSupplierEdit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modelSupplierEdit.setModal(true);
        modelSupplierEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelSupplierEditKeyReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Tên nhà cung cấp :");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("SDT :");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Địa chỉ :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Mã số Thuế :");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Email :");

        txtSupPhoneEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupPhoneEditActionPerformed(evt);
            }
        });

        txtSupNameEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSupNameEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupNameEditActionPerformed(evt);
            }
        });

        txtSupAddressEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupAddressEditActionPerformed(evt);
            }
        });

        txtSupTaxCodeEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupTaxCodeEditActionPerformed(evt);
            }
        });

        txtSupEmailEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupEmailEditActionPerformed(evt);
            }
        });

        btnSupplierEdit.setBackground(new java.awt.Color(0, 0, 255));
        btnSupplierEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSupplierEdit.setText("Sửa");
        btnSupplierEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierEditActionPerformed(evt);
            }
        });

        btnSupplierExit2.setBackground(new java.awt.Color(255, 0, 0));
        btnSupplierExit2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSupplierExit2.setText("Thoát");
        btnSupplierExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierExit2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Sửa Thông Tin Nhà Cung Cấp");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSupEmailEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupTaxCodeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupAddressEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupPhoneEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupNameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSupplierEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnSupplierExit2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtSupNameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtSupAddressEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtSupPhoneEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtSupEmailEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtSupTaxCodeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupplierEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplierExit2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modelSupplierEditLayout = new javax.swing.GroupLayout(modelSupplierEdit.getContentPane());
        modelSupplierEdit.getContentPane().setLayout(modelSupplierEditLayout);
        modelSupplierEditLayout.setHorizontalGroup(
            modelSupplierEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modelSupplierEditLayout.setVerticalGroup(
            modelSupplierEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        txtSearchSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        btnDelete.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnDelete.setText("XÓA");
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setFocusPainted(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setPreferredSize(new java.awt.Dimension(100, 90));
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        actionPanel.add(btnDelete);

        btnImport.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnImport.setText("IMPORT");
        btnImport.setBorder(null);
        btnImport.setBorderPainted(false);
        btnImport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImport.setFocusPainted(false);
        btnImport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImport.setPreferredSize(new java.awt.Dimension(100, 90));
        btnImport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        actionPanel.add(btnImport);

        btnExport.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnExport.setText("EXPORT");
        btnExport.setBorder(null);
        btnExport.setBorderPainted(false);
        btnExport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExport.setFocusPainted(false);
        btnExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExport.setPreferredSize(new java.awt.Dimension(100, 90));
        btnExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        actionPanel.add(btnExport);

        headerPanel.add(actionPanel, java.awt.BorderLayout.WEST);

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1226, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(SupplierScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(SupplierScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
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
        searchSuppliers();
    }//GEN-LAST:event_btnOpenModalAddSupActionPerformed

    private void txtSearchSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSupplierActionPerformed
        searchSuppliers();
    }//GEN-LAST:event_txtSearchSupplierActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
//        if (unitBUS.importExcel()) {
//            MessageDialog.info(null, "Import file thành công");
//            List<Unit> units = unitBUS.getAllUnits();
//            fillContent(units);
//        };
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        JTable table = tableDesign.getTable();
    int selectedRow = table.getSelectedRow();
    
    if (selectedRow != -1) {
     
        supplierEdit = (String) table.getValueAt(selectedRow, 0);
        txtSupNameEdit.setText((String) table.getValueAt(selectedRow, 1));
        txtSupAddressEdit.setText((String) table.getValueAt(selectedRow, 2));
        txtSupPhoneEdit.setText((String) table.getValueAt(selectedRow, 3));
        txtSupEmailEdit.setText((String) table.getValueAt(selectedRow, 4));
        txtSupTaxCodeEdit.setText((String) table.getValueAt(selectedRow, 5));

        
        if (table.getCellEditor() != null) {
            table.getCellEditor().stopCellEditing();
        }
        
        modelSupplierEdit.setSize(800, 600);
        modelSupplierEdit.setLocationRelativeTo(null);
        modelSupplierEdit.pack(); 
        modelSupplierEdit.setVisible(true);
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        modelSupplierAdd.setSize(800, 600);
        modelSupplierAdd.setLocationRelativeTo(null);
        modelSupplierAdd.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSupPhoneAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupPhoneAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupPhoneAddActionPerformed

    private void txtSupNameAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupNameAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupNameAddActionPerformed

    private void txtSupAddressAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupAddressAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupAddressAddActionPerformed

    private void txtSupTaxCodeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupTaxCodeAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupTaxCodeAddActionPerformed

    private void txtSupEmailAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupEmailAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupEmailAddActionPerformed

    private void btnSupplierAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierAddActionPerformed
        // TODO add your handling code here:
        if(checkValidData(txtSupNameAdd, txtSupAddressAdd, txtSupPhoneAdd, txtSupEmailAdd, txtSupTaxCodeAdd)){
            String name = txtSupNameAdd.getText().trim();
            String address = txtSupAddressAdd.getText().trim();
            String phone = txtSupPhoneAdd.getText().trim();
            String email = txtSupEmailAdd.getText().trim();
            String taxcode = txtSupTaxCodeAdd.getText().trim();

            Supplier sup = new Supplier(null, name, address, phone, email, taxcode);
            supplierBUS.createrSupplier(sup);
            MessageDialog.info(null, "Thêm nhà cung cấp thành công");
            clearData(txtSupNameAdd,txtSupAddressAdd,txtSupPhoneAdd,txtSupEmailAdd,txtSupTaxCodeAdd);
            modelSupplierAdd.dispose();
            fillContent(supplierBUS.getAllSuppliers());
        }

    }//GEN-LAST:event_btnSupplierAddActionPerformed

    private void modelSupplierAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelSupplierAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_modelSupplierAddMouseClicked

    private void txtSupPhoneEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupPhoneEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupPhoneEditActionPerformed

    private void txtSupNameEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupNameEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupNameEditActionPerformed

    private void txtSupAddressEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupAddressEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupAddressEditActionPerformed

    private void txtSupTaxCodeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupTaxCodeEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupTaxCodeEditActionPerformed

    private void txtSupEmailEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupEmailEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupEmailEditActionPerformed

    private void btnSupplierEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierEditActionPerformed
        // TODO add your handling code here:
        String name = txtSupNameEdit.getText().trim();
        String address = txtSupAddressEdit.getText().trim();
        String phone = txtSupPhoneEdit.getText().trim();
        String email = txtSupEmailEdit.getText().trim();
        String taxCode = txtSupTaxCodeEdit.getText().trim();

        if (!name.equals("") && !address.equals("") && !phone.equals("") && !email.equals("") && !taxCode.equals("")) {
            Supplier supplier = supplierBUS.getSupplierById(supplierEdit);
            // Kiểm tra tên nhà cung cấp đã tồn tại chưa
            if(supplierBUS.getSupByName(name) != null) {
                MessageDialog.warring(null, "Tên nhà cung cấp đã tồn tại trong hệ thống.");
                return;
            }
            // Cập nhật thông tin nhà cung cấp
            supplier.setName(name);
            supplier.setAddress(address);
            supplier.setPhone(phone);
            supplier.setEmail(email);
            supplier.setTaxCode(taxCode);
            supplierBUS.updateSupplier(supplier);

            MessageDialog.info(null, "Thay đổi thông tin nhà cung cấp thành công.");
            txtSupNameEdit.setText("");
            txtSupAddressEdit.setText("");
            txtSupPhoneEdit.setText("");
            txtSupEmailEdit.setText("");
            txtSupTaxCodeEdit.setText("");
            modelSupplierEdit.dispose();
            fillContent(supplierBUS.getAllSuppliers()); // Cập nhật lại danh sách nhà cung cấp
        } else {
            MessageDialog.info(null, "Các trường không được rỗng.");
        }

    }//GEN-LAST:event_btnSupplierEditActionPerformed

    private void modelSupplierEditKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelSupplierEditKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_modelSupplierEditKeyReleased

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
       JFileChooser fileChooser = new JFileChooser();
       fileChooser.setDialogTitle("Chọn vị trí để lưu file Excel");

       // Hiện hộp thoại lưu file
       int userSelection = fileChooser.showSaveDialog(this);
       if (userSelection == JFileChooser.APPROVE_OPTION) {
           File fileToSave = fileChooser.getSelectedFile();
           String filePath = fileToSave.getAbsolutePath();

           // Kiểm tra định dạng file và thêm đuôi .xlsx nếu cần
           if (!filePath.endsWith(".xlsx")) {
               filePath += ".xlsx";
           }

           // Gọi phương thức exportToExcel từ đối tượng supplierBUS
           supplierBUS.exportToExcel(filePath);
       }
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnSupplierExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierExitActionPerformed
        modelSupplierAdd.dispose();
    }//GEN-LAST:event_btnSupplierExitActionPerformed

    private void btnSupplierExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierExit2ActionPerformed
        modelSupplierEdit.dispose();
    }//GEN-LAST:event_btnSupplierExit2ActionPerformed

    //private String unitIdEdit;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SupplierScroll;
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnOpenModalAddSup;
    private javax.swing.JButton btnSupplierAdd;
    private javax.swing.JButton btnSupplierEdit;
    private javax.swing.JButton btnSupplierExit;
    private javax.swing.JButton btnSupplierExit2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JDialog modelSupplierAdd;
    private javax.swing.JDialog modelSupplierEdit;
    private javax.swing.JPanel pnAll;
    private javax.swing.JTextField txtSearchSupplier;
    private javax.swing.JTextField txtSupAddressAdd;
    private javax.swing.JTextField txtSupAddressEdit;
    private javax.swing.JTextField txtSupEmailAdd;
    private javax.swing.JTextField txtSupEmailEdit;
    private javax.swing.JTextField txtSupNameAdd;
    private javax.swing.JTextField txtSupNameEdit;
    private javax.swing.JTextField txtSupPhoneAdd;
    private javax.swing.JTextField txtSupPhoneEdit;
    private javax.swing.JTextField txtSupTaxCodeAdd;
    private javax.swing.JTextField txtSupTaxCodeEdit;
    // End of variables declaration//GEN-END:variables

    
}
