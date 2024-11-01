/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.ProductPromotionDetail;
import entity.ReturnOrder;
import jakarta.persistence.EntityManager;
import entity.ReturnOrderDetail;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author daoducdanh
 */
public class ReturnOrderDetailDAL {
    private EntityManager entityManager;
    
    public ReturnOrderDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(ReturnOrderDetail returnOrderDetail) {
        entityManager.persist(returnOrderDetail);
        return true;
    }

    public boolean update(ReturnOrderDetail returnOrderDetail) {
        entityManager.merge(returnOrderDetail);
        return true;
    }

    public ReturnOrderDetail findByReturnOrderIdAndProductId( String returnOrderId, String productId) {
        TypedQuery<ReturnOrderDetail> query =
                entityManager.createQuery("select rod from ReturnOrderDetail rod "
                        + "where rod.returnOrder.returnOrderId = ?1 and rod.product.productId= ?2", ReturnOrderDetail.class);
        query.setParameter(1, returnOrderId);
        query.setParameter(2, productId);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
}
