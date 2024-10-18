/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.manager;

import bus.PromotionBUS;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import entity.Promotion;
import enums.PromotionType;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import util.FormatDate;
import util.MessageDialog;
import util.ResizeImage;
import view.common.TableDesign;

/**
 *
 * @author Hoang
 */


public class TABPromotion extends javax.swing.JPanel {

    private PromotionBUS promotionBUS;
    private TableDesign tableDesign;

    public TABPromotion() {
        promotionBUS = new PromotionBUS(ConnectDB.getEntityManager());
        initComponents();
        setUIManager();
        fillTable();
        addIconFeature();
    }

    private void setUIManager() {
//        txtNameUnitAdd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đơn vị tính");
//        txtNameUnitEdit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đơn vị tính mới");
        jDate.setDate(Date.valueOf(LocalDate.now()));
        jDateFrom.setDate(Date.valueOf(LocalDate.now()));
        jDateTo.setDate(Date.valueOf(LocalDate.now()));
        UIManager.put("Button.arc", 10);
    }

    private void fillTable() {
        String[] headers = {"Mã khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá", "Loại khuyến mãi", "Trạng thái"};
        List<Integer> tableWidths = Arrays.asList(200, 200, 200, 200, 200, 200);
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

    private void fillContent(List<Promotion> promotions) {
        tableDesign.getModelTable().setRowCount(0);
        DecimalFormat df = new DecimalFormat("#%");
        for (Promotion promotion : promotions) {
            tableDesign.getModelTable().addRow(new Object[]{promotion.getPromotionId(), FormatDate.formatDate(promotion.getStartedDate()),
            FormatDate.formatDate(promotion.getEndedDate()), df.format(promotion.getDiscount()), 
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
        pnPromotionProduct = new javax.swing.JPanel();
        btnApply = new javax.swing.JButton();
        scrollTableDesPre = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        spinnerQuantity = new javax.swing.JSpinner();
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
        modalPromotion.setMinimumSize(new java.awt.Dimension(1021, 580));
        modalPromotion.setResizable(false);

        pnAll1.setBackground(new java.awt.Color(255, 255, 255));
        pnAll1.setMaximumSize(new java.awt.Dimension(1021, 580));
        pnAll1.setMinimumSize(new java.awt.Dimension(1021, 580));

        pnPromotionOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnPromotionOrder.setMaximumSize(new java.awt.Dimension(1021, 580));
        pnPromotionOrder.setMinimumSize(new java.awt.Dimension(1021, 580));

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

        javax.swing.GroupLayout pnPromotionOrderLayout = new javax.swing.GroupLayout(pnPromotionOrder);
        pnPromotionOrder.setLayout(pnPromotionOrderLayout);
        pnPromotionOrderLayout.setHorizontalGroup(
            pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(spinnerDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jDateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnPromotionOrderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(409, 409, 409))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionOrderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        pnPromotionOrderLayout.setVerticalGroup(
            pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionOrderLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnPromotionOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinnerDiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(btnDTM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        tabbedPanePromotion.addTab("Khuyến mãi trên hóa đơn", pnPromotionOrder);

        pnPromotionProduct.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout pnPromotionProductLayout = new javax.swing.GroupLayout(pnPromotionProduct);
        pnPromotionProduct.setLayout(pnPromotionProductLayout);
        pnPromotionProductLayout.setHorizontalGroup(
            pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPromotionProductLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPromotionProductLayout.createSequentialGroup()
                        .addComponent(scrollTableDesPre, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(pnPromotionProductLayout.createSequentialGroup()
                        .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnPromotionProductLayout.createSequentialGroup()
                                .addComponent(spinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnPromotionProductLayout.setVerticalGroup(
            pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPromotionProductLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnPromotionProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnApply, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(spinnerQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTableDesPre, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
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
                .addComponent(tabbedPanePromotion, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
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
                .addGap(0, 1, Short.MAX_VALUE))
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
                btnUpdateActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Ngày hiệu lực");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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
                btnSearchActionPerformed(evt);
            }
        });

        btnEmail.setBackground(new java.awt.Color(115, 165, 71));
        btnEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnEmail.setText("Gửi mail");
        btnEmail.setMaximumSize(new java.awt.Dimension(150, 40));
        btnEmail.setMinimumSize(new java.awt.Dimension(150, 40));
        btnEmail.setPreferredSize(new java.awt.Dimension(150, 40));

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
                .addGap(18, 18, 18)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
//        int selectedRow = tableDesign.getTable().getSelectedRow();
//        if (selectedRow != -1) {
//            unitIdEdit = (String) tableDesign.getTable().getValueAt(selectedRow, 0);
//            txtNameUnitEdit.setText((String) tableDesign.getTable().getValueAt(selectedRow, 1));
//            modalEditUnit.setLocationRelativeTo(null);
//            modalEditUnit.setVisible(true);
//            if (tableDesign.getTable().getCellEditor() != null) {
//                tableDesign.getTable().getCellEditor().stopCellEditing();
//            }
//        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        modalPromotion.setLocationRelativeTo(null);
        modalPromotion.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate = jDate.getDate();
        LocalDate date = jdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String promotionType = jComboBoxType.getSelectedItem().toString();
        List<Promotion> promotions = promotionBUS.search(date, promotionType);
        fillContent(promotions);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDTMActionPerformed
        // TODO add your handling code here:
        java.util.Date jdate1 = jDate.getDate();
        LocalDate start = jdate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.util.Date jdate2 = jDate.getDate();
        LocalDate end = jdate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double discount = (int) spinnerDiscount.getValue() * 1.0 / 100;
        try{
            Promotion promotion = new Promotion(null, start, end, discount, false, PromotionType.ORDER);
            promotionBUS.createPromotion(promotion, null);
            MessageDialog.info(null, "Thêm chương trình khuyến mãi thành công");
            modalPromotion.dispose();
            List<Promotion> promotions = promotionBUS.getAllPromotions();
            fillContent(promotions);
        }
        catch(Exception e ){
            MessageDialog.error(null, e.getMessage());
        }
    }//GEN-LAST:event_btnDTMActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        // TODO add your handling code here:
//        if (prescriptionDetails.isEmpty()) {
//            MessageDialog.warring(null, "Không có sản phẩm nào trong đơn thuốc mẫu !!!");
//        } else {
//            TabOrder tabHoaDon = (TabOrder) tabbedPane.getSelectedComponent();
//            int quantityPre = (int) spinnerQuantity.getValue();
//            if (tabHoaDon.addDonThuocMau(prescriptionDetails, quantityPre)) {
//                modalPromotion.dispose();
//            }
//        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private String unitIdEdit;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnDTM;
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JComboBox<String> jComboBoxType;
    private com.toedter.calendar.JDateChooser jDate;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JDialog modalPromotion;
    private javax.swing.JPanel pnAll;
    private javax.swing.JPanel pnAll1;
    private javax.swing.JPanel pnPromotionOrder;
    private javax.swing.JPanel pnPromotionProduct;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JScrollPane scrollTableDesPre;
    private javax.swing.JSpinner spinnerDiscount;
    private javax.swing.JSpinner spinnerQuantity;
    private javax.swing.JTabbedPane tabbedPanePromotion;
    // End of variables declaration//GEN-END:variables
}
