/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.StatsOrderByDayDTO;
import entity.Order;
import enums.PaymentMethod;
import enums.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
import util.StringUtils;

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

    public List<Order> search(LocalDateTime start, LocalDateTime end, String txtCustomer, String txtEmployee) {
        StringBuilder jpql = new StringBuilder("select o from Order o where (o.orderDate between ?1 and ?2) ");

        int paramIndex = 3;

        if (!txtCustomer.equals("")) {
            jpql.append(" and o.customer is not null and o.customer.name like ?3 ");
            paramIndex++;
        }
        if (!txtEmployee.equals("")) {
            jpql.append(" and o.employee.name like ?").append(paramIndex).append(" ");
        }
        TypedQuery<Order> query = entityManager.createQuery(jpql.toString(), Order.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        if (!txtCustomer.equals("")) {
            query.setParameter(3, '%' + txtCustomer + '%');
        }
        if (!txtEmployee.equals("")) {
            query.setParameter(paramIndex, '%' + txtEmployee + '%');
        }
        return query.getResultList();
    }

    public List<Order> searchByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "select o from Order o where (o.orderDate between ?1 and ?2) ";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        return query.getResultList();
    }
    
    public List<Order> statisByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end";

        Query query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("start", start);
        query.setParameter("end", end);

        return query.getResultList();
    }


    
}
