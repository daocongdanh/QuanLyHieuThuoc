/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.SupplierDAL;
import entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class SupplierBUS {
    private SupplierDAL supplierDAL;
    private EntityTransaction transaction;
    
    public SupplierBUS(EntityManager entityManager){
        this.supplierDAL = new SupplierDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public boolean createrSupplier(Supplier supplier){
        try{
            transaction.begin();
            supplierDAL.insert(supplier);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public boolean updateSupplier(Supplier supplier){
        try{
            transaction.begin();
            supplierDAL.update(supplier);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    
    public Supplier getSupplierById(String id){
        return supplierDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhà cung cấp với id = " + id));
    }
    
    public List<Supplier> getAllSuppliers(){
        return supplierDAL.findAll();
    }
}
