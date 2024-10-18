/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Promotion;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
import entity.Product;
import enums.PromotionType;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
/**
 *
 * @author daoducdanh
 */
public class PromotionDAL implements BaseDAL<Promotion, String>{
    private EntityManager entityManager;
    
    public PromotionDAL(EntityManager entityManager) {
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
    public boolean update(Promotion promotion) {
        entityManager.merge(promotion);
        return true;
    }

    @Override
    public Optional<Promotion> findById(String id) {
        return Optional.ofNullable(entityManager.find(Promotion.class, id));
    }

    @Override
    public List<Promotion> findAll() {
        return entityManager.createQuery("select p from Promotion p", Promotion.class).getResultList();
    }

    public List<Promotion> findAllByCurrentDate(PromotionType promotionType){
        TypedQuery<Promotion> query = 
                entityManager.createQuery("select p from Promotion p where (current_date between "
                        + "p.startedDate and p.endedDate) and p.promotionType = ?1", Promotion.class);
        query.setParameter(1, promotionType);
        return query.getResultList();
    }

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
