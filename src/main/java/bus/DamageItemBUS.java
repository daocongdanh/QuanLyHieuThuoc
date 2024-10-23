/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.DamageItemDAL;
import dal.ProductTransactionHistoryDAL;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author daoducdanh
 */
public class DamageItemBUS {
    private DamageItemDAL damageItemDAL;
    private ProductTransactionHistoryDAL productTransactionHistoryDAL;
    private BatchDAL batchDAL;
    private EntityTransaction transaction;
    
    public DamageItemBUS(EntityManager entityManager){
        this.damageItemDAL = new DamageItemDAL(entityManager);
        this.productTransactionHistoryDAL = new ProductTransactionHistoryDAL(entityManager);
        this.batchDAL = new BatchDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public boolean createDamageItem(Employee employee, List<DamageItemDetail> damageItemDetails){
        try{
            transaction.begin();
            
            DamageItem damageItem = new DamageItem(null, LocalDate.now(), employee, damageItemDetails);
            damageItemDAL.insert(damageItem);
            
            for(DamageItemDetail damageItemDetail : damageItemDetails){
                Batch batch = damageItemDetail.getBatch();
                batch.setStatus(false);
                batchDAL.update(batch);
            }
            Map<String, List<DamageItemDetail>> map = damageItemDetails.stream()
                    .collect(Collectors.groupingBy(
                            damageItemDetail -> damageItemDetail.getUnitDetail().getProduct().getProductId(),
                            LinkedHashMap::new,
                            Collectors.toList()
                    ));
            map.forEach((key, value) -> {
                DamageItemDetail damageItemDetail = value.get(0);
                UnitDetail unitDetail = damageItemDetail.getUnitDetail();
                Product product = unitDetail.getProduct();
                int sumQuantity = value.stream()
                        .mapToInt(x -> x.getQuantity() * x.getUnitDetail().getConversionRate())
                        .sum();
                double costPrice = product.getPurchasePrice() * sumQuantity;
                double transactionPrice = value.stream()
                        .mapToDouble(DamageItemDetail::getLineTotal)
                        .sum();
                int finalStock = batchDAL.getFinalStockByProduct(product.getProductId());
                
                ProductTransactionHistory productTransactionHistory
                        = new ProductTransactionHistory(damageItem.getDamageItemId(), damageItem.getOrderDate(),
                                "Hủy hàng hóa", "",
                                transactionPrice, costPrice, -sumQuantity, finalStock, product);
                productTransactionHistoryDAL.insert(productTransactionHistory);

            });
            
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public List<DamageItem> getAllDamageItems(){
        return damageItemDAL.findAll();
    }
    
    public List<DamageItem> search(LocalDate start, LocalDate end, String txtEmployee){
        return damageItemDAL.search(start, end, txtEmployee);
    }
}
