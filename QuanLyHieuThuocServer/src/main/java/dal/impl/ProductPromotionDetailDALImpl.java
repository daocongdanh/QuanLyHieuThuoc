package dal.impl;

import dal.ProductPromotionDetailDAL;
import entity.ProductPromotionDetail;
import entity.Promotion;
import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductPromotionDetailDALImpl implements ProductPromotionDetailDAL {

    private EntityManager entityManager;

    public ProductPromotionDetailDALImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(ProductPromotionDetail productPromotionDetail) {
        entityManager.persist(productPromotionDetail);
        return true;
    }

    @Override
    public ProductPromotionDetail findByProductAndPromotion(Product product, Promotion promotion) {
        TypedQuery<ProductPromotionDetail> query = 
                entityManager.createQuery("select ppd from ProductPromotionDetail ppd "
                        + "where ppd.product = ?1 and ppd.promotion = ?2", ProductPromotionDetail.class);
        query.setParameter(1, product);
        query.setParameter(2, promotion);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ProductPromotionDetail> findAllByPromotion(Promotion promotion) {
        TypedQuery<ProductPromotionDetail> query = entityManager.createQuery("select ppd from ProductPromotionDetail ppd where "
                + "ppd.promotion = ?1", ProductPromotionDetail.class);
        query.setParameter(1, promotion);
        return query.getResultList();
    }

    @Override
    public boolean removeAll(List<ProductPromotionDetail> productPromotionDetail) {
        for (ProductPromotionDetail detail : productPromotionDetail) {
            entityManager.remove(detail);
        }
        return true;
    }

    @Override
    public boolean insertAll(List<ProductPromotionDetail> productPromotionDetail) {
        for (ProductPromotionDetail detail : productPromotionDetail) {
            entityManager.persist(detail);
        }
        return true;
    }
}
