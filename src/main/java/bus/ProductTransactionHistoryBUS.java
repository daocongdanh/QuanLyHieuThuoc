/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.ProductTransactionHistoryDAL;
import entity.ProductTransactionHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductTransactionHistoryBUS {
     private ProductTransactionHistoryDAL transactionDAL;
    private EntityTransaction transaction;
   
    
    public ProductTransactionHistoryBUS(EntityManager entityManager){
        this.transactionDAL = new ProductTransactionHistoryDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public List<ProductTransactionHistory> getAllProductTransactionHistory(){
        return transactionDAL.findAll();
    }
    
}
