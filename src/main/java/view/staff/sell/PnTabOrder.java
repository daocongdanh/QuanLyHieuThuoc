/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.staff.sell;

import bus.BatchBUS;
import bus.CustomerBUS;
import bus.OrderBUS;
import bus.PromotionBUS;
import bus.UnitDetailBUS;
import com.formdev.flatlaf.FlatClientProperties;
import connectDB.ConnectDB;
import dto.BatchDTO;
import dto.OrderDTO;
import dto.OrderDetailSelected;
import entity.Customer;
import entity.Product;
import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import util.FormatNumber;
import util.MessageDialog;
import entity.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JSpinner;
import util.CurrentEmployee;
import view.common.SuggestPriceButton;
import view.staff.TABSell;

/**
 *
 * @author Hoang
 */
public class PnTabOrder extends javax.swing.JPanel {

    /**
     * Creates new form TabHoaDon
     */
    private OrderBUS orderBUS;
    private CustomerBUS customerBUS;
    private UnitDetailBUS unitDetailBUS;
    private BatchBUS batchBUS;
    private PromotionBUS promotionBUS;
    private TABSell lapHoaDonForm;

    public PnTabOrder(TABSell lapHoaDonForm) {
        this.lapHoaDonForm = lapHoaDonForm;
        this.orderBUS = new OrderBUS(ConnectDB.getEntityManager());
        this.customerBUS = new CustomerBUS(ConnectDB.getEntityManager());
        this.unitDetailBUS = new UnitDetailBUS(ConnectDB.getEntityManager());
        this.batchBUS = new BatchBUS(ConnectDB.getEntityManager());
        this.promotionBUS = new PromotionBUS(ConnectDB.getEntityManager());
        initComponents();
        txtTienKhachDua.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0 đ");
        txtTimKhachHang.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại khách hàng");
    }

