/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.manager;

import bus.ProductBUS;
import bus.PromotionBUS;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import entity.Promotion;
import enums.PromotionType;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

import gui.login.LoadApplication;
import util.MessageDialog;
import util.ResizeImage;
import gui.common.TableDesign;
import entity.*;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.JTable;
import util.FormatNumber;
import gui.common.TableActionCellEditorOneAction;
import gui.common.TableActionCellRenderOneAction;
import gui.common.TableActionEventOneAction;

/**
 *
 * @author Hoang
 */
public class TABPromotion extends javax.swing.JPanel {

    private PromotionBUS promotionBUS;
    private TableDesign tableDesign;
    private TableDesign tableDesignAdd;
    private TableDesign tableDesignUpdate;
    private ProductBUS productBUS;

    public TABPromotion() throws RemoteException {
        promotionBUS = LoadApplication.promotionBUS;
        productBUS = LoadApplication.productBUS;
        initComponents();
        setUIManager();
        fillTable();
        addIconFeature();
        fillTableModalAdd();
        fillTableModalUpdate();
        addEventBtnRemoveAdd();
        addEventBtnRemoveUpdate();
    }

    private void setUIManager() {
        searchProduct.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm sản phẩm");
        searchProduct1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm sản phẩm");
        jDate.setDate(Date.valueOf(LocalDate.now()));
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
        jDateFromP.setDate(Date.valueOf(LocalDate.now()));
        jDateToP.setDate(Date.valueOf(LocalDate.now()));
        UIManager.put("Button.arc", 10);
    }

    private void fillTable() throws RemoteException {
        String[] headers = {"Mã khuyến mãi", "Chương trình khuyến mãi", "Ngày bắt đầu",
            "Ngày kết thúc", "Giảm giá", "Loại khuyến mãi", "Trạng thái"};
        List<Integer> tableWidths = Arrays.asList(100, 300, 200, 200, 100, 150, 150);
        tableDesign = new TableDesign(headers, tableWidths);
        scrollTable.setViewportView(tableDesign.getTable());
        scrollTable.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        List<Promotion> promotions = promotionBUS.getAllPromotions();
        fillContent(promotions);
    }

    private void addIconFeature() {
        btnAdd.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/addBtn.svg")), 35, 35));
        btnUpdate.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/editBtn.svg")), 35, 35));
    }

