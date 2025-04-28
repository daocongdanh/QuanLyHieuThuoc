package dal.impl;

import dal.BatchDAL;
import entity.Batch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

import util.GenerateId;
import entity.Product;

public class BatchDALImpl extends  BaseDALImpl<Batch, String> implements BatchDAL {
    private EntityManager entityManager;

    public BatchDALImpl(EntityManager entityManager) {
        super(entityManager, Batch.class);
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
    public Batch findByNameAndProduct(String name, String productId) {
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.product.productId = ?1 and b.name = ?2", Batch.class);
        query.setParameter(1, productId);
        query.setParameter(2, name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Batch> findByProduct(Product product) {
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.product = ?1", Batch.class);
        query.setParameter(1, product);
        return query.getResultList();
    }

    @Override
    public int getFinalStockByProduct(String productId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select sum(b.stock) from Batch b "
                        + "where b.product.productId = ?1 and b.expirationDate > CURRENT_DATE and b.status = true "
                        + "group by b.product.productId", Long.class);
        query.setParameter(1, productId);
        List<Long> result = query.getResultList();
        return result.isEmpty() ? 0 : result.get(0).intValue();
    }

    @Override
    public Batch findByName(String batchName) {
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.name = ?1", Batch.class);
        query.setParameter(1, batchName);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Batch findBatchNearExpirationHaveProductId(String productId) {
        TypedQuery<Batch> query = entityManager.createQuery(
                "SELECT b FROM Batch b WHERE b.product.productId = :productId "
                        + "AND b.expirationDate > CURRENT_DATE AND b.status = true "
                        + "ORDER BY b.expirationDate ASC", Batch.class);
        query.setParameter("productId", productId);
        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Batch> findAll1() {
        return entityManager.createQuery("select b from Batch b", Batch.class).getResultList();
    }

    @Override
    public List<Batch> getAllBatchExpiration() {
        TypedQuery<Batch> query = entityManager.createQuery(
                "select b from Batch b where b.expirationDate <= CURRENT_DATE and b.status = true", Batch.class);
        return query.getResultList();
    }

    @Override
    public List<Batch> findByProductId(String productId) {
        TypedQuery<Batch> query = 
                entityManager.createQuery("select b from Batch b where b.product.productId = ?1", Batch.class);
        query.setParameter(1, productId);
        return query.getResultList();
    }
}
