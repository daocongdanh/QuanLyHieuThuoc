/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.DamageItemDAL;
import dal.OrderDAL;
import dal.PurchaseOrderDAL;
import dal.ReturnOrderDAL;
import jakarta.persistence.EntityManager;
import dto.Report;
import java.time.LocalDateTime;
import java.util.List;
import entity.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author daoducdanh
 */
public class ReportBUS {

    private OrderDAL orderDAL;
    private PurchaseOrderDAL purchaseOrderDAL;
    private ReturnOrderDAL returnOrderDAL;
    private DamageItemDAL damageItemDAL;

    public ReportBUS(EntityManager entityManager) {
        this.orderDAL = new OrderDAL(entityManager);
        this.purchaseOrderDAL = new PurchaseOrderDAL(entityManager);
        this.returnOrderDAL = new ReturnOrderDAL(entityManager);
        this.damageItemDAL = new DamageItemDAL(entityManager);
    }

    public Report getAllReportByTime(LocalDateTime start, LocalDateTime end, String type) {
        List<Order> orders = new ArrayList<>();
        List<ReturnOrder> returnOrders = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        List<DamageItem> damageItems = new ArrayList<>();
        Map<String, Map<Integer, Double>> orderType = new LinkedHashMap<>();
        List<Object> objects = new ArrayList<>();
        if (type.equals("Tất cả")) {
            orders = orderDAL.searchByDate(start, end);
            returnOrders = returnOrderDAL.searchByDate(start, end);
            purchaseOrders = purchaseOrderDAL.searchByDate(start, end);
            damageItems = damageItemDAL.searchByDate(start, end);
        } else if (type.equals("Bán hàng")) {
            orders = orderDAL.searchByDate(start, end);
        } else if (type.equals("Trả hàng")) {
            returnOrders = returnOrderDAL.searchByDate(start, end);
        } else if (type.equals("Nhập hàng")) {
            purchaseOrders = purchaseOrderDAL.searchByDate(start, end);
        } else if (type.equals("Xuất hủy")) {
            damageItems = damageItemDAL.searchByDate(start, end);
        }

        objects.addAll(orders);
        objects.addAll(returnOrders);
        objects.addAll(purchaseOrders);
        objects.addAll(damageItems);

        objects.sort((o1, o2) -> {
            LocalDateTime date1 = getOrderDate(o1);
            LocalDateTime date2 = getOrderDate(o2);
            return date2.compareTo(date1);
        });

        Map<Integer, Double> orderData = new HashMap<>();
        orderData.put(orders.size(), orders.stream().mapToDouble(Order::getTotalPrice).sum());
        orderType.put("Bán hàng", orderData);

        Map<Integer, Double> PurchaseOrderData = new HashMap<>();
        PurchaseOrderData.put(purchaseOrders.size(), purchaseOrders.stream().mapToDouble(PurchaseOrder::getTotalPrice).sum());
        orderType.put("Nhập hàng", PurchaseOrderData);

        Map<Integer, Double> ReturnOrderData = new HashMap<>();
        ReturnOrderData.put(returnOrders.size(), returnOrders.stream().mapToDouble(ReturnOrder::getTotalPrice).sum());
        orderType.put("Trả hàng", ReturnOrderData);

        Map<Integer, Double> DamageItemData = new HashMap<>();
        DamageItemData.put(damageItems.size(), damageItems.stream().mapToDouble(DamageItem::getTotalPrice).sum());
        orderType.put("Xuất hủy", DamageItemData);

        double profit = 0;

        for (Map.Entry<String, Map<Integer, Double>> entry : orderType.entrySet()) {
            double totalValue = 0;
            for (double value : entry.getValue().values()) {
                totalValue += value;
            }
            if (entry.getKey().equals("Bán hàng")) {
                profit += totalValue;
            } else {
                profit -= totalValue;
            }
        }

        return new Report(objects, orderType, profit);
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

}
