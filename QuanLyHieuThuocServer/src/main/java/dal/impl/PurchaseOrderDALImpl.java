package dal.impl;

import dal.PurchaseOrderDAL;
import entity.PurchaseOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

/**
 * Lớp triển khai của PurchaseOrderDAL.
 */
public class PurchaseOrderDALImpl extends BaseDALImpl<PurchaseOrder, String> implements PurchaseOrderDAL {

    private EntityManager entityManager;

    public PurchaseOrderDALImpl(EntityManager entityManager) {
        super(entityManager, PurchaseOrder.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(PurchaseOrder purchaseOrder) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(purchaseOrder.getEmployee().getPhone(), PurchaseOrder.class);
        purchaseOrder.setPurchaseOrderId(id);
        entityManager.persist(purchaseOrder);
        return true;
    }

    @Override
    public List<PurchaseOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee) {
        StringBuilder jpql = new StringBuilder("select po from PurchaseOrder po where (po.orderDate between ?1 and ?2) ");
        if (!txtEmployee.equals("")) {
            jpql.append(" and po.employee.name like ?3");
        }
        TypedQuery<PurchaseOrder> query = entityManager.createQuery(jpql.toString(), PurchaseOrder.class);
        query.setParameter(1, start);
        query.setParameter(2, end);

        if (!txtEmployee.equals("")) {
            query.setParameter(3, '%' + txtEmployee + '%');
        }
        return query.getResultList();
    }

    @Override
    public List<PurchaseOrder> searchByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "select po from PurchaseOrder po where (po.orderDate between ?1 and ?2) ";
        TypedQuery<PurchaseOrder> query = entityManager.createQuery(jpql, PurchaseOrder.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        return query.getResultList();
    }

    @Override
    public List<PurchaseOrder> searchByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) {
        String jpql = "select po from PurchaseOrder po where (po.orderDate between ?1 and ?2) and po.employee.employeeId = ?3";
        TypedQuery<PurchaseOrder> query = entityManager.createQuery(jpql, PurchaseOrder.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        query.setParameter(3, empID);
        return query.getResultList();
    }
}
