/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff;

import view.staff.sell.PnTabOrder;
import bus.PrescriptionBUS;
import bus.ProductBUS;
import com.formdev.flatlaf.FlatClientProperties;
import connectDB.ConnectDB;
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
import entity.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import util.MessageDialog;
import view.common.TableActionCellEditorOneAction;
import view.common.TableActionCellRenderOneAction;
import view.login.LoadApplication;
import view.common.TableActionEventOneAction;

/**
 *
 * @author Hoang
 */
public class TABSell extends javax.swing.JPanel {

    static int transactionNumber = 1;
    private final ProductBUS productBUS;
    private TableDesign tablePrescription;
    private TableDesign tablePrescriptionDetail;
    private final PrescriptionBUS prescriptionBUS;
    private List<PrescriptionDetail> prescriptionDetails = new ArrayList<>();

    /**
     * Creates new form LapHoaDonForm
     */
    public TABSell() {
        productBUS = LoadApplication.productBUS;
        prescriptionBUS = LoadApplication.prescriptionBUS;
        lookAndFeelSet();
        initComponents();
        customUI();
        addTabHoaDon();
        fillTablePrescription();
        fillTablePrescriptionDetail();

    }

    private void fillTablePrescriptionDetail() {
        String[] headers = {"Mã SP", "Tên SP", "ĐVT", "Số lượng", "Ghi chú"};
        List<Integer> tableWidths = Arrays.asList(100, 200, 100, 100, 200);
        tablePrescriptionDetail = new TableDesign(headers, tableWidths);
        tablePrescriptionDetail.setTableHeaderFontSize(12);
        scrollTableDesPre.setViewportView(tablePrescriptionDetail.getTable());
    }

    private void fillTablePrescription() {
        String[] headers = {"Mã đơn thuốc mẫu", "Tên đơn thuốc mẫu", "Chọn"};
        List<Integer> tableWidths = Arrays.asList(150, 450, 100);
        tablePrescription = new TableDesign(headers, tableWidths, List.of(false, false, true));
        tablePrescription.setTableHeaderFontSize(12);
        scrollTable.setViewportView(tablePrescription.getTable());
        List<Prescription> prescriptions = prescriptionBUS.getAllPrescriptions();
        fillContent(prescriptions);
        addEventBtnEditInTable();
    }

    private void fillContent(List<Prescription> prescriptions) {
        tablePrescription.getModelTable().setRowCount(0);
        for (Prescription prescription : prescriptions) {
            tablePrescription.getModelTable().addRow(new Object[]{prescription.getPrescriptionId(),
                prescription.getName(), null});
        }
    }

