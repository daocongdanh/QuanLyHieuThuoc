/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.*;
import dto.DamageItemDTO;
import dto.ReturnOrderDetailDTO;
import entity.*;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class DamageItemBUS {
    private DamageItemDAL damageItemDAL;
    private EntityTransaction transaction;
    private BatchDAL batchDAL;
    private DamageItemDetailDAL damageItemDetailDAL;
    private UnitDetailDAL unitDetailDAL;
    private ProductDAL productDAL;
    private EmployeeDAL employeeDAL;
    
    public DamageItemBUS(EntityManager entityManager){
        this.damageItemDAL = new DamageItemDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.batchDAL = new BatchDAL(entityManager);
        this.damageItemDetailDAL = new DamageItemDetailDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
        this.productDAL = new ProductDAL(entityManager);
        this.employeeDAL = new EmployeeDAL(entityManager);
    }
    
    public boolean createDamageItem(String employeeId, List<DamageItemDTO> damageItemDTOs){
        try{
            transaction.begin();
            Employee employee = employeeDAL.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
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
}
