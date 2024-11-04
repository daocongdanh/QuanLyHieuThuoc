/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff;

import bus.OrderBUS;
import bus.PurchaseOrderBUS;
import bus.ReturnOrderBUS;
import bus.DamageItemBUS;
import bus.OrderDetailBUS;
import bus.PurchaseOrderDetailBUS;
import bus.ReturnOrderDetailBUS;
import bus.DamageItemDetailBUS;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import view.common.TableDesign;
import entity.*;
import enums.ReturnOrderDetailStatus;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.UIManager;
import util.*;
import view.login.LoadApplication;
import util.CurrentEmployee;

/**
 *
 * @author daoducdanh
 */
public class TABIndividualReport extends javax.swing.JPanel {

    private final OrderBUS orderBUS;
    private final PurchaseOrderBUS purchaseOrderBUS;
    private final ReturnOrderBUS returnOrderBUS;
    private final DamageItemBUS damageItemBUS;
    private final OrderDetailBUS orderDetailBUS;
    private final PurchaseOrderDetailBUS purchaseOrderDetailBUS;
    private final ReturnOrderDetailBUS returnOrderDetailBUS;
    private final DamageItemDetailBUS damageItemDetailBUS;
    private TableDesign tableDesign;

    public TABIndividualReport() {
        orderBUS = LoadApplication.orderBUS;
        purchaseOrderBUS = LoadApplication.purchaseOrderBUS;
        returnOrderBUS = LoadApplication.returnOrderBUS;
        damageItemBUS = LoadApplication.damageItemBUS;
        orderDetailBUS = LoadApplication.orderDetailBUS;
        purchaseOrderDetailBUS = LoadApplication.purchaseOrderDetailBUS;
        returnOrderDetailBUS = LoadApplication.returnOrderDetailBUS;
        damageItemDetailBUS = LoadApplication.damageItemDetailBUS;
        initComponents();
        setUIManager();
        fillTable();
    }

    private void setUIManager() {
        UIManager.put("Button.arc", 10);
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
        btnView.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/View.svg")), 35, 35));
    }

