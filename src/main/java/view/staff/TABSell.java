/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff;

import view.staff.sell.PnTabOrder;
import bus.ProductBUS;
import com.formdev.flatlaf.FlatClientProperties;
import entity.Product;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import java.util.Arrays;
import java.util.List;
import view.common.TableDesign;
import java.awt.event.KeyEvent;
import util.MessageDialog;
import view.login.LoadApplication;

/**
 *
 * @author Hoang
 */
public class TABSell extends javax.swing.JPanel {

    static int transactionNumber = 1;
    private final ProductBUS productBUS;

    /**
     * Creates new form LapHoaDonForm
     */
    public TABSell() {
        productBUS = LoadApplication.productBUS;
        lookAndFeelSet();
        initComponents();
        customUI();
        addTabHoaDon();

    }

    private void customUI() {
        txtTimSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã vạch, Số đăng ký");
    }

    private void lookAndFeelSet() {
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("Component.arc", 8);
        UIManager.put("Button.arc", 10);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TabbedPane.tabHeight", 45);
        UIManager.put("ToggleButton.selectedBackground", new Color(81, 154, 244));
        UIManager.put("ToggleButton.selectedForeground", Color.WHITE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtTimSanPham = new javax.swing.JTextField();
        btnMa = new javax.swing.JButton();
        btnThemHD = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new java.awt.BorderLayout());

        pnMi.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setToolTipText("");

        txtTimSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimSanPhamKeyPressed(evt);
            }
        });

        btnMa.setBackground(new java.awt.Color(115, 165, 71));
        btnMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMa.setForeground(new java.awt.Color(255, 255, 255));
        btnMa.setText("Mã");
        btnMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaActionPerformed(evt);
            }
        });

        btnThemHD.setBackground(new java.awt.Color(115, 165, 71));
        btnThemHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemHD.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHD.setText("Thêm Hóa Đơn");
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(txtTimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnThemHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnMiLayout = new javax.swing.GroupLayout(pnMi);
        pnMi.setLayout(pnMiLayout);
        pnMiLayout.setHorizontalGroup(
            pnMiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnMiLayout.setVerticalGroup(
            pnMiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMiLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnMi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaActionPerformed
        // TODO add your handling code here:
        String sdk = txtTimSanPham.getText().trim();
        searchProduct(sdk);
    }//GEN-LAST:event_btnMaActionPerformed

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        if (tabbedPane.getTabCount() < 15) {
            addTabHoaDon();
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ tối đa 15 tab");
        }
    }//GEN-LAST:event_btnThemHDActionPerformed

    private void txtTimSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSanPhamKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String sdk = txtTimSanPham.getText().trim();
            searchProduct(sdk);
            txtTimSanPham.requestFocus();
        }
    }//GEN-LAST:event_txtTimSanPhamKeyPressed

    private void addTabHoaDon() {
        PnTabOrder banHang = new PnTabOrder(this);
        tabbedPane.add("Tab", banHang);
        int index = tabbedPane.indexOfComponent(banHang);
        tabbedPane.setTabComponentAt(index, createTabTitle(tabbedPane, "Giao dịch " + transactionNumber++, banHang));
        tabbedPane.setSelectedIndex(index);
    }

    public void removeAndAddNewTab(PnTabOrder tabHoaDon) {
        tabbedPane.remove(tabHoaDon);
        addTabHoaDon();
    }

    private JPanel createTabTitle(JTabbedPane tabbedPane, String title, Component tabComponent) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setOpaque(false);

        // Label hiển thị tên tab
        JLabel label = new JLabel(title);
        panel.add(label);

        // Nút close
        JButton closeButton = new JButton("x");
        closeButton.setMargin(new Insets(0, 1, 0, 0));
        closeButton.setPreferredSize(new Dimension(15, 15));

        // Hành động khi nhấn nút close
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tabbedPane.indexOfComponent(tabComponent);
                if (index != -1 && tabbedPane.getTabCount() != 1) {
                    tabbedPane.remove(index);  // Xóa tab tương ứng
                }
            }
        });

        panel.add(Box.createRigidArea(new Dimension(5, 0)));
        panel.add(closeButton);

        return panel;
    }

    private void searchProduct(String sdk) {
        Product product = productBUS.getProductBySDK(sdk);
        if (product != null) {
            PnTabOrder tabHoaDon = (PnTabOrder) tabbedPane.getSelectedComponent();
            tabHoaDon.addSanPham(product);
            txtTimSanPham.setText("");
        } else {
            MessageDialog.warning(null, "Sản phẩm không tồn tại !!!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMa;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnMi;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField txtTimSanPham;
    // End of variables declaration//GEN-END:variables

}
