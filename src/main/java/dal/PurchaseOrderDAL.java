/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.PurchaseOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
/**
 *
 * @author daoducdanh
 */
public class PurchaseOrderDAL implements BaseDAL<PurchaseOrder, String>{
    private EntityManager entityManager;
    
    public PurchaseOrderDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(PurchaseOrder purchaseOrder) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(purchaseOrder.getEmployee().getPhone(), PurchaseOrder.class);
        purchaseOrder.setPurchaseOrderId(id);
        entityManager.persist(purchaseOrder);
        return true;
    }

    @Override
    public boolean update(PurchaseOrder purchaseOrder) {
        entityManager.merge(purchaseOrder);
        return true;
    }

    @Override
    public Optional<PurchaseOrder> findById(String id) {
        return Optional.ofNullable(entityManager.find(PurchaseOrder.class, id));

    }

    @Override
    public List<PurchaseOrder> findAll() {
        return entityManager.createQuery("select p from PurchaseOrder p", PurchaseOrder.class).getResultList();
    }

    public List<PurchaseOrder> search(LocalDate start, LocalDate end, String txtEmployee) {
        StringBuilder jpql = new StringBuilder("select po from PurchaseOrder po where (po.orderDate between ?1 and ?2) ");
        
        if(!txtEmployee.equals("")){
            jpql.append(" and po.employee.name like ?3");
        }
        
        TypedQuery<PurchaseOrder> query = entityManager.createQuery(jpql.toString(), PurchaseOrder.class);
        
        query.setParameter(1, start);
        query.setParameter(2, end);

        if(!txtEmployee.equals("")){
            query.setParameter(3, '%' + txtEmployee + '%');
        }
        
        return query.getResultList();
    }


    
}
