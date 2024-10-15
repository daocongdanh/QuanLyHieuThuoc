/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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

    
}
