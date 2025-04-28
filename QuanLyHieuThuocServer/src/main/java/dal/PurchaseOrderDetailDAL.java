package dal;

import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import java.util.List;

public interface PurchaseOrderDetailDAL {
    boolean insert(PurchaseOrderDetail purchaseOrderDetail);
    List<PurchaseOrderDetail> getListPurchaseOrderDetailByPurchaseOrder(PurchaseOrder purchaseOrder);
}
