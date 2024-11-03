/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.returnOrder;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dto.BatchDTO;
import dto.OrderDetailSelected;
import entity.Product;
import entity.*;
import java.awt.Component;
import java.awt.Container;
import java.util.Objects;
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
    private String reason;
    private OrderDetail orderDetail;
    private int type;
    private int maxQuantity;

    public PnOrderDetailReturn() {
        initComponents();
    }

    public PnOrderDetailReturn(OrderDetail orderDetail, OrderDetailSelected orderDetailSelected,
            TABReturnOrder tabTraHang, int type) {
        this.product = orderDetail.getBatch().getProduct();
        this.tabTraHang = tabTraHang;
        this.orderDetailSelected = orderDetailSelected;
        this.type = type;
        this.orderDetail = orderDetail;
        initComponents();
        fillFirst();
        validType(type);
        reason = "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PnOrderDetailReturn other = (PnOrderDetailReturn) obj;
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.tabTraHang, other.tabTraHang)) {
            return false;
        }
        if (!Objects.equals(this.orderDetailSelected, other.orderDetailSelected)) {
            return false;
        }
        return Objects.equals(this.orderDetail, other.orderDetail);
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderDetailSelected getOrderDetailSelected() {
        return orderDetailSelected;
    }

    public void setOrderDetailSelected(OrderDetailSelected orderDetailSelected) {
        this.orderDetailSelected = orderDetailSelected;
    }

    private void validType(int type) {
        if (type == 1) {
            lblIcon.setIcon(ResizeImage.resizeImage((new FlatSVGIcon(getClass().getResource("/img/tick.svg"))), 30, 30));
            btnXoaOderDetail.setIcon(null);
            spinnerSoLuong.setEnabled(false);
        } else if (type == 2) {
            lblIcon.setIcon(ResizeImage.resizeImage((new FlatSVGIcon(getClass().getResource("/img/note.svg"))), 30, 30));
            spinnerSoLuong.setEnabled(true);
            lblIcon.setVisible(true);
            btnXoaOderDetail.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/delete.jpg")), 25, 25));
            SpinnerNumberModel model = (SpinnerNumberModel) spinnerSoLuong.getModel();
            model.setMaximum(maxQuantity);
            model.setMinimum(1);
        }
    }

    public Product getProduct() {
        return product;
    }

    public String getReason() {
        return this.reason;
    }

    public int getSoLuong() {
        return (int) spinnerSoLuong.getValue();
    }

    private void fillFirst() {
        txtDvt.setText(product.getUnit().getName());
        txtTenSP.setText(product.getName());
        pnHinh.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/"
                + product.getImage())), 82, 82));
        maxQuantity = 0;
        for (BatchDTO x : orderDetailSelected.getBatchDTOs()) {
            maxQuantity += x.getQuantity();
        }
        fillQuantity(maxQuantity);
        setLineTotal();
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
        jPanel2 = new javax.swing.JPanel();
        btnXoaOderDetail = new javax.swing.JLabel();
        btnXoaOderDetail.setIcon( ResizeImage.resizeImage( new javax.swing.ImageIcon(getClass().getResource("/img/delete.jpg")) , 25, 25));
        pnHinh = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JLabel();
        txtDvt = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        spinnerSoLuong = new javax.swing.JSpinner();
        txtDonGia = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();

        modalGhiChu.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalGhiChu.setTitle("Lý do trả hàng");
        modalGhiChu.setMinimumSize(new java.awt.Dimension(665, 405));
        modalGhiChu.setModal(true);
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
        setMaximumSize(new java.awt.Dimension(111111111, 140));
        setMinimumSize(new java.awt.Dimension(1129, 140));
        setPreferredSize(new java.awt.Dimension(1129, 147));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 22, 26);
        flowLayout2.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout2);

        btnXoaOderDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaOderDetailMouseClicked(evt);
            }
        });
        jPanel2.add(btnXoaOderDetail);

        pnHinh.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(pnHinh);

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenSP.setText("jLabel1");
        txtTenSP.setMaximumSize(new java.awt.Dimension(300, 1111111111));
        txtTenSP.setMinimumSize(new java.awt.Dimension(300, 25));
        txtTenSP.setName(""); // NOI18N
        txtTenSP.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanel2.add(txtTenSP);

        txtDvt.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDvt.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDvt.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(txtDvt);

        add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(517, 144));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 40, 50);
        flowLayout1.setAlignOnBaseline(true);
        jPanel3.setLayout(flowLayout1);

        spinnerSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerSoLuong.setEnabled(false);
        spinnerSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerSoLuongStateChanged(evt);
            }
        });
        jPanel3.add(spinnerSoLuong);

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDonGia.setText("0 đ");
        txtDonGia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel3.add(txtDonGia);

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTongTien.setText("0 đ");
        txtTongTien.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(txtTongTien);

        lblIcon.setText("Ghi chú");
        lblIcon.setMaximumSize(new java.awt.Dimension(38, 38));
        lblIcon.setMinimumSize(new java.awt.Dimension(38, 38));
        lblIcon.setPreferredSize(new java.awt.Dimension(38, 38));
        lblIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconMouseClicked(evt);
            }
        });
        jPanel3.add(lblIcon);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public Double getLineTotal() {
        int quantity = (Integer) spinnerSoLuong.getValue();
        return quantity * product.getPrice();
    }

    public void setLineTotal() {
        int quantity = (Integer) spinnerSoLuong.getValue();
        txtDonGia.setText(FormatNumber.formatToVND(product.getPrice()));
        txtTongTien.setText(FormatNumber.formatToVND(quantity * product.getPrice()));
    }


    private void spinnerSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerSoLuongStateChanged
        setLineTotal();
        tabTraHang.changeTongTienHoaDon();
    }//GEN-LAST:event_spinnerSoLuongStateChanged

    private void lblIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconMouseClicked
        if (type == 2) {
            txtAreaReason.setText(reason);
            modalGhiChu.setLocationRelativeTo(null);
            modalGhiChu.setVisible(true);
        } else if (type == 1) {
            tabTraHang.addOrderDetailToReturn(this);
        }

    }//GEN-LAST:event_lblIconMouseClicked

    private void btnExitModalAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitModalAddActionPerformed
        // TODO add your handling code here:
        txtAreaReason.setText("");
        modalGhiChu.dispose();
    }//GEN-LAST:event_btnExitModalAddActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reason = txtAreaReason.getText();
        modalGhiChu.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXoaOderDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaOderDetailMouseClicked
        // TODO add your handling code here:
        if (type == 2) {
            Container parent = this.getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }
            tabTraHang.changeTongTienHoaDon();
        }
    }//GEN-LAST:event_btnXoaOderDetailMouseClicked

    public JSpinner getSpinnerSoLuong() {
        return spinnerSoLuong;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExitModalAdd;
    private javax.swing.JLabel btnXoaOderDetail;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JDialog modalGhiChu;
    private javax.swing.JLabel pnHinh;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JTextArea txtAreaReason;
    private javax.swing.JLabel txtDonGia;
    private javax.swing.JLabel txtDvt;
    private javax.swing.JLabel txtTenSP;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
