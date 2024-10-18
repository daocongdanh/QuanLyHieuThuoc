/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.DamageItemDAL;
import dal.EmployeeDAL;
import dal.ProductDAL;
import dal.UnitDAL;
import dal.UnitDetailDAL;
import dto.DamageItemDTO;
import entity.DamageItem;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class DamageItemBUS {
    private DamageItemDAL damageItemDAL;
    private EntityTransaction transaction;
    private BatchDAL batchDAL;
    private UnitDAL unitDAL;
    private UnitDetailDAL unitDetailDAL;
    private ProductDAL productDAL;
    private EmployeeDAL employeeDAL;
    
    public DamageItemBUS(EntityManager entityManager){
        this.damageItemDAL = new DamageItemDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.batchDAL = new BatchDAL(entityManager);
        this.unitDAL = new UnitDAL(entityManager);
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
    
    public List<DamageItem> getAllDamageItems(){
        return damageItemDAL.findAll();
    }
    
    public List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee){
        return damageItemDAL.search(start, end, txtEmployee);
    }
}