    private void fillTable() {
        String[] headers = {"Mã phiếu", "Ngày tạo", "Loại phiếu", "Giá trị"};
        List<Integer> tableWidths = Arrays.asList(200, 200, 200, 200);
        tableDesign = new TableDesign(headers, tableWidths);
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        List<Order> orders = orderBUS.getOrderByDateAndEmp(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59, 9999999), CurrentEmployee.getEmployee().getEmployeeId());
        List<PurchaseOrder> purchaseOrders = purchaseOrderBUS.getByDateAndEmp(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59, 9999999), CurrentEmployee.getEmployee().getEmployeeId());
        List<ReturnOrder> returnOrders = returnOrderBUS.getByDateAndEmp(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59, 9999999), CurrentEmployee.getEmployee().getEmployeeId());
        List<DamageItem> damageItems = damageItemBUS.getByDateAndEmp(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59, 9999999), CurrentEmployee.getEmployee().getEmployeeId());
        fillContent(orders, purchaseOrders, returnOrders, damageItems);
    }

    private void fillContent(List<Order> orders, List<PurchaseOrder> purchaseOrders, List<ReturnOrder> returnOrders, List<DamageItem> damageItems) {
        List<Object> objects = new ArrayList<>();
        objects.addAll(orders);
        objects.addAll(purchaseOrders);
        objects.addAll(returnOrders);
        objects.addAll(damageItems);

        objects.sort((o1, o2) -> {
            LocalDateTime date1 = getOrderDate(o1);
            LocalDateTime date2 = getOrderDate(o2);
            return date2.compareTo(date1);
        });
        int countHD = 0, countPNH = 0, countPTH = 0, countPXH = 0;
        double tongDoanhThu = 0.0, tongBan = 0.0, tongNhap = 0.0, tongTra = 0.0, tongHuy = 0.0;
        tableDesign.getModelTable().setRowCount(0);
        for (Order order : orders) {
            countHD++;
            tongBan += order.getTotalPrice();
            tongDoanhThu += order.getTotalPrice();
        }
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            countPNH++;
            tongNhap -= purchaseOrder.getTotalPrice();
            tongDoanhThu -= purchaseOrder.getTotalPrice();
        }
        for (ReturnOrder returnOrder : returnOrders) {
            countPTH++;
            tongTra -= returnOrder.getTotalPrice();
            tongDoanhThu -= returnOrder.getTotalPrice();
        }
        for (DamageItem damageItem : damageItems) {
            countPXH++;
            tongHuy -= damageItem.getTotalPrice();
            tongDoanhThu -= damageItem.getTotalPrice();
        }

        objects.forEach(obj -> {
            if (obj instanceof Order order) {
                tableDesign.getModelTable().addRow(new Object[]{order.getOrderId(), FormatDate.formatDate(order.getOrderDate()),
                    "Hóa đơn bán hàng", FormatNumber.formatToVND(order.getTotalPrice())});
            } else if (obj instanceof ReturnOrder returnOrder) {
                tableDesign.getModelTable().addRow(new Object[]{returnOrder.getReturnOrderId(), FormatDate.formatDate(returnOrder.getOrderDate()),
                    "Phiếu trả hàng", FormatNumber.formatToVND(returnOrder.getTotalPrice())});
            } else if (obj instanceof PurchaseOrder purchaseOrder) {
                tableDesign.getModelTable().addRow(new Object[]{purchaseOrder.getPurchaseOrderId(), FormatDate.formatDate(purchaseOrder.getOrderDate()),
                    "Phiếu nhập hàng", FormatNumber.formatToVND(purchaseOrder.getTotalPrice())});
            } else if (obj instanceof DamageItem damageItem) {
                tableDesign.getModelTable().addRow(new Object[]{damageItem.getDamageItemId(), FormatDate.formatDate(damageItem.getOrderDate()),
                    "Phiếu xuất hủy", FormatNumber.formatToVND(damageItem.getTotalPrice())});
            }
        });

        String[] headers = {"Loại phiếu", "Số lượng", "Tổng giá trị"};
        List<Integer> tableWidths = Arrays.asList(200, 150, 200);
        TableDesign table = new TableDesign(headers, tableWidths);
        scrollDoanhSo.setViewportView(table.getTable());
        scrollDoanhSo.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        table.getModelTable().setRowCount(0);
        table.getModelTable().addRow(new Object[]{"Hóa đơn bán hàng", countHD, FormatNumber.formatToVND(tongBan)});
        table.getModelTable().addRow(new Object[]{"Phiếu nhập hàng", countPNH, FormatNumber.formatToVND(tongNhap)});
        table.getModelTable().addRow(new Object[]{"Phiếu trả hàng", countPTH, FormatNumber.formatToVND(tongTra)});
        table.getModelTable().addRow(new Object[]{"Phiếu xuất hủy", countPXH, FormatNumber.formatToVND(tongHuy)});
        
        txtTongDoanhThu.setText(FormatNumber.formatToVND(tongDoanhThu));
    }

    private LocalDateTime getOrderDate(Object obj) {
        if (obj instanceof Order order) {
            return order.getOrderDate();
        } else if (obj instanceof ReturnOrder returnOrder) {
            return returnOrder.getOrderDate();
        } else if (obj instanceof PurchaseOrder purchaseOrder) {
            return purchaseOrder.getOrderDate();
        } else if (obj instanceof DamageItem damageItem) {
            return damageItem.getOrderDate();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modalDetail = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        scrollTableDetail = new javax.swing.JScrollPane();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        jDateTo = new com.toedter.calendar.JDateChooser();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnView = new javax.swing.JButton();
        comboboxType = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        scrollDoanhSo = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        lblTongDoanhThu = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JLabel();

        modalDetail.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalDetail.setTitle("Chi tiết phiếu nhập hàng");
        modalDetail.setMinimumSize(new java.awt.Dimension(1311, 700));
        modalDetail.setModal(true);
        modalDetail.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));
        jPanel3.add(scrollTableDetail);

        javax.swing.GroupLayout modalDetailLayout = new javax.swing.GroupLayout(modalDetail.getContentPane());
        modalDetail.getContentPane().setLayout(modalDetailLayout);
        modalDetailLayout.setHorizontalGroup(
            modalDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modalDetailLayout.setVerticalGroup(
            modalDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 204, 0));
        setMinimumSize(new java.awt.Dimension(1226, 278));
        setPreferredSize(new java.awt.Dimension(1226, 278));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        pnAll.setBackground(new java.awt.Color(255, 255, 255));
        pnAll.setPreferredSize(new java.awt.Dimension(1226, 278));
        pnAll.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        headerPanel.setMinimumSize(new java.awt.Dimension(1190, 104));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(590, 100));

        btnSearch.setBackground(new java.awt.Color(115, 165, 71));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tra cứu");
        btnSearch.setMaximumSize(new java.awt.Dimension(150, 40));
        btnSearch.setMinimumSize(new java.awt.Dimension(150, 40));
        btnSearch.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

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
                btnViewActionPerformed(evt);
            }
        });

        comboboxType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboboxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hóa đơn bán hàng", "Phiếu nhập hàng", "Phiếu trả hàng", "Phiếu xuất hủy" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(comboboxType, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboboxType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        headerPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(1226, 174));
        jPanel4.setPreferredSize(new java.awt.Dimension(1226, 174));
        jPanel4.setLayout(new java.awt.BorderLayout());

        scrollTable.setMinimumSize(new java.awt.Dimension(1226, 140));
        scrollTable.setPreferredSize(new java.awt.Dimension(1226, 140));
        jPanel4.add(scrollTable, java.awt.BorderLayout.CENTER);

        jPanel1.setMaximumSize(new java.awt.Dimension(1416, 370));
        jPanel1.setMinimumSize(new java.awt.Dimension(1416, 370));
        jPanel1.setPreferredSize(new java.awt.Dimension(1416, 370));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setMaximumSize(new java.awt.Dimension(1416, 300));
        jPanel2.setMinimumSize(new java.awt.Dimension(1416, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(1416, 300));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel2.add(scrollDoanhSo);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 20, 20);
        flowLayout1.setAlignOnBaseline(true);
        jPanel6.setLayout(flowLayout1);

        lblTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        lblTongDoanhThu.setText("Tổng doanh thu:");
        jPanel6.add(lblTongDoanhThu);

        txtTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongDoanhThu.setForeground(new java.awt.Color(255, 0, 0));
        txtTongDoanhThu.setText("0 đ");
        jPanel6.add(txtTongDoanhThu);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_START);
        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pnAll.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(pnAll);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        java.util.Date date1 = jDateFrom.getDate();
        java.util.Date date2 = jDateTo.getDate();

        if (date1 == null) {
            MessageDialog.warning(null, "Ngày bắt đầu không hợp lệ");
            return;
        }

        if (date2 == null) {
            MessageDialog.warning(null, "Ngày kết thúc không hợp lệ");
            return;
        }

        LocalDate starts = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ends = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDateTime start = starts.atStartOfDay();
        LocalDateTime end = ends.atTime(23, 59, 59, 9999999);

        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        
        if(comboboxType.getSelectedIndex() == 0) {
            List<Order> orders = orderBUS.getOrderByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            List<PurchaseOrder> purchaseOrders = purchaseOrderBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            List<ReturnOrder> returnOrders = returnOrderBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            List<DamageItem> damageItems = damageItemBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            fillContent(orders, purchaseOrders, returnOrders, damageItems);
        }
        
        if(comboboxType.getSelectedIndex() == 1) {
            List<Order> orders = orderBUS.getOrderByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            List<PurchaseOrder> purchaseOrders = new ArrayList<>();
            List<ReturnOrder> returnOrders = new ArrayList<>();
            List<DamageItem> damageItems = new ArrayList<>();
            fillContent(orders, purchaseOrders, returnOrders, damageItems);
        }
        
        if(comboboxType.getSelectedIndex() == 2) {
            List<Order> orders = new ArrayList<>();
            List<PurchaseOrder> purchaseOrders = purchaseOrderBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            List<ReturnOrder> returnOrders = new ArrayList<>();
            List<DamageItem> damageItems = new ArrayList<>();
            fillContent(orders, purchaseOrders, returnOrders, damageItems);
        }
        
        if(comboboxType.getSelectedIndex() == 3) {
            List<Order> orders = new ArrayList<>();
            List<PurchaseOrder> purchaseOrders = new ArrayList<>();
            List<ReturnOrder> returnOrders = returnOrderBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            List<DamageItem> damageItems = new ArrayList<>();
            fillContent(orders, purchaseOrders, returnOrders, damageItems);
        }
        
        if(comboboxType.getSelectedIndex() == 4) {
            List<Order> orders = new ArrayList<>();
            List<PurchaseOrder> purchaseOrders = new ArrayList<>();
            List<ReturnOrder> returnOrders = new ArrayList<>();
            List<DamageItem> damageItems = damageItemBUS.getByDateAndEmp(start, end, CurrentEmployee.getEmployee().getEmployeeId());
            fillContent(orders, purchaseOrders, returnOrders, damageItems);
        }
        
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        JTable table = tableDesign.getTable();
        int selectedRow = table.getSelectedRow();
        if(selectedRow < 0) {
            MessageDialog.warning(null, "Hãy chọn phiếu cần xem chi tiết!");
            return;
        }
        String loaiPhieu = (String) table.getValueAt(selectedRow, 2);
        String maPhieu = (String) table.getValueAt(selectedRow, 0);
        
        if(loaiPhieu.equalsIgnoreCase("Hóa đơn bán hàng")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Đơn giá", "Khuyến mãi", "Tổng giá trị"};
            List<Integer> tableWidths = Arrays.asList(170, 200, 150, 150, 200, 170, 150);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            Order o = orderBUS.findById(maPhieu);
            List<OrderDetail> orderDetails = orderDetailBUS.getListOrderDetailByOrder(o);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (OrderDetail orderDetail : orderDetails) {
                String productId = orderDetail.getBatch().getProduct().getProductId();
                String productName = orderDetail.getBatch().getProduct().getName();
                String unitName = orderDetail.getBatch().getProduct().getUnit().getName();               
                int quantity = orderDetail.getQuantity();
                double price = orderDetail.getPrice()*1.1;
                double km = (orderDetail.getDiscount()*100);
                double lineTotal = orderDetail.getLineTotal();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[6] = (double) existingData[6] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, (int)km + "%", lineTotal});
                }
            }

            // Xóa dữ liệu cũ trong bảng
            tableDetail.getModelTable().setRowCount(0);

            // Thêm các sản phẩm đã cộng dồn vào bảng
            for (Object[] productData : productMap.values()) {
                productData[4] = FormatNumber.formatToVND((double) productData[4]);
                productData[6] = FormatNumber.formatToVND((double) productData[6]);
                tableDetail.getModelTable().addRow(productData);
            }
        }
        
        if(loaiPhieu.equalsIgnoreCase("Phiếu trả hàng")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng trả", "Đơn giá", "Tổng giá trị", "Lý do trả", "Trạng thái", "Lý do nhập lại/hủy"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200, 200, 150, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            ReturnOrder ro = returnOrderBUS.findById(maPhieu);
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
        }
        
        if(loaiPhieu.equalsIgnoreCase("Phiếu nhập hàng")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập", "Tổng giá trị"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            PurchaseOrder po = purchaseOrderBUS.getByID(maPhieu);
            List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailBUS.getListPurchaseOrderDetailsByPurchaseOrder(po);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
                String productId = purchaseOrderDetail.getBatch().getProduct().getProductId();
                String productName = purchaseOrderDetail.getBatch().getProduct().getName();
                String unitName = purchaseOrderDetail.getBatch().getProduct().getUnit().getName();
                int quantity = purchaseOrderDetail.getQuantity();
                double price = purchaseOrderDetail.getPrice()*1.1;
                double lineTotal = purchaseOrderDetail.getLineTotal();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[5] = (double) existingData[5] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, lineTotal});
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
        }
        
        if(loaiPhieu.equalsIgnoreCase("Phiếu xuất hủy")) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập", "Tổng giá trị hủy"};
            List<Integer> tableWidths = Arrays.asList(150, 200, 120, 120, 200, 200);
            TableDesign tableDetail = new TableDesign(headers, tableWidths);
            scrollTableDetail.setViewportView(tableDetail.getTable());
            scrollTableDetail.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

            DamageItem d = damageItemBUS.getByID(maPhieu);
            List<DamageItemDetail> damageItemDetails = damageItemDetailBUS.getListDamageItemDetailByDamageItem(d);

            // Sử dụng Map để cộng dồn các sản phẩm cùng mã
            Map<String, Object[]> productMap = new HashMap<>();

            for (DamageItemDetail damageItemDetail : damageItemDetails) {
                String productId = damageItemDetail.getBatch().getProduct().getProductId();
                String productName = damageItemDetail.getBatch().getProduct().getName();
                String unitName = damageItemDetail.getBatch().getProduct().getUnit().getName();
                int quantity = damageItemDetail.getQuantity();
                double price = damageItemDetail.getPrice()*1.1;
                double lineTotal = damageItemDetail.getLineTotal();

                if (productMap.containsKey(productId)) {
                    // Nếu sản phẩm đã tồn tại trong Map, cộng dồn số lượng và tổng giá trị
                    Object[] existingData = productMap.get(productId);
                    existingData[3] = (int) existingData[3] + quantity; // Cộng dồn số lượng
                    existingData[5] = (double) existingData[5] + lineTotal; // Cộng dồn tổng giá trị
                } else {
                    // Nếu sản phẩm chưa tồn tại trong Map, thêm mới vào Map
                    productMap.put(productId, new Object[]{productId, productName, unitName, quantity, price, lineTotal});
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
        }
            
        modalDetail.setLocationRelativeTo(null);
        modalDetail.setVisible(true);
    }//GEN-LAST:event_btnViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> comboboxType;
    private javax.swing.JPanel headerPanel;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JDialog modalDetail;
    private javax.swing.JPanel pnAll;
    private javax.swing.JScrollPane scrollDoanhSo;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JScrollPane scrollTableDetail;
    private javax.swing.JLabel txtTongDoanhThu;
    // End of variables declaration//GEN-END:variables

}
