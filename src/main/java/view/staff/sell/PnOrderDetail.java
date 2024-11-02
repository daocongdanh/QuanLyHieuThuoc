/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.sell;

import entity.Product;
import java.awt.Component;
import java.util.List;
import entity.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import util.FormatNumber;
import util.MessageDialog;
import util.ResizeImage;

/**
 *
 * @author Hoang
 */
public class PnOrderDetail extends javax.swing.JPanel {

    private Product product;
    private List<Batch> batchs;
    private Promotion promotion;
    private PnTabOrder tabHoaDon;

    public PnOrderDetail() {
        initComponents();
    }

    public PnOrderDetail(Product product, List<Batch> batchs, Promotion promotion, PnTabOrder tabHoaDon) {
        this.batchs = batchs;
        this.product = product;
        this.promotion = promotion;
        this.tabHoaDon = tabHoaDon;
        initComponents();
        fillFirst();
    }

    public Product getProduct() {
        return product;
    }

    public int getSoLuong() {
        return (int) spinnerSoLuong.getValue();
    }

    private void fillFirst() {
        txtTenSP.setText(product.getName());
        pnHinh.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/"
                + product.getImage())), 82, 82));
        txtDVT.setText(product.getUnit().getName());
        spinnerSoLuong.setValue(1);

        if (promotion != null) {
            DecimalFormat df = new DecimalFormat("- #.00 %");
            txtDiscount.setText(df.format(promotion.getDiscount()));
            txtDonGia.setText(FormatNumber.formatToVND(product.getPrice() * (1 - promotion.getDiscount())));
        } else {
            txtDiscount.setText("");
            txtDonGia.setText(FormatNumber.formatToVND(product.getPrice()));
        }
        int value = (Integer) spinnerSoLuong.getValue();
        txtTongTien.setText(FormatNumber.formatToVND(product.getPrice() * value));

    }

    public void fillQuantity(int quantity) {
        spinnerSoLuong.setValue(quantity);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogChonLo = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        btnXacNhan = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        pnChuaLo = new javax.swing.JPanel();
        btnXoaOderDetail = new javax.swing.JLabel();
        btnXoaOderDetail.setIcon( ResizeImage.resizeImage( new javax.swing.ImageIcon(getClass().getResource("/img/delete.jpg")) , 25, 25));
        txtTenSP = new javax.swing.JLabel();
        spinnerSoLuong = new javax.swing.JSpinner();
        txtDonGia = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        pnHinh = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JLabel();
        pnListBatch = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDVT = new javax.swing.JLabel();

        dialogChonLo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogChonLo.setTitle("Chọn lô");
        dialogChonLo.setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(651, 285));

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        scrollPane.setBorder(null);

        pnChuaLo.setBackground(new java.awt.Color(255, 255, 255));
        pnChuaLo.setLayout(new javax.swing.BoxLayout(pnChuaLo, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(pnChuaLo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout dialogChonLoLayout = new javax.swing.GroupLayout(dialogChonLo.getContentPane());
        dialogChonLo.getContentPane().setLayout(dialogChonLoLayout);
        dialogChonLoLayout.setHorizontalGroup(
            dialogChonLoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogChonLoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dialogChonLoLayout.setVerticalGroup(
            dialogChonLoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogChonLoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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
        spinnerSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));
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

        pnHinh.setBackground(new java.awt.Color(204, 204, 204));

        txtDiscount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiscount.setForeground(new java.awt.Color(255, 0, 0));
        txtDiscount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDiscount.setText("- 5.00 %");
        txtDiscount.setToolTipText("");
        txtDiscount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        pnListBatch.setBackground(new java.awt.Color(255, 255, 255));
        pnListBatch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Lô hàng");

        txtDVT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDVT.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnXoaOderDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnListBatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDVT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    public Double getLineTotal() {
        int quantity = (Integer) spinnerSoLuong.getValue();
        return quantity * product.getPrice();
    }

    public double getPriceDiscount() {
        if (promotion != null) {
            return getLineTotal() * promotion.getDiscount();
        } else {
            return 0;
        }
    }

    public void setLineTotal() {
        int quantity = (int) spinnerSoLuong.getValue();
        if (promotion != null) {
            txtDonGia.setText(FormatNumber.formatToVND(product.getPrice() * (1 - promotion.getDiscount())));
            txtTongTien.setText(FormatNumber.formatToVND(quantity * product.getPrice() * (1 - promotion.getDiscount())));
        } else {
            txtDonGia.setText(FormatNumber.formatToVND(product.getPrice()));
            txtTongTien.setText(FormatNumber.formatToVND(quantity * product.getPrice()));
        }
    }

    private void spinnerSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerSoLuongStateChanged
        int quantity = (int) spinnerSoLuong.getValue();
        int stock = batchs.stream()
                .mapToInt(Batch::getStock)
                .sum();
        if (quantity > stock) {
            MessageDialog.warning(null, "Không đủ số lượng !!!");
            spinnerSoLuong.setValue(quantity - 1);
            return;
        }
        pnListBatch.removeAll();
        setLineTotal();
        for (Batch batch : batchs) {
            if (batch.getStock() >= quantity) {
                PnSelectBatch pnSelectBatch = new PnSelectBatch(batch, quantity);
                pnListBatch.add(pnSelectBatch);
                quantity = 0;
                break;
            } else {
                quantity -= batch.getStock();
                PnSelectBatch pnSelectBatch = new PnSelectBatch(batch, batch.getStock());
                pnListBatch.add(pnSelectBatch);
            }
        }
        pnListBatch.revalidate();
        pnListBatch.repaint();
        tabHoaDon.changeTongTienHoaDon();
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
                value += pnSelectBatch.getQuantity();
            }
        }
        spinnerSoLuong.setValue(value);
    }

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
//        List<BatchDTO> batchDTOs = getSelectedBatchDTO();
//        for (BatchDTO batchDTO : batchDTOs) {
//            pnListBatch.add(new PnSelectBatch(batchDTO, spinnerSoLuong));
//        }
//        pnListBatch.revalidate();
//        pnListBatch.repaint();
//        dialogChonLo.dispose();
//
//        int value = 0;
//        for (Component component : pnListBatch.getComponents()) {
//            if (component instanceof PnSelectBatch) {
//                PnSelectBatch pnSelectBatch = (PnSelectBatch) component;
//                value += pnSelectBatch.getBatchDTO().getQuantity();
//            }
//        }
//        spinnerSoLuong.setValue(value);
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnXoaOderDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaOderDetailMouseClicked
        // TODO add your handling code here:
        Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
        tabHoaDon.changeTongTienHoaDon();
    }//GEN-LAST:event_btnXoaOderDetailMouseClicked

