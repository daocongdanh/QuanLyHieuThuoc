/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.manager;

import bus.PrescriptionBUS;
import bus.ProductBUS;
import bus.UnitDetailBUS;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dto.PrescriptionDTO;
import entity.Prescription;
import entity.PrescriptionDetail;
import entity.Product;
import entity.UnitDetail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import util.MessageDialog;
import util.ResizeImage;
import view.common.EachRowEditor;
import view.common.SpinnerEditor;
import view.common.TableActionCellEditorOneAction;
import view.common.TableActionCellRenderOneAction;
import view.common.TableActionEventOneAction;
import view.common.TableDesign;
import view.login.LoadApplication;

/**
 *
 * @author Hoang
 */
public class TABPrecription extends javax.swing.JPanel {

    /**
     * Creates new form TABPrecription
     */
    private TableDesign tableDesign;
    private TableDesign tableDesignAdd;
    private TableDesign tableDesignEdit;
    private final ProductBUS productBUS;
    private final UnitDetailBUS unitDetailBUS;
    private final PrescriptionBUS prescriptionBUS;
    private List<Product> listProductAdd;
    private List<Product> listProductEdit;
    private List<PrescriptionDetail> listPrescriptionDetailsEdit;

    public TABPrecription() {
        productBUS = LoadApplication.productBUS;
        unitDetailBUS = LoadApplication.unitDetailBUS;
        prescriptionBUS = LoadApplication.prescriptionBUS;
        initComponents();
        setUIManager();
        addIconFeature();
        fillTable();
        fillTableModalAdd();
        fillTableModalEdit();
    }

    private void addIconFeature() {
        btnAdd.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/addBtn.svg")), 35, 35));
        btnUpdate.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/editBtn.svg")), 35, 35));
        btnDelete.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/deleteBtn.svg")), 35, 35));
        btnImport.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/import.svg")), 35, 35));
        btnExport.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/export.svg")), 35, 35));
    }

    private void setUIManager() {
//        txtCusAddressAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Địa chỉ");
//        txtCusAddressEdit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Địa chỉ");
//        txtCusEmailAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
//        txtCusEmailEdit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
//        txtCusNameAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên khách hàng");
//        txtCusNameEdit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên khách hàng");
//        txtCusPhoneAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại");
//        txtCusPhoneEdit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại");
//        UIManager.put("Button.arc", 10);
    }

    private void fillTable() {
        String[] headers = {"Mã đơn thuốc", "Tên đơn thuốc"};
        List<Integer> tableWidths = Arrays.asList(150, 350);
        tableDesign = new TableDesign(headers, tableWidths);
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        fillAllPre();
    }

    private void fillAllPre() {
        List<Prescription> list = prescriptionBUS.getAllPrescriptions();
        fillContent(list);
    }

    private void fillContent(List<Prescription> prescriptions) {
        tableDesign.getModelTable().setRowCount(0);
        for (Prescription pre : prescriptions) {
            tableDesign.getModelTable().addRow(new Object[]{pre.getPrescriptionId(),
                pre.getName()});
        }
    }

