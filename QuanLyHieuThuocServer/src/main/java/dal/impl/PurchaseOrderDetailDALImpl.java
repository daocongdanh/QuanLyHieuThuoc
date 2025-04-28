package dal.impl;

import dal.PurchaseOrderDetailDAL;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Lớp triển khai của PurchaseOrderDetailDAL.
 */
public class PurchaseOrderDetailDALImpl implements PurchaseOrderDetailDAL {

    private EntityManager entityManager;

    public PurchaseOrderDetailDALImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(PurchaseOrderDetail purchaseOrderDetail) {
        entityManager.persist(purchaseOrderDetail);
        return true;
    }

    @Override
    public List<PurchaseOrderDetail> getListPurchaseOrderDetailByPurchaseOrder(PurchaseOrder purchaseOrder) {
        TypedQuery<PurchaseOrderDetail> query = entityManager.createQuery(
                "select pod from PurchaseOrderDetail pod where pod.purchaseOrder = ?1", 
                PurchaseOrderDetail.class
        );
        query.setParameter(1, purchaseOrder);
        return query.getResultList();
    }
}
