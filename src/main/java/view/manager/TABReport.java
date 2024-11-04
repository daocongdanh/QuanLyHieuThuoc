/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.manager;

import bus.OrderBUS;
import bus.PurchaseOrderBUS;
import bus.ReturnOrderBUS;
import bus.DamageItemBUS;
import bus.OrderDetailBUS;
import bus.PurchaseOrderDetailBUS;
import bus.ReturnOrderDetailBUS;
import bus.DamageItemDetailBUS;
import bus.ReportBUS;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import dto.Report;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import view.common.TableDesign;
import entity.*;
import enums.PaymentMethod;
import enums.ReturnOrderDetailStatus;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.*;
import static util.JTableExporter.openFile;
import view.login.LoadApplication;

/**
 *
 * @author daoducdanh
 */
public class TABReport extends javax.swing.JPanel {

    private final ReportBUS reportBUS;
    private final OrderBUS orderBUS;
    private final PurchaseOrderBUS purchaseOrderBUS;
    private final ReturnOrderBUS returnOrderBUS;
    private final DamageItemBUS damageItemBUS;
    private final OrderDetailBUS orderDetailBUS;
    private final PurchaseOrderDetailBUS purchaseOrderDetailBUS;
    private final ReturnOrderDetailBUS returnOrderDetailBUS;
    private final DamageItemDetailBUS damageItemDetailBUS;
    private TableDesign tableDesign;

    public TABReport() {
        reportBUS = LoadApplication.reportBUS;
        orderBUS = LoadApplication.orderBUS;
        purchaseOrderBUS = LoadApplication.purchaseOrderBUS;
        returnOrderBUS = LoadApplication.returnOrderBUS;
        damageItemBUS = LoadApplication.damageItemBUS;
        orderDetailBUS = LoadApplication.orderDetailBUS;
        purchaseOrderDetailBUS = LoadApplication.purchaseOrderDetailBUS;
        returnOrderDetailBUS = LoadApplication.returnOrderDetailBUS;
        damageItemDetailBUS = LoadApplication.damageItemDetailBUS;
        initComponents();
        setUIManager();
        fillTable();
    }

    private void setUIManager() {

        UIManager.put("Button.arc", 10);
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
        btnView.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/View.svg")), 35, 35));
    }

    private void fillTable() {
        String[] headers = {"Mã phiếu", "Ngày lập", "Loại thu chi", "Người lập", "Giá trị"};
        List<Integer> tableWidths = Arrays.asList(240, 240, 240, 240, 240);
        tableDesign = new TableDesign(headers, tableWidths);
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
    }

