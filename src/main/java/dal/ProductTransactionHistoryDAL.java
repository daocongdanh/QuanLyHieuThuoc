/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Batch;
import jakarta.persistence.EntityManager;
import entity.ProductTransactionHistory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

import java.util.Optional;

/**
 *
 * @author daoducdanh
 */
public class ProductTransactionHistoryDAL {

    private EntityManager entityManager;

    public ProductTransactionHistoryDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insert(ProductTransactionHistory productTransactionHistory) {
        entityManager.persist(productTransactionHistory);
        return true;
    }

    public boolean update(ProductTransactionHistory productTransactionHistory) {
        entityManager.merge(productTransactionHistory);
        return true;
    }

    public Optional<ProductTransactionHistory> findByTransactionIdAndProductId(String transactionId, String productId) {
        try {
            TypedQuery<ProductTransactionHistory> query
                    = entityManager.createQuery("select pth from ProductTransactionHistory pth where pth.transactionId = ?1 "
                            + "and pth.product.productId = ?2", ProductTransactionHistory.class);
            query.setParameter(1, transactionId);
            query.setParameter(2, productId);
            return query.getResultList().isEmpty() ? Optional.empty() : Optional.of(query.getResultList().get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<ProductTransactionHistory> findAll() {
        return entityManager.createQuery("select p from ProductTransactionHistory p", ProductTransactionHistory.class).getResultList();
    }

    public List<ProductTransactionHistory> getProductTransactionHistoryByProductId(String productId) {
            TypedQuery<ProductTransactionHistory> query
                    = entityManager.createQuery("select pth from ProductTransactionHistory pth where pth.product.productId = ?1",
                    ProductTransactionHistory.class);
            query.setParameter(1, productId);
            return query.getResultList();
    }
}
