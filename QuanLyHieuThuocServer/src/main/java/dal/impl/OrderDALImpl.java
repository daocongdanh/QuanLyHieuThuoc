package dal.impl;

import dal.OrderDAL;
import entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class OrderDALImpl implements OrderDAL {
    private EntityManager entityManager;

    public OrderDALImpl(EntityManager entityManager) {
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
    public boolean update(Order order) {
        // Implement update logic if needed
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Override
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

    @Override
    public List<Order> searchByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "select o from Order o where (o.orderDate between ?1 and ?2) ";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        return query.getResultList();
    }

    @Override
    public List<Order> statisByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end";
        Query query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

    @Override
    public List<Order> findOrderByDateAndEmp(LocalDateTime start, LocalDateTime end, String employeeID) {
        String jpql = "select o from Order o where (o.orderDate between ?1 and ?2) and o.employee.employeeId = ?3";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        query.setParameter(3, employeeID);
        return query.getResultList();
    }
}
