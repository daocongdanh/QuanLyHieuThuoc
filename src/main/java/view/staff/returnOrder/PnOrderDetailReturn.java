/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.returnOrder;

import view.staff.sell.PnSelectBatch;
import bus.OrderDetailBUS;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import dto.BatchDTO;
import dto.OrderDetailSelected;
import entity.Product;
import java.awt.Component;
import java.util.List;
import entity.*;
import javax.swing.*;
import util.ResizeImage;
import util.FormatNumber;
import view.staff.TABReturnOrder;

/**
 *
 * @author Hoang
 */
public class PnOrderDetailReturn extends javax.swing.JPanel {

    private Product product;
    private TABReturnOrder tabTraHang;
    private OrderDetailSelected orderDetailSelected;
    private UnitDetail unitDetail;
    private String reason;

    public PnOrderDetailReturn() {
        initComponents();
    }

    public PnOrderDetailReturn(OrderDetail orderDetail, UnitDetail unitDetail, OrderDetailSelected orderDetailSelected,
            TABReturnOrder tabTraHang) {
        this.unitDetail = unitDetail;
        this.product = unitDetail.getProduct();
        this.tabTraHang = tabTraHang;
        this.orderDetailSelected = orderDetailSelected;
        initComponents();
        fillFirst();
        reason = "";
    }

    public Product getProduct() {
        return product;
    }

    public UnitDetail getSelectedUnitDetail() {
        return (UnitDetail) comboChonDvt.getSelectedItem();
    }
    
    public String getReason(){
        return this.reason;
    }

    public int getSoLuong() {
        return (int) spinnerSoLuong.getValue();
    }

    public JComboBox<UnitDetail> getComboChonDvt() {
        return comboChonDvt;
    }

