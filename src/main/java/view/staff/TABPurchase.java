         /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff;

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
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.CurrentEmployee;

import view.login.LoadApplication;
import view.staff.purchase.PnPurchaseOrderDetail;
import view.staff.purchase.PnPurchaseSelectBatch;

/**
 *
 * @author Hoang
 */
public class TABPurchase extends javax.swing.JPanel {

    /**
     * Creates new form TabHoaDon
     */
    private final UnitDetailBUS unitDetailBUS;
    private final BatchBUS batchBUS;
    private final ProductBUS productBUS;
    private final SupplierBUS supplierBUS;
    private final PurchaseOrderBUS purchaseOrderBUS;

    public TABPurchase() {
        initComponents();
        productBUS = LoadApplication.productBUS;
        unitDetailBUS = LoadApplication.unitDetailBUS;
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
        jPanel8 = new javax.swing.JPanel();
        lblTotalPrice = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblSupplierName = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblSupplierId = new javax.swing.JLabel();
        txtSupplierId = new javax.swing.JLabel();
        txtSearchSupplier = new javax.swing.JTextField();
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
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

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTotalPrice.setText("Tổng tiền hàng:");

        txtTotalPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTotalPrice.setText("0 đ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblTotalPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(lblTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblSupplierName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSupplierName.setText("Tên nhà cung cấp:");

        txtSupplierName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSupplierName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSupplierName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblSupplierId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSupplierId.setText("Mã nhà cung cấp:");

        txtSupplierId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSupplierId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(txtSupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSupplierId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSupplierId, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        txtSearchSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addContainerGap(548, Short.MAX_VALUE)
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnConfirmPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchSupplier)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(549, Short.MAX_VALUE))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(txtSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addComponent(btnConfirmPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
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

        btnMa.setBackground(new java.awt.Color(115, 165, 71));
        btnMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMa.setForeground(new java.awt.Color(255, 255, 255));
        btnMa.setText("Mã");
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
                .addGap(100, 100, 100)
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

    private void searchProduct(String textTim) {
        Product product = productBUS.getProductBySDK(textTim);
        if (product != null) {
            addSanPham(product);
            txtSearchProduct.setText("");
        } else {
            MessageDialog.warning(null, "Sản phẩm không tồn tại !!!");
        }
    }

    private void addSanPham(Product product) {

        List<UnitDetail> unitDetails = unitDetailBUS.getListUnitProduct(product);
        for (Component component : pnContent.getComponents()) {
            if (component instanceof PnPurchaseOrderDetail) {
                PnPurchaseOrderDetail existingDetail = (PnPurchaseOrderDetail) component;

                if (existingDetail.getProduct().equals(product)) {
                    UnitDetail selectedUnit = existingDetail.getSelectedUnitDetail();
                    if (selectedUnit != null) {
                        unitDetails.remove(selectedUnit);
                    }
                }
            }
        }

        if (unitDetails.isEmpty()) {
            MessageDialog.warning(null, "Sản phẩm đã tồn tại");
            return;
        }

        List<Batch> batchs = batchBUS.getListBatchEnable(product);
        PnPurchaseOrderDetail pnPurchaseOrderDetail = new PnPurchaseOrderDetail(product, unitDetails, batchs, this);
        pnContent.add(pnPurchaseOrderDetail);
        pnContent.revalidate();
        pnContent.repaint();

        updateUnitDetailsForProduct(product, unitDetails.get(0));
    }

    private void updateUnitDetailsForProduct(Product product, UnitDetail unitDetailTmp) {

        for (Component component : pnContent.getComponents()) {
            if (component instanceof PnPurchaseOrderDetail) {
                PnPurchaseOrderDetail existingDetail = (PnPurchaseOrderDetail) component;
                if (existingDetail.getProduct().equals(product)) {
                    UnitDetail selectedUnit = existingDetail.getSelectedUnitDetail();
                    if (!selectedUnit.equals(unitDetailTmp)) {
                        existingDetail.updateUnitDetails(unitDetailTmp);
                    }
                }
            }
        }
    }

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
        String textTim = txtSearchProduct.getText();
        searchProduct(textTim);
    }//GEN-LAST:event_txtSearchProductActionPerformed

    private void btnMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaActionPerformed
        // TODO add your handling code here:
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
                Supplier sup = supplierBUS.getSupplierByPhone(phoneSupplier);
                if (sup == null) {
                    supplier = null;
                    MessageDialog.warning(null, "Nhà cung cấp không tồn tại !!!");
                    return;
                }
                supplier = sup;
                txtSupplierId.setText(sup.getSupplierId());
                txtSupplierName.setText(sup.getName());
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
                    List<UnitDetail> unitDetails = unitDetailBUS.getListUnitProduct(p);
                    UnitDetail checkExists = unitDetails
                            .stream()
                            .filter(u -> u.getUnit().getName().equalsIgnoreCase(unitName)).findFirst().orElse(null);
                    if(checkExists == null){
                        MessageDialog.warning(null, "Đơn vị tính không tồn tại: " + productName);
                        continue;
                    }
                    
                    List<Batch> batchs = batchBUS.getListBatchEnable(p);
                    pnPurchaseOrderDetail = new PnPurchaseOrderDetail(p, unitDetails, batchs, this);
                    for (Component component : pnContent.getComponents()) {
                        if (component instanceof PnPurchaseOrderDetail) {
                            PnPurchaseOrderDetail existingDetail = (PnPurchaseOrderDetail) component;
                            if(p.getProductId().equalsIgnoreCase(existingDetail.getProduct().getProductId()) && unitName.equalsIgnoreCase(existingDetail.getComboChonDvt().getSelectedItem().toString().trim())) {
                                check = 1;
                                pnPurchaseOrderDetail = existingDetail;
                            }
                        }
                    }

                    if (check == 1) {
                        BatchDTO batchDTO = new BatchDTO(batchName, quantity, experationDate, quantity);
                        UnitDetail unitDetail = (UnitDetail) pnPurchaseOrderDetail.getComboChonDvt().getSelectedItem();
                        pnPurchaseOrderDetail.getPnListBatch().add(new PnPurchaseSelectBatch(batchDTO, unitDetail, pnPurchaseOrderDetail.getSpinnerSoLuong()));
                        pnPurchaseOrderDetail.getPnListBatch().revalidate();
                        pnPurchaseOrderDetail.getPnListBatch().repaint();
                        
                        pnPurchaseOrderDetail.setQuantity();
                        
                        pnContent.add(pnPurchaseOrderDetail);
                        pnContent.revalidate();
                        pnContent.repaint();

                        updateUnitDetailsForProduct(p, unitDetails.get(0));
                    } else {
                        for (Component component : pnContent.getComponents()) {
                            if (component instanceof PnPurchaseOrderDetail) {
                                PnPurchaseOrderDetail existingDetail = (PnPurchaseOrderDetail) component;
                                if (existingDetail.getProduct().getProductId().equalsIgnoreCase(p.getProductId())) {
                                    UnitDetail selectedUnit = existingDetail.getSelectedUnitDetail();
                                    if (selectedUnit != null) {
                                        unitDetails.remove(selectedUnit);
                                    }
                                }
                            }
                        }
                        
                        pnPurchaseOrderDetail = new PnPurchaseOrderDetail(p, unitDetails, batchs, this);
                        pnPurchaseOrderDetail.getComboChonDvt().setSelectedItem(unitName);
                        
                        Batch b = batchBUS.getBatchByName(batchName);
                        if(b != null) {
                            BatchDTO batchDTO = new BatchDTO(batchName, b.getStock(), b.getExpirationDate(), quantity);
                            UnitDetail unitDetail = (UnitDetail) pnPurchaseOrderDetail.getComboChonDvt().getSelectedItem();
                            pnPurchaseOrderDetail.getPnListBatch().add(new PnPurchaseSelectBatch(batchDTO, unitDetail, pnPurchaseOrderDetail.getSpinnerSoLuong()));
                        }
                        else {
                            BatchDTO batchDTO = new BatchDTO(batchName, quantity, experationDate, quantity);
                            UnitDetail unitDetail = (UnitDetail) pnPurchaseOrderDetail.getComboChonDvt().getSelectedItem();
                            pnPurchaseOrderDetail.getPnListBatch().add(new PnPurchaseSelectBatch(batchDTO, unitDetail, pnPurchaseOrderDetail.getSpinnerSoLuong())); 
                        }
                        
                        pnPurchaseOrderDetail.getPnListBatch().revalidate();
                        pnPurchaseOrderDetail.getPnListBatch().repaint();
                        
                        pnPurchaseOrderDetail.setQuantity();
                        pnContent.add(pnPurchaseOrderDetail);
                        pnContent.revalidate();
                        pnContent.repaint();

                        updateUnitDetailsForProduct(p, unitDetails.get(0)); 
                    }                               
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Import file bị lỗi");
        }
    }//GEN-LAST:event_btnImportActionPerformed