    private void fillContent(Report report) {
        tableDesign.getModelTable().setRowCount(0);
        report.getReports().forEach(obj -> {
            if (obj instanceof Order order) {
                tableDesign.getModelTable().addRow(new Object[]{order.getOrderId(), FormatDate.formatDate(order.getOrderDate()),
                    "Bán hàng", order.getEmployee().getName(), FormatNumber.formatToVND(order.getTotalPrice())});
            } else if (obj instanceof ReturnOrder returnOrder) {
                tableDesign.getModelTable().addRow(new Object[]{returnOrder.getReturnOrderId(), FormatDate.formatDate(returnOrder.getOrderDate()),
                    "Trả hàng", returnOrder.getEmployee().getName(), FormatNumber.formatToVND(returnOrder.getTotalPrice())});
            } else if (obj instanceof PurchaseOrder purchaseOrder) {
                tableDesign.getModelTable().addRow(new Object[]{purchaseOrder.getPurchaseOrderId(), FormatDate.formatDate(purchaseOrder.getOrderDate()),
                    "Nhập hàng", purchaseOrder.getEmployee().getName(), FormatNumber.formatToVND(purchaseOrder.getTotalPrice())});
            } else if (obj instanceof DamageItem damageItem) {
                tableDesign.getModelTable().addRow(new Object[]{damageItem.getDamageItemId(), FormatDate.formatDate(damageItem.getOrderDate()),
                    "Xuất hủy", damageItem.getEmployee().getName(), FormatNumber.formatToVND(damageItem.getTotalPrice())});
            }
        });

        int countHD = 0, countPNH = 0, countPTH = 0, countPXH = 0;
        double tongDoanhThu = 0.0, tongBan = 0.0, tongNhap = 0.0, tongTra = 0.0, tongHuy = 0.0;
        
//        report.getOrderType().forEach((key, value) -> {
//            if (!value.isEmpty()) {
//                Integer qty = value.keySet().iterator().next();
//                Double price = value.values().iterator().next();
//                if (key.equals("Bán hàng")) {
//                    countHD++;
//                    tongBan += price;
//                } else if (key.equals("Trả hàng")) {
//                    countPTH++;
//                    tongTra += price;
//                } else if (key.equals("Nhập hàng")) {
//                    purchaseQty.setText(qty.toString());
//                    if(qty > 0){
//                        purchasePrice.setText("-" + FormatNumber.formatToVND(price));
//                    }
//                } else if (key.equals("Xuất hủy")) {
//                    damageQty.setText(qty.toString());
//                    if(qty > 0){
//                        damagePrice.setText("-" + FormatNumber.formatToVND(price));
//                    }
//                }
//            }
//        });

        txtTongDoanhThu.setText(FormatNumber.formatToVND(report.getProfit()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        modalDetail = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        scrollTableDetail = new javax.swing.JScrollPane();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        jDateTo = new com.toedter.calendar.JDateChooser();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtOrder = new javax.swing.JButton();
        comboboxType = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        scrollDoanhSo = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        lblTongDoanhThu = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 756, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        modalDetail.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalDetail.setTitle("Chi tiết phiếu nhập hàng");
        modalDetail.setMinimumSize(new java.awt.Dimension(1311, 700));
        modalDetail.setModal(true);
        modalDetail.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));
        jPanel3.add(scrollTableDetail);

        javax.swing.GroupLayout modalDetailLayout = new javax.swing.GroupLayout(modalDetail.getContentPane());
        modalDetail.getContentPane().setLayout(modalDetailLayout);
        modalDetailLayout.setHorizontalGroup(
            modalDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modalDetailLayout.setVerticalGroup(
            modalDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 204, 0));
        setMinimumSize(new java.awt.Dimension(1226, 278));
        setPreferredSize(new java.awt.Dimension(1226, 278));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        pnAll.setBackground(new java.awt.Color(255, 255, 255));
        pnAll.setPreferredSize(new java.awt.Dimension(1226, 278));
        pnAll.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        headerPanel.setMinimumSize(new java.awt.Dimension(1190, 104));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(590, 100));

        btnSearch.setBackground(new java.awt.Color(115, 165, 71));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tra cứu");
        btnSearch.setMaximumSize(new java.awt.Dimension(150, 40));
        btnSearch.setMinimumSize(new java.awt.Dimension(150, 40));
        btnSearch.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jDateTo.setBackground(new java.awt.Color(255, 255, 255));
        jDateTo.setDateFormatString("dd/MM/yyyy");
        jDateTo.setFocusable(false);
        jDateTo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateTo.setPreferredSize(new java.awt.Dimension(100, 22));

        jDateFrom.setBackground(new java.awt.Color(255, 255, 255));
        jDateFrom.setDateFormatString("dd/MM/yyyy");
        jDateFrom.setFocusable(false);
        jDateFrom.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateFrom.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("-->");

