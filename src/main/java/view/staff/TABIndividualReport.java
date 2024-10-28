/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff;

import bus.OrderBUS;
import bus.PurchaseOrderBUS;
import bus.ReturnOrderBUS;
import bus.DamageItemBUS;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import view.common.TableDesign;
import entity.*;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.UIManager;
import util.*;
import view.login.LoadApplication;
import util.CurrentEmployee;

/**
 *
 * @author daoducdanh
 */
public class TABIndividualReport extends javax.swing.JPanel {

    private final OrderBUS orderBUS;
    private final PurchaseOrderBUS purchaseOrderBUS;
    private final ReturnOrderBUS returnOrderBUS;
    private final DamageItemBUS damageItemBUS;
    private TableDesign tableDesign;

    public TABIndividualReport() {
        orderBUS = LoadApplication.orderBUS;
        purchaseOrderBUS = LoadApplication.purchaseOrderBUS;
        returnOrderBUS = LoadApplication.returnOrderBUS;
        damageItemBUS = LoadApplication.damageItemBUS;
        initComponents();
        setUIManager();
        fillTable();
    }

    private void setUIManager() {
        UIManager.put("Button.arc", 10);
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
    }

    private void fillTable() {
        String[] headers = {"Mã phiếu", "Ngày tạo", "Loại phiếu", "Giá trị"};
        List<Integer> tableWidths = Arrays.asList(200, 200, 200, 200);
        tableDesign = new TableDesign(headers, tableWidths);
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
    }

    private void fillContent(List<Order> orders, List<PurchaseOrder> purchaseOrders, List<ReturnOrder> returnOrders, List<DamageItem> damageItems) {
        List<Object> objects = new ArrayList<>();
        objects.addAll(orders);
        objects.addAll(purchaseOrders);
        objects.addAll(returnOrders);
        objects.addAll(damageItems);

        objects.sort((o1, o2) -> {
            LocalDateTime date1 = getOrderDate(o1);
            LocalDateTime date2 = getOrderDate(o2);
            return date2.compareTo(date1);
        });
        int countHD = 0, countPNH = 0, countPTH = 0, countPXH = 0;
        double tongDoanhThu = 0.0, tongBan = 0.0, tongNhap = 0.0, tongTra = 0.0, tongHuy = 0.0;
        tableDesign.getModelTable().setRowCount(0);
        for (Order order : orders) {
            countHD++;
            tongBan += order.getTotalPrice();
            tongDoanhThu += order.getTotalPrice();
        }
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            countPNH++;
            tongNhap -= purchaseOrder.getTotalPrice();
            tongDoanhThu -= purchaseOrder.getTotalPrice();
        }
        for (ReturnOrder returnOrder : returnOrders) {
            countPTH++;
            tongTra -= returnOrder.getTotalPrice();
            tongDoanhThu -= returnOrder.getTotalPrice();
        }
        for (DamageItem damageItem : damageItems) {
            countPXH++;
            tongHuy -= damageItem.getTotalPrice();
            tongDoanhThu -= damageItem.getTotalPrice();
        }

        objects.forEach(obj -> {
            if (obj instanceof Order order) {
                tableDesign.getModelTable().addRow(new Object[]{order.getOrderId(), FormatDate.formatDate(order.getOrderDate()),
                    "Hóa đơn bán hàng", FormatNumber.formatToVND(order.getTotalPrice())});
            } else if (obj instanceof ReturnOrder returnOrder) {
                tableDesign.getModelTable().addRow(new Object[]{returnOrder.getReturnOrderId(), FormatDate.formatDate(returnOrder.getOrderDate()),
                    "Phiếu trả hàng", FormatNumber.formatToVND(returnOrder.getTotalPrice())});
            } else if (obj instanceof PurchaseOrder purchaseOrder) {
                tableDesign.getModelTable().addRow(new Object[]{purchaseOrder.getPurchaseOrderId(), FormatDate.formatDate(purchaseOrder.getOrderDate()),
                    "Phiếu nhập hàng", FormatNumber.formatToVND(purchaseOrder.getTotalPrice())});
            } else if (obj instanceof DamageItem damageItem) {
                tableDesign.getModelTable().addRow(new Object[]{damageItem.getDamageItemId(), FormatDate.formatDate(damageItem.getOrderDate()),
                    "Phiếu xuất hủy", FormatNumber.formatToVND(damageItem.getTotalPrice())});
            }
        });

