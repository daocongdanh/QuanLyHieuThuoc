/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.manager;

import bus.ReturnOrderBUS;
import bus.ReturnOrderDetailBUS;
import bus.UnitBUS;
import enums.ReturnOrderDetailStatus;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import enums.ReturnOrderDetailStatus;
import gui.common.TableDesign;
import entity.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import util.*;
import gui.common.TableActionCellEditorOneAction;
import gui.common.TableActionCellEditorReturnManage;
import gui.common.TableActionCellRenderOneAction;
import gui.common.TableActionCellRenderReturnManage;
import gui.login.LoadApplication;

import gui.common.TableActionEventOneAction;
import gui.common.TableActionEventReturnManage;

import static gui.login.LoadApplication.*;

/**
 *
 * @author Hoang
 */
public class TABReturnOrder extends javax.swing.JPanel {

    private final ReturnOrderBUS returnOrderBUS;
    private final ReturnOrderDetailBUS returnOrderDetailBUS;
    private final UnitBUS unitBUS;
//    private final UnitDetailBUS unitDetailBUS;
    private TableDesign tableDesign;
    private TableDesign tableDesignView;
    private TableDesign tableDesignEdit;

    public TABReturnOrder() throws RemoteException {
        returnOrderBUS = LoadApplication.returnOrderBUS;
        returnOrderDetailBUS = LoadApplication.returnOrderDetailBUS;
        unitBUS = LoadApplication.unitBUS;
//        unitDetailBUS = LoadApplication.unitDetailBUS;
        initComponents();
        setUIManager();
        fillTable();
        fillTableModal();
    }

    private void setUIManager() {
        txtOrderIdSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã hóa đơn");
        txtEmpName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên nhân viên");
        UIManager.put("Button.arc", 10);
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
        btnView.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/View.svg")), 35, 35));
    }

    private void fillTable() throws RemoteException {
        String[] headers = {"Mã trả hàng", "Thời gian", "Nhân viên", "Tổng tiền",  "Trạng thái", "Thao tác"};
        List<Integer> tableWidths = Arrays.asList(200, 150, 200, 150, 150, 150);
        tableDesign = new TableDesign(headers, tableWidths, List.of(false, false, false, false, false, true));
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        addEventBtnViewInTable(tableDesign);
        List<ReturnOrder> returnOrders = returnOrderBUS.getListReturnOrdersByStatus(false);
        fillContent(returnOrders);
    }

    private void fillTableModal() {
        String[] headers = {"Mã hàng", "Tên hàng", "Đơn vị tính", "Số lượng", "Giá trả hàng","Lý do trả", "Lý do xử lý" ,"Thao tác"};
        List<Integer> tableWidths = Arrays.asList(85, 200, 70 , 70, 140,200,200, 130);
        tableDesignView = new TableDesign(headers, tableWidths, List.of(false, false,false, false, false,false , true, true));
        scrollTableView.setViewportView(tableDesignView.getTable());
        scrollTableView.setBorder(BorderFactory.createEmptyBorder(15, 10, 20, 10));
        tableDesignView.setTableHeaderFontSize(14);
    }