    private void fillTableModalAdd() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Thao tác"};
        List<Integer> tableWidths = Arrays.asList(150, 150, 150, 150);
        tableDesignAdd = new TableDesign(headers, tableWidths, List.of(false, false, false, true));
        tableDesignAdd.setTableHeaderFontSize(13);
        scrollTableDesPre.setViewportView(tableDesignAdd.getTable());
    }

    private void fillTableModalUpdate() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Giá bán", "Thao tác"};
        List<Integer> tableWidths = Arrays.asList(150, 150, 100, 100, 100);
        tableDesignUpdate = new TableDesign(headers, tableWidths, List.of(false, false, false, false, true));
        tableDesignUpdate.setTableHeaderFontSize(13);
        scrollTableDesPre1.setViewportView(tableDesignUpdate.getTable());
    }

    private void addEventBtnRemoveAdd() {
        JTable table = tableDesignAdd.getTable();
        TableActionEventOneAction event = (int row) -> {
            int selectedRow = table.getSelectedRow();
            table.getCellEditor().stopCellEditing();
            tableDesignAdd.getModelTable().removeRow(selectedRow);

        };
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new TableActionCellRenderOneAction(2));
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new TableActionCellEditorOneAction(event, 2));
    }

    private void addEventBtnRemoveUpdate() {
        JTable table = tableDesignUpdate.getTable();
        TableActionEventOneAction event = (int row) -> {
            int selectedRow = table.getSelectedRow();
            table.getCellEditor().stopCellEditing();
            tableDesignUpdate.getModelTable().removeRow(selectedRow);
        };
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new TableActionCellRenderOneAction(2));
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellEditor(new TableActionCellEditorOneAction(event, 2));
    }

    private void fillContent(List<Promotion> promotions) {
        tableDesign.getModelTable().setRowCount(0);
        DecimalFormat df = new DecimalFormat("#%");
        for (Promotion promotion : promotions) {
            tableDesign.getModelTable().addRow(new Object[]{promotion.getPromotionId(), promotion.getName(), promotion.getStartedDate(),
                promotion.getEndedDate(), df.format(promotion.getDiscount()),
                promotion.getPromotionType().equals(PromotionType.PRODUCT) ? "Sản phẩm" : "Hóa đơn",
                promotion.isStatus() == true ? "Đã phát hành" : "Chưa phát hành"});
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

        modalPromotion = new javax.swing.JDialog();
        pnAll1 = new javax.swing.JPanel();
        tabbedPanePromotion = new javax.swing.JTabbedPane();
        pnPromotionOrder = new javax.swing.JPanel();
        btnDTM = new javax.swing.JButton();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jDateTo = new com.toedter.calendar.JDateChooser();
        spinnerDiscount = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPromotionOrder = new javax.swing.JTextField();
        pnPromotionProduct = new javax.swing.JPanel();
        scrollTableDesPre = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDateFromP = new com.toedter.calendar.JDateChooser();
        jDateToP = new com.toedter.calendar.JDateChooser();
        spinnerDiscountP = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        searchProduct = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtPromotionProduct = new javax.swing.JTextField();
        modalUpdateProduct = new javax.swing.JDialog();
        pnPromotionProduct1 = new javax.swing.JPanel();
        scrollTableDesPre1 = new javax.swing.JScrollPane();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jDateFromP1 = new com.toedter.calendar.JDateChooser();
        jDateToP1 = new com.toedter.calendar.JDateChooser();
        spinnerDiscountP1 = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        searchProduct1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        txtPromotionProduct1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        modalUpdateOrder = new javax.swing.JDialog();
        pnPromotionOrder1 = new javax.swing.JPanel();
        btnUpdateOrder = new javax.swing.JButton();
        jDateFromUpdate = new com.toedter.calendar.JDateChooser();
        jDateToUpdate = new com.toedter.calendar.JDateChooser();
        spinnerDiscountUpdate = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPromotionOrder1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        pnAll = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jComboBoxType = new javax.swing.JComboBox<>();
        jDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnEmail = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();

        modalPromotion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalPromotion.setTitle("Tạo chương trình khuyến mãi");
        modalPromotion.setMinimumSize(new java.awt.Dimension(1021, 616));
        modalPromotion.setResizable(false);

        pnAll1.setBackground(new java.awt.Color(255, 255, 255));
        pnAll1.setMaximumSize(new java.awt.Dimension(1021, 616));
        pnAll1.setMinimumSize(new java.awt.Dimension(1021, 616));

        pnPromotionOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnPromotionOrder.setMaximumSize(new java.awt.Dimension(1021, 616));
        pnPromotionOrder.setMinimumSize(new java.awt.Dimension(1021, 616));
        pnPromotionOrder.setPreferredSize(new java.awt.Dimension(1021, 616));

        btnDTM.setBackground(new java.awt.Color(116, 167, 72));
        btnDTM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDTM.setForeground(new java.awt.Color(255, 255, 255));
        btnDTM.setText("Tạo mới");
        btnDTM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDTMActionPerformed(evt);
            }
        });

        jDateFrom.setBackground(new java.awt.Color(255, 255, 255));
        jDateFrom.setDateFormatString("dd/MM/yyyy");
        jDateFrom.setFocusable(false);
        jDateFrom.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateFrom.setPreferredSize(new java.awt.Dimension(100, 22));

        jDateTo.setBackground(new java.awt.Color(255, 255, 255));
        jDateTo.setDateFormatString("dd/MM/yyyy");
        jDateTo.setFocusable(false);
        jDateTo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateTo.setPreferredSize(new java.awt.Dimension(100, 22));

        spinnerDiscount.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerDiscount.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerDiscount.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Ngày bắt đầu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Ngày kết thúc");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Giảm giá");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setText("Chương trình khuyến mãi");

        txtPromotionOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPromotionOrder.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout pnPromotionOrderLayout = new javax.swing.GroupLayout(pnPromotionOrder);
        pnPromotionOrder.setLayout(pnPromotionOrderLayout);
        pnPromotionOrderLayout.setHorizontalGroup(
            pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(spinnerDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addComponent(txtPromotionOrder))))
                .addContainerGap(209, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionOrderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        pnPromotionOrderLayout.setVerticalGroup(
            pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionOrderLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtPromotionOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinnerDiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(btnDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );

        tabbedPanePromotion.addTab("Khuyến mãi trên hóa đơn", pnPromotionOrder);

        pnPromotionProduct.setBackground(new java.awt.Color(255, 255, 255));
        pnPromotionProduct.setMaximumSize(new java.awt.Dimension(1021, 616));
        pnPromotionProduct.setMinimumSize(new java.awt.Dimension(1021, 616));
        pnPromotionProduct.setPreferredSize(new java.awt.Dimension(1021, 616));
        pnPromotionProduct.setRequestFocusEnabled(false);

        scrollTableDesPre.setBackground(new java.awt.Color(255, 255, 255));
        scrollTableDesPre.setPreferredSize(new java.awt.Dimension(771, 238));

        jButton1.setBackground(new java.awt.Color(116, 167, 72));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tạo mới");
        jButton1.setPreferredSize(new java.awt.Dimension(95, 42));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Ngày bắt đầu");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Ngày kết thúc");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Giảm giá");

        jDateFromP.setBackground(new java.awt.Color(255, 255, 255));
        jDateFromP.setDateFormatString("dd/MM/yyyy");
        jDateFromP.setFocusable(false);
        jDateFromP.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateFromP.setPreferredSize(new java.awt.Dimension(100, 22));

        jDateToP.setBackground(new java.awt.Color(255, 255, 255));
        jDateToP.setDateFormatString("dd/MM/yyyy");
        jDateToP.setFocusable(false);
        jDateToP.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateToP.setPreferredSize(new java.awt.Dimension(100, 22));

        spinnerDiscountP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerDiscountP.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerDiscountP.setFocusable(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Tìm kiếm sản phẩm");

        searchProduct.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jButton2.setBackground(new java.awt.Color(116, 167, 72));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Tìm kiếm");
        jButton2.setPreferredSize(new java.awt.Dimension(95, 42));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setText("Chương trình khuyến mãi");

        txtPromotionProduct.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPromotionProduct.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout pnPromotionProductLayout = new javax.swing.GroupLayout(pnPromotionProduct);
        pnPromotionProduct.setLayout(pnPromotionProductLayout);
        pnPromotionProductLayout.setHorizontalGroup(
            pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPromotionProductLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnPromotionProductLayout.createSequentialGroup()
                            .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnPromotionProductLayout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(txtPromotionProduct))
                            .addGap(18, 18, 18)
                            .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jDateFromP, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jDateToP, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(spinnerDiscountP, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollTableDesPre, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnPromotionProductLayout.createSequentialGroup()
                            .addComponent(searchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 57, Short.MAX_VALUE))
        );
        pnPromotionProductLayout.setVerticalGroup(
            pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionProductLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateFromP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jDateToP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(spinnerDiscountP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtPromotionProduct))
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTableDesPre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        tabbedPanePromotion.addTab("Khuyến mãi trên sản phẩm", pnPromotionProduct);

        javax.swing.GroupLayout pnAll1Layout = new javax.swing.GroupLayout(pnAll1);
        pnAll1.setLayout(pnAll1Layout);
        pnAll1Layout.setHorizontalGroup(
            pnAll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAll1Layout.createSequentialGroup()
                .addComponent(tabbedPanePromotion, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnAll1Layout.setVerticalGroup(
            pnAll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAll1Layout.createSequentialGroup()
                .addComponent(tabbedPanePromotion, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalPromotionLayout = new javax.swing.GroupLayout(modalPromotion.getContentPane());
        modalPromotion.getContentPane().setLayout(modalPromotionLayout);
        modalPromotionLayout.setHorizontalGroup(
            modalPromotionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalPromotionLayout.createSequentialGroup()
                .addComponent(pnAll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modalPromotionLayout.setVerticalGroup(
            modalPromotionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalPromotionLayout.createSequentialGroup()
                .addComponent(pnAll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        modalUpdateProduct.setTitle("Cập nhật chương trình khuyến mãi");
        modalUpdateProduct.setMinimumSize(new java.awt.Dimension(1021, 576));

        pnPromotionProduct1.setBackground(new java.awt.Color(255, 255, 255));
        pnPromotionProduct1.setMaximumSize(new java.awt.Dimension(1021, 616));
        pnPromotionProduct1.setMinimumSize(new java.awt.Dimension(1021, 576));
        pnPromotionProduct1.setPreferredSize(new java.awt.Dimension(1021, 576));
        pnPromotionProduct1.setRequestFocusEnabled(false);

        scrollTableDesPre1.setBackground(new java.awt.Color(255, 255, 255));
        scrollTableDesPre1.setPreferredSize(new java.awt.Dimension(771, 238));

        jButton3.setBackground(new java.awt.Color(116, 167, 72));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cập nhật");
        jButton3.setPreferredSize(new java.awt.Dimension(95, 42));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton3ActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Ngày bắt đầu");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Ngày kết thúc");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Giảm giá");

        jDateFromP1.setBackground(new java.awt.Color(255, 255, 255));
        jDateFromP1.setDateFormatString("dd/MM/yyyy");
        jDateFromP1.setFocusable(false);
        jDateFromP1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateFromP1.setPreferredSize(new java.awt.Dimension(100, 22));

        jDateToP1.setBackground(new java.awt.Color(255, 255, 255));
        jDateToP1.setDateFormatString("dd/MM/yyyy");
        jDateToP1.setFocusable(false);
        jDateToP1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateToP1.setPreferredSize(new java.awt.Dimension(100, 22));

        spinnerDiscountP1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerDiscountP1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerDiscountP1.setFocusable(false);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("Tìm kiếm sản phẩm");

        searchProduct1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jButton4.setBackground(new java.awt.Color(116, 167, 72));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Tìm kiếm");
        jButton4.setPreferredSize(new java.awt.Dimension(95, 42));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton4ActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        txtPromotionProduct1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPromotionProduct1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setText("Chương trình khuyến mãi");

        javax.swing.GroupLayout pnPromotionProduct1Layout = new javax.swing.GroupLayout(pnPromotionProduct1);
        pnPromotionProduct1.setLayout(pnPromotionProduct1Layout);
        pnPromotionProduct1Layout.setHorizontalGroup(
            pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPromotionProduct1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnPromotionProduct1Layout.createSequentialGroup()
                            .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnPromotionProduct1Layout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(txtPromotionProduct1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jDateFromP1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jDateToP1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(spinnerDiscountP1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollTableDesPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnPromotionProduct1Layout.createSequentialGroup()
                                .addComponent(searchProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 57, Short.MAX_VALUE))
        );
        pnPromotionProduct1Layout.setVerticalGroup(
            pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionProduct1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnPromotionProduct1Layout.createSequentialGroup()
                        .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateFromP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateToP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerDiscountP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnPromotionProduct1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPromotionProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPromotionProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTableDesPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalUpdateProductLayout = new javax.swing.GroupLayout(modalUpdateProduct.getContentPane());
        modalUpdateProduct.getContentPane().setLayout(modalUpdateProductLayout);
        modalUpdateProductLayout.setHorizontalGroup(
            modalUpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPromotionProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        modalUpdateProductLayout.setVerticalGroup(
            modalUpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPromotionProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        modalUpdateOrder.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        modalUpdateOrder.setTitle("Cập nhật chương trình khuyến mãi");
        modalUpdateOrder.setMinimumSize(new java.awt.Dimension(752, 463));

        pnPromotionOrder1.setBackground(new java.awt.Color(255, 255, 255));
        pnPromotionOrder1.setMaximumSize(new java.awt.Dimension(752, 463));
        pnPromotionOrder1.setMinimumSize(new java.awt.Dimension(752, 463));
        pnPromotionOrder1.setPreferredSize(new java.awt.Dimension(752, 463));

        btnUpdateOrder.setBackground(new java.awt.Color(116, 167, 72));
        btnUpdateOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnUpdateOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateOrder.setText("Cập nhật");
        btnUpdateOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnUpdateOrderActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jDateFromUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jDateFromUpdate.setDateFormatString("dd/MM/yyyy");
        jDateFromUpdate.setFocusable(false);
        jDateFromUpdate.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateFromUpdate.setPreferredSize(new java.awt.Dimension(100, 22));

        jDateToUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jDateToUpdate.setDateFormatString("dd/MM/yyyy");
        jDateToUpdate.setFocusable(false);
        jDateToUpdate.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDateToUpdate.setPreferredSize(new java.awt.Dimension(100, 22));

        spinnerDiscountUpdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinnerDiscountUpdate.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spinnerDiscountUpdate.setFocusable(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Ngày bắt đầu");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Ngày kết thúc");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setText("Giảm giá");

        txtPromotionOrder1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPromotionOrder1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setText("Chương trình khuyến mãi");

        javax.swing.GroupLayout pnPromotionOrder1Layout = new javax.swing.GroupLayout(pnPromotionOrder1);
        pnPromotionOrder1.setLayout(pnPromotionOrder1Layout);
        pnPromotionOrder1Layout.setHorizontalGroup(
            pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPromotionOrder1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnPromotionOrder1Layout.createSequentialGroup()
                        .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11))
                            .addComponent(jLabel13)
                            .addGroup(pnPromotionOrder1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(4, 4, 4)))
                        .addGap(40, 40, 40)
                        .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateToUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addComponent(jDateFromUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spinnerDiscountUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPromotionOrder1))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        pnPromotionOrder1Layout.setVerticalGroup(
            pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionOrder1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPromotionOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(40, 40, 40)
                .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jDateFromUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jDateToUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnPromotionOrder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinnerDiscountUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(41, 41, 41)
                .addComponent(btnUpdateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalUpdateOrderLayout = new javax.swing.GroupLayout(modalUpdateOrder.getContentPane());
        modalUpdateOrder.getContentPane().setLayout(modalUpdateOrderLayout);
        modalUpdateOrderLayout.setHorizontalGroup(
            modalUpdateOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPromotionOrder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modalUpdateOrderLayout.setVerticalGroup(
            modalUpdateOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modalUpdateOrderLayout.createSequentialGroup()
                .addComponent(pnPromotionOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(204, 204, 0));

        pnAll.setBackground(new java.awt.Color(255, 255, 255));
        pnAll.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));

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
                try {
                    btnUpdateActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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

        jComboBoxType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hóa đơn", "Sản phẩm" }));
        jComboBoxType.setToolTipText("");

        jDate.setBackground(new java.awt.Color(255, 255, 255));
        jDate.setDateFormatString("dd/MM/yyyy");
        jDate.setFocusable(false);
        jDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jDate.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ngày hiệu lực");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Loại khuyến mãi");

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

        btnEmail.setBackground(new java.awt.Color(115, 165, 71));
        btnEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnEmail.setText("Gửi mail");
        btnEmail.setMaximumSize(new java.awt.Dimension(150, 40));
        btnEmail.setMinimumSize(new java.awt.Dimension(150, 40));
        btnEmail.setPreferredSize(new java.awt.Dimension(150, 40));
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnEmailActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(btnEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnAll.add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1254, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tableDesign.getTable().getSelectedRow();
        if (selectedRow < 0) {
            MessageDialog.warning(null, "Hãy chọn khuyến mãi muốn cập nhật thông tin");
            return;
        }
        String status = (String) tableDesign.getModelTable().getValueAt(selectedRow, 6);
        if (!status.equals("Chưa phát hành")) {
            MessageDialog.warning(null, "Chương trình khuyến mãi đã phát hành nên không thể chỉnh sửa");
            return;
        }
        String type = (String) tableDesign.getModelTable().getValueAt(selectedRow, 5);
        if (type.equals("Hóa đơn")) {
            Optional<Promotion> promotionExists = promotionBUS.getPromotionById((String) tableDesign.getModelTable().getValueAt(selectedRow, 0));
            if (promotionExists.isEmpty()) {
                MessageDialog.warning(null, "Không tìm thấy khuyến mãi");
                return;
            }
            Promotion promotion = promotionExists.get();
            jDateFromUpdate.setDate(Date.valueOf(promotion.getStartedDate()));
            jDateToUpdate.setDate(Date.valueOf(promotion.getEndedDate()));
            spinnerDiscountUpdate.setValue((int) (promotion.getDiscount() * 100));
            txtPromotionOrder1.setText(promotion.getName());
            modalUpdateOrder.setLocationRelativeTo(null);
            modalUpdateOrder.setVisible(true);
        } else {
            Optional<Promotion> promotionExists = promotionBUS.getPromotionById((String) tableDesign.getModelTable().getValueAt(selectedRow, 0));
            if (promotionExists.isEmpty()) {
                MessageDialog.warning(null, "Không tìm thấy khuyến mãi");
                return;
            }
            Promotion promotion = promotionExists.get();
            jDateFromP1.setDate(Date.valueOf(promotion.getStartedDate()));
            jDateToP1.setDate(Date.valueOf(promotion.getEndedDate()));
            spinnerDiscountP1.setValue((int) (promotion.getDiscount() * 100));
            txtPromotionProduct1.setText(promotion.getName());
            List<Product> products = promotionBUS.getAllByPromotion(promotion);
            tableDesignUpdate.getModelTable().setRowCount(0);
            for (Product product : products) {
                tableDesignUpdate.getModelTable().addRow(new Object[]{product.getProductId(), product.getName(),
                    product.getUnit().getName(),
                    FormatNumber.formatToVND(product.getPrice()), null});
            }
            modalUpdateProduct.setLocationRelativeTo(null);
            modalUpdateProduct.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        tabbedPanePromotion.setSelectedIndex(0);
        modalPromotion.setLocationRelativeTo(null);
        modalPromotion.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate = jDate.getDate();
        LocalDate date = jdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String promotionType = jComboBoxType.getSelectedItem().toString();
        List<Promotion> promotions = promotionBUS.search(date, promotionType);
        fillContent(promotions);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDTMActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate1 = jDateFrom.getDate();
        LocalDate start = jdate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.util.Date jdate2 = jDateTo.getDate();
        LocalDate end = jdate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String name = txtPromotionOrder.getText().trim();
        double discount = (int) spinnerDiscount.getValue() * 1.0 / 100;
        if (start.isBefore(LocalDate.now())) {
            MessageDialog.warning(null, "Ngày bắt đầu phải sau ngày hiện tại");
            return;
        }
        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        try {
            Promotion promotion = new Promotion(null, name, start, end, discount, false, PromotionType.ORDER);
            promotionBUS.createPromotion(promotion, null);
            MessageDialog.info(null, "Thêm chương trình khuyến mãi thành công");
            modalPromotion.dispose();
            List<Promotion> promotions = promotionBUS.getAllPromotions();
            fillContent(promotions);
            clearDataModalOrder();
        } catch (Exception e) {
            MessageDialog.error(null, e.getMessage());
        }
    }//GEN-LAST:event_btnDTMActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String productId = searchProduct.getText().trim();
        if (!checkExist(productId, tableDesignAdd)) {
            MessageDialog.warning(null, "Sản phẩm đã tồn tại");
            return;
        }
        Optional<Product> productExsist = productBUS.searchProductById(productId);
        if (productExsist.isEmpty()) {
            MessageDialog.warning(null, "Không tìm thấy sản phẩm");
        } else {
            Product product = productExsist.get();
            searchProduct.setText("");
            tableDesignAdd.getModelTable().addRow(new Object[]{product.getProductId(), product.getName(),
                FormatNumber.formatToVND(product.getPrice()), null});
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void clearDataModalOrder() {
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
        spinnerDiscount.setValue(1);
    }

    private void clearDataModalProduct() {
        jDateFromP.setDate(Date.valueOf(LocalDate.now()));
        jDateToP.setDate(Date.valueOf(LocalDate.now()));
        spinnerDiscountP.setValue(1);
        tableDesignAdd.getModelTable().setRowCount(0);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate1 = jDateFromP.getDate();
        LocalDate start = jdate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.util.Date jdate2 = jDateToP.getDate();
        LocalDate end = jdate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String name = txtPromotionProduct.getText().trim();
        double discount = (int) spinnerDiscountP.getValue() * 1.0 / 100;
        if (start.isBefore(LocalDate.now())) {
            MessageDialog.warning(null, "Ngày bắt đầu phải sau ngày hiện tại");
            return;
        }
        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        List<String> listProductIds = new ArrayList<>();
        int index = tableDesignAdd.getModelTable().getRowCount();
        if (index == 0) {
            MessageDialog.warning(null, "Không có sản phẩm");
            return;
        }
        for (int i = 0; i < index; i++) {
            listProductIds.add((String) tableDesignAdd.getModelTable().getValueAt(i, 0));
        }
        try {
            Promotion promotion = new Promotion(null, name, start, end, discount, false, PromotionType.PRODUCT);
            promotionBUS.createPromotion(promotion, listProductIds);
            MessageDialog.info(null, "Thêm chương trình khuyến mãi thành công");
            modalPromotion.dispose();
            List<Promotion> promotions = promotionBUS.getAllPromotions();
            fillContent(promotions);
            clearDataModalProduct();
        } catch (Exception e) {
            MessageDialog.error(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnUpdateOrderActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnUpdateOrderActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate1 = jDateFromUpdate.getDate();
        LocalDate start = jdate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.util.Date jdate2 = jDateToUpdate.getDate();
        LocalDate end = jdate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double discount = (int) spinnerDiscountUpdate.getValue() * 1.0 / 100;
        String name  = txtPromotionOrder1.getText().trim();
        if (start.isBefore(LocalDate.now())) {
            MessageDialog.warning(null, "Ngày bắt đầu phải sau ngày hiện tại");
            return;
        }
        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        int selectedRow = tableDesign.getTable().getSelectedRow();
        String promotionId = (String) tableDesign.getModelTable().getValueAt(selectedRow, 0);
        Promotion promotion = new Promotion(promotionId, name, start, end, discount, false, PromotionType.ORDER);
        if (promotionBUS.updatePromotion(promotion, null)) {
            MessageDialog.info(null, "Cập nhật chương trình khuyến mãi thành công");
            modalUpdateOrder.dispose();
            List<Promotion> promotions = promotionBUS.getAllPromotions();
            fillContent(promotions);
        } else {
            MessageDialog.error(null, "Lỗi khi cập chương trình khuyến mãi");
        }
    }//GEN-LAST:event_btnUpdateOrderActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate1 = jDateFromP1.getDate();
        LocalDate start = jdate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.util.Date jdate2 = jDateToP1.getDate();
        LocalDate end = jdate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double discount = (int) spinnerDiscountP1.getValue() * 1.0 / 100;
        String name = txtPromotionProduct1.getText().trim();
        if (start.isBefore(LocalDate.now())) {
            MessageDialog.warning(null, "Ngày bắt đầu phải sau ngày hiện tại");
            return;
        }
        if (start.isAfter(end)) {
            MessageDialog.warning(null, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }
        int selectedRow = tableDesign.getTable().getSelectedRow();
        String promotionId = (String) tableDesign.getModelTable().getValueAt(selectedRow, 0);
        Promotion promotion = new Promotion(promotionId, name, start, end, discount, false, PromotionType.PRODUCT);
        List<String> listProductIds = new ArrayList<>();
        int index = tableDesignUpdate.getModelTable().getRowCount();
        if (index == 0) {
            MessageDialog.warning(null, "Không có sản phẩm");
            return;
        }
        for (int i = 0; i < index; i++) {
            listProductIds.add((String) tableDesignUpdate.getModelTable().getValueAt(i, 0));
        }
        if (promotionBUS.updatePromotion(promotion, listProductIds)) {
            MessageDialog.info(null, "Cập nhật chương trình khuyến mãi thành công");
            modalUpdateProduct.dispose();
            List<Promotion> promotions = promotionBUS.getAllPromotions();
            fillContent(promotions);
        } else {
            MessageDialog.error(null, "Lỗi khi cập chương trình khuyến mãi");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String productId = searchProduct1.getText().trim();
        if (!checkExist(productId, tableDesignUpdate)) {
            MessageDialog.warning(null, "Sản phẩm đã tồn tại");
            return;
        }
        Optional<Product> productExsist = productBUS.searchProductById(productId);
        if (productExsist.isEmpty()) {
            MessageDialog.warning(null, "Không tìm thấy sản phẩm");
        } else {
            Product product = productExsist.get();
            searchProduct1.setText("");
            tableDesignUpdate.getModelTable().addRow(new Object[]{product.getProductId(), product.getName(),
                FormatNumber.formatToVND(product.getPrice()), null});
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnEmailActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableDesign.getTable().getSelectedRow();
        if (selectedRow < 0) {
            MessageDialog.warning(null, "Hãy chọn khuyến mãi muốn gửi tới khách hàng");
            return;
        }
        String status = (String) tableDesign.getModelTable().getValueAt(selectedRow, 5);
        if (status.equals("Đã phát hành")) {
            MessageDialog.warning(null, "Chương trình khuyến mãi đã phát hành");
            return;
        }
        if (MessageDialog.confirm(null, "Bạn có chắc muốn phát hành chương trình khuyến mãi?", "Phát hành chương trình khuyến mãi")) {
            promotionBUS.sendMail(tableDesign.getTable().getValueAt(selectedRow, 0).toString());
            MessageDialog.info(null, "Gửi mail chương trình khuyến mãi đến khách hàng thành công");
            tableDesign.getTable().setValueAt("Đã phát hành", selectedRow, 5);
        }
    }//GEN-LAST:event_btnEmailActionPerformed

    private boolean checkExist(String productId, TableDesign tableDesignCheck) {
        int index = tableDesignCheck.getModelTable().getRowCount();
        for (int i = 0; i < index; i++) {
            String pid = (String) tableDesignCheck.getModelTable().getValueAt(i, 0);
            if (productId.equals(pid)) {
                return false;
            }
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDTM;
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateOrder;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxType;
    private com.toedter.calendar.JDateChooser jDate;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateFromP;
    private com.toedter.calendar.JDateChooser jDateFromP1;
    private com.toedter.calendar.JDateChooser jDateFromUpdate;
    private com.toedter.calendar.JDateChooser jDateTo;
    private com.toedter.calendar.JDateChooser jDateToP;
    private com.toedter.calendar.JDateChooser jDateToP1;
    private com.toedter.calendar.JDateChooser jDateToUpdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JDialog modalPromotion;
    private javax.swing.JDialog modalUpdateOrder;
    private javax.swing.JDialog modalUpdateProduct;
    private javax.swing.JPanel pnAll;
    private javax.swing.JPanel pnAll1;
    private javax.swing.JPanel pnPromotionOrder;
    private javax.swing.JPanel pnPromotionOrder1;
    private javax.swing.JPanel pnPromotionProduct;
    private javax.swing.JPanel pnPromotionProduct1;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JScrollPane scrollTableDesPre;
    private javax.swing.JScrollPane scrollTableDesPre1;
    private javax.swing.JTextField searchProduct;
    private javax.swing.JTextField searchProduct1;
    private javax.swing.JSpinner spinnerDiscount;
    private javax.swing.JSpinner spinnerDiscountP;
    private javax.swing.JSpinner spinnerDiscountP1;
    private javax.swing.JSpinner spinnerDiscountUpdate;
    private javax.swing.JTabbedPane tabbedPanePromotion;
    private javax.swing.JTextField txtPromotionOrder;
    private javax.swing.JTextField txtPromotionOrder1;
    private javax.swing.JTextField txtPromotionProduct;
    private javax.swing.JTextField txtPromotionProduct1;
    // End of variables declaration//GEN-END:variables
}
