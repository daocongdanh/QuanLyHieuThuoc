/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.manager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setTitleMenu();
//        addMenuClick();
//        menu1.showForm();
    }
//
//    private void addMenuClick() {
//
//        List<MenuChoice> menuList = Arrays.asList(
//                menuStatistical, menuReport, menuCustomer, menuDamaged, menuOrder,
//                menuProduct, menuPromotion, menuPurchase,
//                menuReturn, menuStaff, menuSupplier, menuUnit
//        );
//        Map<MenuChoice, Supplier<JPanel>> menuPanelMap = new HashMap<>();
//
//        // Tạo "Loading Panel"
//        JPanel loadingPanel = new JPanel();
//        loadingPanel.setLayout(new GridBagLayout());
//        JLabel loadingLabel = new JLabel("Đang tải...");
//        loadingLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        loadingPanel.add(loadingLabel, gbc);
//        menuPanelMap.put(menuStatistical, () -> new TABStats());
//// menuPanelMap.put(menuReport, () -> new TABReportPanel());
//        menuPanelMap.put(menuCustomer, () -> new TABCustomer());
//        menuPanelMap.put(menuDamaged, () -> new TABDamageItem());
//        menuPanelMap.put(menuOrder, () -> new TABOrder());
//        menuPanelMap.put(menuProduct, () -> new TABProduct());
//// menuPanelMap.put(menuPromotion, () -> new TABPromotionPanel());
//        menuPanelMap.put(menuPurchase, () -> new TABPurchaseOrder());
//        menuPanelMap.put(menuReturn, () -> new TABReturnOrder());
//        menuPanelMap.put(menuStaff, () -> new TABEmployee());
//        menuPanelMap.put(menuSupplier, () -> new TABSupplier());
//        menuPanelMap.put(menuUnit, () -> new TABUnit());
//        menuPanelMap.put(menuPromotion, () -> new TABPromotion());
//        menuPanelMap.put(menuReport, () -> new TABReport());
//        menuSwitch(new TABStats(), menuStatistical, mainContent, menuList, currentPanel);
//
//        for (MenuChoice menu : menuList) {
//            menu.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    // Hiển thị Loading Panel trước
//                    menuSwitch(loadingPanel, menu, mainContent, menuList, currentPanel);
//
//                    // Dùng SwingWorker để xử lý panel mới trong nền
//                    new SwingWorker<JPanel, Void>() {
//                        @Override
//                        protected JPanel doInBackground() {
//                            // Khởi tạo panel mới trong nền
//                            return menuPanelMap.get(menu).get();
//                        }
//
//                        @Override
//                        protected void done() {
//                            try {
//                                // Khi hoàn tất, lấy panel mới và hiển thị
//                                JPanel newPanel = get();
//                                if (newPanel != null) {
//                                    menuSwitch(newPanel, menu, mainContent, menuList, currentPanel);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }.execute();
//                }
//            });
//        }
//    }
//
//    private void setTitleMenu() {
//        menuStatistical.setIconMenu(new FlatSVGIcon(getClass().getResource("/img/thongKeIcon.svg")));
//        menuStatistical.setTitleMenu("Thống Kê");
//        menuReport.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/baoCaoIcon.svg"))));
//        menuReport.setTitleMenu("Báo Cáo Thu Chi");
//        menuCustomer.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/customerIcon.svg"))));
//        menuCustomer.setTitleMenu("Quản Lý Khách Hàng");
//        menuDamaged.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/damageIcon.svg"))));
//        menuDamaged.setTitleMenu("Quản Lý Xuất Hủy");
//        menuOrder.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/orderIcon.svg"))));
//        menuOrder.setTitleMenu("Quản Lý Đơn Hàng");
//        menuProduct.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/productIcon.svg"))));
//        menuProduct.setTitleMenu("Quản Lý Sản Phẩm");
//        menuPromotion.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/promotionIcon.svg"))));
//        menuPromotion.setTitleMenu("Quản Lý Khuyến Mãi");
//        menuPurchase.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/purchaseIcon.svg"))));
//        menuPurchase.setTitleMenu("Quản Lý Nhập Hàng");
//        menuReturn.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/returnProductIcon.svg"))));
//        menuReturn.setTitleMenu("Quản Lý Phiếu Trả Hàng");
//        menuStaff.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/employeeIcon.svg"))));
//        menuStaff.setTitleMenu("Quản Lý Nhân Viên");
//        menuSupplier.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/supplierIcon.svg"))));
//        menuSupplier.setTitleMenu("Quản Lý Nhà Cung Cấp");
//        menuUnit.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/unitIcon.svg"))));
//        menuUnit.setTitleMenu("Quản Lý Đơn Vị Tính");
//        menuExit.setTitleMenu("Đăng xuất");
//        menuExit.setIconMenu((new FlatSVGIcon(getClass().getResource("/img/exiticon.svg"))));
//        menuExit.setDefault();
//        lblIcon.setIcon(ResizeImage.resizeImage(new javax.swing.ImageIcon(getClass().getResource("/img/icon.jpg")), 68, 68));
//    }

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
        mainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Box.setBackground(new java.awt.Color(204, 204, 255));
        Box.setLayout(new java.awt.BorderLayout());

        pnLeft.setBackground(new java.awt.Color(211, 237, 187));
        pnLeft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 2));
        pnLeft.setLayout(new javax.swing.BoxLayout(pnLeft, javax.swing.BoxLayout.LINE_AXIS));
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
            .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("style");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatIntelliJLaf.setup();
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
    private javax.swing.JPanel pnLeft;
    // End of variables declaration//GEN-END:variables
}
