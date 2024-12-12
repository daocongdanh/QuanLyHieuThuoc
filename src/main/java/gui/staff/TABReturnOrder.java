/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.staff;

import bus.*;
import com.formdev.flatlaf.FlatClientProperties;
import dto.BatchDTO;
import dto.OrderDetailSelected;
import dto.ReturnOrderDetailDTO;
import entity.Customer;
import java.awt.Component;
import java.util.*;
import util.FormatNumber;
import util.MessageDialog;
import entity.*;
import javax.swing.JTextField;
import gui.login.LoadApplication;
import gui.staff.returnOrder.PnOrderDetailReturn;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import util.CurrentEmployee;

/**
 *
 * @author Hoang
 */
public class TABReturnOrder extends javax.swing.JPanel {

    /**
     * Creates new form TabHoaDon
     */
    private final OrderBUS orderBUS;
    private final CustomerBUS customerBUS;
    private final BatchBUS batchBUS;
    private final PromotionBUS promotionBUS;
    private final OrderDetailBUS orderDetailBUS;
    private final ReturnOrderBUS returnOrderBUS;

    public TABReturnOrder() {
        initComponents();
        orderBUS = LoadApplication.orderBUS;
        customerBUS = LoadApplication.customerBUS;
        batchBUS = LoadApplication.batchBUS;
        promotionBUS = LoadApplication.promotionBUS;
        orderDetailBUS = LoadApplication.orderDetailBUS;
        returnOrderBUS = LoadApplication.returnOrderBUS;

        txtSearchOrder.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã hóa đơn");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMid = new javax.swing.JPanel();
        pnHaveContent = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnHaveOrder = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnHaveReturn = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        btnTaoPhieu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCusPhone = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtReturnTotal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCusName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JTextField();
        headerPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnOpenModalAddUnit = new javax.swing.JButton();
        txtSearchOrder = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        pnMid.setMinimumSize(new java.awt.Dimension(200, 200));
        pnMid.setOpaque(false);
        pnMid.setLayout(new javax.swing.BoxLayout(pnMid, javax.swing.BoxLayout.LINE_AXIS));

        pnHaveContent.setBackground(new java.awt.Color(255, 255, 255));
        pnHaveContent.setLayout(new javax.swing.BoxLayout(pnHaveContent, javax.swing.BoxLayout.Y_AXIS));

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane1.setDividerLocation(490);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");

        pnHaveOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnHaveOrder.setLayout(new javax.swing.BoxLayout(pnHaveOrder, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(pnHaveOrder);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setToolTipText("");
        jScrollPane3.setViewportView(pnHaveReturn);

        pnHaveReturn.setBackground(new java.awt.Color(255, 255, 255));
        pnHaveReturn.setLayout(new javax.swing.BoxLayout(pnHaveReturn, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(pnHaveReturn);

        jSplitPane1.setRightComponent(jScrollPane3);

        pnHaveContent.add(jSplitPane1);

        pnMid.add(pnHaveContent);

        add(pnMid, java.awt.BorderLayout.CENTER);

        pnLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnLeft.setPreferredSize(new java.awt.Dimension(485, 650));

        btnTaoPhieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTaoPhieu.setText("Tạo phiếu ( F8 )");
        btnTaoPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoPhieuActionPerformed(evt);
            }
        });
        btnTaoPhieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTaoPhieuKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD, jLabel3.getFont().getSize()+2));
        jLabel3.setText("Số điện thoại:");

