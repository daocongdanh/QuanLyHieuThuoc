/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.staff;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import view.common.MenuChoice;
import static view.common.MenuChoice.menuSwitch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import view.manager.TABStats;
import view.staff.damageItem.TabDamageItem;

/**
 *
 * @author Hoang
 */
public class MenuManagerStaff extends javax.swing.JFrame {

    private JPanel currentPanel;

    public MenuManagerStaff() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitleMenu();
        addMenuClick();
    }

    private void setTitleMenu() {
        menuReport.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/baoCaoIcon.svg"))));
        menuReport.setTitleMenu("Thống kê cá nhân");
        menuCustomer.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/customerIcon.svg"))));
        menuCustomer.setTitleMenu("Quản Lý Khách Hàng");
        menuDamaged.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/damageIcon.svg"))));
        menuDamaged.setTitleMenu("Phiếu Xuất Hủy");
        menuProduct.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/productIcon.svg"))));
        menuProduct.setTitleMenu("Quản Lý Sản Phẩm");
        menuPurchase.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/purchaseIcon.svg"))));
        menuPurchase.setTitleMenu("Phiếu Nhập Hàng");
        menuReturn.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/returnProductIcon.svg"))));
        menuReturn.setTitleMenu("Phiếu Trả Hàng");
        menuSupplier.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/supplierIcon.svg"))));
        menuSupplier.setTitleMenu("Quản Lý Nhà Cung Cấp");
        menuSell.setTitleMenu("Bán Hàng");
        menuSell.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/sellIcon.svg"))));
        menuExit.setTitleMenu("Đăng xuất");

    }

    private void addMenuClick() {

        JPanel loadingPanel = new JPanel();
        loadingPanel.setLayout(new GridBagLayout());
        JLabel loadingLabel = new JLabel("Đang tải...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        loadingPanel.add(loadingLabel, gbc);
        
        List<MenuChoice> menuList = Arrays.asList(
                menuReport, menuCustomer, menuDamaged, menuProduct, menuPurchase,
                menuReturn, menuSupplier, menuSell, menuExit
        );
        Map<MenuChoice, Supplier<JPanel>> menuPanelMap = new HashMap<>();
        menuPanelMap.put(menuReport, () -> new TABIndividualReport());
        menuPanelMap.put(menuCustomer, () -> new TABStaffCustomer());
        menuPanelMap.put(menuDamaged, () -> new TabDamageItem());
//        menuPanelMap.put(menuProduct, tabProduct);
        menuPanelMap.put(menuPurchase, () -> new TABPurchase());
        menuPanelMap.put(menuReturn, () -> new TABReturnOrder());
        menuPanelMap.put(menuSupplier, () -> new TABStaffSupplier());
        menuPanelMap.put(menuSell, () -> new TABSell());
        menuSwitch(new TABSell(), menuSell, mainContent, menuList, currentPanel);

        for (MenuChoice menu : menuList) {
            menu.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Hiển thị Loading Panel trước
                    menuSwitch(loadingPanel, menu, mainContent, menuList, currentPanel);

                    // Dùng SwingWorker để xử lý panel mới trong nền
                    new SwingWorker<JPanel, Void>() {
                        @Override
                        protected JPanel doInBackground() {
                            // Khởi tạo panel mới trong nền
                            return menuPanelMap.get(menu).get();
                        }

                        @Override
                        protected void done() {
                            try {
                                // Khi hoàn tất, lấy panel mới và hiển thị
                                JPanel newPanel = get();
                                if (newPanel != null) {
                                    menuSwitch(newPanel, menu, mainContent, menuList, currentPanel);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.execute();
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Box = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        menuSell = new view.common.MenuChoice();
        menuProduct = new view.common.MenuChoice();
        menuReport = new view.common.MenuChoice();
        menuCustomer = new view.common.MenuChoice();
        menuSupplier = new view.common.MenuChoice();
        menuPurchase = new view.common.MenuChoice();
        menuReturn = new view.common.MenuChoice();
        menuDamaged = new view.common.MenuChoice();
        menuExit = new view.common.MenuChoice();
        mainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Box.setBackground(new java.awt.Color(255, 255, 255));
        Box.setLayout(new java.awt.BorderLayout());

        pnLeft.setBackground(new java.awt.Color(211, 237, 187));
        pnLeft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 2));

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuSell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuDamaged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(menuSell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuDamaged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
        );

        Box.add(pnLeft, java.awt.BorderLayout.WEST);

        mainContent.setBackground(new java.awt.Color(204, 204, 0));
        mainContent.setLayout(new javax.swing.BoxLayout(mainContent, javax.swing.BoxLayout.LINE_AXIS));
        Box.add(mainContent, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            //</editor-fold>
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MenuManagerStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuManagerStaff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Box;
    private javax.swing.JPanel mainContent;
    private view.common.MenuChoice menuCustomer;
    private view.common.MenuChoice menuDamaged;
    private view.common.MenuChoice menuExit;
    private view.common.MenuChoice menuProduct;
    private view.common.MenuChoice menuPurchase;
    private view.common.MenuChoice menuReport;
    private view.common.MenuChoice menuReturn;
    private view.common.MenuChoice menuSell;
    private view.common.MenuChoice menuSupplier;
    private javax.swing.JPanel pnLeft;
    // End of variables declaration//GEN-END:variables
}