        txtTongHD.setText(countHD + "");
        txtTongPNH.setText(countPNH + "");
        txtTongPTH.setText(countPTH + "");
        txtTongPXH.setText(countPXH + "");
        txtTongGiaTriBan.setText(FormatNumber.formatToVND(tongBan));
        txtTongGiaTriNhap.setText(FormatNumber.formatToVND(tongNhap));
        txtTongGiaTriTra.setText(FormatNumber.formatToVND(tongTra));
        txtTongGiaTriHuy.setText(FormatNumber.formatToVND(tongHuy));
        txtTongDoanhThu.setText(FormatNumber.formatToVND(tongDoanhThu));
    }

    private LocalDateTime getOrderDate(Object obj) {
        if (obj instanceof Order order) {
            return order.getOrderDate();
        } else if (obj instanceof ReturnOrder returnOrder) {
            return returnOrder.getOrderDate();
        } else if (obj instanceof PurchaseOrder purchaseOrder) {
            return purchaseOrder.getOrderDate();
        } else if (obj instanceof DamageItem damageItem) {
            return damageItem.getOrderDate();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        jDateTo = new com.toedter.calendar.JDateChooser();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTongHoaDon = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        txtTongHD = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JLabel();
        lblTongHoaDon1 = new javax.swing.JLabel();
        lblTongHoaDon2 = new javax.swing.JLabel();
        lblTongHoaDon3 = new javax.swing.JLabel();
        txtTongPNH = new javax.swing.JLabel();
        txtTongPTH = new javax.swing.JLabel();
        txtTongPXH = new javax.swing.JLabel();
        lblTongHoaDon4 = new javax.swing.JLabel();
        lblTongHoaDon5 = new javax.swing.JLabel();
        lblTongHoaDon6 = new javax.swing.JLabel();
        lblTongHoaDon7 = new javax.swing.JLabel();
        txtTongGiaTriBan = new javax.swing.JLabel();
        txtTongGiaTriTra = new javax.swing.JLabel();
        txtTongGiaTriHuy = new javax.swing.JLabel();
        txtTongGiaTriNhap = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(905, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        headerPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(1226, 174));
        jPanel4.setPreferredSize(new java.awt.Dimension(1226, 174));
        jPanel4.setLayout(new java.awt.BorderLayout());

        scrollTable.setMinimumSize(new java.awt.Dimension(1226, 140));
        scrollTable.setPreferredSize(new java.awt.Dimension(1226, 140));
        jPanel4.add(scrollTable, java.awt.BorderLayout.CENTER);

        jPanel1.setMaximumSize(new java.awt.Dimension(1416, 250));
        jPanel1.setMinimumSize(new java.awt.Dimension(1416, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(1416, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblTongHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon.setText("Bán hàng:       SL:");

        lblTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        lblTongDoanhThu.setText("Tổng doanh thu:");

        txtTongHD.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongHD.setText("0");

        txtTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        txtTongDoanhThu.setText("0 đ");

        lblTongHoaDon1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon1.setText("Nhập hàng:    SL:");

        lblTongHoaDon2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon2.setText("Trả hàng:   SL");

        lblTongHoaDon3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon3.setText("Xuất hủy:   SL:");

        txtTongPNH.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongPNH.setText("0");

        txtTongPTH.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongPTH.setText("0");

        txtTongPXH.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongPXH.setText("0");

        lblTongHoaDon4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon4.setText("|     Tổng giá trị:");

        lblTongHoaDon5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon5.setText("|     Tổng giá trị:");

        lblTongHoaDon6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon6.setText("|     Tổng giá trị:");

        lblTongHoaDon7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongHoaDon7.setText("|     Tổng giá trị:");

        txtTongGiaTriBan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongGiaTriBan.setText("0 đ");

        txtTongGiaTriTra.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongGiaTriTra.setText("0 đ");

        txtTongGiaTriHuy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongGiaTriHuy.setText("0 đ");

        txtTongGiaTriNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongGiaTriNhap.setText("0 đ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTongHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTongHD))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTongHoaDon1)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongPNH)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTongHoaDon5)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongGiaTriNhap))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTongHoaDon4)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongGiaTriBan)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTongHoaDon2)
                            .addComponent(lblTongHoaDon3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTongPTH)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongHoaDon6)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongGiaTriTra))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTongPXH)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongHoaDon7)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongGiaTriHuy))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTongDoanhThu)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongDoanhThu)))
                .addGap(419, 419, 419))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongHoaDon)
                    .addComponent(txtTongHD)
                    .addComponent(lblTongHoaDon3)
                    .addComponent(txtTongPXH)
                    .addComponent(lblTongHoaDon4)
                    .addComponent(lblTongHoaDon7)
                    .addComponent(txtTongGiaTriHuy)
                    .addComponent(txtTongGiaTriBan))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongHoaDon1)
                    .addComponent(txtTongPNH)
                    .addComponent(lblTongHoaDon5)
                    .addComponent(txtTongGiaTriNhap)
                    .addComponent(lblTongHoaDon2)
                    .addComponent(txtTongPTH)
                    .addComponent(lblTongHoaDon6)
                    .addComponent(txtTongGiaTriTra))
                .addGap(36, 36, 36)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongDoanhThu)
                    .addComponent(lblTongDoanhThu))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jSeparator1.setPreferredSize(new Dimension(jPanel1.getPreferredSize().width,3));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_START);
        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pnAll.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(pnAll);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        java.util.Date date1 = jDateFrom.getDate();
        java.util.Date date2 = jDateTo.getDate();

        if (date1 == null) {
            MessageDialog.warning(null, "Ngày bắt đầu không hợp lệ");
            return;
        }

        if (date2 == null) {
            MessageDialog.warning(null, "Ngày kết thúc không hợp lệ");
            return;
        }

        LocalDateTime start = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }

        List<Order> orders = orderBUS.getOrderByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
        List<PurchaseOrder> purchaseOrders = purchaseOrderBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
        List<ReturnOrder> returnOrders = returnOrderBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
        List<DamageItem> damageItems = damageItemBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
        fillContent(orders, purchaseOrders, returnOrders, damageItems);
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel headerPanel;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongHoaDon;
    private javax.swing.JLabel lblTongHoaDon1;
    private javax.swing.JLabel lblTongHoaDon2;
    private javax.swing.JLabel lblTongHoaDon3;
    private javax.swing.JLabel lblTongHoaDon4;
    private javax.swing.JLabel lblTongHoaDon5;
    private javax.swing.JLabel lblTongHoaDon6;
    private javax.swing.JLabel lblTongHoaDon7;
    private javax.swing.JPanel pnAll;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JLabel txtTongDoanhThu;
    private javax.swing.JLabel txtTongGiaTriBan;
    private javax.swing.JLabel txtTongGiaTriHuy;
    private javax.swing.JLabel txtTongGiaTriNhap;
    private javax.swing.JLabel txtTongGiaTriTra;
    private javax.swing.JLabel txtTongHD;
    private javax.swing.JLabel txtTongPNH;
    private javax.swing.JLabel txtTongPTH;
    private javax.swing.JLabel txtTongPXH;
    // End of variables declaration//GEN-END:variables

}
