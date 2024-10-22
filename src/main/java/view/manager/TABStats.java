package view.manager;

import bus.OrderBUS;
import bus.PurchaseOrderBUS;
import bus.ReturnOrderBUS;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dto.StatsDTO;
import dto.StatsOrderDTO;
import gui.barchart.ModelChart;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import util.FormatDate;
import util.FormatNumber;
import util.ResizeImage;
import view.login.LoadApplication;

/**
 *
 * @author Hoang
 */
public class TABStats extends javax.swing.JPanel {

    private final OrderBUS orderBUS;
    private final PurchaseOrderBUS purchaseOrderBUS;
    private final ReturnOrderBUS returnOrderBUS;

    public TABStats() {
        orderBUS = LoadApplication.orderBUS;
        returnOrderBUS = LoadApplication.returnOrderBUS;
        purchaseOrderBUS = LoadApplication.purchaseOrderBUS;
        initComponents();
        initChart();
        initHeader();
        tableLayout();
        chart.start();
    }

    private void initHeader() {
        lblIconReturn.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/returnProductIcon.svg")), 55, 55));
        lblIconOrder.setIcon(ResizeImage.resizeImage(new FlatSVGIcon(getClass().getResource("/img/orderIcon.svg")), 55, 55));
        LocalDate localDateStart = LocalDate.now();
        LocalDate localDateEnd = LocalDate.now();

        LocalDateTime start = localDateStart.atStartOfDay(); // 00:00:00
        LocalDateTime end = localDateEnd.atTime(23, 59, 59, 999999999); // 23:59:59.999999999
        StatsDTO quantityAndSumPriceOrder
                = orderBUS.getQuantityAndSumPriceByDate(start, end);

        StatsDTO quantityAndSumPriceReturnOrder
                = returnOrderBUS.getQuantityAndSumPriceByDate(start, end);
        
        StatsDTO quantityAndSumPricePurchase
                = purchaseOrderBUS.getQuantityAndSumPriceByDate(start, end);

        txtQuantityOrder.setText(quantityAndSumPriceOrder.getQuantity() + " đơn hàng");
        txtSumPriceOrder.setText(FormatNumber.formatToVND(quantityAndSumPriceOrder.getSumPrice()));

        txtQuantityReturn.setText(quantityAndSumPriceReturnOrder.getQuantity() + " đơn trả hàng");
        txtSumPriceReturn.setText(FormatNumber.formatToVND(quantityAndSumPriceReturnOrder.getSumPrice()));
    }

    private void initChart() {
        lblChart.setText("thống kê doanh thu 7 ngày gần nhất".toUpperCase());
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        loadDataChart();
    }

    public void loadDataChart() {
        LocalDateTime start = LocalDateTime.now();
        List<StatsOrderDTO> listTK = orderBUS.getStatisticByDate(start.minusDays(7).toLocalDate().atStartOfDay(), start);
        for (StatsOrderDTO e : listTK) {
            chart.addData(new ModelChart(FormatDate.formatDateNoHour(e.getTime()),
                    new double[]{e.getSumPrice()}));
        }
    }

    private void tableLayout() {
//        String[] header = new String[]{"STT", "Thời gian", "Doanh thu", "Chi phí", "Lợi nhuận"};
//        modal = new DefaultTableModel();
//        modal.setColumnIdentifiers(header);
//        table.setModel(modal);
//
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        table.setDefaultRenderer(Object.class, centerRenderer);
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
//        table.getColumnModel().getColumn(0).setPreferredWidth(30);

//        loadTable(listTK);
//        sortTable();
    }

