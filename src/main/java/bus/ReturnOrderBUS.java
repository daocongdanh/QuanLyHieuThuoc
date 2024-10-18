/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import connectDB.ConnectDB;
import dal.ReturnOrderDAL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import entity.*;
import java.time.LocalDate;
/**
 *
 * @author daoducdanh
 */
public class ReturnOrderBUS {
    private ReturnOrderDAL returnOrderDAL;
    private EntityTransaction transaction;
    
    public ReturnOrderBUS(EntityManager entityManager){
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();
    }
    
    public List<ReturnOrder> getAllReturnOrders(){
        return returnOrderDAL.findAll();
    }
    
    public List<ReturnOrder> search(LocalDate start, LocalDate end, String txtEmployee){
        return returnOrderDAL.search(start, end, txtEmployee);
    }
}
