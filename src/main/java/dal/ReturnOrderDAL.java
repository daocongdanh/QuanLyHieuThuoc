/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.ReturnOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
/**
 *
 * @author daoducdanh
 */
public class ReturnOrderDAL implements BaseDAL<ReturnOrder, String>{
    private EntityManager entityManager;
    
    public ReturnOrderDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(ReturnOrder returnOrder) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(returnOrder.getEmployee().getPhone(), ReturnOrder.class);
        returnOrder.setReturnOrderId(id);
        entityManager.persist(returnOrder);
        return true;
    }

    @Override
    public boolean update(ReturnOrder returnOrder) {
        entityManager.merge(returnOrder);
        return true;
    }

    @Override
    public Optional<ReturnOrder> findById(String id) {
        return Optional.ofNullable(entityManager.find(ReturnOrder.class, id));
    }

    @Override
    public List<ReturnOrder> findAll() {
        return entityManager.createQuery("select ro from ReturnOrder ro", ReturnOrder.class).getResultList();
    }

    public List<ReturnOrder> search(LocalDate start, LocalDate end, String txtEmployee) {
        return null;
    }


    
}