    private void fillFirst() {
        
        lblIcon.setIcon(ResizeImage.resizeImage((new FlatSVGIcon(getClass().getResource("/img/note.svg"))),30,30));
        
        comboChonDvt.removeAllItems();
        comboChonDvt.addItem(unitDetail);

        txtTenSP.setText(product.getName());
        pnHinh.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/"
                + product.getImage())), 82, 82));

        if (orderDetailSelected != null) {
            comboChonDvt.setSelectedItem(orderDetailSelected.getUnitDetail());
            List<BatchDTO> batchDTOs = orderDetailSelected.getBatchDTOs();
            int qty = 0;

            for (BatchDTO batchDTO : batchDTOs) {
                qty += batchDTO.getQuantity();
                PnSelectBatchReturn pnSelectBatchReturn = new PnSelectBatchReturn(batchDTO, orderDetailSelected.getUnitDetail(), spinnerSoLuong);
                pnListBatch.add(pnSelectBatchReturn);
            }

            spinnerSoLuong.setValue(qty);
            setLineTotal();
        }
    }

    public void fillQuantity(int quantity) {
        spinnerSoLuong.setValue(quantity);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modalGhiChu = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaReason = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        btnExitModalAdd = new javax.swing.JButton();
        txtTenSP = new javax.swing.JLabel();
        spinnerSoLuong = new javax.swing.JSpinner();
        txtDonGia = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        comboChonDvt = new javax.swing.JComboBox<>();
        pnHinh = new javax.swing.JLabel();
        pnListBatch = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();

        modalGhiChu.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalGhiChu.setTitle("Lý do trả hàng");
        modalGhiChu.setMaximumSize(new java.awt.Dimension(665, 405));
        modalGhiChu.setMinimumSize(new java.awt.Dimension(665, 405));
        modalGhiChu.setModal(true);
        modalGhiChu.setPreferredSize(new java.awt.Dimension(665, 405));
        modalGhiChu.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Lý Do Trả Hàng");

        txtAreaReason.setColumns(20);
        txtAreaReason.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtAreaReason.setRows(5);
        txtAreaReason.setMargin(new java.awt.Insets(4, 8, 4, 8));
        txtAreaReason.setName(""); // NOI18N
        jScrollPane1.setViewportView(txtAreaReason);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnExitModalAdd.setBackground(new java.awt.Color(92, 107, 192));
        btnExitModalAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitModalAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnExitModalAdd.setText("Hủy bỏ");
        btnExitModalAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitModalAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExitModalAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExitModalAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalGhiChuLayout = new javax.swing.GroupLayout(modalGhiChu.getContentPane());
        modalGhiChu.getContentPane().setLayout(modalGhiChuLayout);
        modalGhiChuLayout.setHorizontalGroup(
            modalGhiChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        modalGhiChuLayout.setVerticalGroup(
            modalGhiChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        setMaximumSize(new java.awt.Dimension(1139, 170));
        setMinimumSize(new java.awt.Dimension(1139, 170));
        setPreferredSize(new java.awt.Dimension(1139, 170));
        setRequestFocusEnabled(false);

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenSP.setText("jLabel1");

        spinnerSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerSoLuong.setEnabled(false);
        spinnerSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerSoLuongStateChanged(evt);
            }
        });

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDonGia.setText("0 đ");
        txtDonGia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTongTien.setText("0 đ");
        txtTongTien.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        comboChonDvt.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboChonDvt.setEnabled(false);

        pnHinh.setBackground(new java.awt.Color(204, 204, 204));

        pnListBatch.setBackground(new java.awt.Color(255, 255, 255));
        pnListBatch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        lblIcon.setText("Ghi chú");
        lblIcon.setMaximumSize(new java.awt.Dimension(38, 38));
        lblIcon.setMinimumSize(new java.awt.Dimension(38, 38));
        lblIcon.setPreferredSize(new java.awt.Dimension(38, 38));
        lblIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(pnHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 43, Short.MAX_VALUE)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboChonDvt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addComponent(pnListBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboChonDvt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(pnHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(pnListBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
    }// </editor-fold>//GEN-END:initComponents

    public Double getLineTotal() {
        if (unitDetail != null) {
            int quantity = (Integer) spinnerSoLuong.getValue();
            return unitDetail.getConversionRate() * quantity * product.getPrice();
        }
        return 0.0;
    }

    public void setLineTotal() {
        int quantity = (Integer) spinnerSoLuong.getValue();
        txtDonGia.setText(FormatNumber.formatToVND(unitDetail.getConversionRate() * product.getPrice()));
        txtTongTien.setText(FormatNumber.formatToVND(unitDetail.getConversionRate()
                * quantity * product.getPrice()));

    }


    private void spinnerSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerSoLuongStateChanged
        setLineTotal();
        tabTraHang.changeTongTienHoaDon();
    }//GEN-LAST:event_spinnerSoLuongStateChanged

    private void lblIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconMouseClicked
        txtAreaReason.setText(reason);
        modalGhiChu.setLocationRelativeTo(null);
        modalGhiChu.setVisible(true);
    }//GEN-LAST:event_lblIconMouseClicked

    private void btnExitModalAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitModalAddActionPerformed
        // TODO add your handling code here:
        txtAreaReason.setText("");
        modalGhiChu.dispose();
    }//GEN-LAST:event_btnExitModalAddActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reason  = txtAreaReason.getText();
        modalGhiChu.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public JPanel getPnListBatch() {
        return pnListBatch;
    }

    public JSpinner getSpinnerSoLuong() {
        return spinnerSoLuong;
    }

    public void setQuantity() {
        int value = 0;
        for (Component component : pnListBatch.getComponents()) {
            if (component instanceof PnSelectBatch) {
                PnSelectBatch pnSelectBatch = (PnSelectBatch) component;
                value += pnSelectBatch.getBatchDTO().getQuantity();
            }
        }
        spinnerSoLuong.setValue(value);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExitModalAdd;
    private javax.swing.JComboBox<UnitDetail> comboChonDvt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JDialog modalGhiChu;
    private javax.swing.JLabel pnHinh;
    private javax.swing.JPanel pnListBatch;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JTextArea txtAreaReason;
    private javax.swing.JLabel txtDonGia;
    private javax.swing.JLabel txtTenSP;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
