package bus.impl;

import bus.PurchaseOrderDetailBUS;
import connectDB.ConnectDB;
import dal.PurchaseOrderDetailDAL;
import dal.impl.PurchaseOrderDetailDALImpl;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class PurchaseOrderDetailBUSImpl extends UnicastRemoteObject implements PurchaseOrderDetailBUS {

    private final PurchaseOrderDetailDAL purchaseOrderDetailDAL;

    public PurchaseOrderDetailBUSImpl(EntityManager entityManager) throws RemoteException {
        this.purchaseOrderDetailDAL = new PurchaseOrderDetailDALImpl(entityManager);
    }

    @Override
    public List<PurchaseOrderDetail> getListPurchaseOrderDetailsByPurchaseOrder(PurchaseOrder purchaseOrder) throws RemoteException {
        return purchaseOrderDetailDAL.getListPurchaseOrderDetailByPurchaseOrder(purchaseOrder);
    }
}