    private void fillTableModalAdd() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Ghi chú", "Thao tác"};
        List<Integer> tableWidths = Arrays.asList(150, 350, 150, 120, 350, 130);
        tableDesignAdd = new TableDesign(headers, tableWidths, List.of(false, false, true, true, true, true));
        tableDesignAdd.setTableHeaderFontSize(13);
        scrollPanelAdd.setViewportView(tableDesignAdd.getTable());
    }

    private void fillTableModalEdit() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Ghi chú", "Thao tác"};
        List<Integer> tableWidths = Arrays.asList(150, 350, 150, 120, 350, 130);
        tableDesignEdit = new TableDesign(headers, tableWidths, List.of(false, false, true, true, true, true));
        tableDesignEdit.setTableHeaderFontSize(13);
        scrollPanelEdit.setViewportView(tableDesignEdit.getTable());
    }

    private void fillProductModalAddAndEdit(List<Product> products, TableDesign tableDesign) {
        DefaultTableModel tableModel = tableDesign.getModelTable();
        JTable table = tableDesign.getTable();

        Map<String, String> selectedUnitMap = new HashMap<>();
        Map<String, String> selectedQuantityMap = new HashMap<>();
        Map<String, String> descriptionMap = new HashMap<>();

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String productId = tableModel.getValueAt(row, 0).toString();
            Object selectedUnit = tableModel.getValueAt(row, 2);
            Object selectedQuantity = tableModel.getValueAt(row, 3);
            Object desValue = tableModel.getValueAt(row, 4);

            if (selectedUnit != null) {
                selectedUnitMap.put(productId, selectedUnit.toString());
            }
            if (selectedQuantity != null) {
                selectedQuantityMap.put(productId, selectedQuantity.toString());
            }
            if (desValue != null) {
                descriptionMap.put(productId, desValue.toString());
            }
        }

        tableModel.setRowCount(0);

        EachRowEditor rowEditorDvt = new EachRowEditor(table);
        EachRowEditor rowEditorSoLuong = new EachRowEditor(table);
        EachRowEditor rowEditorDes = new EachRowEditor(table);

        for (Product product : products) {
            tableModel.addRow(new Object[]{
                product.getProductId(), product.getName(), null, null, null, null
            });

            int rowIndex = tableModel.getRowCount() - 1;

            JComboBox<String> comboOnly = new JComboBox<>();
            List<UnitDetail> unitDetails = unitDetailBUS.getListUnitProduct(product);
            for (UnitDetail unitDetail : unitDetails) {
                comboOnly.addItem(unitDetail.getUnit().getName());
            }

            String selectedUnit = selectedUnitMap.get(product.getProductId());
            if (selectedUnit != null) {
                comboOnly.setSelectedItem(selectedUnit);
            } else {
                comboOnly.setSelectedIndex(0);
            }
            tableModel.setValueAt(comboOnly.getSelectedItem(), rowIndex, 2);

            JSpinner spinnerOnly = new JSpinner();
            String selectedQuantity = selectedQuantityMap.get(product.getProductId());
            if (selectedQuantity != null) {
                spinnerOnly.setValue(Integer.valueOf(selectedQuantity));
            } else {
                spinnerOnly.setValue(1);
            }
            tableModel.setValueAt(spinnerOnly.getValue(), rowIndex, 3);

            JTextField desText = new JTextField();
            String des = descriptionMap.get(product.getProductId());
            if (des != null) {
                desText.setText(des);
                tableModel.setValueAt(des, rowIndex, 4);
            } else {
                desText.setText("");
            }

            rowEditorDvt.setEditorAt(rowIndex, new DefaultCellEditor(comboOnly));
            rowEditorSoLuong.setEditorAt(rowIndex, new SpinnerEditor(spinnerOnly));
            rowEditorDes.setEditorAt(rowIndex, new DefaultCellEditor(desText));
        }

        table.getColumn("Đơn vị tính").setCellEditor(rowEditorDvt);
        table.getColumn("Số lượng").setCellEditor(rowEditorSoLuong);
        table.getColumn("Ghi chú").setCellEditor(rowEditorDes);
    }

    private void fillProductModalEditFirstTime(List<PrescriptionDetail> prescriptionDetails, TableDesign tableDesign) {
        DefaultTableModel tableModel = tableDesign.getModelTable();
        JTable table = tableDesign.getTable();

        Map<String, String> selectedUnitMap = new HashMap<>();
        Map<String, String> selectedQuantityMap = new HashMap<>();

        for (PrescriptionDetail prescriptionDetail : prescriptionDetails) {
            String productId = prescriptionDetail.getUnitDetail().getProduct().getProductId();
            String unitSelected = prescriptionDetail.getUnitDetail().getUnit().getName();
            Integer selectedQuantity = prescriptionDetail.getQuantity();

            selectedUnitMap.put(productId, unitSelected);
            selectedQuantityMap.put(productId, selectedQuantity.toString());
        }

        if (table.getCellEditor() != null) {
            table.getCellEditor().stopCellEditing();
        }
        tableModel.setRowCount(0);

        EachRowEditor rowEditorDvt = new EachRowEditor(table);
        EachRowEditor rowEditorSoLuong = new EachRowEditor(table);

        for (PrescriptionDetail prescriptionDetail : prescriptionDetails) {
            Product product = prescriptionDetail.getUnitDetail().getProduct();

            tableModel.addRow(new Object[]{
                product.getProductId(), product.getName(), null, null, prescriptionDetail.getDescription(), null
            });

            int rowIndex = tableModel.getRowCount() - 1;

            JComboBox<String> comboOnly = new JComboBox<>();
            JSpinner spinnerOnly = new JSpinner();

            List<UnitDetail> unitDetails = unitDetailBUS.getListUnitProduct(product);
            for (UnitDetail unitDetail : unitDetails) {
                comboOnly.addItem(unitDetail.getUnit().getName());
            }

            String selectedUnit = selectedUnitMap.get(product.getProductId());
            if (selectedUnit != null) {
                comboOnly.setSelectedItem(selectedUnit);
            } else {
                comboOnly.setSelectedIndex(0);
            }
            tableModel.setValueAt(comboOnly.getSelectedItem(), rowIndex, 2);

            String selectedQuantity = selectedQuantityMap.get(product.getProductId());
            if (selectedQuantity != null) {
                spinnerOnly.setValue(Integer.valueOf(selectedQuantity));
            } else {
                spinnerOnly.setValue(1);
            }
            tableModel.setValueAt(spinnerOnly.getValue(), rowIndex, 3);

            rowEditorDvt.setEditorAt(rowIndex, new DefaultCellEditor(comboOnly));
            rowEditorSoLuong.setEditorAt(rowIndex, new SpinnerEditor(spinnerOnly));
        }

        table.getColumn("Đơn vị tính").setCellEditor(rowEditorDvt);
        table.getColumn("Số lượng").setCellEditor(rowEditorSoLuong);
    }

    private void addEventBtnDeleteInTable(TableDesign tableDesign, List<Product> listProductOnTable) {
        JTable table = tableDesign.getTable();
        TableActionEventOneAction event = (int row) -> {
            int selectedRow = table.getSelectedRow();
            if (MessageDialog.confirm(null, "Bạn có chắc chắn xóa", "Xác nhận")) {
                if (table.getCellEditor() != null) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = tableDesign.getModelTable();
                model.removeRow(selectedRow);
                listProductOnTable.remove(row);
                table.revalidate();
                table.repaint();
            }
        };
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new TableActionCellRenderOneAction(2));
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new TableActionCellEditorOneAction(event, 2));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modalAddPrecription = new javax.swing.JDialog();
        pnAllDialogAdd = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtNamePrescription = new javax.swing.JTextField();
        txtSearchProduct = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnAddCustomer1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scrollPanelAdd = new javax.swing.JScrollPane();
        btnExitModalAdd = new javax.swing.JButton();
        btnAddPrecription = new javax.swing.JButton();
        modalEditPrecription = new javax.swing.JDialog();
        pnAllDialoEdit = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtNamePrescriptionEdit = new javax.swing.JTextField();
        txtSearchProductEdit = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnSearchProductModal = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        scrollPanelEdit = new javax.swing.JScrollPane();
        btnExitModalEdit = new javax.swing.JButton();
        btnEditPrescription = new javax.swing.JButton();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnOpenModalAddCus = new javax.swing.JButton();
        txtSearchCus = new javax.swing.JTextField();
        actionPanel = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();

        modalAddPrecription.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalAddPrecription.setTitle("Thêm đơn thuốc mẫu");
        modalAddPrecription.setMinimumSize(new java.awt.Dimension(1199, 630));
        modalAddPrecription.setModal(true);
        modalAddPrecription.setResizable(false);

        pnAllDialogAdd.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setText("Tên đơn thuốc mẫu");

        txtNamePrescription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSearchProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProductActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setText("Tìm sản phẩm");

        btnAddCustomer1.setBackground(new java.awt.Color(115, 165, 71));
        btnAddCustomer1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddCustomer1.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCustomer1.setText("Tìm kiếm");
        btnAddCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomer1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNamePrescription)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtSearchProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAddCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamePrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanelAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanelAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
        );

        btnExitModalAdd.setBackground(new java.awt.Color(92, 107, 192));
        btnExitModalAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitModalAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnExitModalAdd.setText("Hủy bỏ");
        btnExitModalAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitModalAddActionPerformed(evt);
            }
        });

        btnAddPrecription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddPrecription.setText("Xác nhận");
        btnAddPrecription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPrecriptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnAllDialogAddLayout = new javax.swing.GroupLayout(pnAllDialogAdd);
        pnAllDialogAdd.setLayout(pnAllDialogAddLayout);
        pnAllDialogAddLayout.setHorizontalGroup(
            pnAllDialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAllDialogAddLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(pnAllDialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnAllDialogAddLayout.createSequentialGroup()
                        .addComponent(btnAddPrecription, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExitModalAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pnAllDialogAddLayout.setVerticalGroup(
            pnAllDialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAllDialogAddLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnAllDialogAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPrecription, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExitModalAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalAddPrecriptionLayout = new javax.swing.GroupLayout(modalAddPrecription.getContentPane());
        modalAddPrecription.getContentPane().setLayout(modalAddPrecriptionLayout);
        modalAddPrecriptionLayout.setHorizontalGroup(
            modalAddPrecriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalAddPrecriptionLayout.createSequentialGroup()
                .addComponent(pnAllDialogAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modalAddPrecriptionLayout.setVerticalGroup(
            modalAddPrecriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalAddPrecriptionLayout.createSequentialGroup()
                .addComponent(pnAllDialogAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        modalEditPrecription.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalEditPrecription.setTitle("Sửa đơn thuốc mẫu");
        modalEditPrecription.setMinimumSize(new java.awt.Dimension(1199, 630));
        modalEditPrecription.setModal(true);
        modalEditPrecription.setResizable(false);

        pnAllDialoEdit.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setText("Tên đơn thuốc mẫu");

        txtNamePrescriptionEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSearchProductEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchProductEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProductEditActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setText("Tìm sản phẩm");

        btnSearchProductModal.setBackground(new java.awt.Color(115, 165, 71));
        btnSearchProductModal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchProductModal.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchProductModal.setText("Tìm kiếm");
        btnSearchProductModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchProductModalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNamePrescriptionEdit)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtSearchProductEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSearchProductModal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamePrescriptionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchProductEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchProductModal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanelEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanelEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
        );

        btnExitModalEdit.setBackground(new java.awt.Color(92, 107, 192));
        btnExitModalEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitModalEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnExitModalEdit.setText("Hủy bỏ");
        btnExitModalEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitModalEditActionPerformed(evt);
            }
        });

        btnEditPrescription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditPrescription.setText("Xác nhận");
        btnEditPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPrescriptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnAllDialoEditLayout = new javax.swing.GroupLayout(pnAllDialoEdit);
        pnAllDialoEdit.setLayout(pnAllDialoEditLayout);
        pnAllDialoEditLayout.setHorizontalGroup(
            pnAllDialoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAllDialoEditLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(pnAllDialoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnAllDialoEditLayout.createSequentialGroup()
                        .addComponent(btnEditPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExitModalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pnAllDialoEditLayout.setVerticalGroup(
            pnAllDialoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAllDialoEditLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnAllDialoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExitModalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalEditPrecriptionLayout = new javax.swing.GroupLayout(modalEditPrecription.getContentPane());
        modalEditPrecription.getContentPane().setLayout(modalEditPrecriptionLayout);
        modalEditPrecriptionLayout.setHorizontalGroup(
            modalEditPrecriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalEditPrecriptionLayout.createSequentialGroup()
                .addComponent(pnAllDialoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modalEditPrecriptionLayout.setVerticalGroup(
            modalEditPrecriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalEditPrecriptionLayout.createSequentialGroup()
                .addComponent(pnAllDialoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        pnAll.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
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

        btnOpenModalAddCus.setBackground(new java.awt.Color(115, 165, 71));
        btnOpenModalAddCus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOpenModalAddCus.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenModalAddCus.setText("Thêm khách hàng");
        jPanel6.add(btnOpenModalAddCus);

        txtSearchCus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchCus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchCusKeyReleased(evt);
            }
        });
        jPanel6.add(txtSearchCus);

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

        btnDelete.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnDelete.setText("XÓA");
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setFocusPainted(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setPreferredSize(new java.awt.Dimension(100, 90));
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actionPanel.add(btnDelete);

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

        btnExport.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnExport.setText("EXPORT");
        btnExport.setBorder(null);
        btnExport.setBorderPainted(false);
        btnExport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExport.setFocusPainted(false);
        btnExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExport.setPreferredSize(new java.awt.Dimension(100, 90));
        btnExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actionPanel.add(btnExport);

        headerPanel.add(actionPanel, java.awt.BorderLayout.WEST);

        pnAll.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1167, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
        );

        pnAll.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(pnAll);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchCusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchCusKeyReleased

    }//GEN-LAST:event_txtSearchCusKeyReleased

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

    }//GEN-LAST:event_btnImportActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        listProductAdd = new ArrayList<>();
        addEventBtnDeleteInTable(tableDesignAdd, listProductAdd);
        modalAddPrecription.setLocationRelativeTo(null);
        modalAddPrecription.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void searchAddPre() {
        String textTim = txtSearchProduct.getText();
        Product product = productBUS.searchProductBySDKOrId(textTim);
        if (product != null) {
            if (listProductAdd.contains(product)) {
                MessageDialog.info(null, "Sản phẩm đã được chọn!!!");
                return;
            }
            listProductAdd.add(product);
            fillProductModalAddAndEdit(listProductAdd, tableDesignAdd);

        } else {
            MessageDialog.info(null, "Sản phẩm không tồn tại !!!");
        }
    }
    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed
        searchAddPre();
    }//GEN-LAST:event_txtSearchProductActionPerformed

    private List<PrescriptionDTO> getPrescriptionDTOsFromTableModal(TableDesign tableDesign) {
        List<PrescriptionDTO> prescriptions = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tableDesign.getModelTable();

        for (int row = 0; row < model.getRowCount(); row++) {
            String productId = model.getValueAt(row, 0).toString();
            String unitName = model.getValueAt(row, 2).toString();
            int quantity = Integer.parseInt(model.getValueAt(row, 3).toString());
            String description = model.getValueAt(row, 4) != null ? model.getValueAt(row, 4).toString() : "";

            PrescriptionDTO prescription = new PrescriptionDTO(productId, unitName, quantity, description);
            prescriptions.add(prescription);
        }

        return prescriptions;
    }

    private void btnAddPrecriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPrecriptionActionPerformed
        // TODO add your handling code here:
        List<PrescriptionDTO> listPrecription = getPrescriptionDTOsFromTableModal(tableDesignAdd);
        String namePre = txtNamePrescription.getText().trim();

        if (namePre.equals("")) {
            MessageDialog.warring(null, "Tên đơn thuốc mẫu không được để trống !!!");
            return;
        }
        if (listPrecription.isEmpty()) {
            MessageDialog.warring(null, "Chưa chọn sản phẩm !!!");
            return;
        }
        Prescription prescription = new Prescription(null, namePre);

        if (prescriptionBUS.createPrescription(prescription, listPrecription)) {
            MessageDialog.info(null, "Thêm đơn thuốc mẫu thành công");
            deleteDataModalAdd();
            modalAddPrecription.dispose();
            fillAllPre();

        } else {
            MessageDialog.error(null, "Có lỗi xảy ra trong quá trình thêm");
        }
    }//GEN-LAST:event_btnAddPrecriptionActionPerformed

    private void deleteDataModalAdd() {
        if (tableDesignAdd.getTable().getCellEditor() != null) {
            tableDesignAdd.getTable().getCellEditor().stopCellEditing();
        }
        txtNamePrescription.setText("");
        txtSearchProduct.setText("");
        tableDesignAdd.getModelTable().setRowCount(0);
    }

    private void deleteDataModalEdit() {
        if (tableDesignEdit.getTable().getCellEditor() != null) {
            tableDesignEdit.getTable().getCellEditor().stopCellEditing();
        }
        txtNamePrescriptionEdit.setText("");
        txtSearchProductEdit.setText("");
        tableDesignEdit.getModelTable().setRowCount(0);
    }

    private void fillModalEdit(String prescriptionId) {
        List<PrescriptionDetail> listPreDetail = prescriptionBUS.getAllPrescripDetailsByPrescription(prescriptionId);
        listPrescriptionDetailsEdit = new ArrayList<>();
        for (PrescriptionDetail preDetail : listPreDetail) {
            listProductEdit.add(preDetail.getUnitDetail().getProduct());
            listPrescriptionDetailsEdit.add(preDetail);
        }
        fillProductModalEditFirstTime(listPrescriptionDetailsEdit, tableDesignEdit);
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int rowSelected = tableDesign.getTable().getSelectedRow();
        if (rowSelected != -1) {
            listProductEdit = new ArrayList<>();
            txtNamePrescriptionEdit.setText(tableDesign.getModelTable().getValueAt(rowSelected, 1).toString());

            addEventBtnDeleteInTable(tableDesignEdit, listProductEdit);

            prescriptionIdEdit = (String) tableDesign.getTable().getValueAt(rowSelected, 0);
            fillModalEdit(prescriptionIdEdit);

            modalEditPrecription.setLocationRelativeTo(null);
            modalEditPrecription.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void searchEditPre() {
        String textTim = txtSearchProductEdit.getText();
        Product product = productBUS.searchProductBySDKOrId(textTim);
        if (product != null) {
            if (listProductEdit.contains(product)) {
                MessageDialog.info(null, "Sản phẩm đã được chọn!!!");
                return;
            }
            listProductEdit.add(product);
            fillProductModalAddAndEdit(listProductEdit, tableDesignEdit);

        } else {
            MessageDialog.info(null, "Sản phẩm không tồn tại !!!");
        }
    }

    private void txtSearchProductEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductEditActionPerformed
        // TODO add your handling code here:
        searchEditPre();
    }//GEN-LAST:event_txtSearchProductEditActionPerformed

    private void btnEditPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPrescriptionActionPerformed
        // TODO add your handling code here:
        List<PrescriptionDTO> listPrecription = getPrescriptionDTOsFromTableModal(tableDesignEdit);
        String namePre = txtNamePrescriptionEdit.getText().trim();

        if (namePre.equals("")) {
            MessageDialog.warring(null, "Tên đơn thuốc mẫu không được để trống !!!");
            return;
        }
        if (listPrecription.isEmpty()) {
            MessageDialog.warring(null, "Chưa chọn sản phẩm !!!");
            return;
        }

        Prescription prescription = prescriptionBUS.getPrescriptionById(prescriptionIdEdit);
        prescription.setName(namePre);

        if (prescriptionBUS.editPrescription(prescription, listPrecription, listPrescriptionDetailsEdit)) {
            MessageDialog.info(null, "Sửa đơn thuốc mẫu thành công");
            deleteDataModalEdit();
            modalEditPrecription.dispose();
            fillAllPre();
        } else {
            MessageDialog.error(null, "Có lỗi xảy ra trong quá trình thêm");
        }
    }//GEN-LAST:event_btnEditPrescriptionActionPerformed

    private void btnSearchProductModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchProductModalActionPerformed
        searchEditPre();
    }//GEN-LAST:event_btnSearchProductModalActionPerformed

    private void btnAddCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomer1ActionPerformed
        searchAddPre();
    }//GEN-LAST:event_btnAddCustomer1ActionPerformed

    private void btnExitModalAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitModalAddActionPerformed
        deleteDataModalAdd();
        modalAddPrecription.dispose();
    }//GEN-LAST:event_btnExitModalAddActionPerformed

    private void btnExitModalEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitModalEditActionPerformed
        deleteDataModalEdit();
        modalEditPrecription.dispose();
    }//GEN-LAST:event_btnExitModalEditActionPerformed

    private String prescriptionIdEdit;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCustomer1;
    private javax.swing.JButton btnAddPrecription;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEditPrescription;
    private javax.swing.JButton btnExitModalAdd;
    private javax.swing.JButton btnExitModalEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnOpenModalAddCus;
    private javax.swing.JButton btnSearchProductModal;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JDialog modalAddPrecription;
    private javax.swing.JDialog modalEditPrecription;
    private javax.swing.JPanel pnAll;
    private javax.swing.JPanel pnAllDialoEdit;
    private javax.swing.JPanel pnAllDialogAdd;
    private javax.swing.JScrollPane scrollPanelAdd;
    private javax.swing.JScrollPane scrollPanelEdit;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTextField txtNamePrescription;
    private javax.swing.JTextField txtNamePrescriptionEdit;
    private javax.swing.JTextField txtSearchCus;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtSearchProductEdit;
    // End of variables declaration//GEN-END:variables
}
