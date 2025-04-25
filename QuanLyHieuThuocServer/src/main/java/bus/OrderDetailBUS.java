package bus;

import entity.Order;
import entity.OrderDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OrderDetailBUS extends Remote {

    List<OrderDetail> getListOrderDetailByOrder(Order order) throws RemoteException;
}
