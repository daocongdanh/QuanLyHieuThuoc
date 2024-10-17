/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.sell;

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
public class PnChonLo extends javax.swing.JPanel {

    /**
     * Creates new form pnChonLo
     */
    private Batch batch;
    private UnitDetail unitDetail;

    public PnChonLo() {
        initComponents();
        setMargin();
    }

    public PnChonLo(Batch batch, UnitDetail unitDetail) {
        this.batch = batch;
        this.unitDetail = unitDetail;
        initComponents();

        double stock = batch.getStock();
        double conversionRate = unitDetail.getConversionRate();
        int result = (int) Math.floor(stock / conversionRate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String name = batch.getName() + " - " + formatter.format(batch.getExpirationDate())
                    + " - Tồn: " + result;
            btnTenLo.setText(name);
        setMargin();
        
        
    }

    public JToggleButton getBtnTenLo() {
        return this.btnTenLo;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
    }

    private void setMargin() {
        // Set an empty border with 10 pixels margin on all sides
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTenLo = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 70));
        setMinimumSize(new java.awt.Dimension(400, 70));
        setPreferredSize(new java.awt.Dimension(400, 70));
        setRequestFocusEnabled(false);

        btnTenLo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTenLo.setText("jToggleButton1");
        btnTenLo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTenLo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTenLoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTenLo, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTenLo, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTenLoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTenLoActionPerformed
        // TODO add your handling code here:
        Container parent = this.getParent();
        
        JSpinner jSpinner = null;
        for(Component component : parent.getComponents()){
            if (component instanceof JSpinner) {
                jSpinner = (JSpinner) component;
                break;
            }
        }
            
        if(btnTenLo.isSelected()){
            jSpinner.setEnabled(true);
        }
        else{
            jSpinner.setEnabled(false);
        }
    }//GEN-LAST:event_btnTenLoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnTenLo;
    // End of variables declaration//GEN-END:variables
}
