/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Batch;
import entity.Order;
import jakarta.persistence.EntityManager;
import entity.OrderDetail;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class OrderDetailDAL {

    private EntityManager entityManager;

    public OrderDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insert(OrderDetail orderDetail) {
        entityManager.persist(orderDetail);
        return true;
    }

    public List<OrderDetail> getListOrderDetailByOrder(Order order) {
        TypedQuery<OrderDetail> query
                = entityManager.createQuery("select od from OrderDetail od where od.order = ?1 ", OrderDetail.class);
        query.setParameter(1, order);
        return query.getResultList();
    }

//    public Long getSumOfQuantityByOrderAndBatch(Order order, Batch batch) {
//        TypedQuery<Long> query = entityManager.createQuery(
//                "select sum(od.quantity) from OrderDetail od where od.order = ?1 and od.batch = ?2",
//                Long.class);
//        query.setParameter( 1, order);
//        query.setParameter( 2, batch);
//        Long result = query.getSingleResult();
//        return result != null ? result : 0;
//    }

}
