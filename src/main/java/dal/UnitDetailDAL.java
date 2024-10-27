/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.persistence.EntityManager;
import entity.UnitDetail;
import jakarta.persistence.TypedQuery;
import java.util.List;
import entity.Product;
/**
 *
 * @author daoducdanh
 */
public class UnitDetailDAL{
    private EntityManager entityManager;
    
    public UnitDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(UnitDetail unitDetail) {
        entityManager.persist(unitDetail);
        return true;
    }
    
    public boolean remove( UnitDetail unitDetail){
        entityManager.remove(unitDetail);
        return true;
    }
    
    public UnitDetail findByProductAndUnit(String productId, String unitId){
        TypedQuery<UnitDetail> query = 
                entityManager.createQuery("select ud from UnitDetail ud where "
                        + "ud.product.productId = ?1 and ud.unit.unitId = ?2", UnitDetail.class);
        query.setParameter(1, productId);
        query.setParameter(2, unitId);
        return query.getSingleResult();
    }
    
    public List<UnitDetail> findByProduct(Product product){
        TypedQuery<UnitDetail> query = 
                entityManager.createQuery("select ud from UnitDetail ud where "
                        + "ud.product = ?1", UnitDetail.class);
        query.setParameter(1, product);
        return query.getResultList();
    }
    
      public List<UnitDetail> findByProductId(String productId){
        TypedQuery<UnitDetail> query = 
                entityManager.createQuery("select ud from UnitDetail ud where "
                        + "ud.product.productId = ?1", UnitDetail.class);
        query.setParameter(1, productId);
        return query.getResultList();
    }
    
    public List<UnitDetail> findByUnit( String unitId){
        TypedQuery<UnitDetail> query = 
                entityManager.createQuery("select ud from UnitDetail ud where "
                        + "ud.unit.unitId = ?1", UnitDetail.class);
        query.setParameter(1, unitId);
        return query.getResultList();
    }
    
    public UnitDetail findUnitDefaultByProduct(Product product){
        TypedQuery<UnitDetail> query = 
                entityManager.createQuery("select ud from UnitDetail ud where ud.product= ?1 and ud.isDefault = true", UnitDetail.class);
        query.setParameter(1, product);
        return query.getSingleResult();
    }
}