    private List<PurchaseOrderDTO> createListPurchaseOrderDetail() {
        List<PurchaseOrderDTO> podtos = new ArrayList<>();
        List<PnPurchaseOrderDetail> listPurchasePnOrderDetail = getAllPnPurchaseOrderDetailThuoc();
        if (listPurchasePnOrderDetail == null) {
            MessageDialog.warning(null, "Chưa có chọn sản phẩm !!!");
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
                            podtos.add(new PurchaseOrderDTO(pnPurchaseOrderDetail.getProduct().getProductId(),
                                    pnPurchaseOrderDetail.getSelectedUnitDetail().getUnit().getName(), quantity, batchName,
                                    pnSelectBatch.getBatchDTO().getExpirationDate()));
                        }

                    }
                }
            }
        }
        return podtos;
    }

    private void clearTxtEmpty(JLabel... jLabels) {
        for (JLabel x : jLabels) {
            x.setText("");
        }

    }

    private void clearPnOrderDetail() {
        clearTxtEmpty(txtSupplierId, txtSupplierName, txtTotalPrice);
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
    }

    private void btnConfirmPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmPurchaseActionPerformed
        if(txtSupplierId.getText().trim().equals("") || txtSupplierName.getText().trim().equals("")) {
            MessageDialog.warning(null, "Chưa có nhà cung cấp!");
            return;
        }
        List<PurchaseOrderDTO> purchaseOrderDTOs = createListPurchaseOrderDetail();
        try {
            if (purchaseOrderBUS.createPurchaseOrder(CurrentEmployee.getEmployee(), supplier, purchaseOrderDTOs)) {
                MessageDialog.info(null, "Nhập hàng thành công!");
                clearPnOrderDetail();
            }
        } catch (Exception e) {
            MessageDialog.error(null, e.getMessage());
        }
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
    private double tongTienHang;
    private Supplier supplier;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmPurchase;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnMa;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSupplierId;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnMid;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtSearchSupplier;
    private javax.swing.JLabel txtSupplierId;
    private javax.swing.JLabel txtSupplierName;
    private javax.swing.JLabel txtTotalPrice;
    // End of variables declaration//GEN-END:variables

}