    public boolean addDonThuocMau(List<PrescriptionDetail> prescriptionDetails, int quantityPre) {
        if (checkQuantityPrescription(prescriptionDetails, quantityPre)) {
            for (PrescriptionDetail pd : prescriptionDetails) {
                UnitDetail unitDetail = pd.getUnitDetail();
                Product product = unitDetail.getProduct();
                List<UnitDetail> unitDetails = unitDetailBUS.getListUnitProduct(product);
                Promotion promotion = promotionBUS.getPromotionByProduct(product);

                PnOrderDetail pnOrderDetailTmp = null;
                for (Component component : pnContent.getComponents()) {
                    if (component instanceof PnOrderDetail) {
                        PnOrderDetail existingDetail = (PnOrderDetail) component;
                        UnitDetail selectedUnitDetail = existingDetail.getSelectedUnitDetail();

                        if (unitDetail.equals(selectedUnitDetail) && product.equals(selectedUnitDetail.getProduct())) {
                            pnOrderDetailTmp = existingDetail;
                        }
                    }
                }
                if (pnOrderDetailTmp != null) {
                    int quantity = pd.getQuantity() * quantityPre * unitDetail.getConversionRate();
                    int qty = quantity;
                    List<Batch> batchs = batchBUS.getListBatchEnable(product);
                    while (quantity > 0) {
                        List<PnSelectBatch> list = new ArrayList<>();
                        for (Component component : pnContent.getComponents()) {
                            if (component instanceof PnOrderDetail) {
                                PnOrderDetail existingDetail = (PnOrderDetail) component;
                                UnitDetail selectedUnitDetail = existingDetail.getSelectedUnitDetail();

                                if (unitDetail.equals(selectedUnitDetail) && product.equals(selectedUnitDetail.getProduct())) {
                                    JPanel pnListBatch = existingDetail.getPnListBatch();
                                    for (Component child : pnListBatch.getComponents()) {
                                        if (child instanceof PnSelectBatch) {
                                            PnSelectBatch pnSelectBatch = (PnSelectBatch) child;
                                            BatchDTO batchDTO = pnSelectBatch.getBatchDTO();
                                            if ((quantity + batchDTO.getQuantity()) <= batchDTO.getStock()) {
                                                batchDTO.setQuantity(batchDTO.getQuantity() + quantity);
                                                pnSelectBatch.setBatchDTO(batchDTO);
                                                quantity = 0;
                                            } else {
                                                quantity -= (batchDTO.getStock() - batchDTO.getQuantity());
                                                batchDTO.setQuantity(batchDTO.getStock());
                                                pnSelectBatch.setBatchDTO(batchDTO);
                                            }
                                            pnSelectBatch.setName();
                                            list.add(pnSelectBatch);
                                        }
                                    }
                                }
                            }
                        }
                        pnOrderDetailTmp.getPnListBatch().removeAll();
                        for (PnSelectBatch selectBatch : list) {
                            pnOrderDetailTmp.getPnListBatch().add(selectBatch);
                        }
                        int oldQuantity = (int) pnOrderDetailTmp.getSpinnerSoLuong().getValue();
                        pnOrderDetailTmp.fillQuantity(oldQuantity + qty);
                        if (quantity == 0) {
                            break;
                        }
                        List<BatchDTO> batchDTOs = new ArrayList<>();
                        for (Batch batch : batchs) {
                            int batchStock = batch.getStock();
                            if (quantity >= batchStock) {
                                quantity -= batchStock;
                                batchDTOs.add(new BatchDTO(batch.getName(), batch.getStock(), batch.getExpirationDate(), batchStock));
                            } else {
                                batchDTOs.add(new BatchDTO(batch.getName(), batch.getStock(), batch.getExpirationDate(), quantity));
                                quantity = 0;
                                break;
                            }
                        }
                        for (BatchDTO batchDTO : batchDTOs) {
                            JSpinner jSpinner = pnOrderDetailTmp.getSpinnerSoLuong();
                            pnOrderDetailTmp.getPnListBatch().add(new PnSelectBatch(batchDTO, unitDetail,
                                    jSpinner));

                        }
                        pnOrderDetailTmp.fillQuantity(oldQuantity + qty);
                    }
                } else {
                    List<Batch> batchs = batchBUS.getListBatchEnable(product);
                    List<BatchDTO> batchDTOs = new ArrayList<>();
                    int quantity = pd.getQuantity() * quantityPre * unitDetail.getConversionRate();
                    for (Batch batch : batchs) {
                        int batchStock = batch.getStock();
                        if (quantity >= batchStock) {
                            quantity -= batchStock;
                            batchDTOs.add(new BatchDTO(batch.getName(), batch.getStock(), batch.getExpirationDate(), batchStock));
                        } else {
                            batchDTOs.add(new BatchDTO(batch.getName(), batch.getStock(), batch.getExpirationDate(), quantity));
                            break;
                        }
                    }
                    OrderDetailSelected orderDetailSelected = new OrderDetailSelected(pd.getUnitDetail(), batchDTOs);
                    PnOrderDetail pnOrderDetail = new PnOrderDetail(product, unitDetails, batchs, promotion, this, orderDetailSelected);
                    pnContent.add(pnOrderDetail);
                }
                pnContent.revalidate();
                pnContent.repaint();
                changeTongTienHoaDon();
            }
            return true;
        }
        return false;
    }

    private boolean checkQuantityPrescription(List<PrescriptionDetail> prescriptionDetails, int quantityPre) {
        for (PrescriptionDetail pd : prescriptionDetails) {
            UnitDetail unitDetail = pd.getUnitDetail();
            Product product = unitDetail.getProduct();

            int finalStock = batchBUS.getFinalStockByProduct(product.getProductId());
            List<PnSelectBatch> selectBatchs = new ArrayList<>();
            for (Component component : pnContent.getComponents()) {
                if (component instanceof PnOrderDetail) {
                    PnOrderDetail existingDetail = (PnOrderDetail) component;
                    UnitDetail selectedUnitDetail = existingDetail.getSelectedUnitDetail();

                    if (unitDetail.equals(selectedUnitDetail) && product.equals(selectedUnitDetail.getProduct())) {
                        JPanel pnListBatch = existingDetail.getPnListBatch();
                        for (Component child : pnListBatch.getComponents()) {
                            if (child instanceof PnSelectBatch) {
                                PnSelectBatch pnSelectBatch = (PnSelectBatch) child;
                                selectBatchs.add(pnSelectBatch);
                            }
                        }
                    }
                }
            }

            // Kiểm tra số lượng tồn
            int quantity = 0;
            if (!selectBatchs.isEmpty()) {
                quantity = (pd.getQuantity() * quantityPre
                        + selectBatchs.stream()
                                .mapToInt(pn -> pn.getBatchDTO().getQuantity())
                                .sum()) * unitDetail.getConversionRate();
                System.out.println(quantity + "--1--" + finalStock);

            } else {
                quantity = pd.getQuantity() * quantityPre * unitDetail.getConversionRate();
                System.out.println(quantity + "--2--" + finalStock);
            }
            if (quantity > finalStock) {
                MessageDialog.warning(null, String.format("Sản phẩm '%s' không đủ số lượng", product.getName()));
                return false;
            }
        }
        return true;
    }

