/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.damageItem;

import bus.*;
import connectDB.ConnectDB;
import entity.*;
import java.awt.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import util.CurrentEmployee;
import util.FormatNumber;
import static util.JTableExporter.openFile;
import util.MessageDialog;

/**
 *
 * @author Hoang
 */
public class TabDamageItem extends javax.swing.JPanel {

    /**
     * Creates new form TabHoaDon
     */
    private DamageItemBUS damageItemBUS;
    private BatchBUS batchBUS;

    public TabDamageItem() {
        this.damageItemBUS = new DamageItemBUS(ConnectDB.getEntityManager());
        this.batchBUS = new BatchBUS(ConnectDB.getEntityManager());
        initComponents();
        fillContent();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMid = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnContent = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        btnTaoPhieu = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JLabel();
        headerPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
        );
        pnMidLayout.setVerticalGroup(
            pnMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        add(pnMid, java.awt.BorderLayout.CENTER);

        pnLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnLeft.setPreferredSize(new java.awt.Dimension(485, 650));

        btnTaoPhieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTaoPhieu.setText("Tạo phiếu ( F8 )");
        btnTaoPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoPhieuActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Tổng tiền:");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Tên nhân viên lập:");
        jLabel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel5AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtEmpName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmpName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 75, Short.MAX_VALUE)
                .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmpName, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 417, Short.MAX_VALUE)
                .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        add(pnLeft, java.awt.BorderLayout.EAST);

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 232, 232), 2, true));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(590, 100));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 16, 24));
        headerPanel.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void fillContent() {
        Employee employee = CurrentEmployee.getEmployee();
        txtEmpName.setText(employee.getName());
        Map<UnitDetail, List<Batch>> map = batchBUS.getListBatchExpiration();
        map.forEach((key, value) -> {
            PnDamageItemDetail pnDamageItemDetail = new PnDamageItemDetail(key, value, this);
            pnContent.add(pnDamageItemDetail);
        });
        pnContent.revalidate();
        pnContent.repaint();
    }
    
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

    private void btnTaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoPhieuActionPerformed
        List<DamageItemDetail> damageItemDetails = createListDamageItemDetail();
        if(damageItemDetails.isEmpty()){
            MessageDialog.warning(null, "Không có sản phẩm");
            return;
        }
        try {
            Employee employee = CurrentEmployee.getEmployee();
            if (MessageDialog.confirm(null, "Bạn có chắc chắn muốn xuất hủy không?", "Xác nhận xuất hủy")) {
                if (damageItemBUS.createDamageItem(employee, damageItemDetails)) {
//                    MessageDialog.info(null, "Chọn nơi lưu trữ file excel");
//                    JFileChooser fileChooser = new JFileChooser();
//                    fileChooser.setDialogTitle("Chọn đường dẫn lưu file Excel");
//                    FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
//                    fileChooser.setFileFilter(filter);
//                    fileChooser.setAcceptAllFileFilterUsed(false);
//
//                    int userChoice = fileChooser.showSaveDialog(null);
//                    if (userChoice == JFileChooser.APPROVE_OPTION) {
//                        try {
//                            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//                            if (!filePath.toLowerCase().endsWith(".xlsx")) {
//                                filePath += ".xlsx";
//                            }
//
////                            TableModel model = tableDesign.getTable().getModel();
//                            Workbook workbook;
//
//                            // Khởi tạo workbook cho định dạng xlsx
//                            if (filePath.endsWith(".xlsx")) {
//                                workbook = new XSSFWorkbook();
//                            } else {
//                                workbook = new HSSFWorkbook();
//                            }
//
//                            Sheet sheet = workbook.createSheet("Sheet1");
//
//                            // Tạo dòng tiêu đề
//                            CellStyle cellStyle = createStyleForHeader(sheet);
//                            Row headerRow = sheet.createRow(0);
//                            Cell headerCell1 = headerRow.createCell(0);
//                            Cell headerCell2 = headerRow.createCell(1);
//                            Cell headerCell3 = headerRow.createCell(2);
//                            Cell headerCell4 = headerRow.createCell(3);
//                            Cell headerCell5 = headerRow.createCell(4);
//                            headerCell1.setCellStyle(cellStyle);
//                            headerCell2.setCellStyle(cellStyle);
//                            headerCell3.setCellStyle(cellStyle);
//                            headerCell4.setCellStyle(cellStyle);
//                            headerCell5.setCellStyle(cellStyle);
//
//                            int r = 0;
//                            // Tạo dòng dữ liệu
//                            for (int i = 0; i < model.getRowCount(); i++) {
//                                Row dataRow = sheet.createRow(i + 1);
//                                for (int j = 0; j < model.getColumnCount(); j++) {
//                                    Cell dataCell = dataRow.createCell(j);
//                                    Object value = model.getValueAt(i, j);
//                                    if (value != null) {
//                                        dataCell.setCellValue(value.toString());
//                                    }
//                                }
//                                r = i;
//                            }
//
//                            Row dataRowBan = sheet.createRow(r + 1);
//                            Cell dataCellBan = dataRowBan.createCell(0);
//                            Cell dataCellBan1 = dataRowBan.createCell(1);
//                            Cell dataCellBan2 = dataRowBan.createCell(2);
//                            dataCellBan.setCellValue("Bán hàng:");
//                            dataCellBan1.setCellValue("Số lượng: " + orderQty.getText());
//                            dataCellBan2.setCellValue("Tổng giá trị:" + orderPrice.getText());
//
//                            Row dataRowNhap = sheet.createRow(r + 2);
//                            Cell dataCellNhap = dataRowNhap.createCell(0);
//                            Cell dataCellNhap1 = dataRowNhap.createCell(1);
//                            Cell dataCellNhap2 = dataRowNhap.createCell(2);
//                            dataCellNhap.setCellValue("Nhập hàng:");
//                            dataCellNhap1.setCellValue("Số lượng: " + purchaseQty.getText());
//                            dataCellNhap2.setCellValue("Tổng giá trị:" + purchasePrice.getText());
//
//                            Row dataRowTra = sheet.createRow(r + 3);
//                            Cell dataCellTra = dataRowTra.createCell(0);
//                            Cell dataCellTra1 = dataRowTra.createCell(1);
//                            Cell dataCellTra2 = dataRowTra.createCell(2);
//                            dataCellTra.setCellValue("Trả hàng:");
//                            dataCellTra1.setCellValue("Số lượng: " + returnQty.getText());
//                            dataCellTra2.setCellValue("Tổng giá trị:" + returnPrice.getText());
//
//                            Row dataRowHuy = sheet.createRow(r + 4);
//                            Cell dataCellHuy = dataRowHuy.createCell(0);
//                            Cell dataCellHuy1 = dataRowHuy.createCell(1);
//                            Cell dataCellHuy2 = dataRowHuy.createCell(2);
//                            dataCellHuy.setCellValue("Xuất hủy:");
//                            dataCellHuy1.setCellValue("Số lượng: " + damageQty.getText());
//                            dataCellHuy2.setCellValue("Tổng giá trị:" + damagePrice.getText());
//
//                            Row dataRowTong = sheet.createRow(r + 5);
//                            Cell dataCellTong = dataRowTong.createCell(0);
//                            dataCellTong.setCellValue("Tổng lợi nhuận: " + profit.getText());
//
//                            // Điều chỉnh kích thước cột
//                            for (int i = 0; i < model.getColumnCount(); i++) {
//                                sheet.autoSizeColumn(i);
//                            }
//
//                            // Ghi dữ liệu ra file
//                            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//                                workbook.write(fileOut);
//                            }
//
//                            openFile(filePath);
//                        } catch (IOException ex) {
//                            JOptionPane.showMessageDialog(null, "Lỗi đọc file!", "Error", JOptionPane.ERROR_MESSAGE);
//                        }
//                    }
                    MessageDialog.warning(null, "Tạo phiếu xuất hủy hàng hóa thành công.");
                    clearPnOrderDetail();
                    fillContent();
                } else {
                    MessageDialog.warning(null, "Tạo phiếu xuất hủy hàng hóa thất bại.");
                }
            }
        } catch (Exception e) {
            MessageDialog.error(null, "Lỗi hệ thống !!!");
        }
    }//GEN-LAST:event_btnTaoPhieuActionPerformed

    private void setTxtEmpty(JLabel... labels) {
        for (JLabel x : labels) {
            x.setText("");
        }
    }

    private void clearPnOrderDetail() {
        setTxtEmpty(txtTongTien);
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
    }

    private List<DamageItemDetail> createListDamageItemDetail() {
        List<DamageItemDetail> damageItemDetails = new ArrayList<>();
        List<PnDamageItemDetail> list = getAllPnDamageItemDetail();
        if (list == null) {
            MessageDialog.warning(null, "Không có sản phẩm !!!");
        } else {
            for (PnDamageItemDetail pnDamageItemDetail : list) {
                List<Batch> batchs = pnDamageItemDetail.getBatchs();
                UnitDetail unitDetail = pnDamageItemDetail.getUnitDetail();
                Product product = unitDetail.getProduct();
                for (Batch batch : batchs) {
                    DamageItemDetail damageItemDetail = new DamageItemDetail(batch.getStock(), product.getPurchasePrice(), batch, unitDetail);
                    damageItemDetails.add(damageItemDetail);
                }
            }
        }
        return damageItemDetails;
    }

    private List<PnDamageItemDetail> getAllPnDamageItemDetail() {
        List<PnDamageItemDetail> damageItemDetails = new ArrayList<>();
        Component[] components = pnContent.getComponents();

        for (Component component : components) {
            if (component instanceof PnDamageItemDetail) {
                damageItemDetails.add((PnDamageItemDetail) component);
            }
        }
        return damageItemDetails;
    }

    public void changeTongTienHoaDon() {
        double tongTien = 0;
        List<PnDamageItemDetail> listPanel = getAllPnDamageItemDetail();
        for (PnDamageItemDetail x : listPanel) {
            tongTien += x.getLineTotal();
        }
        txtTongTien.setText(FormatNumber.formatToVND(tongTien));
    }
    private void jLabel5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel5AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5AncestorAdded

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoPhieu;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnMid;
    private javax.swing.JLabel txtEmpName;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables

}
