/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.purchase;

import view.staff.*;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import entity.*;
import java.awt.Component;
import java.awt.Container;
import java.time.format.DateTimeFormatter;
import javax.swing.JSpinner;

/**
 *
 * @author Hoang
 */
public class PnChonLoNhap extends javax.swing.JPanel {

    /**
     * Creates new form pnChonLoNhap
     */
    private Batch batch;
//    private UnitDetail unitDetail;

    public PnChonLoNhap() {
        initComponents();
        setMargin();
    }

//    public PnChonLoNhap(Batch batch, UnitDetail unitDetail) {
//        this.batch = batch;
//        this.unitDetail = unitDetail;
//        initComponents();
//
//        double stock = batch.getStock();
//        double conversionRate = unitDetail.getConversionRate();
//        int result = (int) Math.floor(stock / conversionRate);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        
//        String name = batch.getName() + " - " + formatter.format(batch.getExpirationDate())
//                    + " - Tồn: " + result;
//            btnBatchName.setText(name);
//        setMargin();
//        
//        
//    }

    public JToggleButton getBtnTenLo() {
        return this.btnBatchName;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

//    public UnitDetail getUnitDetail() {
//        return unitDetail;
//    }
//
//    public void setUnitDetail(UnitDetail unitDetail) {
//        this.unitDetail = unitDetail;
//    }

    private void setMargin() {
        // Set an empty border with 10 pixels margin on all sides
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBatchName = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 70));
        setMinimumSize(new java.awt.Dimension(400, 70));
        setPreferredSize(new java.awt.Dimension(400, 70));
        setRequestFocusEnabled(false);

        btnBatchName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBatchName.setText("jToggleButton1");
        btnBatchName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBatchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatchNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBatchName, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBatchName, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatchNameActionPerformed
        // TODO add your handling code here:
        Container parent = this.getParent();
        
        JSpinner jSpinner = null;
        for(Component component : parent.getComponents()){
            if (component instanceof JSpinner) {
                jSpinner = (JSpinner) component;
                break;
            }
        }
            
        if(btnBatchName.isSelected()){
            jSpinner.setEnabled(true);
        }
        else{
            jSpinner.setEnabled(false);
        }
    }//GEN-LAST:event_btnBatchNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBatchName;
    // End of variables declaration//GEN-END:variables
}
