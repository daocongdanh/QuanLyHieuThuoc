/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.manager;

import view.staff.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import enums.TabMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import util.ResizeImage;
import view.common.MenuChoice;

/**
 *
 * @author Hoang
 */
public final class MenuManagerAdmin extends javax.swing.JFrame {

    /**
     * Creates new form MenuManagerStaff
     */
//    private TAB_STATISTICAL tabStatistical;
//    private TAB_REPORT tabReport;
    private TABCustomer tabCustomer;
//    private TAB_DAMAGED tabDamaged;
//    private TAB_ORDER tabOrder;
    private TABPrecription tabPrescription;
//    private TAB_PRODUCT tabProduct;
//    private TAB_PROMOTION tabPromotion;
//    private TAB_PURCHASE tabPurchase;
//    private TAB_RETURN tabReturn;
//    private TAB_STAFF tabStaff;
//    private TAB_SUPPLIER tabSupplier;
    private TABUnit tabUnit;
    private TabMenu currentTab;
    private TABOrder tabOrder;
    private TABPurchaseOrder tabPurChaseOrder;
    private TABDamageItem tabDamageItem;

    public MenuManagerAdmin() {
        ConnectDB.connect();
        tabUnit = new TABUnit();
//        tabCustomer = new TABCustomer();
        tabPrescription = new TABPrecription();
        tabOrder = new TABOrder();
        tabPurChaseOrder = new TABPurchaseOrder();
        tabDamageItem = new TABDamageItem();
        initComponents();
        UIManagerSet();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitleMenu();
//        menuSwitch(tabUnit, TabMenu.TAB_UNIT, menuUnit);
//        menuSwitch(tabOrder, TabMenu.TAB_ORDER, menuOrder);
//        currentTab = TabMenu.TAB_UNIT;
//        currentTab = TabMenu.TAB_ORDER;
//        menuSwitch(tabPurChaseOrder, TabMenu.TAB_PURCHASE, menuPurchase);
        menuSwitch(tabDamageItem, TabMenu.TAB_DAMAGED, menuDamaged);
    }

    private void setTitleMenu() {         
        menuStatistical.setIconMenu(new FlatSVGIcon(getClass().getResource("/img/thongKeIcon.svg"))); 
        menuStatistical.setTitleMenu("Thống Kê");
        menuReport.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/baoCaoIcon.svg"))));
        menuReport.setTitleMenu("Báo Cáo Thu Chi");
        menuCustomer.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/customerIcon.svg"))));
        menuCustomer.setTitleMenu("Quản Lý Khách Hàng");
        menuDamaged.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/damageIcon.svg"))));
        menuDamaged.setTitleMenu("Quản Lý Xuất Hủy");
        menuOrder.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/orderIcon.svg"))));
        menuOrder.setTitleMenu("Quản Lý Đơn Hàng");
        menuPrescription.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/precriptionIcon.svg"))));
        menuPrescription.setTitleMenu("Quản Lý Đơn Thuốc Mẫu");
        menuProduct.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/productIcon.svg"))));
        menuProduct.setTitleMenu("Quản Lý Sản Phẩm");
        menuPromotion.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/promotionIcon.svg"))));
        menuPromotion.setTitleMenu("Quản Lý Khuyến Mãi");
        menuPurchase.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/purchaseIcon.svg"))));
        menuPurchase.setTitleMenu("Quản Lý Nhập Hàng");
        menuReturn.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/returnProductIcon.svg"))));
        menuReturn.setTitleMenu("Quản Lý Phiếu Trả Hàng");
        menuStaff.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/employeeIcon.svg"))));
        menuStaff.setTitleMenu("Quản Lý Nhân Viên");
        menuSupplier.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/supplierIcon.svg"))));
        menuSupplier.setTitleMenu("Quản Lý Nhà Cung Cấp");
        menuUnit.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/unitIcon.svg"))));
        menuUnit.setTitleMenu("Quản Lý Đơn Vị Tính");
    }

    public void menuSwitch(JPanel panelChuyen, TabMenu tabMoi, MenuChoice panelTab) {
        if (currentTab == tabMoi) {
            return;
        }
        menuStatistical.setDefault();  // Thống Kê
        menuReport.setDefault();       // Báo Cáo Thu Chi
        menuCustomer.setDefault();     // Quản Lý Khách Hàng
        menuDamaged.setDefault();      // Quản Lý Xuất Hủy
        menuOrder.setDefault();        // Quản Lý Đơn Hàng
        menuPrescription.setDefault(); // Quản Lý Đơn Thuốc Mẫu
        menuProduct.setDefault();      // Quản Lý Sản Phẩm
        menuPromotion.setDefault();    // Quản Lý Khuyến Mãi
        menuPurchase.setDefault();     // Quản Lý Nhập Hàng
        menuReturn.setDefault();       // Quản Lý Phiếu Trả Hàng
        menuStaff.setDefault();        // Quản Lý Nhân Viên
        menuSupplier.setDefault();     // Quản Lý Nhà Cung Cấp
        menuUnit.setDefault();
        
        panelTab.setActive();
        currentTab = tabMoi;

        mainContent.removeAll();
        mainContent.add(panelChuyen).setVisible(true);
        mainContent.repaint();
        mainContent.validate();
    }

    private void UIManagerSet() {
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("Component.arc", 8);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TabbedPane.tabHeight", 45);
        UIManager.put("ToggleButton.selectedBackground", new Color(81, 154, 244));
        UIManager.put("ToggleButton.selectedForeground", Color.WHITE);
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
        menuStatistical = new view.common.MenuChoice();
        menuReport = new view.common.MenuChoice();
        menuProduct = new view.common.MenuChoice();
        menuUnit = new view.common.MenuChoice();
        menuPrescription = new view.common.MenuChoice();
        menuStaff = new view.common.MenuChoice();
        menuCustomer = new view.common.MenuChoice();
        menuSupplier = new view.common.MenuChoice();
        menuPurchase = new view.common.MenuChoice();
        menuReturn = new view.common.MenuChoice();
        menuDamaged = new view.common.MenuChoice();
        menuOrder = new view.common.MenuChoice();
        menuPromotion = new view.common.MenuChoice();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Box.setBackground(new java.awt.Color(204, 204, 255));
        Box.setLayout(new java.awt.BorderLayout());

        pnLeft.setBackground(new java.awt.Color(211, 237, 187));
        pnLeft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 2));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("ADMIN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuStatistical, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuDamaged, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(menuStatistical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuDamaged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Box.add(pnLeft, java.awt.BorderLayout.WEST);

        mainContent.setBackground(new java.awt.Color(204, 204, 0));
        mainContent.setLayout(new javax.swing.BoxLayout(mainContent, javax.swing.BoxLayout.LINE_AXIS));
        Box.add(mainContent, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.PREFERRED_SIZE, 952, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuManagerAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainContent;
    private view.common.MenuChoice menuCustomer;
    private view.common.MenuChoice menuDamaged;
    private view.common.MenuChoice menuOrder;
    private view.common.MenuChoice menuPrescription;
    private view.common.MenuChoice menuProduct;
    private view.common.MenuChoice menuPromotion;
    private view.common.MenuChoice menuPurchase;
    private view.common.MenuChoice menuReport;
    private view.common.MenuChoice menuReturn;
    private view.common.MenuChoice menuStaff;
    private view.common.MenuChoice menuStatistical;
    private view.common.MenuChoice menuSupplier;
    private view.common.MenuChoice menuUnit;
    private javax.swing.JPanel pnLeft;
    // End of variables declaration//GEN-END:variables
}
