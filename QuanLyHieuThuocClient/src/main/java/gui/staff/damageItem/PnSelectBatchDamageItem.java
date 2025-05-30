/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.staff.damageItem;

import entity.Batch;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author daoducdanh
 */
public class PnSelectBatchDamageItem extends javax.swing.JPanel {

    private Batch batch;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public PnSelectBatchDamageItem() {
        initComponents();
    }
    
    public PnSelectBatchDamageItem(Batch batch){
        initComponents();
        this.batch = batch;
        
        String name = batch.getName() + " - " + formatter.format(batch.getExpirationDate())
                    + " - SL: " + batch.getStock();
        txtBatch.setText(name);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBatch = new javax.swing.JLabel();

        setBackground(new java.awt.Color(3, 130, 193));

        txtBatch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtBatch.setForeground(new java.awt.Color(255, 255, 255));
        txtBatch.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBatch)
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txtBatch;
    // End of variables declaration//GEN-END:variables
}
