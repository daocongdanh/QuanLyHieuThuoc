package dal.impl;

import dal.ReturnOrderDetailDAL;
import entity.ReturnOrderDetail;
import entity.ReturnOrder;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ReturnOrderDetailDALImpl implements ReturnOrderDetailDAL {
    
    private EntityManager entityManager;

    public ReturnOrderDetailDALImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(ReturnOrderDetail returnOrderDetail) {
        entityManager.persist(returnOrderDetail);
        return true;
    }

    @Override
    public boolean update(ReturnOrderDetail returnOrderDetail) {
        entityManager.merge(returnOrderDetail);
        return true;
    }

    @Override
    public ReturnOrderDetail findByReturnOrderIdAndProductId(String returnOrderId, String productId) {
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

    @Override
    public List<ReturnOrderDetail> getListReturnOrderDetailsByReturnOrder(ReturnOrder returnOrder) {
        TypedQuery<ReturnOrderDetail> query
                = entityManager.createQuery("select rod from ReturnOrderDetail rod where rod.returnOrder = ?1 ", ReturnOrderDetail.class);
        query.setParameter(1, returnOrder);
        return query.getResultList();
    }

    @Override
    public List<ReturnOrderDetail> getALLByStatus(ReturnOrderDetailStatus returnOrderDetailStatus){
        TypedQuery<ReturnOrderDetail> query
                = entityManager.createQuery("select rod from ReturnOrderDetail rod where rod.returnOrderDetailStatus = ?1 ", ReturnOrderDetail.class);
        query.setParameter(1, returnOrderDetailStatus);
        return query.getResultList();
    }
}
