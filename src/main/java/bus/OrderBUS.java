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
import dto.StatsDTO;
import dto.StatsOrderDTO;
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
            
            if(employee == null)
                throw new RuntimeException("Nhân viên không được rỗng");

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
    
    public List<Order> getAllOrders(){
        return orderDAL.findAll();
    }

    public List<Order> search(LocalDateTime start, LocalDateTime end, String txtCustomer, String txtEmployee) {
        return orderDAL.search(start, end, txtCustomer, txtEmployee);
    }
    public Order findById(String orderId){
        return orderDAL.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }
    
    public Order findByIdAndNotInPromotion( String orderId ){
        return orderDAL.findByIdAndNotInPromotion(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }

    public StatsDTO getQuantityAndSumPriceByDate( LocalDateTime start, LocalDateTime end ) {
        List<Order> orders = orderDAL.searchByDate(start,end);
        Integer quantity = orders.size();
        double sumPrice = 0.0;
        for (Order order : orders) {
            sumPrice += order.getTotalPrice();
        }
        return new StatsDTO(quantity, sumPrice);
    }

    public List<StatsOrderDTO> getStatisticByDate(LocalDateTime start, LocalDateTime end){
        List<LocalDate> allDates = new ArrayList<>();
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            allDates.add(date);
        }

        Map<LocalDate, Double> resultMap = new HashMap<>();
        List<Object[]> results = orderDAL.statisByDate(start,end);
        for (Object[] row : results) {
            LocalDate orderDate = ((java.sql.Date) row[0]).toLocalDate();
            Double totalPrice = ((Number) row[1]).doubleValue();
            resultMap.put(orderDate, totalPrice);
        }
        List<StatsOrderDTO> statsList = new ArrayList<>();
        for (LocalDate date : allDates) {
            // Nếu ngày không có trong resultMap, gán sumPrice là 0
            Double sumPrice = resultMap.getOrDefault(date, 0.0);
            StatsOrderDTO statsOrderDTO = new StatsOrderDTO(date, sumPrice);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }
    
    public List<Order> getOrderByDateAndEmp(LocalDateTime start, LocalDateTime end, String employeeID) {
        return orderDAL.findOrderByDateAndEmp(start, end, employeeID);
    }

}