//    private List<BatchDTO> getSelectedBatchDTO() {
//        List<BatchDTO> batchDTOs = new ArrayList<>();
//
//        for (Component component : pnChuaLo.getComponents()) {
//            if (component instanceof JPanel) {
//                JPanel panelContainer = (JPanel) component;
//
//                PnChonLo pnChonLo = null;
//                JSpinner spinner = null;
//
//                for (Component child : panelContainer.getComponents()) {
//                    if (child instanceof PnChonLo) {
//                        pnChonLo = (PnChonLo) child;
//                    } else if (child instanceof JSpinner) {
//                        spinner = (JSpinner) child;
//                    }
//                }
//
//                if (pnChonLo.getBtnTenLo().isSelected()) {
//                    Batch batch = (Batch) pnChonLo.getBatch();
//                    BatchDTO batchDTO = new BatchDTO();
//                    batchDTO.setName(batch.getName());
//                    batchDTO.setExpirationDate(batch.getExpirationDate());
//                    batchDTO.setStock(batch.getStock());
//                    batchDTO.setQuantity((int) spinner.getValue());
//                    batchDTOs.add(batchDTO);
//                }
//            }
//        }
//        return batchDTOs;  // Trả về null nếu không có gì được chọn
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel btnXoaOderDetail;
    private javax.swing.JDialog dialogChonLo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnChuaLo;
    private javax.swing.JLabel pnHinh;
    private javax.swing.JPanel pnListBatch;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JLabel txtDVT;
    private javax.swing.JLabel txtDiscount;
    private javax.swing.JLabel txtDonGia;
    private javax.swing.JLabel txtTenSP;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
