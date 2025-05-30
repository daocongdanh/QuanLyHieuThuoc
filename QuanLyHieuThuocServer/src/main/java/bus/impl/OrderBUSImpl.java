/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus.impl;

import bus.OrderBUS;
import bus.PDFBUS;
import bus.PromotionBUS;
import dal.BatchDAL;
import dal.OrderDAL;
import dal.ProductDAL;
import dal.impl.BatchDALImpl;
import dal.impl.OrderDALImpl;
import dal.impl.ProductDALImpl;
import dto.OrderDTO;
import dto.StatsPriceAndQuantityDTO;
import dto.StatsOrderByDayDTO;
import dto.StatsOrderByYearDTO;
import dto.StatsProductDTO;
import enums.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.*;
import enums.PaymentMethod;
import enums.PromotionType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import util.StringUtils;

public class OrderBUSImpl extends UnicastRemoteObject implements OrderBUS {

    private final OrderDAL orderDAL;
    private final BatchDAL batchDAL;
    private final ProductDAL productDAL;
    private final PromotionBUS promotionBUS;
    private final EntityTransaction transaction;
    private final PDFBUS pdfBUS = new PDFBUSImpl();

    public OrderBUSImpl(EntityManager entityManager) throws RemoteException {
        this.orderDAL = new OrderDALImpl(entityManager);
        this.batchDAL = new BatchDALImpl(entityManager);
        this.productDAL = new ProductDALImpl(entityManager);
        this.promotionBUS = new PromotionBUSImpl(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public synchronized Order createOrder(Employee employee, Customer customer, Promotion promotion,
                               List<OrderDTO> orderDTOs) throws RemoteException {
        try {
            transaction.begin();

            if (employee == null) {
                throw new RuntimeException("Nhân viên không được rỗng");
            }

            List<OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDTO orderDTO : orderDTOs) {
                Batch batch = batchDAL.findByNameAndProduct(orderDTO.getBatchName(), orderDTO.getProductId());
                Product product = productDAL.findById(orderDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
                Promotion exsistPromotion = promotionBUS.getPromotionByProduct(product);
                double discount = exsistPromotion != null ? exsistPromotion.getDiscount() : 0;
                OrderDetail orderDetail = new OrderDetail(orderDTO.getQuantity(),
                        product.getSellingPrice(),
                        discount, batch);
                orderDetails.add(orderDetail);
                int stock = batch.getStock() - orderDTO.getQuantity();
                if (stock < 0) {
                    throw new RuntimeException("Không đủ số lượng");
                }
                batch.setStock(stock);
                batchDAL.update(batch);
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
                order = new Order(null, LocalDateTime.now(), PaymentMethod.CASH, employee, customer, promotion, orderDetails);
            } else {
                order = new Order(null, LocalDateTime.now(), PaymentMethod.CASH, employee, customer, null, orderDetails);
            }

            orderDAL.insert(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return null;
        }
    }
    @Override
    public List<Order> getAllOrders() throws RemoteException {
        return orderDAL.findAll();
    }
    @Override
    public List<Order> search(LocalDateTime start, LocalDateTime end, String txtCustomer, String txtEmployee) throws RemoteException {
        return orderDAL.search(start, end, txtCustomer, txtEmployee);
    }
    @Override
    public Order findById(String orderId) throws RemoteException {
        return orderDAL.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tồn tại hóa đơn này"));
    }
    @Override
    public StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
        List<Order> orders = orderDAL.searchByDate(start, end);
        Integer quantity = orders.size();
        double sumPrice = 0.0;
        for (Order order : orders) {
            sumPrice += order.getTotalPrice();
        }
        return new StatsPriceAndQuantityDTO(quantity, sumPrice);
    }
    @Override
    public List<StatsOrderByDayDTO> getStatisticByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        List<Order> orders = orderDAL.statisByDate(start, end);

        List<StatsOrderByDayDTO> statsList = new ArrayList<>();
        Map<LocalDate, Double> resultMap = new HashMap<>();
        Map<LocalDate, Integer> quantityMap = new HashMap<>();

        for (Order order : orders) {
            LocalDate orderDate = order.getOrderDate().toLocalDate();
            Double totalPrice = order.getTotalPrice();
            if (resultMap.containsKey(orderDate)) {
                resultMap.put(orderDate, resultMap.get(orderDate) + totalPrice);
            } else {
                resultMap.put(orderDate, totalPrice);
            }
            if (quantityMap.containsKey(orderDate)) {
                quantityMap.put(orderDate, quantityMap.get(orderDate) + 1);
            } else {
                quantityMap.put(orderDate, 1);
            }
        }

        for (LocalDate date = start.toLocalDate(); !date.isAfter(end.toLocalDate()); date = date.plusDays(1)) {
            Double sumPrice = resultMap.getOrDefault(date, 0.0);
            Integer totalQuantity = quantityMap.getOrDefault(date, 0);
            StatsOrderByDayDTO statsOrderDTO = new StatsOrderByDayDTO(date.atStartOfDay(), sumPrice, totalQuantity);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }
    @Override
    public List<StatsOrderByDayDTO> getStatisticInDay(LocalDateTime now) throws RemoteException {
        LocalDate startDate = now.toLocalDate();
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = startDate.atTime(23, 59, 59);

        List<StatsOrderByDayDTO> statsList = new ArrayList<>();
        Map<Integer, Double> resultMap = new HashMap<>();
        Map<Integer, Integer> quantityMap = new HashMap<>();

        List<Order> orders = orderDAL.statisByDate(startOfDay, endOfDay);

        // Lưu kết quả vào resultMap và quantityMap
        for (Order order : orders) {
            int hour = order.getOrderDate().toLocalTime().getHour();
            Double totalPrice = order.getTotalPrice();
            if (resultMap.containsKey(hour)) {
                resultMap.put(hour, resultMap.get(hour) + totalPrice);
            } else {
                resultMap.put(hour, totalPrice);
            }
            if (quantityMap.containsKey(hour)) {
                quantityMap.put(hour, quantityMap.get(hour) + 1);
            } else {
                quantityMap.put(hour, 1);
            }
        }

        for (int hour = 0; hour < 24; hour++) {
            Double sumPrice = resultMap.getOrDefault(hour, 0.0);
            Integer quantityOrder = quantityMap.getOrDefault(hour, 0);
            StatsOrderByDayDTO statsOrderDTO = new StatsOrderByDayDTO(now.withHour(hour), sumPrice, quantityOrder);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }

    private List<Order> dataFilter(List<Order> orders, String productType, String paymentType, String promotion) throws RemoteException {
        if (StringUtils.isNotBlank(promotion)) {
            if ("Không khuyến mãi".equals(promotion)) {
                orders = orders.stream()
                        .filter(order -> order.getPromotion() == null
                                && order.getOrderDetails().stream()
                                .noneMatch(detail -> detail.getDiscount() != 0.0))
                        .toList();
            } else if ("Có khuyến mãi".equals(promotion)) {
                orders = orders.stream()
                        .filter(order -> order.getPromotion() != null
                                || order.getOrderDetails().stream()
                                .anyMatch(detail -> detail.getDiscount() != 0.0))
                        .toList();
            }
        }

        if (StringUtils.isNotBlank(paymentType)) {
            switch (paymentType) {
                case "Tín dụng":
                    orders = orders.stream()
                            .filter(order -> order.getPaymentMethod() == PaymentMethod.BANK)
                            .toList();
                    break;
                case "Tiền mặt":
                    orders = orders.stream()
                            .filter(order -> order.getPaymentMethod() == PaymentMethod.CASH)
                            .toList();
                    break;
            }
        }
        if (StringUtils.isNotBlank(productType)) {
            switch (productType) {
                case "Thuốc":
                    orders = orders.stream()
                            .filter(order -> order.getOrderDetails().stream()
                                    .anyMatch(orderDetail -> orderDetail.getBatch().getProduct().getProductType() == ProductType.MEDICINE))
                            .toList();

                    break;
                case "Vật tư y tế":
                    orders = orders.stream()
                            .filter(order -> order.getOrderDetails().stream()
                                    .anyMatch(orderDetail -> orderDetail.getBatch().getProduct().getProductType() == ProductType.MEDICALSUPPLIES))
                            .toList();
                    break;
            }
        }
        return orders;
    }
    @Override
    public List<StatsOrderByDayDTO> searchStats(LocalDateTime start, LocalDateTime end, String productType,
                                                String paymentType, String promotion) throws RemoteException {
        List<StatsOrderByDayDTO> statsList = new ArrayList<>();
        Map<LocalDate, Double> resultMap = new HashMap<>();
        Map<LocalDate, Integer> quantityMap = new HashMap<>();

        List<Order> orders = orderDAL.statisByDate(start, end);
        orders = dataFilter(orders, productType, paymentType, promotion);
        for (Order order : orders) {
            LocalDate orderDate = order.getOrderDate().toLocalDate();
            Double totalPrice = order.getTotalPrice();
            if (resultMap.containsKey(orderDate)) {
                resultMap.put(orderDate, resultMap.get(orderDate) + totalPrice);
            } else {
                resultMap.put(orderDate, totalPrice);
            }
            if (quantityMap.containsKey(orderDate)) {
                quantityMap.put(orderDate, quantityMap.get(orderDate) + 1);
            } else {
                quantityMap.put(orderDate, 1);
            }
        }

        for (LocalDate date = start.toLocalDate(); !date.isAfter(end.toLocalDate()); date = date.plusDays(1)) {
            Double sumPrice = resultMap.getOrDefault(date, 0.0);
            Integer totalQuantity = quantityMap.getOrDefault(date, 0);
            StatsOrderByDayDTO statsOrderDTO = new StatsOrderByDayDTO(date.atStartOfDay(), sumPrice, totalQuantity);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }
    @Override
    public List<StatsOrderByYearDTO> searchStatsByYear(LocalDateTime start, LocalDateTime end,
                                                       String productType, String paymentType, String promotion) throws RemoteException {

        List<StatsOrderByYearDTO> statsList = new ArrayList<>();
        Map<Integer, Double> resultMap = new HashMap<>();
        Map<Integer, Integer> quantityMap = new HashMap<>();

        List<Order> orders = orderDAL.statisByDate(start, end);
        orders = dataFilter(orders, productType, paymentType, promotion);
        for (Order order : orders) {
            Integer year = order.getOrderDate().toLocalDate().getYear();
            Double totalPrice = order.getTotalPrice();

            if (resultMap.containsKey(year)) {
                resultMap.put(year, resultMap.get(year) + totalPrice);
            } else {
                resultMap.put(year, totalPrice);
            }
            if (quantityMap.containsKey(year)) {
                quantityMap.put(year, quantityMap.get(year) + 1);
            } else {
                quantityMap.put(year, 1);
            }
        }

        for (int year = start.getYear(); year <= end.getYear(); year++) {
            Double sumPrice = resultMap.getOrDefault(year, 0.0);
            Integer totalQuantity = quantityMap.getOrDefault(year, 0);
            StatsOrderByYearDTO statsOrderDTO = new StatsOrderByYearDTO(year, sumPrice, totalQuantity);
            statsList.add(statsOrderDTO);
        }

        return statsList;
    }
    @Override
    public List<StatsProductDTO> getStatisticProductByDateAndType(LocalDateTime start, LocalDateTime end, String productType)  throws RemoteException{
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        List<Order> orderList = orderDAL.statisByDate(start, end);
        List<StatsProductDTO> ans = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderDetail> lOd = order.getOrderDetails();
            for (OrderDetail odt : lOd) {
                Optional<StatsProductDTO> existingStats = ans.stream()
                        .filter(dto -> dto.getProductName().equals(odt.getBatch().getProduct().getName()))
                        .findFirst();

                if (existingStats.isPresent()) {
                    StatsProductDTO statsProductDTO = existingStats.get();
                    statsProductDTO.setSumPrice(statsProductDTO.getSumPrice() + odt.getLineTotal());
                    statsProductDTO.setQuantity(statsProductDTO.getQuantity() + odt.getQuantity());

                } else {
                    StatsProductDTO statsProductDTO = new StatsProductDTO();
                    statsProductDTO.setProductName(odt.getBatch().getProduct().getName());
                    statsProductDTO.setTime(order.getOrderDate());
                    statsProductDTO.setSumPrice(odt.getLineTotal());
                    statsProductDTO.setProductType(odt.getBatch().getProduct().getProductType());

                    statsProductDTO.setQuantity(odt.getQuantity());

                    ans.add(statsProductDTO);
                }
            }
        }

        if (productType.equals("Tất cả")) {
            return ans;
        }

        ans = ans.stream().filter(x -> x.getProductType().getDescription().equals(productType))
                .toList();

        return ans;
    }
    @Override
    public List<StatsProductDTO> getStatisticProductByDate(LocalDateTime start, LocalDateTime end)  throws RemoteException{
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        List<Order> orderList = orderDAL.statisByDate(start, end);
        List<StatsProductDTO> ans = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderDetail> lOd = order.getOrderDetails();
            for (OrderDetail odt : lOd) {
                Optional<StatsProductDTO> existingStats = ans.stream()
                        .filter(dto -> dto.getProductName().equals(odt.getBatch().getProduct().getName()))
                        .findFirst();

                if (existingStats.isPresent()) {
                    StatsProductDTO statsProductDTO = existingStats.get();
                    statsProductDTO.setSumPrice(statsProductDTO.getSumPrice() + odt.getLineTotal());
                    statsProductDTO.setQuantity(statsProductDTO.getQuantity() + odt.getQuantity());

                } else {
                    StatsProductDTO statsProductDTO = new StatsProductDTO();
                    statsProductDTO.setProductName(odt.getBatch().getProduct().getName());
                    statsProductDTO.setTime(order.getOrderDate());
                    statsProductDTO.setSumPrice(odt.getLineTotal());
                    statsProductDTO.setProductType(odt.getBatch().getProduct().getProductType());
                    statsProductDTO.setQuantity(odt.getQuantity());

                    ans.add(statsProductDTO);
                }
            }
        }
        return ans;
    }
    @Override
    public List<Order> getOrderByDateAndEmp(LocalDateTime start, LocalDateTime end, String employeeID)  throws RemoteException{
        return orderDAL.findOrderByDateAndEmp(start, end, employeeID);
    }

}