    private void addEventBtnEditInTable() {
        JTable table = tablePrescription.getTable();
        TableActionEventOneAction event = (int row) -> {
            int selectedRow = table.getSelectedRow();
            String prescriptionId = (String) table.getValueAt(selectedRow, 0);
            List<PrescriptionDetail> details = prescriptionBUS.getAllPrescripDetailsByPrescription(prescriptionId);
            if (!details.isEmpty()) {
                prescriptionDetails = details;
                tabbedPaneDTM.setSelectedIndex(1);
                fillContentPrescriptionDetail(prescriptionDetails);
            } else {
                MessageDialog.warning(null, "Không có sản phẩm nào trong đơn thuốc mẫu !!!");
            }
            table.getCellEditor().stopCellEditing();
        };
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new TableActionCellRenderOneAction(1));
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new TableActionCellEditorOneAction(event,1));
    }

    private void fillContentPrescriptionDetail(List<PrescriptionDetail> prescriptionDetails) {
        tablePrescriptionDetail.getModelTable().setRowCount(0);
        for (PrescriptionDetail prescriptionDetail : prescriptionDetails) {
            UnitDetail unitDetail = prescriptionDetail.getUnitDetail();
            Product product = unitDetail.getProduct();
            tablePrescriptionDetail.getModelTable().addRow(new Object[]{product.getProductId(), product.getName(),
                unitDetail.getUnit().getName(), prescriptionDetail.getQuantity(), prescriptionDetail.getDescription()
            });
        }
    }

    private void customUI() {
        txtTimSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên, mã vạch, SĐK");
        txtDTM.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm đơn thuốc mẫu");
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

        modalSelectPrescription = new javax.swing.JDialog();
        pnAll = new javax.swing.JPanel();
        tabbedPaneDTM = new javax.swing.JTabbedPane();
        pnListPre = new javax.swing.JPanel();
        txtDTM = new javax.swing.JTextField();
        btnDTM = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        pnListSelected = new javax.swing.JPanel();
        btnApply = new javax.swing.JButton();
        scrollTableDesPre = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        spinnerQuantity = new javax.swing.JSpinner();
        pnMi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtTimSanPham = new javax.swing.JTextField();
        btnMa = new javax.swing.JButton();
        btnThemHD = new javax.swing.JButton();
        btnDonThuocMau = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();

        modalSelectPrescription.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalSelectPrescription.setTitle("Đơn thuốc mẫu");
        modalSelectPrescription.setMinimumSize(new java.awt.Dimension(1021, 580));
        modalSelectPrescription.setResizable(false);

        pnAll.setBackground(new java.awt.Color(255, 255, 255));
        pnAll.setMaximumSize(new java.awt.Dimension(1021, 580));
        pnAll.setMinimumSize(new java.awt.Dimension(1021, 580));
        pnAll.setPreferredSize(new java.awt.Dimension(1021, 580));

        pnListPre.setBackground(new java.awt.Color(255, 255, 255));
        pnListPre.setMaximumSize(new java.awt.Dimension(1021, 580));
        pnListPre.setMinimumSize(new java.awt.Dimension(1021, 580));

        btnDTM.setBackground(new java.awt.Color(116, 167, 72));
        btnDTM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDTM.setForeground(new java.awt.Color(255, 255, 255));
        btnDTM.setText("Tìm kiếm");
        btnDTM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDTMActionPerformed(evt);
            }
        });

        scrollTable.setPreferredSize(new java.awt.Dimension(771, 238));

        javax.swing.GroupLayout pnListPreLayout = new javax.swing.GroupLayout(pnListPre);
        pnListPre.setLayout(pnListPreLayout);
        pnListPreLayout.setHorizontalGroup(
            pnListPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnListPreLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(pnListPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnListPreLayout.createSequentialGroup()
                        .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addGroup(pnListPreLayout.createSequentialGroup()
                        .addComponent(txtDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216))))
        );
        pnListPreLayout.setVerticalGroup(
            pnListPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnListPreLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnListPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        tabbedPaneDTM.addTab("Danh sách đơn thuốc mẫu ", pnListPre);

        pnListSelected.setBackground(new java.awt.Color(255, 255, 255));

        btnApply.setBackground(new java.awt.Color(116, 167, 72));
        btnApply.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnApply.setForeground(new java.awt.Color(255, 255, 255));
        btnApply.setText("Áp dụng");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        scrollTableDesPre.setPreferredSize(new java.awt.Dimension(771, 238));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Số lượng đơn thuốc");

        spinnerQuantity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerQuantity.setFocusable(false);

        javax.swing.GroupLayout pnListSelectedLayout = new javax.swing.GroupLayout(pnListSelected);
        pnListSelected.setLayout(pnListSelectedLayout);
        pnListSelectedLayout.setHorizontalGroup(
            pnListSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnListSelectedLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(pnListSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnListSelectedLayout.createSequentialGroup()
                        .addComponent(scrollTableDesPre, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(pnListSelectedLayout.createSequentialGroup()
                        .addGroup(pnListSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnListSelectedLayout.createSequentialGroup()
                                .addComponent(spinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnListSelectedLayout.setVerticalGroup(
            pnListSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnListSelectedLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnListSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnApply, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(spinnerQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTableDesPre, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        tabbedPaneDTM.addTab("Chi tiết đơn thuốc mẫu", pnListSelected);

        javax.swing.GroupLayout pnAllLayout = new javax.swing.GroupLayout(pnAll);
        pnAll.setLayout(pnAllLayout);
        pnAllLayout.setHorizontalGroup(
            pnAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAllLayout.createSequentialGroup()
                .addComponent(tabbedPaneDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnAllLayout.setVerticalGroup(
            pnAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAllLayout.createSequentialGroup()
                .addComponent(tabbedPaneDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalSelectPrescriptionLayout = new javax.swing.GroupLayout(modalSelectPrescription.getContentPane());
        modalSelectPrescription.getContentPane().setLayout(modalSelectPrescriptionLayout);
        modalSelectPrescriptionLayout.setHorizontalGroup(
            modalSelectPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalSelectPrescriptionLayout.createSequentialGroup()
                .addComponent(pnAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modalSelectPrescriptionLayout.setVerticalGroup(
            modalSelectPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalSelectPrescriptionLayout.createSequentialGroup()
                .addComponent(pnAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new java.awt.BorderLayout());

        pnMi.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txtTimSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimSanPhamKeyPressed(evt);
            }
        });

        btnMa.setText("Mã");
        btnMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaActionPerformed(evt);
            }
        });

        btnThemHD.setText("Thêm Hóa Đơn");
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });

        btnDonThuocMau.setText("Đơn Thuốc Mẫu");
        btnDonThuocMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonThuocMauActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDonThuocMau, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnThemHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDonThuocMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        tabbedPane.setBackground(new java.awt.Color(232, 234, 237));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
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

    private void btnDonThuocMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonThuocMauActionPerformed
        // TODO add your handling code here:
        tabbedPaneDTM.setSelectedIndex(0);
        modalSelectPrescription.setLocationRelativeTo(null);
        modalSelectPrescription.setVisible(true);
    }//GEN-LAST:event_btnDonThuocMauActionPerformed

    private void btnDTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDTMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDTMActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        // TODO add your handling code here:
        if (prescriptionDetails.isEmpty()) {
            MessageDialog.warning(null, "Không có sản phẩm nào trong đơn thuốc mẫu !!!");
        } else {
            PnTabOrder tabHoaDon = (PnTabOrder) tabbedPane.getSelectedComponent();
            int quantityPre = (int) spinnerQuantity.getValue();
            if (tabHoaDon.addDonThuocMau(prescriptionDetails, quantityPre)) {
                modalSelectPrescription.dispose();
            }
        }
    }//GEN-LAST:event_btnApplyActionPerformed

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
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnDTM;
    private javax.swing.JButton btnDonThuocMau;
    private javax.swing.JButton btnMa;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JDialog modalSelectPrescription;
    private javax.swing.JPanel pnAll;
    private javax.swing.JPanel pnListPre;
    private javax.swing.JPanel pnListSelected;
    private javax.swing.JPanel pnMi;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JScrollPane scrollTableDesPre;
    private javax.swing.JSpinner spinnerQuantity;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTabbedPane tabbedPaneDTM;
    private javax.swing.JTextField txtDTM;
    private javax.swing.JTextField txtTimSanPham;
    // End of variables declaration//GEN-END:variables

}
