/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import jakarta.persistence.EntityManager;
import entity.ProductPromotionDetail;
import entity.*;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
/**
 *
 * @author daoducdanh
 */
public class ProductPromotionDetailDAL {

    private EntityManager entityManager;
    
    public ProductPromotionDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(ProductPromotionDetail productPromotionDetail) {
        entityManager.persist(productPromotionDetail);
        return true;
    }
    
    public ProductPromotionDetail findByProductAndPromotion(Product product, Promotion promotion){
        TypedQuery<ProductPromotionDetail> query = 
                entityManager.createQuery("select ppd from ProductPromotionDetail ppd "
                        + "where ppd.product = ?1 and ppd.promotion = ?2", ProductPromotionDetail.class);
        query.setParameter(1, product);
        query.setParameter(2, promotion);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public List<ProductPromotionDetail> findAllByPromotion(Promotion promotion){
        TypedQuery<ProductPromotionDetail> query = entityManager.createQuery("select ppd from ProductPromotionDetail ppd where "
                + "ppd.promotion = ?1", ProductPromotionDetail.class);
        query.setParameter(1, promotion);
        return query.getResultList();
    }
    
    public boolean removeAll(List<ProductPromotionDetail> productPromotionDetail){
        for(ProductPromotionDetail detail : productPromotionDetail){
            entityManager.remove(detail);
        }
        return true;
    }
    
    public boolean insertAll(List<ProductPromotionDetail> productPromotionDetail){
        for(ProductPromotionDetail detail : productPromotionDetail){
            entityManager.persist(detail);
        }
        return true;
    }
}