    public void addSanPham(Product product) {

        List<UnitDetail> unitDetails = unitDetailBUS.getListUnitProduct(product);
        for (Component component : pnContent.getComponents()) {
            if (component instanceof PnOrderDetail) {
                PnOrderDetail existingDetail = (PnOrderDetail) component;

                if (existingDetail.getProduct().equals(product)) {
                    UnitDetail selectedUnit = existingDetail.getSelectedUnitDetail();
                    if (selectedUnit != null) {
                        unitDetails.remove(selectedUnit);
                    }
                }
            }
        }

        if (unitDetails.isEmpty()) {
            MessageDialog.warning(null, "Sản phẩm đã tồn tại");
            return;
        }

        List<Batch> batchs = batchBUS.getListBatchEnable(product);
        Promotion promotion = promotionBUS.getPromotionByProduct(product);
        PnOrderDetail pnOrderDetail = new PnOrderDetail(product, unitDetails, batchs, promotion, this, null);
        pnContent.add(pnOrderDetail);
        pnContent.revalidate();
        pnContent.repaint();

        updateUnitDetailsForProduct(product, unitDetails.get(0));
    }

    private void updateUnitDetailsForProduct(Product product, UnitDetail unitDetailTmp) {

        for (Component component : pnContent.getComponents()) {
            if (component instanceof PnOrderDetail) {
                PnOrderDetail existingDetail = (PnOrderDetail) component;
                if (existingDetail.getProduct().equals(product)) {
                    UnitDetail selectedUnit = existingDetail.getSelectedUnitDetail();
                    if (!selectedUnit.equals(unitDetailTmp)) {
                        existingDetail.updateUnitDetails(unitDetailTmp);
                    }
                }
            }
        }
    }

    public void changeTongTienHoaDon() {
        tongTienHang = 0.0;
        List<PnOrderDetail> listPanel = getAllPnOrderDetailThuoc();
        for (PnOrderDetail x : listPanel) {
            tongTienHang += x.getLineTotal();
        }
        txtTongTienHang.setText(FormatNumber.formatToVND(tongTienHang));

        discountProduct = listPanel.stream()
                .mapToDouble(PnOrderDetail::getPriceDiscount)
                .sum();
        txtDiscountProduct.setText(FormatNumber.formatToVND(discountProduct));
        promotionOrder = promotionBUS.getPromotionByOrder();

        discountOrder = 0;
        if (promotionOrder != null) {
            discountOrder = (tongTienHang - discountProduct) * promotionOrder.getDiscount();
        }
        txtDiscountOrder.setText(FormatNumber.formatToVND(discountOrder));

        txtTongHoaDon.setText(FormatNumber.formatToVND(tongTienHang - discountProduct - discountOrder));
        //tạo suggest giá
        SuggestPriceButton sg = new SuggestPriceButton(pnPriceSuggest);
        sg.setFinalPrice(tongTienHang);
        sg.setButtonsSuggest();
        List<JToggleButton> listBtn = sg.getListBtnSuggestPrice();
        for (JToggleButton btn : listBtn) {
            btn.addActionListener(e -> {
                JToggleButton selectedButton = (JToggleButton) e.getSource();
                if (selectedButton.isSelected()) {
                    txtTienKhachDua.setText(selectedButton.getText());
                    tinhTienTraKhach();
                }
            });
        }
    }

