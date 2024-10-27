/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.UnitDetailDAL;
import entity.Batch;
import jakarta.persistence.EntityManager;
import java.util.List;
import entity.Product;
import entity.UnitDetail;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Hoang
 */
public class BatchBUS {

    private BatchDAL batchDAL;
    private UnitDetailDAL unitDetailDAL;

    public BatchBUS(EntityManager entityManager) {
        this.batchDAL = new BatchDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
    }

    public List<Batch> getListBatchEnable(Product product){
        return batchDAL.findByProduct(product)
                .stream()
                .filter(batch -> batch.getExpirationDate().isAfter(LocalDate.now()))
                .toList();
    }
    
    public int getFinalStockByProduct(String productId){
        return batchDAL.getFinalStockByProduct(productId);
    }
    
    public Batch getBatchByNameAndProduct(String batchName, String productId){
        return batchDAL.findByNameAndProduct(batchName, productId);
    }
    public List<Batch> getAllBatch(){
        return batchDAL.findAll1();
    }
    
    public List<Batch> getListBatchByProduct(String productId){
        return batchDAL.findByProductId(productId);
    }
    
    public Map<UnitDetail, List<Batch>> getListBatchExpiration(){
        List<Batch> batchs = batchDAL.getAllBatchExpiration();
        Map<UnitDetail, List<Batch>> map = new LinkedHashMap<>();
        for(Batch batch : batchs){
            UnitDetail unitDetail = unitDetailDAL.findUnitDefaultByProduct(batch.getProduct());
            if(map.containsKey(unitDetail)){
                map.get(unitDetail).add(batch);
            }
            else{
                List<Batch> list = new ArrayList<>();
                list.add(batch);
                map.put(unitDetail, list);
            }
        }
        return map;
    }
    
    
    public Batch getBatchByName(String batchName) {
        return batchDAL.findByName(batchName);
    }
      
}
