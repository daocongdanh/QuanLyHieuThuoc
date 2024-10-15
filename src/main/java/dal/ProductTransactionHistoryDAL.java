/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.persistence.EntityManager;
import entity.ProductTransactionHistory;
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
}