    private List<PnOrderDetail> getAllPnOrderDetailThuoc() {
        List<PnOrderDetail> orderDetails = new ArrayList<>();
        Component[] components = pnContent.getComponents();

        for (Component component : components) {
            if (component instanceof PnOrderDetail) {
                orderDetails.add((PnOrderDetail) component);
            }
        }
        return orderDetails;
    }

    private List<OrderDTO> createListOrderDetail() {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        List<PnOrderDetail> listPnOrderDetail = getAllPnOrderDetailThuoc();
        if (listPnOrderDetail == null) {
            MessageDialog.warning(null, "Chưa có chọn sản phẩm !!!");
        } else {
            for (PnOrderDetail pnOrderDetail : listPnOrderDetail) {
                if (pnOrderDetail.getSoLuong() == 0) {
                    MessageDialog.warning(null, String.format("Sản phẩm '%s' chưa chọn lô", pnOrderDetail.getProduct().getName()));
                    return null;
                } else {
                    JPanel pnListBatch = pnOrderDetail.getPnListBatch();
                    for (Component component : pnListBatch.getComponents()) {
                        if (component instanceof PnSelectBatch) {
                            PnSelectBatch pnSelectBatch = (PnSelectBatch) component;
                            int quantity = (int) pnSelectBatch.getSpinnerQuantity().getValue();
                            String batchName = pnSelectBatch.getBatchDTO().getName();
                            orderDTOs.add(new OrderDTO(pnOrderDetail.getProduct().getProductId(),
                                    pnOrderDetail.getSelectedUnitDetail(), quantity, batchName));
                        }
                    }
                }
            }
        }
        return orderDTOs;
    }

