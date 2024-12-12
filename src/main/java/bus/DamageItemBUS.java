/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.DamageItemDAL;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
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
    private BatchDAL batchDAL;
    private EntityTransaction transaction;
    
    public DamageItemBUS(EntityManager entityManager){
        this.damageItemDAL = new DamageItemDAL(entityManager);
        this.batchDAL = new BatchDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public boolean createDamageItem(Employee employee, List<DamageItemDetail> damageItemDetails){
        try{
            transaction.begin();

            DamageItem damageItem = new DamageItem(null, LocalDateTime.now(), employee, damageItemDetails);
            damageItemDAL.insert(damageItem);

            for(DamageItemDetail damageItemDetail : damageItemDetails){
                Batch batch = damageItemDetail.getBatch();
                batch.setStatus(false);
                batchDAL.update(batch);
            }
            
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
//
//    public boolean createDamageItemReturn(Employee employee, Customer customer
//            , DamageItemDTO damageItemDTO) {
//        try {
//            transaction.begin();
//            if (employee == null) {
//                throw new RuntimeException("Nhân viên không được rỗng");
//            }
//            Product product = productDAL.findById(damageItemDTO.getProductId())
//                    .orElseThrow(() -> new RuntimeException("San pham xuat huy bi rong"));
//
//            Batch batch = batchDAL.findByNameAndProduct(damageItemDTO.getBatchName(), product.getProductId());
//            UnitDetail unitDetail = unitDetailDAL.findByProductAndUnit(damageItemDTO.getProductId(), damageItemDTO.getBatchName());
//            DamageItemDetail damageItemDetail = new DamageItemDetail();
//            damageItemDetail.setBatch(batch);
//            damageItemDetail.setUnitDetail(unitDetail);
//            damageItemDetail.setPrice(damageItemDTO.getQuantity() * product.getPrice());
//            damageItemDetail.setQuantity(damageItemDTO.getQuantity());
//            damageItemDetail.setLineTotal();
//            damageItemDetailDAL.insert(damageItemDetail);
//
//            DamageItem damageItem = new DamageItem( null, LocalDateTime.now(), employee , List.of(damageItemDetail));
//            damageItemDAL.insert(damageItem);
//
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            transaction.rollback();
//            return false;
//        }
//    }
    
    public List<DamageItem> getAllDamageItems(){
        return damageItemDAL.findAll();
    }
    
    public List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee){
        return damageItemDAL.search(start, end, txtEmployee);
    }
    
    public List<DamageItem> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID){
        return damageItemDAL.findByDateAndEmp(start, end, empID);
    }
    
    public DamageItem getByID(String Id) {
        return damageItemDAL.findById(Id).orElseThrow(() -> new RuntimeException("Không tồn tại phiếu xuất hủy này"));
    }
}