    private void sortTable() {
//        table.setAutoCreateRowSorter(true);
//        TableSorter.configureTableColumnSorter(table, 0, TableSorter.STRING_COMPARATOR);
    }

//    public void loadTable(List<ThongKe> list) {
//        modal.setRowCount(0);
//        int stt = 1;
//        for (ThongKe e : list) {
//            modal.addRow(new Object[]{
//                stt, Formatter.FormatDate(e.getThoiGian()), Formatter.FormatVND(e.getDoanhThu()), Formatter.FormatVND(e.getChiPhi()), Formatter.FormatVND(e.getLoiNhuan())
//            });
//            stt++;
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtCus = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        txtEmp = new javax.swing.JTextField();
        jDateTo = new com.toedter.calendar.JDateChooser();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtOrder = new javax.swing.JButton();
        pnContent = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblIconOrder = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtSumPriceOrder = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQuantityOrder = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblIconReturn = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSumPriceReturn = new javax.swing.JLabel();
        txtQuantityReturn = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblIconCompare = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtQuantityPurchase = new javax.swing.JLabel();
        txtSumPricePurchase = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblChart1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblChart = new javax.swing.JLabel();
        chart = new gui.barchart.Chart();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 6, new java.awt.Color(255, 255, 255)));
        setMinimumSize(new java.awt.Dimension(1130, 800));
        setPreferredSize(new java.awt.Dimension(1130, 800));
        setLayout(new java.awt.BorderLayout(0, 6));

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        headerPanel.setMinimumSize(new java.awt.Dimension(1190, 104));
        headerPanel.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(590, 100));

        txtCus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCus.setMinimumSize(new java.awt.Dimension(300, 40));
        txtCus.setPreferredSize(new java.awt.Dimension(300, 40));

        btnSearch.setBackground(new java.awt.Color(115, 165, 71));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        txtEmp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmp.setMinimumSize(new java.awt.Dimension(300, 40));
        txtEmp.setPreferredSize(new java.awt.Dimension(300, 40));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("-->");

        txtOrder.setBackground(new java.awt.Color(115, 165, 71));
        txtOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        headerPanel.add(jPanel14, java.awt.BorderLayout.CENTER);

        add(headerPanel, java.awt.BorderLayout.NORTH);

        pnContent.setBackground(new java.awt.Color(255, 255, 255));
        pnContent.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 130));
        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 16, 8));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(232, 232, 232)));
        jPanel3.setPreferredSize(new java.awt.Dimension(370, 110));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(120, 110));
        jPanel7.setLayout(new java.awt.BorderLayout());

        lblIconOrder.setBackground(new java.awt.Color(255, 255, 255));
        lblIconOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconOrder.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblIconOrder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(lblIconOrder, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(120, 110));
        jPanel6.setPreferredSize(new java.awt.Dimension(120, 110));

        txtSumPriceOrder.setFont(new java.awt.Font("Roboto Mono", 1, 36)); // NOI18N
        txtSumPriceOrder.setForeground(new java.awt.Color(51, 51, 51));
        txtSumPriceOrder.setText("50");
        txtSumPriceOrder.setPreferredSize(new java.awt.Dimension(100, 16));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Doanh thu");

        txtQuantityOrder.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtQuantityOrder.setForeground(new java.awt.Color(51, 51, 51));
        txtQuantityOrder.setText("50 phiếu hóa đơn");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSumPriceOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQuantityOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtQuantityOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSumPriceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(232, 232, 232)));
        jPanel8.setPreferredSize(new java.awt.Dimension(370, 100));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMinimumSize(new java.awt.Dimension(120, 110));
        jPanel9.setPreferredSize(new java.awt.Dimension(120, 110));
        jPanel9.setLayout(new java.awt.BorderLayout());

        lblIconReturn.setBackground(new java.awt.Color(255, 255, 255));
        lblIconReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconReturn.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblIconReturn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(lblIconReturn, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(120, 110));
        jPanel10.setPreferredSize(new java.awt.Dimension(120, 110));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Trả hàng");

        txtSumPriceReturn.setFont(new java.awt.Font("Roboto Mono", 1, 36)); // NOI18N
        txtSumPriceReturn.setForeground(new java.awt.Color(51, 51, 51));
        txtSumPriceReturn.setText("50");
        txtSumPriceReturn.setPreferredSize(new java.awt.Dimension(100, 16));

        txtQuantityReturn.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtQuantityReturn.setForeground(new java.awt.Color(51, 51, 51));
        txtQuantityReturn.setText("30 đơn trả hàng");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSumPriceReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQuantityReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(txtQuantityReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSumPriceReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(232, 232, 232)));
        jPanel11.setPreferredSize(new java.awt.Dimension(370, 100));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setMinimumSize(new java.awt.Dimension(120, 110));
        jPanel12.setPreferredSize(new java.awt.Dimension(120, 110));
        jPanel12.setLayout(new java.awt.BorderLayout());

        lblIconCompare.setBackground(new java.awt.Color(255, 255, 255));
        lblIconCompare.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconCompare.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblIconCompare.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel12.add(lblIconCompare, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel12, java.awt.BorderLayout.WEST);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setMinimumSize(new java.awt.Dimension(120, 110));
        jPanel13.setPreferredSize(new java.awt.Dimension(120, 110));

        txtQuantityPurchase.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtQuantityPurchase.setForeground(new java.awt.Color(51, 51, 51));
        txtQuantityPurchase.setText("30 đơn nhập hàng");

        txtSumPricePurchase.setFont(new java.awt.Font("Roboto Mono", 1, 36)); // NOI18N
        txtSumPricePurchase.setForeground(new java.awt.Color(51, 51, 51));
        txtSumPricePurchase.setText("50");
        txtSumPricePurchase.setPreferredSize(new java.awt.Dimension(100, 16));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Nhập hàng");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSumPricePurchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQuantityPurchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(txtQuantityPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSumPricePurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel11);

        jPanel4.add(jPanel2, java.awt.BorderLayout.CENTER);

        lblChart1.setBackground(new java.awt.Color(255, 255, 255));
        lblChart1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblChart1.setText("KẾT QUẢ BÁN HÀNG HÔM NAY");
        lblChart1.setPreferredSize(new java.awt.Dimension(37, 30));
        jPanel4.add(lblChart1, java.awt.BorderLayout.PAGE_START);

        pnContent.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(232, 232, 232)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1188, 30));
        jPanel5.setLayout(new java.awt.BorderLayout());

        lblChart.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        lblChart.setText("Thống kê");
        lblChart.setMaximumSize(new java.awt.Dimension(72, 30));
        lblChart.setMinimumSize(new java.awt.Dimension(72, 30));
        lblChart.setPreferredSize(new java.awt.Dimension(37, 30));
        jPanel5.add(lblChart, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);
        jPanel1.add(chart, java.awt.BorderLayout.CENTER);

        pnContent.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(pnContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
//        // TODO add your handling code here:
//        java.util.Date date1 = jDateFrom.getDate();
//        java.util.Date date2 = jDateTo.getDate();
//
//        LocalDate localDateStart = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate localDateEnd = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        LocalDateTime start = localDateStart.atStartOfDay(); // 00:00:00
//        LocalDateTime end = localDateEnd.atTime(23, 59, 59, 999999999); // 23:59:59.999999999
//
//        if (start.isAfter(end)) {
//            MessageDialog.warring(null, "Ngày bắt đầu phải trước ngày kết thúc");
//            return;
//        }
//        String txtCustomer =txtCus.getText().trim();
//        String txtEmployee = txtEmp.getText().trim();
//
//        List<Order> orders = orderBUS.search(start, end, txtCustomer, txtEmployee);
//        fillContent(orders);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderActionPerformed

    }//GEN-LAST:event_txtOrderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private gui.barchart.Chart chart;
    private javax.swing.JPanel headerPanel;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblChart1;
    private javax.swing.JLabel lblIconCompare;
    private javax.swing.JLabel lblIconOrder;
    private javax.swing.JLabel lblIconReturn;
    private javax.swing.JPanel pnContent;
    private javax.swing.JTextField txtCus;
    private javax.swing.JTextField txtEmp;
    private javax.swing.JButton txtOrder;
    private javax.swing.JLabel txtQuantityOrder;
    private javax.swing.JLabel txtQuantityPurchase;
    private javax.swing.JLabel txtQuantityReturn;
    private javax.swing.JLabel txtSumPriceOrder;
    private javax.swing.JLabel txtSumPricePurchase;
    private javax.swing.JLabel txtSumPriceReturn;
    // End of variables declaration//GEN-END:variables
}
