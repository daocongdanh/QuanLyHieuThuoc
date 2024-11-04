/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.login;

import bus.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import connectDB.ConnectDB;
import jakarta.persistence.EntityManager;
import util.ResizeImage;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Hoang
 */
public class LoadApplication extends javax.swing.JFrame {

    /**
     * Creates new form LoadApplication
     */
    public static OrderBUS orderBUS;
    public static CustomerBUS customerBUS;
    public static BatchBUS batchBUS;
    public static PromotionBUS promotionBUS;
    public static OrderDetailBUS orderDetailBUS;
    public static ReturnOrderBUS returnOrderBUS;
    public static AccountBUS accountBUS;
    public static DamageItemBUS damageItemBUS;
    public static DamageItemDetailBUS damageItemDetailBUS;
    public static EmployeeBUS employeeBUS;
    public static ProductBUS productBUS;
    public static PurchaseOrderBUS purchaseOrderBUS;
    public static PurchaseOrderDetailBUS purchaseOrderDetailBUS;
    public static UnitBUS unitBUS;
    public static ReturnOrderDetailBUS returnOrderDetailBUS;
    public static SupplierBUS supplierBUS;
    public static ReportBUS reportBUS;

    public LoadApplication() {
        initComponents();
        setLocationRelativeTo(null);
        lblImage.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().
                getResource("/img/bgrload.png")), 900, 500));

//        lblIconDoctor.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().
//                getResource("/img/doctor.png")), 667, 444));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        loadingValue = new javax.swing.JLabel();
        progressLoading = new javax.swing.JProgressBar();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBackground.setBackground(new java.awt.Color(153, 255, 102));
        panelBackground.setPreferredSize(new java.awt.Dimension(900, 500));
        panelBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LOADING...");
        panelBackground.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 452, 120, 30));

        loadingValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loadingValue.setForeground(new java.awt.Color(255, 255, 255));
        loadingValue.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loadingValue.setText("0%");
        panelBackground.add(loadingValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 450, 90, 30));

        progressLoading.setForeground(new java.awt.Color(252, 190, 30));
        panelBackground.add(progressLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 484, 900, 20));
        panelBackground.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("gui.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        FlatIntelliJLaf.setup();
        LoadApplication load = new LoadApplication();
        load.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(9);
                load.loadingValue.setText(i + " %");
                load.progressLoading.setValue(i);
                if (i == 60) {
                    ConnectDB.connect();
                    EntityManager em = ConnectDB.getEntityManager();
                    orderBUS = new OrderBUS(em);
                    customerBUS = new CustomerBUS(em);
                    batchBUS = new BatchBUS(em);
                    promotionBUS = new PromotionBUS(em);
                    orderDetailBUS = new OrderDetailBUS(em);
                    returnOrderBUS = new ReturnOrderBUS(em);
                    accountBUS = new AccountBUS(em);
                    damageItemBUS = new DamageItemBUS(em);
                    damageItemDetailBUS = new DamageItemDetailBUS(em);
                    employeeBUS = new EmployeeBUS(em);
                    productBUS = new ProductBUS(em);
                    purchaseOrderBUS = new PurchaseOrderBUS(em);
                    purchaseOrderDetailBUS = new PurchaseOrderDetailBUS(em);
                    unitBUS = new UnitBUS(em);
                    returnOrderDetailBUS = new ReturnOrderDetailBUS(em);
                    supplierBUS = new SupplierBUS(em);
                    reportBUS = new ReportBUS(em);
                }
//                load.progressLoading.setForeground(Color.orange);
            }
            load.setVisible(false);
            new LoginForm().setVisible(true);
        } catch (Exception ex) {
            throw new RuntimeException("Chạy bị lỗi");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel loadingValue;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JProgressBar progressLoading;
    // End of variables declaration//GEN-END:variables
}