    private void fillDataTableModalView(List<ReturnOrderDetail> returnOrderDetails) {
        if ( tableDesignView.getTable().getCellEditor() != null){
            tableDesignView.getTable().getCellEditor().stopCellEditing();
        }
        tableDesignView.getModelTable().setRowCount(0);
        for (ReturnOrderDetail returnOrderDetail : returnOrderDetails) {
            if (returnOrderDetail.getReturnOrderDetailStatus().equals(ReturnOrderDetailStatus.PENDING)) {
                tableDesignView.getModelTable().addRow(new Object[]{
                        returnOrderDetail.getProduct().getProductId(),
                        returnOrderDetail.getProduct().getName(),
                        returnOrderDetail.getProduct().getUnit().getName(),
                        returnOrderDetail.getQuantity(),
                        FormatNumber.formatToVND(returnOrderDetail.getLineTotal()),
                        returnOrderDetail.getReason(),
                        returnOrderDetail.getFinalReason(),
                        null
                });
            } else if ( returnOrderDetail.getReturnOrderDetailStatus().equals(ReturnOrderDetailStatus.RETURNED)){
                tableDesignView.getModelTable().addRow(new Object[]{
                        returnOrderDetail.getProduct().getProductId(),
                        returnOrderDetail.getProduct().getName(),
                        returnOrderDetail.getProduct().getUnit().getName(),
                        returnOrderDetail.getQuantity(),
                        FormatNumber.formatToVND(returnOrderDetail.getLineTotal()),
                        returnOrderDetail.getReason(),
                        returnOrderDetail.getFinalReason(),
                        "Đã Thêm Lại"
                });
            } else if ( returnOrderDetail.getReturnOrderDetailStatus().equals(ReturnOrderDetailStatus.DAMAGED)){
                tableDesignView.getModelTable().addRow(new Object[]{
                        returnOrderDetail.getProduct().getProductId(),
                        returnOrderDetail.getProduct().getName(),
                         returnOrderDetail.getProduct().getUnit().getName(),
                        returnOrderDetail.getQuantity(),
                        FormatNumber.formatToVND(returnOrderDetail.getLineTotal()),
                        returnOrderDetail.getReason(),
                        returnOrderDetail.getFinalReason(),
                        "Đã Hủy"
                });
            }
        }
        addButtonOnTableInModal();
    }

