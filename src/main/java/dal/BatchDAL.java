/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Batch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
import entity.Product;
/**
 *
 * @author daoducdanh
 */
public class BatchDAL implements BaseDAL<Batch, String>{
    private EntityManager entityManager;
    
    public BatchDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public boolean insert(Batch batch) {
        GenerateId generateId = new GenerateId(entityManager);
        batch.setBatchId(generateId.generateOrtherId(Batch.class, "LH"));
        entityManager.persist(batch);
        return true;
    }

    @Override
    public boolean update(Batch batch) {
        entityManager.merge(batch);
        return true;
    }

    @Override
    public Optional<Batch> findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Batch> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Batch findByNameAndProduct(String name, String productId){
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.product.productId = ?1 "
                        + "and b.name = ?2", Batch.class);
        query.setParameter(1, productId);
        query.setParameter(2, name);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public List<Batch> findByProduct (Product product){
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.product = ?1", Batch.class);
        query.setParameter(1, product);
        return query.getResultList();

    }

    public int getFinalStockByProduct(String productId){
        TypedQuery<Long> query = entityManager.createQuery("select sum(b.stock) from Batch b "
                + "where b.product.productId = ?1 and b.expirationDate > CURRENT_DATE group by b.product.productId", Long.class);
        query.setParameter(1, productId);
        return query.getSingleResult().intValue();
    }   
    
    public Batch findByName (String batchName){
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.name = ?1", Batch.class);
        query.setParameter(1, batchName);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    } 
    
}
