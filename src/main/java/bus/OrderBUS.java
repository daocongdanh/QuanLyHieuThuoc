/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.OrderDAL;
import dal.ProductDAL;
import dal.ProductTransactionHistoryDAL;
import dal.UnitDetailDAL;
import dto.OrderDTO;
import dto.StatsPriceAndQuantityDTO;
import dto.StatsOrderByDayDTO;
import dto.StatsOrderByYearDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.*;
import enums.PaymentMethod;
import enums.PromotionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import util.GeneratePDF;

/**
 *
 * @author daoducdanh
 */
public class OrderBUS {

    private final OrderDAL orderDAL;
    private final BatchDAL batchDAL;
    private final UnitDetailDAL unitDetailDAL;
    private final ProductDAL productDAL;
    private final PromotionBUS promotionBUS;
    private final ProductTransactionHistoryDAL productTransactionHistoryDAL;
    private final EntityTransaction transaction;
    private final GeneratePDF generatePDF = new GeneratePDF();

    public OrderBUS(EntityManager entityManager) {
        this.orderDAL = new OrderDAL(entityManager);
        this.batchDAL = new BatchDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
        this.productDAL = new ProductDAL(entityManager);
        this.promotionBUS = new PromotionBUS(entityManager);
        this.productTransactionHistoryDAL = new ProductTransactionHistoryDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    public boolean createOrder(Employee employee, Customer customer, Promotion promotion,
            List<OrderDTO> orderDTOs) {
        try {
            transaction.begin();

            if (employee == null) {
                throw new RuntimeException("Nhân viên không được rỗng");
            }

            List<OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDTO orderDTO : orderDTOs) {
                Unit unit = orderDTO.getUnitDetail().getUnit();
                UnitDetail unitDetail
                        = unitDetailDAL.findByProductAndUnit(orderDTO.getProductId(), unit.getUnitId());
                Batch batch = batchDAL.findByNameAndProduct(orderDTO.getBatchName(), orderDTO.getProductId());
                Product product = productDAL.findById(orderDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
                Promotion exsistPromotion = promotionBUS.getPromotionByProduct(product);
                double discount = exsistPromotion != null ? exsistPromotion.getDiscount() : 0;
                OrderDetail orderDetail = new OrderDetail(orderDTO.getQuantity(),
                        product.getSellingPrice() * unitDetail.getConversionRate(),
                        discount, batch, unitDetail);
                orderDetails.add(orderDetail);
                int stock = batch.getStock() - orderDTO.getQuantity() * unitDetail.getConversionRate();
                if (stock < 0) {
                    throw new RuntimeException("Số lượng không đủ với lô hàng: " + batch.getName());
                }
                batch.setStock(stock);
            }
            Order order;
            if (promotion != null) {
                if (!promotion.getPromotionType().equals(PromotionType.ORDER)) {
                    throw new RuntimeException("Không phải khuyến mãi trên hóa đơn");
                }
                if (promotion.getStartedDate().isAfter(LocalDateTime.now())) {
                    throw new RuntimeException("Chưa đến đợt khuyến mãi");
                }
                if (promotion.getEndedDate().isBefore(LocalDateTime.now())) {
                    throw new RuntimeException("Khuyến mãi đã hết hạn");
                }
                order = new Order(null, LocalDateTime.now(), PaymentMethod.CASH, employee, customer, promotion, orderDetails);
            } else {
                order = new Order(null, LocalDateTime.now(), PaymentMethod.CASH, employee, customer, null, orderDetails);
            }

            orderDAL.insert(order);
            Map<String, List<OrderDetail>> map = orderDetails.stream()
                    .collect(Collectors.groupingBy(
                            orderDetail -> orderDetail.getUnitDetail().getProduct().getProductId(),
                            LinkedHashMap::new,
                            Collectors.toList()
                    ));
            map.forEach((key, value) -> {
                OrderDetail orderDetail = value.get(0);
                UnitDetail unitDetail = orderDetail.getUnitDetail();
                Product product = unitDetail.getProduct();
                int sumQuantity = value.stream()
                        .mapToInt(x -> x.getQuantity() * x.getUnitDetail().getConversionRate())
                        .sum();
                double costPrice = product.getPurchasePrice() * sumQuantity;
                double transactionPrice = value.stream()
                        .mapToDouble(OrderDetail::getLineTotal)
                        .sum();
                int finalStock = batchDAL.getFinalStockByProduct(product.getProductId());
                ProductTransactionHistory productTransactionHistory
                        = new ProductTransactionHistory(order.getOrderId(), order.getOrderDate(),
                                "Bán hàng", order.getCustomer() == null ? "Khách vãng lai" : (order.getCustomer().getName()),
                                transactionPrice, costPrice, -sumQuantity, finalStock, product);
                productTransactionHistoryDAL.insert(productTransactionHistory);

            });

            generatePDF.GeneratePDF(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    public List<Order> getAllOrders() {
        return orderDAL.findAll();
    }

    public List<Order> search(LocalDateTime start, LocalDateTime end, String txtCustomer, String txtEmployee) {
        return orderDAL.search(start, end, txtCustomer, txtEmployee);
    }

    public Order findById(String orderId) {
        return orderDAL.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }

    public Order findByIdAndNotInPromotion(String orderId) {
        return orderDAL.findByIdAndNotInPromotion(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }

    public StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) {
        List<Order> orders = orderDAL.searchByDate(start, end);
        Integer quantity = orders.size();
        double sumPrice = 0.0;
        for (Order order : orders) {
            sumPrice += order.getTotalPrice();
        }
        return new StatsPriceAndQuantityDTO(quantity, sumPrice);
    }

    public List<StatsOrderByDayDTO> getStatisticByDate(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        List<Object[]> results = orderDAL.statisByDate(start, end);
        return getStatsOrderDTOS(start, end, results);
    }

    public List<StatsOrderByDayDTO> getStatisticInDay(LocalDateTime now) {
        // Đặt ngày bắt đầu và kết thúc cho ngày hiện tại
        LocalDate startDate = now.toLocalDate();
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = startDate.atTime(23, 59, 59); // Kết thúc vào 23:59:59

        // Danh sách để lưu doanh thu theo giờ
        List<StatsOrderByDayDTO> statsList = new ArrayList<>();
        Map<Integer, Double> resultMap = new HashMap<>();
        Map<Integer, Integer> quantityMap = new HashMap<>(); // Bản đồ lưu số lượng đơn hàng theo giờ

        // Lấy kết quả từ cơ sở dữ liệu theo giờ
        List<Object[]> results = orderDAL.statisByHour(startOfDay, endOfDay);

        // Lưu kết quả vào resultMap và quantityMap
        for (Object[] row : results) {
            int hour = ((java.sql.Time) row[0]).toLocalTime().getHour();
            Double totalPrice = ((Number) row[1]).doubleValue();
            Integer quantityOrder = ((Number) row[2]).intValue(); // Lấy số lượng đơn hàng từ kết quả

            // Lưu tổng giá trị và số lượng đơn hàng vào bản đồ
            resultMap.put(hour, totalPrice);
            quantityMap.put(hour, quantityOrder);
        }

        // Lặp qua các giờ trong ngày để tạo danh sách thống kê
        for (int hour = 0; hour < 24; hour++) {
            Double sumPrice = resultMap.getOrDefault(hour, 0.0);
            Integer quantityOrder = quantityMap.getOrDefault(hour, 0); // Lấy số lượng đơn hàng
            StatsOrderByDayDTO statsOrderDTO = new StatsOrderByDayDTO(now.withHour(hour), sumPrice, quantityOrder);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }


    public List<StatsOrderByDayDTO> searchStats(LocalDateTime start, LocalDateTime end, String productType, String paymentType, String promotion) {
        List<Object[]> results = orderDAL.findStats(start, end, productType, paymentType, promotion);

        return getStatsOrderDTOS(start, end, results);
    }

    private List<StatsOrderByDayDTO> getStatsOrderDTOS(LocalDateTime start, LocalDateTime end, List<Object[]> results) {
        List<StatsOrderByDayDTO> statsList = new ArrayList<>();
        Map<LocalDate, Double> resultMap = new HashMap<>();
        Map<LocalDate, Integer> quantityMap = new HashMap<>();
        for (Object[] row : results) {
            LocalDate orderDate = ((java.sql.Date) row[0]).toLocalDate();
            Double totalPrice = ((Number) row[1]).doubleValue();
            Integer quantityOrder = ((Number) row[2]).intValue();
            resultMap.put(orderDate, totalPrice);
            quantityMap.put(orderDate, quantityOrder);
        }

        for (LocalDate date = start.toLocalDate(); !date.isAfter(end.toLocalDate()); date = date.plusDays(1)) {
            Double sumPrice = resultMap.getOrDefault(date, 0.0);
            Integer totalQuantity = quantityMap.getOrDefault(date, 0);
            StatsOrderByDayDTO statsOrderDTO = new StatsOrderByDayDTO(date.atStartOfDay(), sumPrice, totalQuantity);
            statsList.add(statsOrderDTO);
        }


        return statsList;
    }

    public List<StatsOrderByYearDTO> searchStatsByYear(LocalDateTime start, LocalDateTime end, String productType, String paymentType, String promotion) {
        List<Object[]> results = orderDAL.findStats(start, end, productType, paymentType, promotion);
        List<StatsOrderByYearDTO> statsList = new ArrayList<>();
        Map<Integer, Double> resultMap = new HashMap<>();
        Map<Integer, Integer> quantityMap = new HashMap<>();

        for (Object[] row : results) {
            int orderYear = ((java.sql.Date) row[0]).toLocalDate().getYear();
            Double totalPrice = ((Number) row[1]).doubleValue();
            Integer quantityOrder = ((Number) row[2]).intValue();

            resultMap.put(orderYear, resultMap.getOrDefault(orderYear, 0.0) + totalPrice);
            quantityMap.put(orderYear, quantityMap.getOrDefault(orderYear, 0) + quantityOrder);
        }

        for (int year = start.getYear(); year <= end.getYear(); year++) {
            Double sumPrice = resultMap.getOrDefault(year, 0.0);
            Integer totalQuantity = quantityMap.getOrDefault(year, 0);
            StatsOrderByYearDTO statsOrderDTO = new StatsOrderByYearDTO(year, sumPrice, totalQuantity);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }


}
