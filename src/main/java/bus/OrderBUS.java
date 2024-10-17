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
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.*;
import enums.PaymentMethod;
import enums.PromotionType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import util.GeneratePDF;

/**
 *
 * @author daoducdanh
 */
public class OrderBUS {

    private OrderDAL orderDAL;
    private BatchDAL batchDAL;
    private UnitDetailDAL unitDetailDAL;
    private ProductDAL productDAL;
    private PromotionBUS promotionBUS;
    private ProductTransactionHistoryDAL productTransactionHistoryDAL;
    private EntityTransaction transaction;
    private GeneratePDF generatePDF = new GeneratePDF();

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
                if (promotion.getStartedDate().isAfter(LocalDate.now())) {
                    throw new RuntimeException("Chưa đến đợt khuyến mãi");
                }
                if (promotion.getEndedDate().isBefore(LocalDate.now())) {
                    throw new RuntimeException("Khuyến mãi đã hết hạn");
                }
                order = new Order(null, LocalDate.now(), PaymentMethod.CASH, employee, customer, promotion, orderDetails);
            } else {
                order = new Order(null, LocalDate.now(), PaymentMethod.CASH, employee, customer, null, orderDetails);
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
    
    public Order findById(String orderId){
        return orderDAL.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }
    
    public Order findByIdAndNotInPromotion( String orderId ){
        return orderDAL.findByIdAndNotInPromotion(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }
    
}
