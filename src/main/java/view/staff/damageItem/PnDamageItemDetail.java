/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.damageItem;

import java.util.List;
import entity.*;
import java.awt.Container;
import javax.swing.*;
import util.ResizeImage;
import util.FormatNumber;

/**
 *
 * @author Hoang
 */
public class PnDamageItemDetail extends javax.swing.JPanel {

//    private UnitDetail unitDetail;
    private List<Batch> batchs;
    private TabDamageItem tabDamageItem;

    public PnDamageItemDetail() {
        initComponents();
    }

//    public PnDamageItemDetail(UnitDetail unitDetail, List<Batch> batchs, TabDamageItem tabDamageItem) {
//        this.unitDetail = unitDetail;
//        this.batchs = batchs;
//        this.tabDamageItem = tabDamageItem;
//        initComponents();
//        fillFirst();
//
//    }

    public int getSoLuong() {
        return (int) spinnerSoLuong.getValue();
    }

//    private void fillFirst() {
//        // fill unit
//        comboChonDvt.removeAllItems();
//        comboChonDvt.addItem(unitDetail);
//        Product product = unitDetail.getProduct();
//
//        txtTenSP.setText(product.getName());
//        pnHinh.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/"
//                + product.getImage())), 82, 82));
//        spinnerSoLuong.setValue(0);
//        int value = (Integer) spinnerSoLuong.getValue();
//        txtTongTien.setText(FormatNumber.formatToVND(product.getPrice() * value));
//
//        int qty = 0;
//
//        for (Batch batch : batchs) {
//            qty += batch.getStock();
//            PnSelectBatchDamageItem pnSelectBatchDamageItem = new PnSelectBatchDamageItem(batch);
//            pnListBatch.add(pnSelectBatchDamageItem);
//        }
//        spinnerSoLuong.setValue(qty);
//        setLineTotal();
//
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnXoaOderDetail = new javax.swing.JLabel();
        btnXoaOderDetail.setIcon( ResizeImage.resizeImage( new javax.swing.ImageIcon(getClass().getResource("/img/delete.jpg")) , 25, 25));
        txtTenSP = new javax.swing.JLabel();
        spinnerSoLuong = new javax.swing.JSpinner();
        txtDonGia = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        pnHinh = new javax.swing.JLabel();
        pnListBatch = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        setMaximumSize(new java.awt.Dimension(1139, 170));
        setMinimumSize(new java.awt.Dimension(1139, 170));
        setPreferredSize(new java.awt.Dimension(1139, 170));
        setRequestFocusEnabled(false);

        btnXoaOderDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaOderDetailMouseClicked(evt);
            }
        });

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenSP.setText("jLabel1");

        spinnerSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerSoLuong.setEnabled(false);

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDonGia.setText("0 đ");
        txtDonGia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTongTien.setText("0 đ");
        txtTongTien.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        pnHinh.setBackground(new java.awt.Color(204, 204, 204));

        pnListBatch.setBackground(new java.awt.Color(255, 255, 255));
        pnListBatch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Lô hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnXoaOderDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)
                        .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnListBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
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
                                    .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(pnHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnXoaOderDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnListBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

//    public Double getLineTotal() {
//        Product product = unitDetail.getProduct();
//        int quantity = (int) spinnerSoLuong.getValue();
//        double price = product.getPurchasePrice() * (1 + product.getVAT());
//        return unitDetail.getConversionRate() * quantity * price;
//    }
//    public void setLineTotal() {
//        Product product = unitDetail.getProduct();
//        int quantity = (int) spinnerSoLuong.getValue();
//        double price = product.getPurchasePrice() * (1 + product.getVAT());
//        txtDonGia.setText(FormatNumber.formatToVND(unitDetail.getConversionRate() * price));
//        txtTongTien.setText(FormatNumber.formatToVND(unitDetail.getConversionRate()
//                * quantity * price));
//    }
//    
//    public List<Batch> getBatchs() {
//        return batchs;
//    }
//
//    public UnitDetail getUnitDetail() {
//        return unitDetail;
//    }
//    
    
    private void btnXoaOderDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaOderDetailMouseClicked
        // TODO add your handling code here:
        Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
        tabDamageItem.changeTongTienHoaDon();
    }//GEN-LAST:event_btnXoaOderDetailMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnXoaOderDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel pnHinh;
    private javax.swing.JPanel pnListBatch;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JLabel txtDonGia;
    private javax.swing.JLabel txtTenSP;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
