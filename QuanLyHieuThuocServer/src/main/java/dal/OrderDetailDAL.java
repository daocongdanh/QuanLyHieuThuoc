package dal;

import entity.Order;
import entity.OrderDetail;
import java.util.List;

public interface OrderDetailDAL {
    boolean insert(OrderDetail orderDetail);
    List<OrderDetail> getListOrderDetailByOrder(Order order);
}