    private void clearOrderDetails() {
        List<PnOrderDetail> orderDetails = getAllPnOrderDetailThuoc();
        for (PnOrderDetail orderDetail : orderDetails) {
            pnContent.remove(orderDetail);
        }
        pnContent.revalidate();
        pnContent.repaint();
        orderDetails.clear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMid = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnContent = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        txtTimKhachHang = new javax.swing.JTextField();
        btnBanHang = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDiscountOrder = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTongHoaDon = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTienTraKhach = new javax.swing.JLabel();
        pnPriceSuggest = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtDiscountProduct = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        pnMid.setMinimumSize(new java.awt.Dimension(200, 200));
        pnMid.setOpaque(false);

        pnContent.setLayout(new javax.swing.BoxLayout(pnContent, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(pnContent);

        javax.swing.GroupLayout pnMidLayout = new javax.swing.GroupLayout(pnMid);
        pnMid.setLayout(pnMidLayout);
        pnMidLayout.setHorizontalGroup(
            pnMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        pnMidLayout.setVerticalGroup(
            pnMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        add(pnMid, java.awt.BorderLayout.CENTER);

        pnLeft.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKhachHangActionPerformed(evt);
            }
        });

        btnBanHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBanHang.setText("Bán Hàng ( F8 )");
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Tổng giảm giá hóa đơn:");
        jLabel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        txtDiscountOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDiscountOrder.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDiscountOrder.setText("0 đ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDiscountOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiscountOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Tổng tiền hàng:");

        txtTongTienHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongTienHang.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTongTienHang.setText("0 đ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Tiền khách đưa:");

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTienKhachDua.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTienKhachDua.setText("0 đ");
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Tổng hóa đơn:");

        txtTongHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTongHoaDon.setText("0 đ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Tiền trả khách:");

        txtTienTraKhach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTienTraKhach.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTienTraKhach.setText("0 đ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienTraKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pnPriceSuggest.setBackground(new java.awt.Color(255, 255, 255));
        pnPriceSuggest.setMaximumSize(new java.awt.Dimension(398, 10));
        pnPriceSuggest.setMinimumSize(new java.awt.Dimension(398, 10));
        pnPriceSuggest.setPreferredSize(new java.awt.Dimension(398, 10));
        pnPriceSuggest.setLayout(new java.awt.GridLayout(2, 3, 10, 6));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Tổng giảm giá sản phẩm:");

        txtDiscountProduct.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDiscountProduct.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtDiscountProduct.setText("0 đ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(txtDiscountProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiscountProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTenKhachHang.setText("Vãng Lai");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKhachHang)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnPriceSuggest, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(txtTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnPriceSuggest, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pnPriceSuggest.getAccessibleContext().setAccessibleName("");

        add(pnLeft, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKhachHangActionPerformed
        String txtTim = txtTimKhachHang.getText().trim();
        if (txtTim == null) {
            MessageDialog.warning(null, "Chưa nhập số điện thoại khách muốn tìm !!!");
        } else {
            Customer cus = customerBUS.getCustomerByPhone(txtTim);
            if (cus == null) {
                customer = null;
                MessageDialog.warning(null, "Khách hàng không tồn tại !!!");
            } else {
                customer = cus;
                txtTenKhachHang.setText(cus.getName());
                txtTimKhachHang.setText("");
            }
        }
    }//GEN-LAST:event_txtTimKhachHangActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        List<OrderDTO> orderDTOs = createListOrderDetail();

        Map<List, Integer> map = new LinkedHashMap<>();
        if (orderDTOs != null) {
            for (OrderDTO o : orderDTOs) {
                System.out.println(o);
                List<String> productBatch = new ArrayList<>();
                productBatch.add(o.getBatchName());
                productBatch.add(o.getProductId());
                if (map.containsKey(productBatch)) {
                    map.put(productBatch, map.get(productBatch) + (o.getQuantity() * o.getUnitDetail().getConversionRate()));
                } else {
                    map.put(productBatch, o.getQuantity() * o.getUnitDetail().getConversionRate());
                }
            }
        }
        map.forEach((key, value) -> {
            Batch batch = batchBUS.getBatchByNameAndProduct((String) key.get(0), (String) key.get(1));
            if (batch.getStock() < value) {
                MessageDialog.warning(null, String.format("Lô hàng '%s' của sản phẩm '%s' không đủ số lượng",
                        (String) key.get(0), (String) key.get(1)));
                return;
            }
        });
        try {
            if (orderBUS.createOrder(CurrentEmployee.getEmployee(), customer, promotionOrder, orderDTOs)) {
                lapHoaDonForm.removeAndAddNewTab(this);
            }
        } catch (Exception e) {
            MessageDialog.error(null, e.getMessage());
        }

    }//GEN-LAST:event_btnBanHangActionPerformed

    private void tinhTienTraKhach() {
        try {
            // TODO add your handling code here:
            // chua tinh giam gia
            Double tienKhachDua = FormatNumber.parseFromVND(txtTienKhachDua.getText());
            Double tongHoaDon = FormatNumber.parseFromVND(txtTongHoaDon.getText());
            if (tienKhachDua - tongHoaDon > 0) {
                txtTienTraKhach.setText(FormatNumber.formatToVND(tienKhachDua - tongHoaDon));
            } else {
                txtTienTraKhach.setText(FormatNumber.formatToVND(0.0));
            }
        } catch (ParseException ex) {
            throw new RuntimeException("Tính tiền trả khách bị lỗi");
        }
    }

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        tinhTienTraKhach();
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        tinhTienTraKhach();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void jLabel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1AncestorAdded

    private void jLabel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2AncestorAdded

    private Customer customer;
    private double tongTienHang;
    private double discountProduct;
    private double discountOrder;
    private Promotion promotionOrder;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnMid;
    private javax.swing.JPanel pnPriceSuggest;
    private javax.swing.JLabel txtDiscountOrder;
    private javax.swing.JLabel txtDiscountProduct;
    private javax.swing.JLabel txtTenKhachHang;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JLabel txtTienTraKhach;
    private javax.swing.JTextField txtTimKhachHang;
    private javax.swing.JLabel txtTongHoaDon;
    private javax.swing.JLabel txtTongTienHang;
    // End of variables declaration//GEN-END:variables
}