        txtOrder.setBackground(new java.awt.Color(115, 165, 71));
        txtOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtOrder.setForeground(new java.awt.Color(255, 255, 255));
        txtOrder.setText("Xuất excel");
        txtOrder.setMaximumSize(new java.awt.Dimension(150, 40));
        txtOrder.setMinimumSize(new java.awt.Dimension(150, 40));
        txtOrder.setPreferredSize(new java.awt.Dimension(150, 40));
        txtOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderActionPerformed(evt);
            }
        });

        comboboxType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Bán hàng", "Trả hàng", "Nhập hàng", "Xuất hủy" }));

        btnView.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnView.setText("XEM CHI TIẾT");
        btnView.setBorder(null);
        btnView.setBorderPainted(false);
        btnView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnView.setFocusPainted(false);
        btnView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnView.setPreferredSize(new java.awt.Dimension(100, 90));
        btnView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(comboboxType, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboboxType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        headerPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(1226, 174));
        jPanel4.setPreferredSize(new java.awt.Dimension(1226, 174));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(scrollTable, java.awt.BorderLayout.CENTER);

        jPanel6.setMaximumSize(new java.awt.Dimension(1416, 370));
        jPanel6.setMinimumSize(new java.awt.Dimension(1416, 370));
        jPanel6.setPreferredSize(new java.awt.Dimension(1416, 370));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel7.setMaximumSize(new java.awt.Dimension(1416, 300));
        jPanel7.setMinimumSize(new java.awt.Dimension(1416, 300));
        jPanel7.setPreferredSize(new java.awt.Dimension(1416, 300));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));
        jPanel7.add(scrollDoanhSo);

        jPanel6.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 20, 20);
        flowLayout1.setAlignOnBaseline(true);
        jPanel8.setLayout(flowLayout1);

        lblTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        lblTongDoanhThu.setText("Tổng doanh thu:");
        jPanel8.add(lblTongDoanhThu);

        txtTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        txtTongDoanhThu.setText("0 đ");
        jPanel8.add(txtTongDoanhThu);

        jPanel6.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6, java.awt.BorderLayout.SOUTH);
        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pnAll.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(pnAll);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        java.util.Date date1 = jDateFrom.getDate();
        java.util.Date date2 = jDateTo.getDate();

        LocalDate localDateStart = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateEnd = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDateTime start = localDateStart.atStartOfDay(); // 00:00:00
        LocalDateTime end = localDateEnd.atTime(23, 59, 59, 999999999); // 23:59:59.999999999

        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        Report report = reportBUS.getAllReportByTime(start, end, (String) comboboxType.getSelectedItem());
        fillContent(report);
    }//GEN-LAST:event_btnSearchActionPerformed

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Tạo font chữ
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);  // BOLDWEIGHT cho Apache POI 3.9
        font.setFontHeightInPoints((short) 14);

        // Tạo CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        return cellStyle;
    }
    
    private void txtOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn lưu file Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                TableModel model = tableDesign.getTable().getModel();
                Workbook workbook;

                // Khởi tạo workbook cho định dạng xlsx
                if (filePath.endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook();
                } else {
                    workbook = new HSSFWorkbook();
                }

                Sheet sheet = workbook.createSheet("Sheet1");

                // Tạo dòng tiêu đề
                CellStyle cellStyle = createStyleForHeader(sheet);
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                    Cell headerCell = headerRow.createCell(i);
                    headerCell.setCellValue(model.getColumnName(i));
                    headerCell.setCellStyle(cellStyle);
                }

                int r = 0;
                // Tạo dòng dữ liệu
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row dataRow = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Cell dataCell = dataRow.createCell(j);
                        Object value = model.getValueAt(i, j);
                        if (value != null) {
                            dataCell.setCellValue(value.toString());
                        }
                    }
                    r = i;
                }
                
