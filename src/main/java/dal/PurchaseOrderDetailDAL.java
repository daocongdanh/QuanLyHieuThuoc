/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import jakarta.persistence.EntityManager;
import entity.PurchaseOrderDetail;
import entity.PurchaseOrder;
import jakarta.persistence.TypedQuery;
import java.util.List;
/**
 *
 * @author daoducdanh
 */
public class PurchaseOrderDetailDAL{
    private EntityManager entityManager;
    
    public PurchaseOrderDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public boolean insert(PurchaseOrderDetail purchaseOrderDetail) {
        entityManager.persist(purchaseOrderDetail);
        return true;
    }
    
    public List<PurchaseOrderDetail> getListPurchaseOrderDetailByPurchaseOrder(PurchaseOrder purchaseOrder) {
        TypedQuery<PurchaseOrderDetail> query
                = entityManager.createQuery("select pod from PurchaseOrderDetail pod where pod.purchaseOrder = ?1 ", PurchaseOrderDetail.class);
        query.setParameter(1, purchaseOrder);
        return query.getResultList();
    }
    
}
