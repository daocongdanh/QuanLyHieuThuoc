/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.manager;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import view.common.MenuChoice;

import static view.common.MenuChoice.menuSwitch;
import view.login.LoadApplication;

/**
 *
 * @author Hoang
 */
public final class MenuManagerAdmin extends javax.swing.JFrame {

    /**
     * Creates new form MenuManagerStaff
     */
    private JPanel currentPanel;

    public MenuManagerAdmin() {
        initComponents();
        UIManagerSet();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitleMenu();
        addMenuClick();

    }

    private void addMenuClick() {
        List<MenuChoice> menuList = Arrays.asList(
                menuStatistical, menuReport, menuCustomer, menuDamaged, menuOrder,
                menuPrescription, menuProduct, menuPromotion, menuPurchase,
                menuReturn, menuStaff, menuSupplier, menuUnit
        );
        Map<MenuChoice, Supplier<JPanel>> menuPanelMap = new HashMap<>();

        // Tạo "Loading Panel"
        JPanel loadingPanel = new JPanel();
        loadingPanel.setLayout(new GridBagLayout());
        JLabel loadingLabel = new JLabel("Đang tải...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        loadingPanel.add(loadingLabel, gbc);
        menuPanelMap.put(menuStatistical, () -> new TABStats());
// menuPanelMap.put(menuReport, () -> new TABReportPanel());
        menuPanelMap.put(menuCustomer, () -> new TABCustomer());
        menuPanelMap.put(menuDamaged, () -> new TABDamageItem());
        menuPanelMap.put(menuOrder, () -> new TABOrder());
        menuPanelMap.put(menuPrescription, () -> new TABPrecription());
        menuPanelMap.put(menuProduct, () -> new TABProduct());
// menuPanelMap.put(menuPromotion, () -> new TABPromotionPanel());
        menuPanelMap.put(menuPurchase, () -> new TABPurchaseOrder());
        menuPanelMap.put(menuReturn, () -> new TABReturnOrder());
        menuPanelMap.put(menuStaff, () -> new TABEmployee());
// menuPanelMap.put(menuSupplier, () -> new TABSupplierPanel());
        menuPanelMap.put(menuUnit, () -> new TABUnit());
        menuPanelMap.put(menuPromotion, () -> new TABPromotion());
        menuPanelMap.put(menuReport, () -> new TABReport());
        menuSwitch(new TABStats(), menuStatistical, mainContent, menuList, currentPanel);

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
        mainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Box.setBackground(new java.awt.Color(204, 204, 255));
        Box.setLayout(new java.awt.BorderLayout());

        pnLeft.setBackground(new java.awt.Color(211, 237, 187));
        pnLeft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 2));

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
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(83, 83, 83)
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

        mainContent.setBackground(new java.awt.Color(255, 255, 255));
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
