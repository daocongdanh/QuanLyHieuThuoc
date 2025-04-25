package bus;

import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import enums.ReturnOrderDetailStatus;

import java.rmi.Remote;
import java.util.List;
import java.rmi.RemoteException;

public interface ReturnOrderDetailBUS extends Remote {
    ReturnOrderDetail findByReturnOrderIdAndProductId(String returnOrderId, String productId) throws RemoteException;
    boolean updateReturnOrderDetailToReturn(ReturnOrderDetail returnOrderDetail) throws RemoteException;
    boolean updateReturnOrderDetailToDamage(ReturnOrderDetail returnOrderDetail) throws RemoteException;
    List<ReturnOrderDetail> getListReturnOrderDetailsByReturnOrder(ReturnOrder returnOrder) throws RemoteException;
    List<ReturnOrderDetail> getListReturnOrderDetailByType(ReturnOrderDetailStatus returnOrderDetailStatus) throws RemoteException;
}
