package dal.impl;

import dal.ProductDAL;
import dal.PromotionDAL;
import entity.Promotion;
import entity.Product;
import enums.PromotionType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class PromotionDALImpl extends BaseDALImpl<Promotion, String> implements PromotionDAL {
    private EntityManager entityManager;
    
    public PromotionDALImpl(EntityManager entityManager) {
        super(entityManager, Promotion.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Promotion promotion) {
        GenerateId generateId = new GenerateId(entityManager);
        promotion.setPromotionId(generateId.generateOrtherId(Promotion.class, "KM"));
        entityManager.persist(promotion);
        return true;
    }

    @Override
    public List<Promotion> findAllByCurrentDate(PromotionType promotionType) {
        TypedQuery<Promotion> query = 
                entityManager.createQuery("select p from Promotion p where (current_date between "
                        + "p.startedDate and p.endedDate) and p.promotionType = ?1", Promotion.class);
        query.setParameter(1, promotionType);
        return query.getResultList();
    }

    @Override
    public List<Promotion> search(LocalDate date, String promotionType) {
        StringBuilder jpql = new StringBuilder("select p from Promotion p where (?1 between p.startedDate and p.endedDate) ");
       
        if(!promotionType.equals("Tất cả")){
            jpql.append(" and p.promotionType = ?2");
        }
        TypedQuery<Promotion> query = entityManager.createQuery(jpql.toString(), Promotion.class);
        
        query.setParameter(1, date);
        if(!promotionType.equals("Tất cả")){
            query.setParameter(2, promotionType.equals("Hóa đơn") ? PromotionType.ORDER : PromotionType.PRODUCT);
        }
        return query.getResultList();
    }
}
