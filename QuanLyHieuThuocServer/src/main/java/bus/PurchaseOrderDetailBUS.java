package bus;

import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PurchaseOrderDetailBUS extends Remote {
    List<PurchaseOrderDetail> getListPurchaseOrderDetailsByPurchaseOrder(PurchaseOrder purchaseOrder) throws RemoteException;
}
