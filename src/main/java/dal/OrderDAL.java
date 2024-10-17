/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

/**
 *
 * @author daoducdanh
 */
public class OrderDAL implements BaseDAL<Order, String> {

    private EntityManager entityManager;

    public OrderDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Order order) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(order.getEmployee().getPhone(), Order.class);
        order.setOrderId(id);
        entityManager.persist(order);
        return true;
    }

    @Override
    public boolean update(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
    }

    public Optional<Order> findByIdAndNotInPromotion(String id) {
        TypedQuery<Order> query = entityManager.createQuery(
                "SELECT o FROM Order o WHERE o.orderId = ?1 AND o.promotion IS NULL", Order.class);
        query.setParameter(1, id);

        List<Order> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
