/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import connectDB.ConnectDB;
import dal.OrderDetailDAL;
import entity.Batch;
import entity.Order;
import entity.OrderDetail;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class OrderDetailBUS {
    
    private OrderDetailDAL orderDetailDAL;
    
    public OrderDetailBUS(EntityManager entityManager) {
        this.orderDetailDAL = new OrderDetailDAL(ConnectDB.getEntityManager());
    }
    
    public List<OrderDetail> getListOrderDetailByOrder( Order order){
        return orderDetailDAL.getListOrderDetailByOrder(order);
    }

}
