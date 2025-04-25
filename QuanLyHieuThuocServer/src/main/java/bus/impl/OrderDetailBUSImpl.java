package bus.impl;

import bus.OrderDetailBUS;
import connectDB.ConnectDB;
import dal.OrderDetailDAL;
import entity.Order;
import entity.OrderDetail;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OrderDetailBUSImpl extends UnicastRemoteObject implements OrderDetailBUS {

    private final OrderDetailDAL orderDetailDAL;

    public OrderDetailBUSImpl(EntityManager entityManager) throws RemoteException {
        this.orderDetailDAL = new OrderDetailDAL(ConnectDB.getEntityManager());
    }

    @Override
    public List<OrderDetail> getListOrderDetailByOrder(Order order) throws RemoteException {
        return orderDetailDAL.getListOrderDetailByOrder(order);
    }
}
