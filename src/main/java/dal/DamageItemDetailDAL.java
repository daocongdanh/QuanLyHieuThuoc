/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.DamageItemDetail;
import entity.DamageItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class DamageItemDetailDAL {
    private EntityManager entityManager;
    
    public DamageItemDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(DamageItemDetail damageItemDetail) {
        entityManager.persist(damageItemDetail);
        return true;
    }

    public List<DamageItemDetail> getListDamageItemDetailByDamageItem(DamageItem damageItem) {
        TypedQuery<DamageItemDetail> query
                = entityManager.createQuery("select did from DamageItemDetail did where did.damageItem = ?1 ", DamageItemDetail.class);
        query.setParameter(1, damageItem);
        return query.getResultList();
    }
   
    
}
