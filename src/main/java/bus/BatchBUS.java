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
import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Hoang
 */
public class BatchBUS {

    private BatchDAL batchDAL;

    public BatchBUS(EntityManager entityManager) {
        this.batchDAL = new BatchDAL(entityManager);
    }

    public List<Batch> getListBatchEnable(Product product) {
        return batchDAL.findByProduct(product)
                .stream()
                .filter(batch -> (batch.getExpirationDate().isAfter(LocalDate.now())
                    && batch.getStock() > 0 && batch.isStatus() == true))
                .sorted(Comparator.comparing(Batch::getExpirationDate))
                .toList();
    }

    public int getFinalStockByProduct(String productId) {
        return batchDAL.getFinalStockByProduct(productId);
    }

    public Batch getBatchByNameAndProduct(String batchName, String productId) {
        return batchDAL.findByNameAndProduct(batchName, productId);
    }

    public List<Batch> getAllBatch() {
        return batchDAL.findAll1();
    }

    public List<Batch> getListBatchByProduct(String productId) {
        return batchDAL.findByProductId(productId);
    }

    public Batch getBatchByName(String batchName) {
        return batchDAL.findByName(batchName);
    }

}
