/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.staff;

import bus.*;
import com.formdev.flatlaf.FlatClientProperties;
import dto.BatchDTO;
import dto.PurchaseOrderDTO;
import entity.Product;
import java.awt.Component;
import java.util.*;
import util.FormatNumber;
import util.MessageDialog;
import entity.*;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.CurrentEmployee;

import gui.login.LoadApplication;
import gui.staff.purchase.PnPurchaseOrderDetail;
import gui.staff.purchase.PnPurchaseSelectBatch;
import java.awt.event.KeyEvent;

/**
 *
 * @author Hoang
 */
public class TABPurchase extends javax.swing.JPanel {

    /**
     * Creates new form TabHoaDon
     */
    private final BatchBUS batchBUS;
    private final ProductBUS productBUS;
    private final SupplierBUS supplierBUS;
    private final PurchaseOrderBUS purchaseOrderBUS;

    public TABPurchase() {
        initComponents();
        productBUS = LoadApplication.productBUS;
        batchBUS = LoadApplication.batchBUS;
        supplierBUS = LoadApplication.supplierBUS;
        purchaseOrderBUS = LoadApplication.purchaseOrderBUS;

        lookAndFeelSet();

        txtSearchProduct.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số đăng ký, mã vạch");
        txtSearchSupplier.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại nhà cung cấp");
    }

    private void lookAndFeelSet() {
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("Component.arc", 8);
        UIManager.put("Button.arc", 10);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TabbedPane.tabHeight", 45);
        UIManager.put("ToggleButton.selectedBackground", new Color(81, 154, 244));
        UIManager.put("ToggleButton.selectedForeground", Color.WHITE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMid = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnContent = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        btnConfirmPurchase = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblSupplierId = new javax.swing.JLabel();
        txtSupplierId = new javax.swing.JTextField();
        txtSearchSupplier = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblSupplierId1 = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblSupplierId2 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        headerPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtSearchProduct = new javax.swing.JTextField();
        btnMa = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnMid.setMinimumSize(new java.awt.Dimension(200, 200));
        pnMid.setOpaque(false);

        pnContent.setBackground(new java.awt.Color(255, 255, 255));
        pnContent.setLayout(new javax.swing.BoxLayout(pnContent, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(pnContent);

        javax.swing.GroupLayout pnMidLayout = new javax.swing.GroupLayout(pnMid);
        pnMid.setLayout(pnMidLayout);
        pnMidLayout.setHorizontalGroup(
            pnMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1433, Short.MAX_VALUE)
        );
        pnMidLayout.setVerticalGroup(
            pnMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );

        add(pnMid, java.awt.BorderLayout.CENTER);

        pnLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnLeft.setPreferredSize(new java.awt.Dimension(485, 650));

        btnConfirmPurchase.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConfirmPurchase.setText("Nhập hàng ( F8 )");
        btnConfirmPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmPurchaseActionPerformed(evt);
            }
        });
        btnConfirmPurchase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnConfirmPurchaseKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblSupplierId.setFont(lblSupplierId.getFont().deriveFont(lblSupplierId.getFont().getStyle() | java.awt.Font.BOLD, lblSupplierId.getFont().getSize()+2));
        lblSupplierId.setText("Mã nhà cung cấp:");

