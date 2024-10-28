/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.manager;

import bus.UnitBUS;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import entity.Unit;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import util.MessageDialog;
import util.ResizeImage;
import view.common.TableDesign;
import view.login.LoadApplication;

/**
 *
 * @author Hoang
 */
public class TABUnit extends javax.swing.JPanel {

    private final UnitBUS unitBUS;
    private TableDesign tableDesign;

    public TABUnit() {
        unitBUS = LoadApplication.unitBUS;
        initComponents();
        setUIManager();
        fillTable();
        addIconFeature();
    }

    private void setUIManager() {
        txtSearchUnit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên đơn vị tính");
        txtNameUnitAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đơn vị tính");
        txtNameUnitEdit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đơn vị tính mới");
        UIManager.put("Button.arc", 10);
    }

    private void fillTable() {
        String[] headers = {"Mã đơn vị tính", "Tên"};
        List<Integer> tableWidths = Arrays.asList(50, 750);
        tableDesign = new TableDesign(headers, tableWidths);
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        List<Unit> units = unitBUS.getAllUnits();
        fillContent(units);
    }

    private void addIconFeature() {
        btnAdd.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/addBtn.svg")), 35, 35));
        btnUpdate.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/editBtn.svg")), 35, 35));
        btnImport.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/import.svg")), 35, 35));
    }

