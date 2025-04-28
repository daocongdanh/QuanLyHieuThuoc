package dal.impl;

import dal.OrderDetailDAL;
import entity.Order;
import entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrderDetailDALImpl implements OrderDetailDAL {

    private EntityManager entityManager;

    public OrderDetailDALImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(OrderDetail orderDetail) {
        entityManager.persist(orderDetail);
        return true;
    }

    @Override
    public List<OrderDetail> getListOrderDetailByOrder(Order order) {
        TypedQuery<OrderDetail> query
                = entityManager.createQuery("select od from OrderDetail od where od.order = ?1 ", OrderDetail.class);
        query.setParameter(1, order);
        return query.getResultList();
    }
}
