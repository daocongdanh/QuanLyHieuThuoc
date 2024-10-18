/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import entity.Batch;
import jakarta.persistence.EntityManager;
import java.util.List;
import entity.Product;
import java.time.LocalDateTime;

/**
 *
 * @author Hoang
 */
public class BatchBUS {

    private BatchDAL batchDAL;

    public BatchBUS(EntityManager entityManager) {
        this.batchDAL = new BatchDAL(entityManager);
    }

    public List<Batch> getListBatchEnable(Product product){
        return batchDAL.findByProduct(product)
                .stream()
                .filter(batch -> batch.getExpirationDate().isAfter(LocalDateTime.now()))
                .toList();
    }
    
    public int getFinalStockByProduct(String productId){
        return batchDAL.getFinalStockByProduct(productId);
    }
    
    public Batch getBatchByNameAndProduct(String batchName, String productId){
        return batchDAL.findByNameAndProduct(batchName, productId);
    }
      
}
