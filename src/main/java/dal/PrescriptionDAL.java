/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Prescription;
import entity.Unit;
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
public class PrescriptionDAL implements BaseDAL<Prescription, String>{
    private EntityManager entityManager;
    
    public PrescriptionDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Prescription prescription) {
        GenerateId generateId = new GenerateId(entityManager);
        prescription.setPrescriptionId(generateId.generateOrtherId(Prescription.class, "DTM"));
        entityManager.persist(prescription);
        return true;
    }

    @Override
    public boolean update(Prescription prescription) {
        entityManager.merge(prescription);
        return true;
    }

    @Override
    public Optional<Prescription> findById(String id) {
        return Optional.ofNullable(entityManager.find(Prescription.class, id));
    }

    @Override
    public List<Prescription> findAll() {
        return entityManager.createQuery("select p from Prescription p", Prescription.class).getResultList();
    }

    public List<Prescription> findbyNameSearch(String name) {
        TypedQuery<Prescription> query = entityManager.createQuery("select pr from Prescription pr where pr.name like ?1", Prescription.class);
        query.setParameter(1, "%" + name + "%");
        return query.getResultList();
    }
    
}
