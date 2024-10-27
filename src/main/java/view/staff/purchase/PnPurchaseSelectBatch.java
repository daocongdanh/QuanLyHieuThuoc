/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.purchase;

import view.staff.*;
import dto.BatchDTO;
import entity.UnitDetail;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class PnPurchaseSelectBatch extends javax.swing.JPanel {

    /**
     * Creates new form PnLo
     */
    private BatchDTO batchDTO;
    private UnitDetail unitDetail;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private JSpinner jSpinner;
    
    public PnPurchaseSelectBatch() {
        initComponents();
    }
    
    private int getStock(){
        double stock = batchDTO.getStock();
        double conversionRate = unitDetail.getConversionRate();
        double result = stock / conversionRate;
        return (int) Math.floor(result);
    }

    public JSpinner getSpinnerQuantity() {
        return spinnerQuantity;
    }
    
    public PnPurchaseSelectBatch(BatchDTO batchDTO,UnitDetail unitDetail, JSpinner jSpinner ){
        initComponents();
        this.batchDTO = batchDTO;
        this.unitDetail = unitDetail;
        this.jSpinner = jSpinner;
        
        String name = batchDTO.getName() + " - " + formatter.format(batchDTO.getExpirationDate())
                    + " - SL: " + batchDTO.getQuantity();
        txtBatch.setText(name);
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fire event để thông báo xóa batch này
                jSpinner.setValue((int)jSpinner.getValue() - batchDTO.getQuantity());
                fireDeleteBatchEvent();
            }
        });
        spinnerQuantity.setValue(batchDTO.getQuantity());
    }

    public void setName(){
        String name = batchDTO.getName() + " - " + formatter.format(batchDTO.getExpirationDate())
                    + " - SL: " + batchDTO.getQuantity();
        txtBatch.setText(name);
    }
    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
    }

    public BatchDTO getBatchDTO() {
        return batchDTO;
    }

    public void setBatchDTO(BatchDTO batchDTO) {
        this.batchDTO = batchDTO;
    }

    public JSpinner getjSpinner() {
        return jSpinner;
    }


    
    private void fireDeleteBatchEvent() {
        // Notify listeners rằng PnPurchaseSelectBatch này cần được xóa
        Container parent = this.getParent();  // Lấy panel cha (pnListBatch)
        if (parent != null) {
            parent.remove(this);  // Xóa chính nó khỏi panel cha
            parent.revalidate();   // Cập nhật lại giao diện
            parent.repaint();
        }
    }
    public JButton getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(JButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modalSoLuong = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        lblExpirationDate = new javax.swing.JLabel();
        txtExpirationDate = new javax.swing.JLabel();
        txtStock = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        spinnerQuantity = new javax.swing.JSpinner();
        btnConfirmQuantityBatch = new javax.swing.JButton();
        txtBatch = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();

        modalSoLuong.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        modalSoLuong.setMinimumSize(new java.awt.Dimension(518, 355));
        modalSoLuong.setModal(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(518, 355));
        jPanel2.setPreferredSize(new java.awt.Dimension(518, 355));

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName.setText("Lô");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(204, 204, 204));
        txtName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtName.setToolTipText("");
        txtName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        lblExpirationDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblExpirationDate.setText("Hạn sử dụng");

        txtExpirationDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtExpirationDate.setForeground(new java.awt.Color(204, 204, 204));
        txtExpirationDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtExpirationDate.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtExpirationDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtStock.setForeground(new java.awt.Color(204, 204, 204));
        txtStock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtStock.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtStock.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        lblStock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStock.setText("Tồn kho");

        lblQuantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantity.setText("Số lượng nhập");

        spinnerQuantity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerQuantity.setFocusable(false);

        btnConfirmQuantityBatch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnConfirmQuantityBatch.setText("Xác nhận");
        btnConfirmQuantityBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmQuantityBatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConfirmQuantityBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExpirationDate)
                            .addComponent(lblQuantity)
                            .addComponent(lblStock)
                            .addComponent(lblName))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblName)
                        .addGap(30, 30, 30)
                        .addComponent(lblExpirationDate)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblStock))
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(spinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConfirmQuantityBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalSoLuongLayout = new javax.swing.GroupLayout(modalSoLuong.getContentPane());
        modalSoLuong.getContentPane().setLayout(modalSoLuongLayout);
        modalSoLuongLayout.setHorizontalGroup(
            modalSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modalSoLuongLayout.setVerticalGroup(
            modalSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(3, 130, 193));

        txtBatch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtBatch.setForeground(new java.awt.Color(255, 255, 255));
        txtBatch.setText("jLabel1");
        txtBatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBatchMouseClicked(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(3, 130, 193));
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("X");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBatch)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void txtBatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBatchMouseClicked
        txtName.setText(batchDTO.getName());
        txtExpirationDate.setText(formatter.format(batchDTO.getExpirationDate()));
        txtStock.setText(getStock() + "");
        spinnerQuantity.setValue(batchDTO.getQuantity());
        modalSoLuong.setTitle(batchDTO.getName() + " - " + formatter.format(batchDTO.getExpirationDate()));
        modalSoLuong.setLocationRelativeTo(null);
        modalSoLuong.setVisible(true);
        
    }//GEN-LAST:event_txtBatchMouseClicked

    private void btnConfirmQuantityBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmQuantityBatchActionPerformed
        // TODO add your handling code here:
        modalSoLuong.dispose();
        int quantity = (int)spinnerQuantity.getValue();
        jSpinner.setValue((int) jSpinner.getValue() - batchDTO.getQuantity() + quantity);
        Container parent = this.getParent();
        List<PnPurchaseSelectBatch> pnPurchaseSelectBatchs = new ArrayList<>();
        for(Component component : parent.getComponents()){
            if(component instanceof PnPurchaseSelectBatch){
                PnPurchaseSelectBatch pnPurchaseSelectBatch = (PnPurchaseSelectBatch) component;
                pnPurchaseSelectBatchs.add(pnPurchaseSelectBatch);
            }
        }
        
        for(PnPurchaseSelectBatch pnPurchaseSelectBatch : pnPurchaseSelectBatchs){
            if(pnPurchaseSelectBatch.getBatchDTO().getName().equals(batchDTO.getName())){
                pnPurchaseSelectBatch.getBatchDTO().setQuantity(quantity);
            }
        }
        
        parent.removeAll();
        parent.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
        for (PnPurchaseSelectBatch pnPurchaseSelectBatch : pnPurchaseSelectBatchs) {
            PnPurchaseSelectBatch newpnPurchaseSelectBatch = new PnPurchaseSelectBatch(pnPurchaseSelectBatch.getBatchDTO(), unitDetail, jSpinner);
            parent.add(newpnPurchaseSelectBatch);
        }
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_btnConfirmQuantityBatchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmQuantityBatch;
    private javax.swing.JButton btnRemove;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblExpirationDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblStock;
    private javax.swing.JDialog modalSoLuong;
    private javax.swing.JSpinner spinnerQuantity;
    private javax.swing.JLabel txtBatch;
    private javax.swing.JLabel txtExpirationDate;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtStock;
    // End of variables declaration//GEN-END:variables
}