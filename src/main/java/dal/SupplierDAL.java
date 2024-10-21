/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

/**
 *
 * @author daoducdanh
 */
public class SupplierDAL implements BaseDAL<Supplier, String>{
    private EntityManager entityManager;

    public SupplierDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public boolean insert(Supplier supplier) {
        GenerateId generateId = new GenerateId(entityManager);
        supplier.setSupplierId(generateId.generateOrtherId(Supplier.class, "NCC"));
        entityManager.persist(supplier);
        return true;
    }

    @Override
    public boolean update(Supplier supplier) {
        entityManager.merge(supplier);
        return true;
    }


    @Override
    public Optional<Supplier> findById(String id) {
        return Optional.ofNullable(entityManager.find(Supplier.class, id));
    }

    @Override
    public List<Supplier> findAll() {
        return entityManager.createQuery("select s from Supplier s", Supplier.class).getResultList();
    }
    
    public Supplier findByName(String name) {
        TypedQuery<Supplier> query = entityManager.createQuery("select s from Supplier s where s.name = ?1", Supplier.class);
        query.setParameter(1, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Supplier> searchSuppliersByText(String text) {
        TypedQuery<Supplier> query = entityManager.createQuery(
            "select s from Supplier s where lower(s.name) like lower(concat('%', ?1, '%')) or " +
            "lower(s.email) like lower(concat('%', ?1, '%')) or " +
            "s.phone like concat('%', ?1, '%')", Supplier.class);
        query.setParameter(1, text);
        return query.getResultList();
    }
    
    
}