    private void addButtonOnTableInModal( ) {
        JTable table = tableDesignView.getTable();
        TableActionEventReturnManage event = new TableActionEventReturnManage() {
            @Override
            public void onReturned(int row) throws RemoteException {
                String returnOrderId = txtReturnOrderId.getText();
                String productId = (String) table.getValueAt(row,0);
                String productName = (String) table.getValueAt(row,1);
                if ( MessageDialog.confirm(null, "Bạn muốn thêm lại sản phẩm " + productName + " vào hệ thống", "Xác nhận") ){
                    ReturnOrderDetail returnOrderDetail = returnOrderDetailBUS.findByReturnOrderIdAndProductId(returnOrderId,productId);
                    returnOrderDetail.setFinalReason((String) table.getValueAt(row,6));
                    if ( returnOrderDetailBUS.updateReturnOrderDetailToReturn(returnOrderDetail) ){
                        MessageDialog.info(null, "Sản phẩm đã thêm lại hệ thống.");
                        fillModal(returnOrderBUS.findById(txtReturnOrderId.getText().trim()));
                        searchByOption();
                    }
                    else {
                        MessageDialog.info(null, "Lỗi hệ thống.");
                    }
                }

            }

            @Override
            public void onDamaged(int row) throws RemoteException {
                String returnOrderId = txtReturnOrderId.getText();
                String productId = (String) table.getValueAt(row,0);
                String productName = (String) table.getValueAt(row,1);
                if ( MessageDialog.confirm(null, "Bạn muốn loại bỏ sản phẩm " + productName, "Xác nhận") ){
                    ReturnOrderDetail returnOrderDetail = returnOrderDetailBUS.findByReturnOrderIdAndProductId(returnOrderId,productId);
                    returnOrderDetail.setFinalReason((String) table.getValueAt(row,6));
                    if ( returnOrderDetailBUS.updateReturnOrderDetailToDamage(returnOrderDetail) ){
                        MessageDialog.info(null, "Sản phẩm loại bỏ.");
                        fillModal(returnOrderBUS.findById(txtReturnOrderId.getText().trim()));
                        searchByOption();
                    }
                    else {
                        MessageDialog.info(null, "Lỗi hệ thống.");
                    }
                }
            }
        };
        for (int row = 0; row < table.getRowCount(); row++) {
//            Object value = table.getValueAt(row, table.getColumnCount() - 1);
                table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new TableActionCellRenderReturnManage());
                table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new TableActionCellEditorReturnManage(event));
        }
        table.revalidate();
        table.repaint();
    }

    private void fillModal(ReturnOrder returnOrder) {
        txtReturnOrderId.setText(returnOrder.getReturnOrderId());
        txtTimeReturnOrder.setText(FormatDate.formatDate(returnOrder.getOrderDate()));
        txtOrderId.setText(returnOrder.getOrder().getOrderId());
        txtEmp.setText(returnOrder.getEmployee().getName());
        txtStatus.setText(returnOrder.isStatus() ? "Đã xử lý" : "Chờ xử lý");
        fillDataTableModalView(returnOrder.getReturnOrderDetails());
    }

    private void addEventBtnViewInTable(TableDesign tableDesign) {
        JTable table = tableDesign.getTable();
        TableActionEventOneAction event = (int row) -> {
            int selectedRow = table.getSelectedRow();
            String returnOrderId = (String) table.getValueAt(selectedRow, 0);
            ReturnOrder returnOrder = returnOrderBUS.findById(returnOrderId);
            fillModal(returnOrder);
            modalReturnOrderView.setLocationRelativeTo(null);
            modalReturnOrderView.setVisible(true);
        };
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new TableActionCellRenderOneAction(1));
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new TableActionCellEditorOneAction(event, 1));
    }

    private void fillContent(List<ReturnOrder> returnOrders) {
        if (tableDesign.getTable().getCellEditor() != null) {
            tableDesign.getTable().getCellEditor().stopCellEditing();
        }
        tableDesign.getModelTable().setRowCount(0);
        for (ReturnOrder returnOrder : returnOrders) {
            String status = returnOrder.isStatus() ? "Đã xử lý" : "Chờ xử lý";
            tableDesign.getModelTable().addRow(new Object[]{returnOrder.getReturnOrderId(),
                FormatDate.formatDate(returnOrder.getOrderDate()), returnOrder.getEmployee().getName(),
                FormatNumber.formatToVND(returnOrder.getTotalPrice()), status, null});
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

        modalReturnOrderView = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtReturnOrderId = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTimeReturnOrder = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtEmp = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        scrollTableView = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        modalReturnOrderDetail = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        scrollTableDetail = new javax.swing.JScrollPane();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtOrderIdSearch = new javax.swing.JTextField();
        jDateTo = new com.toedter.calendar.JDateChooser();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtOrder = new javax.swing.JButton();
        optionStatus = new javax.swing.JComboBox<>();
        txtEmpName = new javax.swing.JTextField();
        btnView = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();

        modalReturnOrderView.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalReturnOrderView.setTitle("Xem đơn trả");
        modalReturnOrderView.setMinimumSize(new java.awt.Dimension(1311, 700));
        modalReturnOrderView.setModal(true);
        modalReturnOrderView.getContentPane().setLayout(new javax.swing.BoxLayout(modalReturnOrderView.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1311, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(1311, 700));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Mã phiếu trả");

        txtReturnOrderId.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtReturnOrderId.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReturnOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtReturnOrderId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Thời gian tạo");

        txtTimeReturnOrder.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTimeReturnOrder.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimeReturnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtTimeReturnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Mã hóa đơn");

        txtOrderId.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtOrderId.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderId, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtOrderId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Trạng thái");

        txtStatus.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtStatus.setText("jLabel1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Người tạo");

        txtEmp.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtEmp.setText("jLabel1");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(232, 232, 232)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTableView, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTableView, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Xác nhận");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                        .addGap(141, 141, 141)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(480, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        modalReturnOrderView.getContentPane().add(jPanel1);

        modalReturnOrderDetail.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalReturnOrderDetail.setTitle("Chi tiết phiếu trả hàng");
        modalReturnOrderDetail.setMinimumSize(new java.awt.Dimension(1311, 700));
        modalReturnOrderDetail.setModal(true);
        modalReturnOrderDetail.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));
        jPanel8.add(scrollTableDetail);

        javax.swing.GroupLayout modalReturnOrderDetailLayout = new javax.swing.GroupLayout(modalReturnOrderDetail.getContentPane());
        modalReturnOrderDetail.getContentPane().setLayout(modalReturnOrderDetailLayout);
        modalReturnOrderDetailLayout.setHorizontalGroup(
            modalReturnOrderDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modalReturnOrderDetailLayout.setVerticalGroup(
            modalReturnOrderDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 204, 0));
        setMinimumSize(new java.awt.Dimension(1226, 278));
        setPreferredSize(new java.awt.Dimension(1226, 278));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        pnAll.setBackground(new java.awt.Color(204, 255, 204));
        pnAll.setPreferredSize(new java.awt.Dimension(1226, 278));
        pnAll.setLayout(new javax.swing.BoxLayout(pnAll, javax.swing.BoxLayout.Y_AXIS));

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        headerPanel.setMaximumSize(new java.awt.Dimension(2147483647, 278));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(0, 130));
        jPanel5.setPreferredSize(new java.awt.Dimension(1190, 130));

        btnSearch.setBackground(new java.awt.Color(115, 165, 71));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setMaximumSize(new java.awt.Dimension(150, 40));
        btnSearch.setMinimumSize(new java.awt.Dimension(150, 40));
        btnSearch.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSearchActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        txtOrderIdSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtOrderIdSearch.setMinimumSize(new java.awt.Dimension(300, 40));
        txtOrderIdSearch.setPreferredSize(new java.awt.Dimension(300, 40));

        jDateTo.setBackground(new java.awt.Color(255, 255, 255));
        jDateTo.setDateFormatString("dd/MM/yyyy");
        jDateTo.setFocusable(false);
        jDateTo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateTo.setPreferredSize(new java.awt.Dimension(100, 22));

        jDateFrom.setBackground(new java.awt.Color(255, 255, 255));
        jDateFrom.setDateFormatString("dd/MM/yyyy");
        jDateFrom.setFocusable(false);
        jDateFrom.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateFrom.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("-->");

        txtOrder.setBackground(new java.awt.Color(115, 165, 71));
        txtOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtOrder.setForeground(new java.awt.Color(255, 255, 255));
        txtOrder.setText("Xuất excel");
        txtOrder.setMaximumSize(new java.awt.Dimension(150, 40));
        txtOrder.setMinimumSize(new java.awt.Dimension(150, 40));
        txtOrder.setPreferredSize(new java.awt.Dimension(150, 40));
        txtOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderActionPerformed(evt);
            }
        });

        optionStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        optionStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ xử lý", "Đã xử lý" }));
        optionStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionStatusActionPerformed(evt);
            }
        });

        txtEmpName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmpName.setMinimumSize(new java.awt.Dimension(300, 40));
        txtEmpName.setPreferredSize(new java.awt.Dimension(300, 40));

        btnView.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnView.setText("XEM CHI TIẾT");
        btnView.setBorder(null);
        btnView.setBorderPainted(false);
        btnView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnView.setFocusPainted(false);
        btnView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnView.setPreferredSize(new java.awt.Dimension(100, 90));
        btnView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnViewActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrderIdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(optionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOrderIdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(optionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        headerPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        pnAll.add(headerPanel);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(1226, 174));
        jPanel4.setPreferredSize(new java.awt.Dimension(1226, 174));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));
        jPanel4.add(scrollTable);

        pnAll.add(jPanel4);

        add(pnAll);
    }// </editor-fold>//GEN-END:initComponents

    private void searchByOption() throws RemoteException {
        java.util.Date date1 = jDateFrom.getDate();
        java.util.Date date2 = jDateTo.getDate();

        LocalDate localDateStart = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateEnd = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDateTime start = localDateStart.atStartOfDay(); // 00:00:00
        LocalDateTime end = localDateEnd.atTime(23, 59, 59, 999999999); // 23:59:59.999999999

        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        String orderIdSearch = txtOrderIdSearch.getText().trim();
        String nameEmp = txtEmpName.getText().trim();
        List<ReturnOrder> returnOrders;
        returnOrders = switch (optionStatus.getSelectedIndex()) {
            case 0 ->
                    returnOrderBUS.search(start, end, nameEmp, orderIdSearch, null);
            case 1 ->
                    returnOrderBUS.search(start, end, nameEmp, orderIdSearch, false);
            default ->
                    returnOrderBUS.search(start, end, nameEmp, orderIdSearch, true);
        };

        fillContent(returnOrders);
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchByOption();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderActionPerformed
        // TODO add your handling code here:
        JTableExporter.exportJTableToExcel(tableDesign.getTable());
    }//GEN-LAST:event_txtOrderActionPerformed

    private void optionStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionStatusActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modalReturnOrderView.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnViewActionPerformed
        JTable table = tableDesign.getTable();
        int selectedRow = table.getSelectedRow();
        if(selectedRow < 0) {
            MessageDialog.warning(null, "Hãy chọn phiếu trả hàng cần xem chi tiết!");
        } else {
            String maPT = (String) table.getValueAt(selectedRow, 0);

            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng trả", "Đơn giá", "Tổng giá trị", "Lý do trả", "Trạng thái", "Lý do nhập lại/hủy"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200, 200, 150, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            ReturnOrder ro = returnOrderBUS.findById(maPT);
            List<ReturnOrderDetail> returnOrderDetails = returnOrderDetailBUS.getListReturnOrderDetailsByReturnOrder(ro);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (ReturnOrderDetail returnOrderDetail : returnOrderDetails) {
                String productId = returnOrderDetail.getProduct().getProductId();
                String productName = returnOrderDetail.getProduct().getName();
                String unitName = returnOrderDetail.getProduct().getUnit().getName();
                int quantity = returnOrderDetail.getQuantity();
                double price = returnOrderDetail.getPrice()*1.1;
                double lineTotal = returnOrderDetail.getLineTotal();
                String lyDoTra = returnOrderDetail.getReason();
                String trangThai = "Đang chờ"; 
                if(returnOrderDetail.getReturnOrderDetailStatus() == ReturnOrderDetailStatus.DAMAGED) trangThai = "Xuất hủy";
                if(returnOrderDetail.getReturnOrderDetailStatus() == ReturnOrderDetailStatus.RETURNED) trangThai = "Bán tiếp";
                String fR = returnOrderDetail.getFinalReason();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[5] = (double) existingData[5] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, lineTotal, lyDoTra, trangThai, fR});
                }
            }

            // Xóa dữ liệu cũ trong bảng
            tableDetail.getModelTable().setRowCount(0);

            // Thêm các sản phẩm đã cộng dồn vào bảng
            for (Object[] productData : productMap.values()) {
                productData[4] = FormatNumber.formatToVND((double) productData[4]);
                productData[5] = FormatNumber.formatToVND((double) productData[5]);
                tableDetail.getModelTable().addRow(productData);
            }

            modalReturnOrderDetail.setLocationRelativeTo(null);
            modalReturnOrderDetail.setVisible(true);
        }
    }//GEN-LAST:event_btnViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JDialog modalReturnOrderDetail;
    private javax.swing.JDialog modalReturnOrderView;
    private javax.swing.JComboBox<String> optionStatus;
    private javax.swing.JPanel pnAll;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JScrollPane scrollTableDetail;
    private javax.swing.JScrollPane scrollTableView;
    private javax.swing.JLabel txtEmp;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JButton txtOrder;
    private javax.swing.JLabel txtOrderId;
    private javax.swing.JTextField txtOrderIdSearch;
    private javax.swing.JLabel txtReturnOrderId;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtTimeReturnOrder;
    // End of variables declaration//GEN-END:variables

}
