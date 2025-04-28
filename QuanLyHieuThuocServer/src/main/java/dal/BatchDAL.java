package dal;

import entity.Account;
import entity.Batch;
import entity.Product;

import java.util.List;
import java.util.Optional;

public interface BatchDAL extends BaseDAL<Batch, String>{
    Batch findByNameAndProduct(String name, String productId);
    List<Batch> findByProduct(Product product);
    int getFinalStockByProduct(String productId);
    Batch findByName(String batchName);
    Batch findBatchNearExpirationHaveProductId(String productId);
    List<Batch> findAll1();
    List<Batch> getAllBatchExpiration();
    List<Batch> findByProductId(String productId);
}