    private void fillContent(List<Unit> units) {
        tableDesign.getModelTable().setRowCount(0);
        for (Unit unit : units) {
            tableDesign.getModelTable().addRow(new Object[]{unit.getUnitId(),
                unit.getName(), null});
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

        modalAddUnit = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNameUnitAdd = new javax.swing.JTextField();
        btnAddUnit = new javax.swing.JButton();
        btnExitModalAdd = new javax.swing.JButton();
        modalEditUnit = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNameUnitEdit = new javax.swing.JTextField();
        btnEditUnit = new javax.swing.JButton();
        btnExitModalEdit = new javax.swing.JButton();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtSearchUnit = new javax.swing.JTextField();
        btnOpenModalAddUnit = new javax.swing.JButton();
        actionPanel = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();

        modalAddUnit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalAddUnit.setTitle("Thêm đơn vị tính");
        modalAddUnit.setMinimumSize(new java.awt.Dimension(738, 260));
        modalAddUnit.setModal(true);
        modalAddUnit.setResizable(false);
        modalAddUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modalAddUnitMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(738, 248));
        jPanel2.setMinimumSize(new java.awt.Dimension(738, 248));
        jPanel2.setPreferredSize(new java.awt.Dimension(738, 248));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Tên đơn vị tính");

        txtNameUnitAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAddUnit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddUnit.setText("Thêm");
        btnAddUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUnitActionPerformed(evt);
            }
        });

        btnExitModalAdd.setBackground(new java.awt.Color(92, 107, 192));
        btnExitModalAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitModalAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnExitModalAdd.setText("Thoát");
        btnExitModalAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitModalAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameUnitAdd)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(509, Short.MAX_VALUE)
                        .addComponent(btnAddUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExitModalAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNameUnitAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExitModalAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalAddUnitLayout = new javax.swing.GroupLayout(modalAddUnit.getContentPane());
        modalAddUnit.getContentPane().setLayout(modalAddUnitLayout);
        modalAddUnitLayout.setHorizontalGroup(
            modalAddUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalAddUnitLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modalAddUnitLayout.setVerticalGroup(
            modalAddUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalAddUnitLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        modalEditUnit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalEditUnit.setTitle("Thêm đơn vị tính");
        modalEditUnit.setMinimumSize(new java.awt.Dimension(738, 260));
        modalEditUnit.setModal(true);
        modalEditUnit.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(738, 248));
        jPanel3.setMinimumSize(new java.awt.Dimension(738, 248));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Tên đơn vị tính");

        txtNameUnitEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnEditUnit.setBackground(new java.awt.Color(78, 94, 186));
        btnEditUnit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditUnit.setForeground(new java.awt.Color(255, 255, 255));
        btnEditUnit.setText("Sửa");
        btnEditUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUnitActionPerformed(evt);
            }
        });

        btnExitModalEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitModalEdit.setText("Thoát");
        btnExitModalEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitModalEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameUnitEdit)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(509, Short.MAX_VALUE)
                        .addComponent(btnEditUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExitModalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNameUnitEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExitModalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalEditUnitLayout = new javax.swing.GroupLayout(modalEditUnit.getContentPane());
        modalEditUnit.getContentPane().setLayout(modalEditUnitLayout);
        modalEditUnitLayout.setHorizontalGroup(
            modalEditUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalEditUnitLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modalEditUnitLayout.setVerticalGroup(
            modalEditUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalEditUnitLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(204, 204, 0));

        pnAll.setBackground(new java.awt.Color(255, 255, 255));
        pnAll.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(590, 100));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 16, 24));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(584, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        txtSearchUnit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchUnit.setMinimumSize(new java.awt.Dimension(300, 40));
        txtSearchUnit.setPreferredSize(new java.awt.Dimension(300, 40));
        txtSearchUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchUnitActionPerformed(evt);
            }
        });
        jPanel6.add(txtSearchUnit);

        btnOpenModalAddUnit.setBackground(new java.awt.Color(115, 165, 71));
        btnOpenModalAddUnit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnOpenModalAddUnit.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenModalAddUnit.setText("Tìm kiếm");
        btnOpenModalAddUnit.setMaximumSize(new java.awt.Dimension(150, 40));
        btnOpenModalAddUnit.setMinimumSize(new java.awt.Dimension(150, 40));
        btnOpenModalAddUnit.setPreferredSize(new java.awt.Dimension(150, 40));
        btnOpenModalAddUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenModalAddUnitActionPerformed(evt);
            }
        });
        jPanel6.add(btnOpenModalAddUnit);

        jPanel5.add(jPanel6);

        headerPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionPanel.setPreferredSize(new java.awt.Dimension(600, 100));
        actionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 5));

        btnAdd.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnAdd.setText("THÊM");
        btnAdd.setBorder(null);
        btnAdd.setBorderPainted(false);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusPainted(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 90));
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        actionPanel.add(btnAdd);

        btnUpdate.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnUpdate.setText("SỬA");
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUpdate.setPreferredSize(new java.awt.Dimension(100, 90));
        btnUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        actionPanel.add(btnUpdate);

        btnImport.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnImport.setText("IMPORT");
        btnImport.setBorder(null);
        btnImport.setBorderPainted(false);
        btnImport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImport.setFocusPainted(false);
        btnImport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImport.setPreferredSize(new java.awt.Dimension(100, 90));
        btnImport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        actionPanel.add(btnImport);

        headerPanel.add(actionPanel, java.awt.BorderLayout.WEST);

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1226, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
        );

        pnAll.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitModalAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitModalAddActionPerformed
        txtNameUnitAdd.setText("");
        modalAddUnit.dispose();
    }//GEN-LAST:event_btnExitModalAddActionPerformed

    private void btnAddUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUnitActionPerformed
        String name = txtNameUnitAdd.getText().trim();
        if (!name.equals("")) {
            if (unitBUS.getUnitByName(name) != null) {
                MessageDialog.warning(null, "Tên đơn vị tính đã tồn tại trong hệ thống.");
                return;
            }
            unitBUS.createUnit(new Unit(null, name));
            MessageDialog.info(null, "Thêm đơn vị tính thành công");
            txtNameUnitAdd.setText("");
            modalAddUnit.dispose();
            fillContent(unitBUS.getAllUnits());
        } else {
            MessageDialog.warning(null, "Dữ liệu nhập không được rỗng.");
        }

    }//GEN-LAST:event_btnAddUnitActionPerformed

    private void btnEditUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUnitActionPerformed
        String name = txtNameUnitEdit.getText().trim();
        if (!name.equals("")) {
            Unit unit = unitBUS.getUnitById(unitIdEdit);

            if (unitBUS.checkUnitForEdit(unitIdEdit) == false) {
                MessageDialog.warning(null, "Đơn vị tính này hiện đang có sản phẩm.");
                txtNameUnitEdit.setText("");
                modalEditUnit.dispose();
                return;
            };

            if (unitBUS.getUnitByName(name) != null) {
                MessageDialog.warning(null, "Tên đơn vị tính đã tồn tại trong hệ thống.");
                return;
            }

            unit.setName(name);
            unitBUS.updateUnit(unit);

            MessageDialog.info(null, "Thay đổi đơn vị tính thành công");
            txtNameUnitEdit.setText("");
            modalEditUnit.dispose();
            fillContent(unitBUS.getAllUnits());
        } else {
            MessageDialog.info(null, "Tên đơn vị tính không được rỗng.");
        }
    }//GEN-LAST:event_btnEditUnitActionPerformed

    private void btnExitModalEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitModalEditActionPerformed
        txtNameUnitEdit.setText("");
        modalEditUnit.dispose();
    }//GEN-LAST:event_btnExitModalEditActionPerformed

    private void btnOpenModalAddUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenModalAddUnitActionPerformed
        String name = txtSearchUnit.getText().trim();
        List<Unit> units = unitBUS.getUnitByNameSearch(name);
        fillContent(units);
    }//GEN-LAST:event_btnOpenModalAddUnitActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        if (unitBUS.importExcel()) {
            MessageDialog.info(null, "Import file thành công");
            List<Unit> units = unitBUS.getAllUnits();
            fillContent(units);
        };
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableDesign.getTable().getSelectedRow();
        if (selectedRow != -1) {
            unitIdEdit = (String) tableDesign.getTable().getValueAt(selectedRow, 0);
            txtNameUnitEdit.setText((String) tableDesign.getTable().getValueAt(selectedRow, 1));
            modalEditUnit.setLocationRelativeTo(null);
            modalEditUnit.setVisible(true);
            if (tableDesign.getTable().getCellEditor() != null) {
                tableDesign.getTable().getCellEditor().stopCellEditing();
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        modalAddUnit.setLocationRelativeTo(null);
        modalAddUnit.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void modalAddUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modalAddUnitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_modalAddUnitMouseClicked

    private void txtSearchUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchUnitActionPerformed
        String name = txtSearchUnit.getText().trim();
        List<Unit> units = unitBUS.getUnitByNameSearch(name);
        fillContent(units);
    }//GEN-LAST:event_txtSearchUnitActionPerformed


    private String unitIdEdit;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddUnit;
    private javax.swing.JButton btnEditUnit;
    private javax.swing.JButton btnExitModalAdd;
    private javax.swing.JButton btnExitModalEdit;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnOpenModalAddUnit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JDialog modalAddUnit;
    private javax.swing.JDialog modalEditUnit;
    private javax.swing.JPanel pnAll;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTextField txtNameUnitAdd;
    private javax.swing.JTextField txtNameUnitEdit;
    private javax.swing.JTextField txtSearchUnit;
    // End of variables declaration//GEN-END:variables
}
