/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.staff.purchase;

import gui.staff.TABPurchase;
import com.formdev.flatlaf.FlatClientProperties;
import dto.BatchDTO;
import entity.Product;
import java.awt.Component;
import java.util.List;
import entity.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import util.ResizeImage;
import util.FormatNumber;
import util.MessageDialog;

/**
 *
 * @author Hoang
 */
public class PnPurchaseOrderDetail extends javax.swing.JPanel {

    private Product product;
    private List<Batch> batchs;
    private TABPurchase tabPurchase;

    public PnPurchaseOrderDetail() {
        initComponents();
    }

    public PnPurchaseOrderDetail(Product product, List<Batch> batchs, TABPurchase tabPurchase) {
        this.batchs = batchs;
        this.product = product;
        this.tabPurchase = tabPurchase;
        initComponents();
        customUI();
        fillFirst();
    }

    public Product getProduct() {
        return product;
    }

    public int getSoLuong() {
        return (int) spinnerSoLuong.getValue();
    }

    private void customUI() {
        txtNameNewBatch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên lô mới");
    }

    private void fillFirst() {
        txtTenSP.setText(product.getName());
        pnHinh.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/"
                + product.getImage())), 82, 82));

        spinnerSoLuong.setValue(0);

        txtDonGia.setText(FormatNumber.formatToVND(product.getPurchasePriceVAT()));
        int value = (Integer) spinnerSoLuong.getValue();
        txtTongTien.setText(FormatNumber.formatToVND(product.getPurchasePriceVAT() * value));
        txtDVT.setText(product.getUnit().getName());
    }

    public void fillQuantity(int quantity) {
        spinnerSoLuong.setValue(quantity);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogChonLo = new javax.swing.JDialog();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnXacNhan = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        pnChuaLo = new javax.swing.JPanel();
        txtTimLo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblName2 = new javax.swing.JLabel();
        lblExpirationDate2 = new javax.swing.JLabel();
        lblQuantity2 = new javax.swing.JLabel();
        spinnerQuantityNewBatch = new javax.swing.JSpinner();
        btnConfirmCreateNewBatch = new javax.swing.JButton();
        txtNameNewBatch = new javax.swing.JTextField();
        ExperationDateNewBatch = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnXoaPurchaseOderDetail = new javax.swing.JLabel();
        btnXoaPurchaseOderDetail.setIcon( ResizeImage.resizeImage( new javax.swing.ImageIcon(getClass().getResource("/img/delete.jpg")) , 25, 25));
        pnHinh = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtDVT = new javax.swing.JLabel();
        spinnerSoLuong = new javax.swing.JSpinner();
        txtDonGia = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnChonLo = new javax.swing.JButton();
        pnListBatch = new javax.swing.JPanel();

        dialogChonLo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogChonLo.setTitle("Chọn Lô");
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

        txtTimLo.setBackground(new java.awt.Color(239, 240, 242));
        txtTimLo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimLo.setMaximumSize(new java.awt.Dimension(500, 55));
        txtTimLo.setMinimumSize(new java.awt.Dimension(500, 55));
        txtTimLo.setPreferredSize(new java.awt.Dimension(500, 55));
        txtTimLo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimLoActionPerformed(evt);
            }
        });

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
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimLo, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(txtTimLo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("                 Lô cũ                 ", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(518, 355));

        lblName2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName2.setText("Lô");

        lblExpirationDate2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblExpirationDate2.setText("Hạn sử dụng");

        lblQuantity2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantity2.setText("Số lượng");

        spinnerQuantityNewBatch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerQuantityNewBatch.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerQuantityNewBatch.setFocusable(false);

        btnConfirmCreateNewBatch.setText("Xác nhận");
        btnConfirmCreateNewBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmCreateNewBatchActionPerformed(evt);
            }
        });

        txtNameNewBatch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        ExperationDateNewBatch.setBackground(new java.awt.Color(255, 255, 255));
        ExperationDateNewBatch.setDateFormatString("dd/MM/yyyy");
        ExperationDateNewBatch.setFocusable(false);
        ExperationDateNewBatch.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        ExperationDateNewBatch.setPreferredSize(new java.awt.Dimension(100, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmCreateNewBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuantity2)
                            .addComponent(lblExpirationDate2)
                            .addComponent(lblName2))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNameNewBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(spinnerQuantityNewBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ExperationDateNewBatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(84, 84, 84))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNameNewBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName2))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ExperationDateNewBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExpirationDate2))
                        .addGap(67, 67, 67)
                        .addComponent(lblQuantity2))
                    .addComponent(spinnerQuantityNewBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnConfirmCreateNewBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jTabbedPane1.addTab("                 Lô mới                ", jPanel4);

        javax.swing.GroupLayout dialogChonLoLayout = new javax.swing.GroupLayout(dialogChonLo.getContentPane());
        dialogChonLo.getContentPane().setLayout(dialogChonLoLayout);
        dialogChonLoLayout.setHorizontalGroup(
            dialogChonLoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        dialogChonLoLayout.setVerticalGroup(
            dialogChonLoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        setMaximumSize(new java.awt.Dimension(111111111, 200));
        setMinimumSize(new java.awt.Dimension(1139, 200));
        setPreferredSize(new java.awt.Dimension(1139, 200));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 22, 26);
        flowLayout1.setAlignOnBaseline(true);
        jPanel5.setLayout(flowLayout1);

        btnXoaPurchaseOderDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaPurchaseOderDetailMouseClicked(evt);
            }
        });
        jPanel5.add(btnXoaPurchaseOderDetail);

        pnHinh.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.add(pnHinh);

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenSP.setText("jLabel1");
        jPanel5.add(txtTenSP);

        jPanel2.add(jPanel5, java.awt.BorderLayout.WEST);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 40, 50);
        flowLayout2.setAlignOnBaseline(true);
        jPanel6.setLayout(flowLayout2);

        txtDVT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDVT.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtDVT.setMinimumSize(new java.awt.Dimension(100, 30));
        txtDVT.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(txtDVT);

        spinnerSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerSoLuong.setEnabled(false);
        spinnerSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerSoLuongStateChanged(evt);
            }
        });
        jPanel6.add(spinnerSoLuong);

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDonGia.setText("0 đ");
        txtDonGia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel6.add(txtDonGia);

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTongTien.setText("0 đ");
        txtTongTien.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(txtTongTien);

        jPanel2.add(jPanel6, java.awt.BorderLayout.EAST);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 0);
        flowLayout3.setAlignOnBaseline(true);
        jPanel3.setLayout(flowLayout3);

        btnChonLo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChonLo.setText("Chọn Lô");
        btnChonLo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonLoActionPerformed(evt);
            }
        });
        for (int i=1; i<=2; i++) {
            JLabel space = new JLabel("\t");
            jPanel3.add(space);
        }
        jPanel3.add(btnChonLo);

        pnListBatch.setBackground(new java.awt.Color(255, 255, 255));
        pnListBatch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        jPanel3.add(pnListBatch);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public Double getLineTotal() {
        int quantity = (Integer) spinnerSoLuong.getValue();
        return quantity * product.getPurchasePriceVAT();
    }

    public void setLineTotal() {
        int quantity = (int) spinnerSoLuong.getValue();
        txtDonGia.setText(FormatNumber.formatToVND(product.getPurchasePriceVAT()));
        txtTongTien.setText(FormatNumber.formatToVND(quantity * product.getPurchasePriceVAT()));
    }

    private void fillBatch() {
        pnChuaLo.removeAll();
        for (Batch batch : batchs) {
            PnChonLoNhap chonLo = new PnChonLoNhap(batch);
            chonLo.setAlignmentX(Component.LEFT_ALIGNMENT);

            JSpinner spinnerModal = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
            spinnerModal.setFont(new Font("Arial", Font.PLAIN, 16));
            Dimension spinnerSize = new Dimension(80, 50); // Chiều rộng 80, chiều cao 30
            spinnerModal.setPreferredSize(spinnerSize);
            spinnerModal.setEnabled(false);

            JPanel panelContainer = new JPanel();
            panelContainer.setBackground(Color.white);
            panelContainer.setLayout(new FlowLayout(FlowLayout.LEFT));

            panelContainer.add(chonLo);
            panelContainer.add(Box.createRigidArea(new Dimension(20, 0)));
            panelContainer.add(spinnerModal);

            pnChuaLo.add(panelContainer);

            for (Component component : pnListBatch.getComponents()) {
                if (component instanceof PnPurchaseSelectBatch) {
                    PnPurchaseSelectBatch pnPurchaseSelectBatch = (PnPurchaseSelectBatch) component;
                    if (chonLo.getBatch().getName().equals(pnPurchaseSelectBatch.getBatchDTO().getName())) {
                        chonLo.getBtnTenLo().setEnabled(false);
                    }
                }
            }
        }
    }

    private void spinnerSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerSoLuongStateChanged
        setLineTotal();
        tabPurchase.changeTongTienHoaDon();
    }//GEN-LAST:event_spinnerSoLuongStateChanged

    private void btnChonLoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonLoActionPerformed
        fillBatch();
        txtTimLo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên lô, hạn sử dụng");

        dialogChonLo.setVisible(true);
        dialogChonLo.pack();
        dialogChonLo.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnChonLoActionPerformed

    public JPanel getPnListBatch() {
        return pnListBatch;
    }

    public JSpinner getSpinnerSoLuong() {
        return spinnerSoLuong;
    }

    public void setQuantity() {
        int value = 0;
        for (Component component : pnListBatch.getComponents()) {
            if (component instanceof PnPurchaseSelectBatch) {
                PnPurchaseSelectBatch pnPurchaseSelectBatch = (PnPurchaseSelectBatch) component;
                value += pnPurchaseSelectBatch.getBatchDTO().getQuantity();
            }
        }
        spinnerSoLuong.setValue(value);
    }

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        List<BatchDTO> batchDTOs = getSelectedBatchDTO();

        for (BatchDTO batchDTO : batchDTOs) {
            pnListBatch.add(new PnPurchaseSelectBatch(batchDTO, spinnerSoLuong));
        }
        pnListBatch.revalidate();
        pnListBatch.repaint();
        dialogChonLo.dispose();

        int value = 0;
        for (Component component : pnListBatch.getComponents()) {
            if (component instanceof PnPurchaseSelectBatch) {
                PnPurchaseSelectBatch pnPurchaseSelectBatch = (PnPurchaseSelectBatch) component;
                value += pnPurchaseSelectBatch.getBatchDTO().getQuantity();
            }
        }
        spinnerSoLuong.setValue(value);
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnXoaPurchaseOderDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaPurchaseOderDetailMouseClicked
        Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
        tabPurchase.changeTongTienHoaDon();
    }//GEN-LAST:event_btnXoaPurchaseOderDetailMouseClicked

    private void txtTimLoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimLoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimLoActionPerformed

    private void btnConfirmCreateNewBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmCreateNewBatchActionPerformed
        String nameNewBatch = txtNameNewBatch.getText().trim();
        try {
            spinnerQuantityNewBatch.commitEdit();
        } catch (ParseException ex) {
            return;
        }

        if (nameNewBatch.equals("")) {
            MessageDialog.error(null, "Tên lô mới không được rỗng!");
            return;
        }
        int stockNewBatch = (int) spinnerQuantityNewBatch.getValue();
        int quantityNewBatch = stockNewBatch;
        try {
            Date date = ExperationDateNewBatch.getDate();
            LocalDate expDateNewBatch = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (!(expDateNewBatch.isAfter(LocalDate.now()))) {
                MessageDialog.error(null, "Ngày hết hạn phải sau ngày hiện tại!");
                return;
            }
            BatchDTO b = new BatchDTO(nameNewBatch, stockNewBatch, expDateNewBatch, quantityNewBatch);

            pnListBatch.add(new PnPurchaseSelectBatch(b, spinnerSoLuong));
            pnListBatch.revalidate();
            pnListBatch.repaint();
            txtNameNewBatch.setText("");
            ExperationDateNewBatch.setDate(null);
            spinnerQuantityNewBatch.setValue(1);
            dialogChonLo.dispose();
        } catch (Exception e) {
            MessageDialog.error(null, "Ngày hết hạn không hợp lệ!");
        }

        int value = 0;
        for (Component component : pnListBatch.getComponents()) {
            if (component instanceof PnPurchaseSelectBatch) {
                PnPurchaseSelectBatch pnPurchaseSelectBatch = (PnPurchaseSelectBatch) component;
                value += pnPurchaseSelectBatch.getBatchDTO().getQuantity();
            }
        }
        spinnerSoLuong.setValue(value);
    }//GEN-LAST:event_btnConfirmCreateNewBatchActionPerformed

    public List<BatchDTO> getSelectedBatchDTO() {
        List<BatchDTO> batchDTOs = new ArrayList<>();

        for (Component component : pnChuaLo.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panelContainer = (JPanel) component;

                PnChonLoNhap pnChonLoNhap = null;
                JSpinner spinner = null;

                for (Component child : panelContainer.getComponents()) {
                    if (child instanceof PnChonLoNhap) {
                        pnChonLoNhap = (PnChonLoNhap) child;
                    } else if (child instanceof JSpinner) {
                        spinner = (JSpinner) child;
                    }
                }

                if (pnChonLoNhap.getBtnTenLo().isSelected()) {
                    Batch batch = (Batch) pnChonLoNhap.getBatch();
                    BatchDTO batchDTO = new BatchDTO();
                    batchDTO.setName(batch.getName());
                    batchDTO.setExpirationDate(batch.getExpirationDate());
                    batchDTO.setStock(batch.getStock());
                    batchDTO.setQuantity((int) spinner.getValue());
                    batchDTOs.add(batchDTO);
                }
            }
        }
        return batchDTOs;  // Trả về null nếu không có gì được chọn
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser ExperationDateNewBatch;
    private javax.swing.JButton btnChonLo;
    private javax.swing.JButton btnConfirmCreateNewBatch;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel btnXoaPurchaseOderDetail;
    private javax.swing.JDialog dialogChonLo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblExpirationDate2;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblQuantity2;
    private javax.swing.JPanel pnChuaLo;
    private javax.swing.JLabel pnHinh;
    private javax.swing.JPanel pnListBatch;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSpinner spinnerQuantityNewBatch;
    private javax.swing.JSpinner spinnerSoLuong;
    private javax.swing.JLabel txtDVT;
    private javax.swing.JLabel txtDonGia;
    private javax.swing.JTextField txtNameNewBatch;
    private javax.swing.JLabel txtTenSP;
    private javax.swing.JTextField txtTimLo;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
