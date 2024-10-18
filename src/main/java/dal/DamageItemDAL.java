/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.DamageItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
/**
 *
 * @author daoducdanh
 */
public class DamageItemDAL implements BaseDAL<DamageItem, String>{
    private EntityManager entityManager;
    
    public DamageItemDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(DamageItem damageItem) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(damageItem.getEmployee().getPhone(), DamageItem.class);
        damageItem.setDamageItemId(id);
        entityManager.persist(damageItem);
        return true;
    }

    @Override
    public boolean update(DamageItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<DamageItem> findById(String id) {
        return Optional.ofNullable(entityManager.find(DamageItem.class, id));
    }

    @Override
    public List<DamageItem> findAll() {
        return entityManager.createQuery("select d from DamageItem d", DamageItem.class).getResultList();
    }

    public List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee) {
        StringBuilder jpql = new StringBuilder("select d from DamageItem d where (d.orderDate between ?1 and ?2) ");
        
        if(!txtEmployee.equals("")){
            jpql.append(" and d.employee.name like ?3");
        }
        
        TypedQuery<DamageItem> query = entityManager.createQuery(jpql.toString(), DamageItem.class);
        
        query.setParameter(1, start);
        query.setParameter(2, end);

        if(!txtEmployee.equals("")){
            query.setParameter(3, '%' + txtEmployee + '%');
        }
        
        return query.getResultList();
    }

    
}
