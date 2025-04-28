package bus.impl;

import bus.ReportBUS;
import dal.DamageItemDAL;
import dal.OrderDAL;
import dal.PurchaseOrderDAL;
import dal.ReturnOrderDAL;
import dal.impl.DamageItemDALImpl;
import dal.impl.OrderDALImpl;
import dal.impl.PurchaseOrderDALImpl;
import dal.impl.ReturnOrderDALImpl;
import jakarta.persistence.EntityManager;
import dto.Report;
import entity.DamageItem;
import entity.Order;
import entity.PurchaseOrder;
import entity.ReturnOrder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportBUSImpl extends UnicastRemoteObject implements ReportBUS {

    private final OrderDAL orderDAL;
    private final PurchaseOrderDAL purchaseOrderDAL;
    private final ReturnOrderDAL returnOrderDAL;
    private final DamageItemDAL damageItemDAL;

    public ReportBUSImpl(EntityManager entityManager) throws RemoteException {
        this.orderDAL = new OrderDALImpl(entityManager);
        this.purchaseOrderDAL = new PurchaseOrderDALImpl(entityManager);
        this.returnOrderDAL = new ReturnOrderDALImpl(entityManager);
        this.damageItemDAL = new DamageItemDALImpl(entityManager);
    }

    @Override
    public Report getAllReportByTime(LocalDateTime start, LocalDateTime end, String type) throws RemoteException {
        List<Order> orders = new ArrayList<>();
        List<ReturnOrder> returnOrders = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        List<DamageItem> damageItems = new ArrayList<>();
        Map<String, Map<Integer, Double>> orderType = new LinkedHashMap<>();
        List<Object> objects = new ArrayList<>();

        // Populate lists based on the report type
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

        // Sort the objects by order date
        objects.sort((o1, o2) -> {
            LocalDateTime date1 = null;
            try {
                date1 = getOrderDate(o1);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            LocalDateTime date2 = null;
            try {
                date2 = getOrderDate(o2);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            return date2.compareTo(date1);
        });

        // Generate the report data
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

        // Calculate the profit
        double profit = 0;
        for (Map.Entry<String, Map<Integer, Double>> entry : orderType.entrySet()) {
            double totalValue = 0;
            for (double value : entry.getValue().values()) {
                totalValue += value;
            }
            if (entry.getKey().equals("Bán hàng")) {
                profit += totalValue;
            } else if (!entry.getKey().equals("Xuất hủy")) {
                profit -= totalValue;
            }
        }

        return new Report(objects, orderType, profit);
    }

    private LocalDateTime getOrderDate(Object obj) throws RemoteException {
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