        txtCusPhone.setEditable(false);
        txtCusPhone.setFont(txtCusPhone.getFont().deriveFont(txtCusPhone.getFont().getSize()+3f));
        txtCusPhone.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCusPhone))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCusPhone)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getStyle() | java.awt.Font.BOLD, jLabel9.getFont().getSize()+2));
        jLabel9.setText("Cần trả khách:");

        txtReturnTotal.setEditable(false);
        txtReturnTotal.setFont(txtReturnTotal.getFont().deriveFont(txtReturnTotal.getFont().getSize()+3f));
        txtReturnTotal.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtReturnTotal))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtReturnTotal)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+2));
        jLabel2.setText("Tên khách hàng:");
        jLabel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel2AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtCusName.setEditable(false);
        txtCusName.setFont(txtCusName.getFont().deriveFont(txtCusName.getFont().getSize()+3f));
        txtCusName.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCusName)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, jLabel4.getFont().getSize()+2));
        jLabel4.setText("Mã hóa đơn:");
        jLabel4.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel4AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtOrderId.setEditable(false);
        txtOrderId.setFont(txtOrderId.getFont().deriveFont(txtOrderId.getFont().getSize()+3f));
        txtOrderId.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtOrderId)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD, jLabel5.getFont().getSize()+2));
        jLabel5.setText("Tên nhân viên lập:");
        jLabel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel5AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtEmpName.setEditable(false);
        txtEmpName.setFont(txtEmpName.getFont().deriveFont(txtEmpName.getFont().getSize()+3f));
        txtEmpName.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmpName))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmpName)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        add(pnLeft, java.awt.BorderLayout.EAST);

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 232, 232), 2, true));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(590, 100));

        btnOpenModalAddUnit.setBackground(new java.awt.Color(115, 165, 71));
        btnOpenModalAddUnit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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

        txtSearchOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearchOrder.setMinimumSize(new java.awt.Dimension(300, 40));
        txtSearchOrder.setPreferredSize(new java.awt.Dimension(300, 40));
        txtSearchOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtSearchOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnOpenModalAddUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenModalAddUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        headerPanel.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoPhieuActionPerformed
        createOrder();
    }//GEN-LAST:event_btnTaoPhieuActionPerformed

    private void createOrder() {
        List<ReturnOrderDetailDTO> listReturnOrderDetailDTOs = createListReturnOrderDetail();
        try {
            if (returnOrderBUS.createReturnOrder(CurrentEmployee.getEmployee(), customer, order, listReturnOrderDetailDTOs)) {
                MessageDialog.info(null, "Tạo phiếu trả hàng thành công.");
                clearPnOrderDetail();
            } else {
                MessageDialog.info(null, "Chưa nhập lý do!!!");
            };
        } catch (Exception e) {
            MessageDialog.error(null, "Chưa nhập lý do!!!");
        }
    }

    private void setTxtEmpty(JTextField... textFields) {
        for (JTextField x : textFields) {
            x.setText("");
        }
    }

    private void clearPnOrderDetail() {
        setTxtEmpty(txtCusName, txtCusPhone, txtEmpName, txtOrderId, txtReturnTotal);
        pnHaveOrder.removeAll();
        pnHaveOrder.revalidate();
        pnHaveOrder.repaint();

        pnHaveReturn.removeAll();
        pnHaveReturn.revalidate();
        pnHaveReturn.repaint();
    }

    private List<ReturnOrderDetailDTO> createListReturnOrderDetail() {
        List<ReturnOrderDetailDTO> returnOrderDetailDTOs = new ArrayList<>();
        List<PnOrderDetailReturn> listPnOrderDetailReturn = getAllPnOrderDetailThuoc();
        if (listPnOrderDetailReturn == null) {
            MessageDialog.warning(null, "Không có sản phẩm !!!");
        } else {
            for (PnOrderDetailReturn pnOrderDetailReturn : listPnOrderDetailReturn) {
                int quantity = (int) pnOrderDetailReturn.getSoLuong();

                returnOrderDetailDTOs.add(new ReturnOrderDetailDTO(pnOrderDetailReturn.getProduct(),
                        quantity, pnOrderDetailReturn.getReason()));
            }
        }
        return returnOrderDetailDTOs;
    }


    private void jLabel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2AncestorAdded

    private void fillOneOrder(Order order) {
        if (order.getCustomer() != null) {
            txtCusName.setText(order.getCustomer().getName());
            txtCusPhone.setText(order.getCustomer().getPhone());
            customer = order.getCustomer();
        } else {
            txtCusName.setText("Vãng lai");
            txtCusPhone.setText("Vãng lai");
        }
        txtEmpName.setText(order.getEmployee().getName());
        txtOrderId.setText(order.getOrderId());

        List<OrderDetail> listOrderDetail = orderDetailBUS.getListOrderDetailByOrder(order);

        Map<Product, Map<Unit, List<BatchDTO>>> productBatchMap = new LinkedHashMap<>();

        for (OrderDetail orderDetail : listOrderDetail) {
            Product product = orderDetail.getBatch().getProduct();

            BatchDTO batchDTO = new BatchDTO(orderDetail.getBatch().getName(), orderDetail.getBatch().getStock(),
                    orderDetail.getBatch().getExpirationDate(), orderDetail.getQuantity());

            // Sử dụng computeIfAbsent để tạo Map cho UnitDetail nếu chưa tồn tại
            productBatchMap
                    .computeIfAbsent(product, k -> new LinkedHashMap<>())
                    .computeIfAbsent(product.getUnit(), k -> new ArrayList<>())
                    .add(batchDTO);
        }

        Set<String> processedProducts = new HashSet<>();
        for (OrderDetail orderDetail : listOrderDetail) {
            Product product = orderDetail.getBatch().getProduct();
            Unit unit = product.getUnit();

            // Tạo chuỗi duy nhất từ Product và UnitDetail
            String uniqueKey = product.getProductId() + "-" + unit.getUnitId();

            if (!processedProducts.contains(uniqueKey)) {
                List<BatchDTO> batchDTOs = productBatchMap.get(product).get(unit);
                OrderDetailSelected odSelected = new OrderDetailSelected(batchDTOs);

                PnOrderDetailReturn pnOd = new PnOrderDetailReturn(orderDetail, odSelected, this, 1);
                pnHaveOrder.add(pnOd);

                // Thêm uniqueKey vào Set để đánh dấu đã xử lý
                processedProducts.add(uniqueKey);
            }
        }
        pnHaveOrder.revalidate();
        pnHaveOrder.repaint();

        pnHaveContent.revalidate();
        pnHaveContent.repaint();
        changeTongTienHoaDon();
    }

    public void addOrderDetailToReturn(PnOrderDetailReturn pnOd) {
        PnOrderDetailReturn pn = new PnOrderDetailReturn(pnOd.getOrderDetail(),
                pnOd.getOrderDetailSelected(), this, 2);
        boolean exists = false;
        for (Component comp : pnHaveReturn.getComponents()) {
            if (comp instanceof PnOrderDetailReturn) {
                PnOrderDetailReturn existingDetail = (PnOrderDetailReturn) comp;
                if (existingDetail.equals(pn)) { // So sánh với đối tượng mới
                    exists = true;
                    break;
                }
            }
        }

        if (!exists) {
            pnHaveReturn.add(pn);
            pnHaveReturn.revalidate();
            pnHaveReturn.repaint();
            pnHaveContent.revalidate();
            pnHaveContent.repaint();
            changeTongTienHoaDon();
        } else {
            MessageDialog.warning(null, "Đã chọn sản phẩm này");
            return;
        }
    }

    private List<PnOrderDetailReturn> getAllPnOrderDetailThuoc() {
        List<PnOrderDetailReturn> orderDetails = new ArrayList<>();
        Component[] components = pnHaveReturn.getComponents();

        for (Component component : components) {
            if (component instanceof PnOrderDetailReturn) {
                orderDetails.add((PnOrderDetailReturn) component);
            }
        }
        return orderDetails;
    }

    public void changeTongTienHoaDon() {
        tongTienHang = 0.0;
        List<PnOrderDetailReturn> listPanel = getAllPnOrderDetailThuoc();
        for (PnOrderDetailReturn x : listPanel) {
            tongTienHang += x.getLineTotal();
        }

        txtReturnTotal.setText(FormatNumber.formatToVND(tongTienHang));
    }

    private void searchHoaDonTra() {
        String orderId = txtSearchOrder.getText().trim();
        try {
            clearPnOrderDetail();
            order = orderBUS.findById(orderId);
            if (order.getPromotion() != null) {
                MessageDialog.info(null, "Hóa đơn được tạo trong kì khuyến mãi");
                return;
            }
            for (OrderDetail x : order.getOrderDetails()) {
                if (x.getDiscount() != 0.0) {
                    MessageDialog.info(null, "Hóa đơn được tạo trong kì khuyến mãi");
                    return;
                }
            }
            if (!returnOrderBUS.checkOrderIsReturned(orderId)) {
                txtSearchOrder.setText("");
                fillOneOrder(order);
            } else {
                MessageDialog.info(null, "Hóa đơn này đã từng được tạo phiếu trả.");
            }
        } catch (Exception e) {
            MessageDialog.warning(null, "Hóa đơn không tồn tại.");
        }
    }

    private void txtSearchOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchOrderActionPerformed
        searchHoaDonTra();
    }//GEN-LAST:event_txtSearchOrderActionPerformed

    private void btnOpenModalAddUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenModalAddUnitActionPerformed
        searchHoaDonTra();
    }//GEN-LAST:event_btnOpenModalAddUnitActionPerformed

    private void jLabel4AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel4AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4AncestorAdded

    private void jLabel5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel5AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5AncestorAdded

    private void btnTaoPhieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTaoPhieuKeyPressed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Bạn đã nhấn phím!");
        if (evt.getKeyCode() == KeyEvent.VK_F8) {
            JOptionPane.showMessageDialog(null, "Bạn đã nhấn phím F8!");
            createOrder();
        }

    }//GEN-LAST:event_btnTaoPhieuKeyPressed
    private double tongTienHang;
    private Customer customer;
    private Order order;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenModalAddUnit;
    private javax.swing.JButton btnTaoPhieu;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnHaveContent;
    private javax.swing.JPanel pnHaveOrder;
    private javax.swing.JPanel pnHaveReturn;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnMid;
    private javax.swing.JTextField txtCusName;
    private javax.swing.JTextField txtCusPhone;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtReturnTotal;
    private javax.swing.JTextField txtSearchOrder;
    // End of variables declaration//GEN-END:variables

}