        txtSupplierId.setEditable(false);
        txtSupplierId.setFont(txtSupplierId.getFont().deriveFont(txtSupplierId.getFont().getSize()+3f));
        txtSupplierId.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSupplierId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSupplierId, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSupplierId)
                        .addContainerGap())))
        );

        txtSearchSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSupplierActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblSupplierId1.setFont(lblSupplierId1.getFont().deriveFont(lblSupplierId1.getFont().getStyle() | java.awt.Font.BOLD, lblSupplierId1.getFont().getSize()+2));
        lblSupplierId1.setText("Tên nhà cung cấp:");

        txtSupplierName.setEditable(false);
        txtSupplierName.setFont(txtSupplierName.getFont().deriveFont(txtSupplierName.getFont().getSize()+3f));
        txtSupplierName.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSupplierId1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSupplierId1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtSupplierName)
                        .addContainerGap())))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblSupplierId2.setFont(lblSupplierId2.getFont().deriveFont(lblSupplierId2.getFont().getStyle() | java.awt.Font.BOLD, lblSupplierId2.getFont().getSize()+2));
        lblSupplierId2.setText("Tổng tiền:");

        txtTotalPrice.setEditable(false);
        txtTotalPrice.setFont(txtTotalPrice.getFont().deriveFont(txtTotalPrice.getFont().getSize()+3f));
        txtTotalPrice.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSupplierId2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSupplierId2, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTotalPrice)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLeftLayout.createSequentialGroup()
                        .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchSupplier, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftLayout.createSequentialGroup()
                        .addComponent(btnConfirmPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(txtSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 425, Short.MAX_VALUE)
                .addComponent(btnConfirmPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        add(pnLeft, java.awt.BorderLayout.EAST);

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 232, 232), 2, true));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtSearchProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProductActionPerformed(evt);
            }
        });
        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyPressed(evt);
            }
        });

        btnMa.setBackground(new java.awt.Color(115, 165, 71));
        btnMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMa.setForeground(new java.awt.Color(255, 255, 255));
        btnMa.setText("Tìm");
        btnMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaActionPerformed(evt);
            }
        });

        btnImport.setBackground(new java.awt.Color(115, 165, 71));
        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImport.setForeground(new java.awt.Color(255, 255, 255));
        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMa, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        headerPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void searchProduct(String sdk) {
        Product product = productBUS.getProductBySDK(sdk);
        if (product != null) {
            addSanPham(product);
            txtSearchProduct.setText("");
        } else {
            MessageDialog.warning(null, "Sản phẩm không tồn tại !!!");
        }
    }

    private void addSanPham(Product product) {

        List<PnPurchaseOrderDetail> pnPurchaseOrderDetails = getAllPnPurchaseOrderDetailThuoc();
        for (PnPurchaseOrderDetail pnPurchaseOrderDetail : pnPurchaseOrderDetails) {
            if (pnPurchaseOrderDetail.getProduct().getProductId().equalsIgnoreCase(product.getProductId())) {
                MessageDialog.warning(null, "Sản phẩm đã tồn tại!");
                return;
            }
        }

        List<Batch> batchs = batchBUS.getListBatchEnable(product);
        PnPurchaseOrderDetail pnPurchaseOrderDetail = new PnPurchaseOrderDetail(product, batchs, this);
        pnContent.add(pnPurchaseOrderDetail);
        pnContent.revalidate();
        pnContent.repaint();

//        updateUnitDetailsForProduct(product, unitDetails.get(0));
    }

//    private void updateUnitDetailsForProduct(Product product, UnitDetail unitDetailTmp) {
//
//        for (Component component : pnContent.getComponents()) {
//            if (component instanceof PnPurchaseOrderDetail) {
//                PnPurchaseOrderDetail existingDetail = (PnPurchaseOrderDetail) component;
//                if (existingDetail.getProduct().equals(product)) {
//                    UnitDetail selectedUnit = existingDetail.getSelectedUnitDetail();
//                    if (!selectedUnit.equals(unitDetailTmp)) {
//                        existingDetail.updateUnitDetails(unitDetailTmp);
//                    }
//                }
//            }
//        }
//    }
    public void changeTongTienHoaDon() {
        tongTienHang = 0.0;
        List<PnPurchaseOrderDetail> listPanelPurchaseOrderDetails = getAllPnPurchaseOrderDetailThuoc();
        for (PnPurchaseOrderDetail x : listPanelPurchaseOrderDetails) {
            tongTienHang += x.getLineTotal();
        }
        txtTotalPrice.setText(FormatNumber.formatToVND(tongTienHang));
    }

    private List<PnPurchaseOrderDetail> getAllPnPurchaseOrderDetailThuoc() {
        List<PnPurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
        Component[] components = pnContent.getComponents();

        for (Component component : components) {
            if (component instanceof PnPurchaseOrderDetail) {
                purchaseOrderDetails.add((PnPurchaseOrderDetail) component);
            }
        }
        return purchaseOrderDetails;
    }

    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed
//        String textTim = txtSearchProduct.getText();
//        searchProduct(textTim);
    }//GEN-LAST:event_txtSearchProductActionPerformed

    private void btnMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaActionPerformed
        // TODO add your handling code here:
        String textTim = txtSearchProduct.getText();
        searchProduct(textTim);
    }//GEN-LAST:event_btnMaActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        clearPnOrderDetail();
        try {
            JFileChooser chooserFile = new JFileChooser();
            chooserFile.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
            chooserFile.setFileFilter(fnef);
            int result = chooserFile.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File excelFile = chooserFile.getSelectedFile();
                FileInputStream excelFIS = new FileInputStream(excelFile);
                BufferedInputStream excelBIS = new BufferedInputStream(excelFIS);
                XSSFWorkbook excelImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImport.getSheetAt(0);

                String phoneSupplier = excelSheet.getRow(3).getCell(1).getStringCellValue().trim();
                supplier = supplierBUS.getSupplierByPhone(phoneSupplier);
                if (supplier == null) {
                    MessageDialog.warning(null, "Nhà cung cấp không tồn tại !!!");
                    return;
                }
                txtSupplierId.setText(supplier.getSupplierId());
                txtSupplierName.setText(supplier.getName());
                txtSearchSupplier.setText("");

                for (int row = 6; row <= excelSheet.getLastRowNum(); row++) {
                    PnPurchaseOrderDetail pnPurchaseOrderDetail;

                    XSSFRow excelRow = excelSheet.getRow(row);
                    String regisNb = excelRow.getCell(1).getStringCellValue().trim();
                    String productName = excelRow.getCell(2).getStringCellValue().trim();
                    String batchName = excelRow.getCell(3).getStringCellValue().trim();
                    String experationDateStr = excelRow.getCell(4).getStringCellValue().trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate experationDate = LocalDate.parse(experationDateStr, formatter);
                    String unitName = excelRow.getCell(5).getStringCellValue().trim();
                    int quantity = (int) excelRow.getCell(6).getNumericCellValue();

                    int check = 0;
                    Product p = (Product) productBUS.getProductBySDK(regisNb);
                    if (p == null) {
                        MessageDialog.warning(null, "Sản phẩm không tồn tại: " + productName);
                        continue;
                    }

                    List<Batch> batchs = batchBUS.getListBatchEnable(p);
                    pnPurchaseOrderDetail = new PnPurchaseOrderDetail(p, batchs, this);
                    for (Component component : pnContent.getComponents()) {
                        if (component instanceof PnPurchaseOrderDetail) {
                            PnPurchaseOrderDetail existingDetail = (PnPurchaseOrderDetail) component;
                            if (p.getProductId().equalsIgnoreCase(existingDetail.getProduct().getProductId())) {
                                check = 1;
                                pnPurchaseOrderDetail = existingDetail;
                            }
                        }
                    }

                    if (check == 1) {
                        BatchDTO batchDTO = new BatchDTO(batchName, quantity, experationDate, quantity);
                        pnPurchaseOrderDetail.getPnListBatch().add(new PnPurchaseSelectBatch(batchDTO, pnPurchaseOrderDetail.getSpinnerSoLuong()));
                        pnPurchaseOrderDetail.getPnListBatch().revalidate();
                        pnPurchaseOrderDetail.getPnListBatch().repaint();

                        pnPurchaseOrderDetail.setQuantity();

                        pnContent.add(pnPurchaseOrderDetail);
                        pnContent.revalidate();
                        pnContent.repaint();

                    } else {
                        pnPurchaseOrderDetail = new PnPurchaseOrderDetail(p, batchs, this);

                        Batch b = batchBUS.getBatchByName(batchName);
                        if (b != null) {
                            BatchDTO batchDTO = new BatchDTO(batchName, b.getStock(), b.getExpirationDate(), quantity);
                            pnPurchaseOrderDetail.getPnListBatch().add(new PnPurchaseSelectBatch(batchDTO, pnPurchaseOrderDetail.getSpinnerSoLuong()));
                        } else {
                            BatchDTO batchDTO = new BatchDTO(batchName, quantity, experationDate, quantity);
                            pnPurchaseOrderDetail.getPnListBatch().add(new PnPurchaseSelectBatch(batchDTO, pnPurchaseOrderDetail.getSpinnerSoLuong()));
                        }

                        pnPurchaseOrderDetail.getPnListBatch().revalidate();
                        pnPurchaseOrderDetail.getPnListBatch().repaint();

                        pnPurchaseOrderDetail.setQuantity();
                        pnContent.add(pnPurchaseOrderDetail);
                        pnContent.revalidate();
                        pnContent.repaint();

                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Import file bị lỗi");
        }
        changeTongTienHoaDon();
    }//GEN-LAST:event_btnImportActionPerformed

    private List<PurchaseOrderDTO> createListPurchaseOrderDetail() {
        List<PurchaseOrderDTO> podtos = new ArrayList<>();
        List<PnPurchaseOrderDetail> listPurchasePnOrderDetail = getAllPnPurchaseOrderDetailThuoc();
        if (listPurchasePnOrderDetail == null) {
            MessageDialog.warning(null, "Chưa có sản phẩm !!!");
        } else {
            for (PnPurchaseOrderDetail pnPurchaseOrderDetail : listPurchasePnOrderDetail) {
                if (pnPurchaseOrderDetail.getSoLuong() == 0) {
                    MessageDialog.warning(null, String.format("Sản phẩm '%s' chưa chọn lô", pnPurchaseOrderDetail.getProduct().getName()));
                    return null;
                } else {
                    JPanel pnListBatch = pnPurchaseOrderDetail.getPnListBatch();
                    for (Component component : pnListBatch.getComponents()) {
                        if (component instanceof PnPurchaseSelectBatch) {
                            PnPurchaseSelectBatch pnSelectBatch = (PnPurchaseSelectBatch) component;
                            int quantity = (int) pnSelectBatch.getSpinnerQuantity().getValue();
                            String batchName = pnSelectBatch.getBatchDTO().getName();
                            if (pnSelectBatch.getBatchDTO().getExpirationDate().isBefore(LocalDate.now())) {
                                MessageDialog.warning(null, "Lô hàng " + batchName + " có ngày hết hạn không hợp lệ");
                                return null;
                            }
                            podtos.add(new PurchaseOrderDTO(pnPurchaseOrderDetail.getProduct().getProductId(),
                                    pnPurchaseOrderDetail.getProduct().getUnit().getName(), quantity, batchName,
                                    pnSelectBatch.getBatchDTO().getExpirationDate()));
                        }

                    }
                }
            }
        }
        return podtos;
    }

    private void clearTxtEmpty(JTextField... jTextFields) {
        for (JTextField x : jTextFields) {
            x.setText("");
        }

    }

    private void clearPnOrderDetail() {
        clearTxtEmpty(txtSupplierId, txtSupplierName, txtTotalPrice);
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
    }

    private void createOrder() {
        if (txtSupplierId.getText().trim().equals("") || txtSupplierName.getText().trim().equals("")) {
            MessageDialog.warning(null, "Chưa có nhà cung cấp!");
            return;
        }
        List<PurchaseOrderDTO> purchaseOrderDTOs = createListPurchaseOrderDetail();
        if (purchaseOrderDTOs == null) {
            return;
        }
        try {
            if (purchaseOrderBUS.createPurchaseOrder(CurrentEmployee.getEmployee(), supplier, purchaseOrderDTOs)) {
                MessageDialog.info(null, "Nhập hàng thành công!");
                clearPnOrderDetail();
            }
        } catch (Exception e) {
            MessageDialog.error(null, e.getMessage());
        }
    }

    private void btnConfirmPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmPurchaseActionPerformed
        createOrder();
    }//GEN-LAST:event_btnConfirmPurchaseActionPerformed

    private void txtSearchSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSupplierActionPerformed
        String txtTim = txtSearchSupplier.getText().trim();
        if (txtTim == null) {
            MessageDialog.warning(null, "Chưa nhập số điện thoại nhà cung cấp !!!");
        } else {
            Supplier sup = supplierBUS.getSupplierByPhone(txtTim);
            if (sup == null) {
                supplier = null;
                MessageDialog.warning(null, "Nhà cung cấp không tồn tại !!!");
            } else {
                supplier = sup;
                txtSupplierId.setText(sup.getSupplierId());
                txtSupplierName.setText(sup.getName());
                txtSearchSupplier.setText("");
            }
        }
    }//GEN-LAST:event_txtSearchSupplierActionPerformed

    private void txtSearchProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String sdk = txtSearchProduct.getText().trim();
            searchProduct(sdk);
            txtSearchProduct.requestFocus();
        }
    }//GEN-LAST:event_txtSearchProductKeyPressed

    private void btnConfirmPurchaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConfirmPurchaseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F8) {
            createOrder();
        }
    }//GEN-LAST:event_btnConfirmPurchaseKeyPressed
    private double tongTienHang;
    private Supplier supplier;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmPurchase;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnMa;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSupplierId;
    private javax.swing.JLabel lblSupplierId1;
    private javax.swing.JLabel lblSupplierId2;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnMid;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtSearchSupplier;
    private javax.swing.JTextField txtSupplierId;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JTextField txtTotalPrice;
    // End of variables declaration//GEN-END:variables

}