//                Row dataRowBan = sheet.createRow(r + 1);
//                Cell dataCellBan = dataRowBan.createCell(0);
//                Cell dataCellBan1 = dataRowBan.createCell(1);
//                Cell dataCellBan2 = dataRowBan.createCell(2);
//                dataCellBan.setCellValue("Bán hàng:");
//                dataCellBan1.setCellValue("Số lượng: " + orderQty.getText());
//                dataCellBan2.setCellValue("Tổng giá trị:" + orderPrice.getText());
//                
//                Row dataRowNhap = sheet.createRow(r + 2);
//                Cell dataCellNhap = dataRowNhap.createCell(0);
//                Cell dataCellNhap1 = dataRowNhap.createCell(1);
//                Cell dataCellNhap2 = dataRowNhap.createCell(2);
//                dataCellNhap.setCellValue("Nhập hàng:");
//                dataCellNhap1.setCellValue("Số lượng: " + purchaseQty.getText());
//                dataCellNhap2.setCellValue("Tổng giá trị:" + purchasePrice.getText());
//                
//                Row dataRowTra = sheet.createRow(r + 3);
//                Cell dataCellTra = dataRowTra.createCell(0);
//                Cell dataCellTra1 = dataRowTra.createCell(1);
//                Cell dataCellTra2 = dataRowTra.createCell(2);
//                dataCellTra.setCellValue("Trả hàng:");
//                dataCellTra1.setCellValue("Số lượng: " + returnQty.getText());
//                dataCellTra2.setCellValue("Tổng giá trị:" + returnPrice.getText());
//                
//                Row dataRowHuy = sheet.createRow(r + 4);
//                Cell dataCellHuy = dataRowHuy.createCell(0);
//                Cell dataCellHuy1 = dataRowHuy.createCell(1);
//                Cell dataCellHuy2 = dataRowHuy.createCell(2);
//                dataCellHuy.setCellValue("Xuất hủy:");
//                dataCellHuy1.setCellValue("Số lượng: " + damageQty.getText());
//                dataCellHuy2.setCellValue("Tổng giá trị:" + damagePrice.getText());
//                
//                Row dataRowTong = sheet.createRow(r + 5);
//                Cell dataCellTong = dataRowTong.createCell(0);
//                dataCellTong.setCellValue("Tổng lợi nhuận: " + profit.getText());

                // Điều chỉnh kích thước cột
                for (int i = 0; i < model.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                // Ghi dữ liệu ra file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                openFile(filePath);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đọc file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtOrderActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        JTable table = tableDesign.getTable();
        int selectedRow = table.getSelectedRow();
        if(selectedRow < 0) {
            MessageDialog.warning(null, "Hãy chọn phiếu cần xem chi tiết!");
            return;
        }
        String loaiPhieu = (String) table.getValueAt(selectedRow, 2);
        String maPhieu = (String) table.getValueAt(selectedRow, 0);

        if(loaiPhieu.equalsIgnoreCase("Bán hàng")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Đơn giá", "Khuyến mãi", "Tổng giá trị"};
            List<Integer> tableWidths = Arrays.asList(170, 200, 150, 150, 200, 170, 150);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            Order o = orderBUS.findById(maPhieu);
            List<OrderDetail> orderDetails = orderDetailBUS.getListOrderDetailByOrder(o);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (OrderDetail orderDetail : orderDetails) {
                String productId = orderDetail.getBatch().getProduct().getProductId();
                String productName = orderDetail.getBatch().getProduct().getName();
                String unitName = orderDetail.getBatch().getProduct().getUnit().getName();
                int quantity = orderDetail.getQuantity();
                double price = orderDetail.getPrice()*1.1;
                double km = (orderDetail.getDiscount()*100);
                double lineTotal = orderDetail.getLineTotal();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[6] = (double) existingData[6] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, (int)km + "%", lineTotal});
                }
            }

            // Xóa dữ liệu cũ trong bảng
            tableDetail.getModelTable().setRowCount(0);

            // Thêm các sản phẩm đã cộng dồn vào bảng
            for (Object[] productData : productMap.values()) {
                productData[4] = FormatNumber.formatToVND((double) productData[4]);
                productData[6] = FormatNumber.formatToVND((double) productData[6]);
                tableDetail.getModelTable().addRow(productData);
            }
        }

        if(loaiPhieu.equalsIgnoreCase("Trả hàng")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng trả", "Đơn giá", "Tổng giá trị", "Lý do trả"
                    , "Trạng thái", "Lý do nhập lại/hủy"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200, 200, 150, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            ReturnOrder ro = returnOrderBUS.findById(maPhieu);
            List<ReturnOrderDetail> returnOrderDetails = returnOrderDetailBUS.getListReturnOrderDetailsByReturnOrder(ro);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (ReturnOrderDetail returnOrderDetail : returnOrderDetails) {
                String productId = returnOrderDetail.getProduct().getProductId();
                String productName = returnOrderDetail.getProduct().getName();
                String unitName = returnOrderDetail.getProduct().getUnit().getName();
                int quantity = returnOrderDetail.getQuantity();
                double price = returnOrderDetail.getPrice()*1.1;
                double lineTotal = returnOrderDetail.getLineTotal();
                String lyDoTra = returnOrderDetail.getReason();
                String trangThai = "Đang chờ";
                if(returnOrderDetail.getReturnOrderDetailStatus() == ReturnOrderDetailStatus.DAMAGED) trangThai = "Xuất hủy";
                if(returnOrderDetail.getReturnOrderDetailStatus() == ReturnOrderDetailStatus.RETURNED) trangThai = "Bán tiếp";
                String fR = returnOrderDetail.getFinalReason();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[5] = (double) existingData[5] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, lineTotal, lyDoTra, trangThai, fR});
                }
            }

            // Xóa dữ liệu cũ trong bảng
            tableDetail.getModelTable().setRowCount(0);

            // Thêm các sản phẩm đã cộng dồn vào bảng
            for (Object[] productData : productMap.values()) {
                productData[4] = FormatNumber.formatToVND((double) productData[4]);
                productData[5] = FormatNumber.formatToVND((double) productData[5]);
                tableDetail.getModelTable().addRow(productData);
            }
        }

        if(loaiPhieu.equalsIgnoreCase("Nhập hàng")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập", "Tổng giá trị"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            PurchaseOrder po = purchaseOrderBUS.getByID(maPhieu);
            List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailBUS.getListPurchaseOrderDetailsByPurchaseOrder(po);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
                String productId = purchaseOrderDetail.getBatch().getProduct().getProductId();
                String productName = purchaseOrderDetail.getBatch().getProduct().getName();
                String unitName = purchaseOrderDetail.getBatch().getProduct().getUnit().getName();
                int quantity = purchaseOrderDetail.getQuantity();
                double price = purchaseOrderDetail.getPrice()*1.1;
                double lineTotal = purchaseOrderDetail.getLineTotal();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[5] = (double) existingData[5] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, lineTotal});
                }
            }

            // Xóa dữ liệu cũ trong bảng
            tableDetail.getModelTable().setRowCount(0);

            // Thêm các sản phẩm đã cộng dồn vào bảng
            for (Object[] productData : productMap.values()) {
                productData[4] = FormatNumber.formatToVND((double) productData[4]);
                productData[5] = FormatNumber.formatToVND((double) productData[5]);
                tableDetail.getModelTable().addRow(productData);
            }
        }

        if(loaiPhieu.equalsIgnoreCase("Xuất hủy")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập", "Tổng giá trị hủy"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            DamageItem d = damageItemBUS.getByID(maPhieu);
            List<DamageItemDetail> damageItemDetails = damageItemDetailBUS.getListDamageItemDetailByDamageItem(d);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (DamageItemDetail damageItemDetail : damageItemDetails) {
                String productId = damageItemDetail.getBatch().getProduct().getProductId();
                String productName = damageItemDetail.getBatch().getProduct().getName();
                String unitName = damageItemDetail.getBatch().getProduct().getUnit().getName();
                int quantity = damageItemDetail.getQuantity();
                double price = damageItemDetail.getPrice()*1.1;
                double lineTotal = damageItemDetail.getLineTotal();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[5] = (double) existingData[5] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, lineTotal});
                }
            }

            // Xóa dữ liệu cũ trong bảng
            tableDetail.getModelTable().setRowCount(0);

            // Thêm các sản phẩm đã cộng dồn vào bảng
            for (Object[] productData : productMap.values()) {
                productData[4] = FormatNumber.formatToVND((double) productData[4]);
                productData[5] = FormatNumber.formatToVND((double) productData[5]);
                tableDetail.getModelTable().addRow(productData);
            }
        }

        modalDetail.setLocationRelativeTo(null);
        modalDetail.setVisible(true);
    }//GEN-LAST:event_btnViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> comboboxType;
    private javax.swing.JPanel headerPanel;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JDialog modalDetail;
    private javax.swing.JPanel pnAll;
    private javax.swing.JScrollPane scrollDoanhSo;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JScrollPane scrollTableDetail;
    private javax.swing.JButton txtOrder;
    private javax.swing.JLabel txtTongDoanhThu;
    // End of variables declaration//GEN-END:variables

}
