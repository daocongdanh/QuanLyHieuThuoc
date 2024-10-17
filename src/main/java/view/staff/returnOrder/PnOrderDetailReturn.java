/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.returnOrder;

import view.staff.sell.PnSelectBatch;
import bus.OrderDetailBUS;
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
    private List<Batch> batchs;
    private TABReturnOrder tabTraHang;
    private OrderDetailSelected orderDetailSelected;
    private UnitDetail unitDetail;
    private OrderDetail orderDetail;
    private OrderDetailBUS orderDetailBUS;

    public PnOrderDetailReturn() {
        initComponents();
    }

    public PnOrderDetailReturn(OrderDetail orderDetail, UnitDetail unitDetail, OrderDetailSelected orderDetailSelected,
            TABReturnOrder tabTraHang) {
        this.unitDetail = unitDetail;
//        this.batchs = orderDetail.getBatch();
        this.product = unitDetail.getProduct();
        this.tabTraHang = tabTraHang;
        this.orderDetailSelected = orderDetailSelected;
        this.orderDetail = orderDetail;
        this.orderDetailBUS = new OrderDetailBUS(ConnectDB.getEntityManager());
        initComponents();
        fillFirst();

    }

    public Product getProduct() {
        return product;
    }

    public UnitDetail getSelectedUnitDetail() {
        return (UnitDetail) comboChonDvt.getSelectedItem();
    }

    public int getSoLuong() {
        return (int) spinnerSoLuong.getValue();
    }

    public JComboBox<UnitDetail> getComboChonDvt() {
        return comboChonDvt;
    }

    private void fillFirst() {
        comboChonDvt.removeAllItems();
        comboChonDvt.addItem(unitDetail);

        txtTenSP.setText(product.getName());
        pnHinh.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/"
                + product.getImage())), 82, 82));

        spinnerSoLuong.setValue(1);

        int value = (Integer) spinnerSoLuong.getValue();
        txtTongTien.setText(FormatNumber.formatToVND(product.getPrice() * value));

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

        txtTenSP = new javax.swing.JLabel();
        spinnerSoLuong = new javax.swing.JSpinner();
        txtDonGia = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        comboChonDvt = new javax.swing.JComboBox<>();
        pnHinh = new javax.swing.JLabel();
        pnListBatch = new javax.swing.JPanel();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(pnHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboChonDvt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnListBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
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
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
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
    private javax.swing.JComboBox<UnitDetail> comboChonDvt;
    private javax.swing.JLabel pnHinh;
    private javax.swing.JPanel pnListBatch;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JLabel txtDonGia;
    private javax.swing.JLabel txtTenSP;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
