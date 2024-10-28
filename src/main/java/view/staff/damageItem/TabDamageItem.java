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
import util.CurrentEmployee;
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
        
        changeTongTienHoaDon();
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
                            Cell headerCell1 = headerRow.createCell(0);
                            Cell headerCell2 = headerRow.createCell(1);
                            Cell headerCell3 = headerRow.createCell(2);
                            Cell headerCell4 = headerRow.createCell(3);
                            Cell headerCell5 = headerRow.createCell(4);
                            Cell headerCell6 = headerRow.createCell(5);
                            Cell headerCell7 = headerRow.createCell(6);
                            
                            headerCell1.setCellValue("Mã sản phẩm");
                            headerCell2.setCellValue("Tên sản phẩm");
                            headerCell3.setCellValue("Số lô");
                            headerCell4.setCellValue("Hạn sử dụng");
                            headerCell5.setCellValue("Đơn vị tính");
                            headerCell6.setCellValue("Số lượng");
                            headerCell7.setCellValue("Giá trị hủy");
                            
                            headerCell1.setCellStyle(cellStyle);
                            headerCell2.setCellStyle(cellStyle);
                            headerCell3.setCellStyle(cellStyle);
                            headerCell4.setCellStyle(cellStyle);
                            headerCell5.setCellStyle(cellStyle);
                            headerCell6.setCellStyle(cellStyle);
                            headerCell7.setCellStyle(cellStyle);
                            
                            int r = 0;
                            double t = 0.0;
                            // Tạo dòng dữ liệu
                            for (DamageItemDetail damageItemDetail : damageItemDetails) {
                                Row dataRow = sheet.createRow(r + 1);
                                Cell dataCell1 = dataRow.createCell(0);
                                Cell dataCell2 = dataRow.createCell(1);
                                Cell dataCell3 = dataRow.createCell(2);
                                Cell dataCell4 = dataRow.createCell(3);
                                Cell dataCell5 = dataRow.createCell(4);
                                Cell dataCell6 = dataRow.createCell(5);
                                Cell dataCell7 = dataRow.createCell(6);
                                dataCell1.setCellValue(damageItemDetail.getUnitDetail().getProduct().getProductId());
                                dataCell2.setCellValue(damageItemDetail.getUnitDetail().getProduct().getName());
                                dataCell3.setCellValue(damageItemDetail.getBatch().getName());
                                dataCell4.setCellValue(damageItemDetail.getBatch().getExpirationDate().toString());
                                dataCell5.setCellValue(damageItemDetail.getUnitDetail().getUnit().getName());
                                dataCell6.setCellValue(damageItemDetail.getQuantity());
                                dataCell7.setCellValue(damageItemDetail.getLineTotal());
                                t += damageItemDetail.getLineTotal();
                                r++;
                            }

                            Row dataRowNV = sheet.createRow(r + 1);
                            Cell dataCellNV = dataRowNV.createCell(0);
                            Cell dataCellNV1 = dataRowNV.createCell(1);
                            dataCellNV.setCellValue("Nhân viên lập đơn:");
                            dataCellNV1.setCellValue(CurrentEmployee.getEmployee().getName() + "_" + CurrentEmployee.getEmployee().getEmployeeId());
                            
                            Row dataRowTGT = sheet.createRow(r + 2);
                            Cell dataCellTGT = dataRowTGT.createCell(0);
                            Cell dataCellTGT1 = dataRowTGT.createCell(1);
                            dataCellTGT.setCellValue("Tổng giá trị hủy:");
                            dataCellTGT1.setCellValue(t);

                            // Điều chỉnh kích thước cột
                            for (int i = 0; i < 6; i++) {
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
