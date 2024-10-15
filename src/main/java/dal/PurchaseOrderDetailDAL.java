/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import jakarta.persistence.EntityManager;
import entity.PurchaseOrderDetail;
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
    
}
