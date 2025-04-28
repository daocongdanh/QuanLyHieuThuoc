package dal.impl;

import dal.ReturnOrderDAL;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class ReturnOrderDALImpl extends BaseDALImpl<ReturnOrder, String> implements ReturnOrderDAL {

    private EntityManager entityManager;

    public ReturnOrderDALImpl(EntityManager entityManager) {
        super(entityManager, ReturnOrder.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(ReturnOrder returnOrder) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(returnOrder.getEmployee().getPhone(), ReturnOrder.class);
        returnOrder.setReturnOrderId(id);
        entityManager.persist(returnOrder);
        return true;
    }


    @Override
    public List<ReturnOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee, String orderId, Boolean status) {
        StringBuilder jpql = new StringBuilder("select ro from ReturnOrder ro where (ro.orderDate between :start and :end)");
        if (status != null) {
            jpql.append(" AND ro.status = :status");
        }

        if (txtEmployee != null && !txtEmployee.trim().isEmpty()) {
            jpql.append(" AND ro.employee.name LIKE :txtEmployee");
        }
        if (orderId != null && !orderId.trim().isEmpty()) {
            jpql.append(" AND ro.order.orderId = :orderId");
        }
        TypedQuery<ReturnOrder> query = entityManager.createQuery(jpql.toString(), ReturnOrder.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        if (status != null) {
            query.setParameter("status", status);
        }
        if (txtEmployee != null && !txtEmployee.trim().isEmpty()) {
            query.setParameter("txtEmployee", "%" + txtEmployee.trim() + "%");
        }
        if (orderId != null && !orderId.trim().isEmpty()) {
            query.setParameter("orderId", orderId.trim());
        }
        return query.getResultList();
    }

    @Override
    public boolean checkOrderIsReturned(String orderId) {
        TypedQuery<ReturnOrder> query = entityManager.createQuery(
                "select ro from ReturnOrder ro where ro.order.orderId = ?1", ReturnOrder.class);
        query.setParameter(1, orderId);
        List<ReturnOrder> result = query.getResultList();
        return !result.isEmpty();
    }

    @Override
    public List<ReturnOrder> getListReturnOrdersByStatus(boolean status) {
        TypedQuery<ReturnOrder> query = entityManager.createQuery(
                "select ro from ReturnOrder ro where ro.status = ?1", ReturnOrder.class);
        query.setParameter(1, status);
        return query.getResultList();
    }

    @Override
    public List<ReturnOrder> searchByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "select ro from ReturnOrder ro where (ro.orderDate between ?1 and ?2) ";
        TypedQuery<ReturnOrder> query = entityManager.createQuery(jpql, ReturnOrder.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        return query.getResultList();
    }

    @Override
    public List<ReturnOrder> searchByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) {
        String jpql = "select ro from ReturnOrder ro where (ro.orderDate between ?1 and ?2) and ro.employee.employeeId = ?3";
        TypedQuery<ReturnOrder> query = entityManager.createQuery(jpql, ReturnOrder.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        query.setParameter(3, empID);
        return query.getResultList();
    }

    @Override
    public ReturnOrderDetail findByReturnOrderIdAndProductId(String returnOrderId, String productId) {
        TypedQuery<ReturnOrderDetail> query =
                entityManager.createQuery("select rod from ReturnOrderDetail rod where rod.returnOrder.returnOrderId = ?1 " +
                        " and rod.product.productId = ?2", ReturnOrderDetail.class);
        query.setParameter(1, returnOrderId);
        query.setParameter(2, productId);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
