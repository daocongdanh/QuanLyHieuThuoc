/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.DamageItemDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

   
    
}
